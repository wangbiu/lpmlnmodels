package cn.edu.seu.kse.lpmln.solver.impl;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;
import cn.edu.seu.kse.lpmln.model.*;

import java.util.*;

import static cn.edu.seu.kse.lpmln.app.LPMLNApp.SEMANTICS_WEAK;
import static cn.edu.seu.kse.lpmln.util.CommonStrings.*;
import static cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper.*;

/**
 * Conflict-Driven Disjunctive Answer Set Solving
 * CDNL:conflict driven nogood learning
 * @author 许鸿翔
 * @date 2019/3/27
 */
public class LPMLNCDNLSolver extends LPMLNBaseSolver{
    /**
     * T:in
     * F:not in
     * no key:unassigned
     */
    protected Map<String,Boolean> assignment = new HashMap<>();

    /**
     * unassigned
     */
    protected LinkedList<String> toAssign = new LinkedList<>();

    /**
     * 标识当前是否冲突
     */
    protected boolean conflict = false;

    /**
     * \delta
     */
    protected Nogood conflictNogood = null;

    //protected String conflictSigma = null;

    /**
     * decision level
     */
    protected int dl = 0;

    /**
     * unfounded set
     */
    protected Set<String> u = new HashSet<>();

    /**
     * C_{\Pi}
     */
    protected List<Set<String>> cPi = new ArrayList<>();

    protected Set<Map<String,Boolean>> dynamicNogoodSet = new HashSet<>();

    /**
     * accessor
     */
    protected Map<String,Set<String>> cPiAccessor = new HashMap<>();

    /**
     * sourcePtr pointer，
     * no key：X
     * key null: o
     * key number: B,idx
     */
    protected Map<String,Integer> sourcePtr = new HashMap<>();


    /**
     * \Delta_{\Pi}
     * 规则满足，包括体部成立辅助谓词
     * +
     * \Theta_{\overrightarrow{\Pi}}
     * 原子支持，包括支持辅助谓词
     */
    protected List<Nogood> nogoodCompletion = new ArrayList<>();

    /**
     * \Theta_{\overrightarrow{\Pi}}
     * 动态nogood
     */
    protected List<Nogood> nogoodDynamic = new ArrayList<>();


    /**
     * 文字到原子支持的下标
     */
    protected Map<String,Set<Integer>> ltnCompletion = new HashMap<>();

    /**
     * 文字到动态nogood的下标
     */
    protected Map<String,Set<Integer>> ltnDynamicWatch = new HashMap<>();

    protected List<Rule> rules;

    protected Set<String> literals = new HashSet<>();

    protected Map<String,List<String>> supporters = new HashMap<>();

    protected Map<String,Set<Integer>> supportRule = new HashMap<>();

    protected Map<String,Integer> dlMap = new HashMap<>();

    protected LinkedList<String> assignStack = new LinkedList<>();

    protected Map<String,Integer> stackPosition = new HashMap<>();

    protected Set<String> toFlip = new HashSet<>();

    protected Set<String> metaFilt;

    /**
     * \sigma
     */
    protected LinkedList<SignedLiteral> resultUnits = new LinkedList<>();

    private Set<String> facts = new HashSet<>();


    protected long T1 = 0;
    protected long T3 = 0;
    protected long T5 = 0;
    protected long T7 = 0;
    @Override
    public void executeSolving(){
        init();
        doSolving();
    }

    public void doSolving(){
        while(true){
            long t1 = System.currentTimeMillis();
            propagation();
            long t2 = System.currentTimeMillis();
            T1 += t2-t1;
//            System.out.println(t2-t1+" ms");
            if(conflict){
                if(dl==0){
                    return;
                }
                long t3 = System.currentTimeMillis();
                if(!analysisAndUndo()){
                    return;
                }

                long t4 = System.currentTimeMillis();
                T3 += t4-t3;
                conflict = false;
            }else if(toAssign.size()==0){
                long t5 = System.currentTimeMillis();
                weightedAs.add(generate());
                if(!backTrackAndFlip()){
                    return;
                }
                long t6 = System.currentTimeMillis();
                T5 += t6-t5;
            }
            else{
                long t7 = System.currentTimeMillis();
                decide();
                long t8 = System.currentTimeMillis();
                T7 += t8-t7;
            }
        }
    }

    protected void generateFact(){
        nogoodCompletion.forEach(nogood -> {
            SignedLiteral fact = nogood.getResultUnit(assignment);
            if(fact!=null) {
                if (!fact.getLiteral().equals(EXT_FALSE)) {
                    resultUnits.add(new SignedLiteral(fact.getLiteral(), !fact.isSign()));
                }
            }
        });
    }

    protected void decide(){
        String next = toAssign.peek();
        if(toFlip.contains(next)){
            resultUnits.add(new SignedLiteral(next,true));
        }else{
            toFlip.add(next);
            resultUnits.add(new SignedLiteral(next,false));
        }
        dl++;
    }


    /**
     * @return assignment中的非辅助部分
     */
    public List<SignedLiteral> curAssign(){
        List<SignedLiteral> res = new ArrayList<>();
        assignment.forEach((k,v)->{
            if(!k.startsWith(EXT)){
                res.add(new SignedLiteral(k,v));
            }
        });
        return res;
    }

    protected List<SignedLiteral> unknownAssign(){
        List<SignedLiteral> cur = curAssign();
        cur.removeIf(a->facts.contains(a.getLiteral()));
        return cur;
    }

    /**
     * 1.传播原子，传播到不动点
     * 2.扩展nogood
     * 所有出参都以成员变量表示，调用前注意处理
     * input:   \Pi \nabla \A
     * output:  A \nabla
     */
    protected long T9 = 0;
    protected long T11 = 0;
    protected long T13 = 0;
    protected long T15 = 0;
    protected void propagation(){
        while(true){
            long t9 = System.currentTimeMillis();
            while(resultUnits.size()>0){
                SignedLiteral unit = resultUnits.peek();
                Boolean cur = assignment.get(unit.getLiteral());
                if(cur==null){
                    assign(unit.getLiteral(),unit.isSign());
                    //还没分配
                    long t13 = System.currentTimeMillis();
                    getResultUnit(unit.getLiteral(),ltnCompletion,nogoodCompletion);
                    if(conflict){
                        long t14 = System.currentTimeMillis();
                        T13 += t14-t13;
                        return;
                    }
                    long t14 = System.currentTimeMillis();
                    T13 += t14-t13;

                    long t15 = System.currentTimeMillis();
                    getResultUnit(unit.getLiteral(), ltnDynamicWatch,nogoodDynamic);
                    if(conflict){
                        long t16 = System.currentTimeMillis();
                        T15 += t16-t15;
                        return;
                    }
                    long t16 = System.currentTimeMillis();
                    T15 += t16-t15;
                }else if(!cur.equals(unit.isSign())){
                    //分配冲突
                    conflict = true;
                    return;
                }
                resultUnits.poll();
            }
            long t10 = System.currentTimeMillis();
            T9 += t10-t9;
            long t11 = System.currentTimeMillis();

            if(cPi.size()==0){
                return;
            }

            u.removeIf(p->assignment.containsKey(p)&& !assignment.get(p));

            if(u.size()==0){
                unfoundedSet();
            }

            if(u.size()==0){
                return;
            }

            u.clear();
            long t12 = System.currentTimeMillis();
            T11 += t12-t11;
        }
    }

    /**
     * 分配使用此函数，处理首尾
     * @param lit 文字
     * @param val TF
     */
    protected void assign(String lit,boolean val){
        assignment.put(lit,val);
        dlMap.put(lit,dl);
        stackPosition.put(lit,assignStack.size());
        assignStack.push(lit);
        toAssign.remove(lit);
    }

    /**
     * 移除使用此函数，处理首尾
     * @param lit 文字
     */
    protected void resign(String lit){
        assignment.remove(lit);
        if(!lit.startsWith(EXT)){
            toAssign.push(lit);
        }
        dlMap.remove(lit);
        stackPosition.remove(lit);
        if(cPiAccessor.containsKey(lit)&&cPiAccessor.get(lit).size()>1){
            sourcePtr.put(lit,null);
        }
    }


    /**
     * 计算unfoundedSet
     *
     * sourcePtr pointer:
     * no key：X
     * key null: o
     * key number: B,idx
     */
    protected void unfoundedSet(){
        //这里u应该是空集
        assert u.size()==0;
        LinkedList<String> s = new LinkedList<>(getS());

        while(s.size()>0){
            String a = s.poll();
            u.add(a);
            while(u.size()>0){
                boolean existSupporter = false;
                Set<Integer> ebp = new HashSet<>();
                for (String lit : u) {
                    supportRule.get(lit).forEach(idx->{
                        for (String pb : rules.get(idx).getPositiveBody()) {
                            if(u.contains(pb)){
                                return;
                            }
                        }
                        ebp.add(idx);
                    });
                }
                for (Integer idx : ebp) {
                    String vb = getVB(idx);
                    if(!assignment.containsKey(vb)||assignment.get(vb)){
                        existSupporter = true;
                        break;
                    }
                }
                if(!existSupporter){
                    u.forEach(lit->{
                        resultUnits.add(new SignedLiteral(lit,false));
                    });
                    for (String tempA : u) {
                        Nogood dyn = new Nogood();
                        ebp.forEach(idx->{
                            dyn.add(getVB(idx),false);
                        });
                        dyn.add(tempA,true);
                        putInDynamic(dyn);
                    }
                    return;
                }

                //B:idx
                for (Integer idx : ebp) {
                    String vb = getVB(idx);
                    if(!inAF(vb)){
                        List<String> coExist = new ArrayList<>();
                        rules.get(idx).getPositiveBody().forEach(pb->{
                            if(cPiAccessor.containsKey(a)&&cPiAccessor.get(a).contains(pb)&&s.contains(pb)){
                                coExist.add(pb);
                            }
                        });

                        if(coExist.size()==0){
                            for (String c : u) {
                                sourcePtr.put(c,idx);
                            }
                            s.removeAll(u);
                            u.clear();
                            break;
                        }else{
                            u.addAll(coExist);
                            break;
                        }
                    }
                }
            }
        }
        //reestablish
        establishSourcePointer();
    }

    protected void putInDynamic(Nogood n){
        if(dynamicNogoodSet.contains(n.getCore())){
            return;
        }
        dynamicNogoodSet.add(n.getCore());

        n.findWatchers(assignment);
        if(n.getW1()==null||n.getW2()==null){
            //watcher 不完整
            resultUnits.forEach(ru->assignStack.push(ru.getLiteral()));
            for (String str : assignStack) {
                if(n.getKeySet().contains(str)){
                    if(n.getW1()==null){
                        n.setW1(str);
                    }else{
                        if(!str.equals(n.getW1())){
                            n.setW2(str);
                        }
                    }
                }
                if(n.getW1()!=null&&n.getW2()!=null){
                    break;
                }
            }
            resultUnits.forEach(ru->assignStack.pop());
        }

        addIntoMap(ltnDynamicWatch,n.getW1(),nogoodDynamic.size());
        addIntoMap(ltnDynamicWatch,n.getW2(),nogoodDynamic.size());
        nogoodDynamic.add(n);
    }

    protected boolean inAF(String a){
        return assignment.containsKey(a)&&!assignment.get(a);
    }

    protected Set<String> getS(){
        Set<String> s = new HashSet<>(literals.size());
        literals.forEach(a->{
            if(inAF(a)){
                return;
            }
            Integer idx = source(a);
            if(idx==null||inAF(getVB(idx))){
                s.add(a);
            }
        });

        Set<String> t = new HashSet<>();
        Set<Set<String>> sccVisited = new HashSet<>();
        for (String inS : s) {
            Set<String> scc = cPiAccessor.get(inS);
            if(sccVisited.contains(scc)){
                continue;
            }
            sccVisited.add(scc);
            if(scc==null){
                continue;
            }
            scc.forEach(a->{
                if(!inAF(a)&&!s.contains(a)){
                    Integer idx = source(a);
                    if(idx!=null){
                        for (String pb : rules.get(idx).getPositiveBody()) {
                            if(s.contains(pb)||t.contains(pb)){
                                t.add(a);
                                return;
                            }
                        }
                    }
                }
            });
        }
        s.addAll(t);
        return s;
    }

    protected Integer source(String a){
        return sourcePtr.get(a);
    }

    /**
     * 计算ru
     * @param key 查找索引，文字
     * @param map 从哪些nogood里找
     * @param nogoodList map对应的nogoodlist
     */
    protected void getResultUnit(String key,Map<String,Set<Integer>> map,List<Nogood> nogoodList){
        if(!map.containsKey(key)){
            return;
        }
        for (Integer idx : new ArrayList<>(map.get(key))) {
            Nogood tocheck = nogoodList.get(idx);
            SignedLiteral ru = tocheck.getResultUnit(assignment);
            if(tocheck.isWatcherChanged()){
                tocheck.getWatcherTobeRemoved().forEach(rmv->{
                    if(!map.containsKey(rmv)){
                        System.out.println(123);
                        return;
                    }
                    map.get(rmv).remove(idx);
                });
                addIntoMap(map,tocheck.getW2(),idx);
                addIntoMap(map,tocheck.getW1(),idx);
            }
            if(ru!=null){
                if(ru.getLiteral().equals(EXT_FALSE)){
                    conflictNogood = tocheck;
                    conflict = true;
                    return;
                }
                if(assignment.containsKey(ru.getLiteral())&&
                        assignment.get(ru.getLiteral())==!ru.isSign()){
                    continue;
                }
                resultUnits.add(new SignedLiteral(ru.getLiteral(),!ru.isSign()));
            }
        }
    }

    protected boolean assertTrue(){
        for (Nogood n : nogoodDynamic) {
            if(!ltnDynamicWatch.containsKey(n.getW1())){
                return false;
            }
            if(!ltnDynamicWatch.containsKey(n.getW2())){
                return false;
            }
        }
        return true;
    }

    protected static String SHOW = "#show ";
    protected void generateMeta(){
        Set<String> allowedPreds = new HashSet<>();
        if(lpmlnProgram.getMetarule().length()>0){
            metaFilt = new HashSet<>();
            String[] metas = lpmlnProgram.getMetarule().split("\n");
            for (String meta : metas) {
                int idx1 = meta.indexOf(SHOW);
                int idx2 = meta.indexOf("/");
                allowedPreds.add(meta.substring(idx1+SHOW.length(),idx2));
            }
        }

        literals.forEach(lit->{
            String predicate = null;
            if(lit.indexOf("(")>0){
                predicate = lit.substring(0,lit.indexOf("("));
            }else{
                predicate = lit;
            }
            if(allowedPreds.contains(predicate)){
                metaFilt.add(lit);
            }
        });
    }

    public void init(){
        conflict = false;
        dl = 0;
        conflictNogood = null;
        rules = lpmlnProgram.getRules();
        weightedAs = new ArrayList<>();

        initCPi();

        initLiterals();

        toAssign.addAll(literals);

        initNogood();

        generateMeta();

        generateFact();

        propagation();

        facts.addAll(assignment.keySet());
    }

    protected void initCPi(){
        cPi.clear();
        Map<String,Set<String>> reachable = dependToReachable(getLiteralPostiveDependency(lpmlnProgram));
        Set<Set<String>> loops = ufsToLitSets(reachable,reachableToUfs(reachable));
        loops.removeIf(loop -> loop.size() < 2);
        cPi.addAll(loops);
        loops.forEach(loop->{
            loop.forEach(lit->{
                cPiAccessor.put(lit,loop);
            });
        });
        establishSourcePointer();
    }

    protected void establishSourcePointer(){
        sourcePtr.clear();
        literals.forEach(lit->{
            if(cPiAccessor.containsKey(lit)&&cPiAccessor.get(lit).size()>1){
                //o
                sourcePtr.put(lit,null);
            }
        });
    }

    protected void initLiterals(){
        rules.forEach(r->{
            literals.addAll(r.getHead());
            literals.addAll(r.getPositiveBody());
            r.getNegativeBody().forEach(nb-> literals.add(nb.substring(NOT.length())));
        });
    }

    protected void initNogood(){
        //initRuleSatisfy();
        initComp();
    }

    protected void constructMap(List<Nogood> nogoods,Map<String,Set<Integer>> map){
        for(int i=0;i<nogoods.size();i++){
            Nogood nogood = nogoods.get(i);
            nogood.findWatchers(assignment);
            addIntoMap(map,nogood.getW1(),i);
            addIntoMap(map,nogood.getW2(),i);
        }
    }

    protected void addIntoMap(Map<String,Set<Integer>> map,String k,int v){
        Set<Integer> idxs;
        if(map.containsKey(k)){
            idxs = map.get(k);
        }else{
            idxs = new HashSet<>();
            map.put(k,idxs);
        }
        idxs.add(v);
    }

    protected void initComp(){
        nogoodCompletion.clear();
        ltnCompletion.clear();
        literals.forEach(lit->{
            supporters.put(lit,new ArrayList<>());
            supportRule.put(lit,new HashSet<>());
        });

        //解释一致性产出的nogood
        literals.forEach(literal->{
            if(literal.startsWith(NEGATION) &&
                    literals.contains(literal.substring(NEGATION.length()))){
                Nogood toadd = new Nogood(literal.substring(NEGATION.length()));
                nogoodCompletion.add(toadd);
            }
        });

        for(int i=0;i<rules.size();i++){
            Rule r = rules.get(i);
            List<String> head = r.getHead();
            for (String curLit : head) {
                String sup = getAtomSupport(i, curLit);
                supporters.get(curLit).add(sup);
                supportRule.get(curLit).add(i);

                //支持辅助谓词
                //项都成立但支持不成立
                Nogood n1 = new Nogood();
                n1.add(sup, false);
                r.getPositiveBody().forEach(pb -> n1.add(pb, true));
                r.getNegativeBody().forEach(nb -> n1.add(nb.substring(NOT.length()), false));
                r.getHead().forEach(h -> {
                    if (!h.equals(curLit)) {
                        n1.add(h, false);
                        //夹杂一下
                        Nogood n2 = new Nogood();
                        n2.add(sup, true);
                        n2.add(h, true);
                        nogoodCompletion.add(n2);
                    }
                });
                nogoodCompletion.add(n1);
                //支持成立但是有项不成立
                r.getPositiveBody().forEach(pb -> {
                    Nogood n2 = new Nogood();
                    n2.add(sup, true);
                    n2.add(pb, false);
                    nogoodCompletion.add(n2);
                });
                r.getNegativeBody().forEach(nb -> {
                    Nogood n2 = new Nogood();
                    n2.add(sup, true);
                    n2.add(nb.substring(NOT.length()), true);
                    nogoodCompletion.add(n2);
                });

                //规则满足
                if(LPMLNApp.semantics.equals(SEMANTICS_WEAK)&&!r.isSoft()){
                    Nogood n3 = new Nogood();
                    n3.add(sup, true);
                    //TODO:考虑头部not
                    n3.add(curLit, false);
                    nogoodCompletion.add(n3);
                }
            }

            //体部辅助谓词
            //项都成立但体部不成立
            String vb = getVB(i);
            Nogood n1 = new Nogood();
            n1.add(vb, false);
            r.getPositiveBody().forEach(pb -> n1.add(pb, true));
            r.getNegativeBody().forEach(nb -> n1.add(nb.substring(NOT.length()), false));
            nogoodCompletion.add(n1);
            //体部成立但是有项不成立
            r.getPositiveBody().forEach(pb -> {
                Nogood n2 = new Nogood();
                n2.add(vb, true);
                n2.add(pb, false);
                nogoodCompletion.add(n2);
            });
            r.getNegativeBody().forEach(nb -> {
                Nogood n2 = new Nogood();
                n2.add(vb, true);
                n2.add(nb.substring(NOT.length()), true);
                nogoodCompletion.add(n2);
            });

            //约束满足
            if(LPMLNApp.semantics.equals(SEMANTICS_WEAK)&&!r.isSoft()){
                if(r.getHead().size()==0){
                    Nogood n3 = new Nogood();
                    r.getPositiveBody().forEach(pb -> n3.add(pb, true));
                    r.getNegativeBody().forEach(nb -> n3.add(nb.substring(NOT.length()), false));
                    nogoodCompletion.add(n3);
                }
            }
        }

        //原子支持
        supporters.forEach((lit,supporter)->{
            Nogood n4 = new Nogood();
            n4.add(lit,true);
            supporter.forEach(p->n4.add(p,false));
            nogoodCompletion.add(n4);
        });

        constructMap(nogoodCompletion,ltnCompletion);
    }

    protected String getAtomSupport(int i,String atom){
        return EXT+i+'_'+SUP+atom;
    }

    protected String getVB(int i){
        return EXT+VB+i;
    }

    /**
     * 所有出参都以成员变量表示，调用前注意处理
     * input:   \delta \Pi \nabla \A
     * output:  \varepsilon \k
     */
    protected boolean analysisAndUndo(){
        analysis();
        //dl提前赋值
        return backTrackAndFlip();
    }

    /**
     * 回溯
     * @return T:continue F:end loop
     */
    protected boolean backTrackAndFlip(){
        while (assignStack.size()>0&&!toFlip.contains(assignStack.peek())){
            resign(assignStack.pop());
        }
        if (assignStack.size()>0){
            String flip = assignStack.poll();
            resultUnits.add(new SignedLiteral(flip,true));
            dl = dlMap.get(flip);
            resign(flip);
            toFlip.remove(flip);
            return true;
        }
        return false;
    }

    protected void backTrackToSigma(String literal){
        while (assignStack.size()>0&&!assignStack.peek().equals(literal)){
            resign(assignStack.pop());
        }
    }

    /**
     * conflict analysis
     */
    protected int counter=0;
    protected Comparator<SignedLiteral> comparator = new Comparator<SignedLiteral>() {
        @Override
        public int compare(SignedLiteral o1, SignedLiteral o2) {
            if(o1==null||o2==null||stackPosition.get(o1.getLiteral())==null||stackPosition.get(o2.getLiteral())==null){
                return 0;
            }
            return stackPosition.get(o2.getLiteral())-stackPosition.get(o1.getLiteral());
        }
    };
    protected void analysis(){
        LinkedList<SignedLiteral> delta = new LinkedList<>();
        counter++;
        if(counter==2){
            System.out.println();
        }
        findConflictNogood();
        if(conflictNogood==null){
            System.out.println();
        }
        delta.addAll(conflictNogood.getSignedLiterals());
        int k = dl;
        boolean nogoodChanged = false;
        while(delta.size()>1){
            //TODO:这边方式可能需要修改下
            delta.sort(comparator);
            SignedLiteral sigma = delta.poll();
            SignedLiteral remainMax = delta.peek();
            if(sigma.equals(remainMax)){
                delta.poll();
                remainMax = delta.peek();
            }
            k = dlLevel(remainMax);
            if(dlLevel(sigma) == k){
                backTrackToSigma(sigma.getLiteral());
//                resign(sigma.getLiteral());
                List<SignedLiteral> temp = findSourceNogoodItems(new SignedLiteral(sigma.getLiteral(),!sigma.isSign()));
                delta.addAll(temp);
                nogoodChanged = true;
            }else{
                //analysis return
                delta.add(sigma);
                break;
            }
        }
        dl = k;
        if (nogoodChanged){
            Nogood toAdd = new Nogood();
            delta.forEach(sl->toAdd.add(sl.getLiteral(),sl.isSign()));
            putInDynamic(toAdd);
        }
        resultUnits.clear();
        conflictNogood = null;
    }


    protected void findConflictNogood(){
        SignedLiteral ru = resultUnits.peek();
        if(conflictNogood==null){
            getResultUnit(ru.getLiteral(),ltnCompletion,nogoodCompletion);
        }
        if(conflictNogood==null){
            getResultUnit(ru.getLiteral(), ltnDynamicWatch,nogoodDynamic);
        }
//        if(conflictNogood==null){
//
//        }
    }

    /**
     * 查找文字的来源nogood，去除文字本身后返回剩余文字列表
     * @param ru 文字
     * @return 剩余列表
     */
    protected List<SignedLiteral> findSourceNogoodItems(SignedLiteral ru){
        List<SignedLiteral> result = new ArrayList<>();
        SignedLiteral temp;
        for (Integer idx : ltnCompletion.getOrDefault(ru.getLiteral(),new HashSet<>())) {
            Nogood cur = nogoodCompletion.get(idx);
            temp = cur.getResultUnit(assignment);
            if(ru.equals(temp)){
                cur.getSignedLiterals().forEach(s->{
                    if(!s.getLiteral().equals(ru.getLiteral())){
                        result.add(s);
                    }
                });
                return result;
            }
        }
        for (Integer idx : ltnDynamicWatch.getOrDefault(ru.getLiteral(),new HashSet<>())) {
            Nogood cur = nogoodDynamic.get(idx);
            temp = cur.getResultUnit(assignment);
            if(ru.equals(temp)){
                cur.getSignedLiterals().forEach(s->{
                    if(!s.getLiteral().equals(ru.getLiteral())){
                        result.add(s);
                    }
                });
                return result;
            }
        }
        assert false;
        return result;
    }

    protected int dlLevel(String sigma){
        return dlMap.get(sigma);
    }

    protected int dlLevel(SignedLiteral sigma){
        if(sigma==null||!dlMap.containsKey(sigma.getLiteral())){
            return 0;
        }
        return dlMap.get(sigma.getLiteral());
    }

//    protected void cdnl(Set<String> c){
//
//    }

    protected WeightedAnswerSet generate(){
        WeightedAnswerSet was = new WeightedAnswerSet();
        AnswerSet as = new AnswerSet();
        was.setAnswerSet(as);
        Integer[] ini = {0,0};
        was.setWeights(Arrays.asList(ini));
        rules.forEach(r->{
            if(isViolated(r)){
                //TODO:小数处理
                was.getWeights().set(0,was.getWeights().get(0)+(int)r.getWeight());
            }
        });
        assignment.forEach((k,v)->{
            if(v&&!k.startsWith(EXT)){
                if(metaFilt==null||metaFilt.contains(k)){
                    as.add(k);
                }
            }
        });
        return was;
    }

    protected boolean isViolated(Rule r){
        for (String h : r.getHead()) {
            if(assignment.get(h)){
                return false;
            }
        }
        for (String pb : r.getPositiveBody()) {
            if(!assignment.get(pb)){
                return false;
            }
        }
        for (String nb : r.getNegativeBody()) {
            if(assignment.get(nb.substring(NOT.length()))){
                return false;
            }
        }
        return true;
    }

    public Set<String> getFacts() {
        return facts;
    }
}