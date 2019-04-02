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
    private Map<String,Boolean> assignment = new HashMap<>();

    /**
     * unassigned
     */
    private LinkedList<String> toAssign = new LinkedList<>();

    /**
     * 标识当前是否冲突
     */
    private boolean conflict = false;

    /**
     * \delta
     */
    private Nogood conflictNogood = null;

    //private String conflictSigma = null;

    /**
     * decision level
     */
    private int dl = 0;

    /**
     * unfounded set
     */
    private Set<String> u = new HashSet<>();

    /**
     * C_{\Pi}
     */
    private List<Set<String>> cPi = new ArrayList<>();

    /**
     * accessor
     */
    private Map<String,Set<String>> cPiAccessor = new HashMap<>();

    /**
     * sourcePtr pointer，
     * no key：X
     * key null: o
     * key number: B,idx
     */
    private Map<String,Integer> sourcePtr = new HashMap<>();


    /**
     * \Delta_{\Pi}
     * 规则满足，包括体部成立辅助谓词
     * +
     * \Theta_{\overrightarrow{\Pi}}
     * 原子支持，包括支持辅助谓词
     */
    private List<Nogood> nogoodCompletion = new ArrayList<>();

    /**
     * \Theta_{\overrightarrow{\Pi}}
     * 动态nogood
     */
    private List<Nogood> nogoodDynamic = new ArrayList<>();


    /**
     * 文字到原子支持的下标
     */
    private Map<String,List<Integer>> ltnCompletion = new HashMap<>();

    /**
     * 文字到动态nogood的下标
     */
    private Map<String,List<Integer>> ltnDynamic = new HashMap<>();

    private List<Rule> rules;

    private Set<String> literals = new HashSet<>();

    private Map<String,List<String>> supporters = new HashMap<>();

    private Map<String,Set<Integer>> supportRule = new HashMap<>();

    private Map<String,Integer> dlMap = new HashMap<>();

    private LinkedList<String> assignStack = new LinkedList<>();

    private Map<String,Integer> stackPosition = new HashMap<>();

    private Set<String> toFlip = new HashSet<>();

    private Set<String> metaFilt;

    /**
     * \sigma
     */
    private LinkedList<SignedLiteral> resultUnits = new LinkedList<>();

    @Override
    public void executeSolving(){
        init();
        generateFact();
        while(true){
            propagation();
            if(conflict){
                if(dl==0){
                    return;
                }
                analysisAndUndo();
            }else if(toAssign.size()==0){
                weightedAs.add(generate());
                if(!backTrackAndFlip()){
                    return;
                }
            }
            else{
                decide();
            }
        }
    }

    private void generateFact(){
        nogoodCompletion.forEach(nogood -> {
            SignedLiteral fact = nogood.getResultUnit(assignment);
            if(fact!=null) {
                if (!fact.getLiteral().equals(EXT_FALSE)) {
                    resultUnits.add(new SignedLiteral(fact.getLiteral(), !fact.isSign()));
                }
            }
        });
    }

    private void decide(){
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
     * debug用
     * @return assignment中的非辅助部分
     */
    private List<SignedLiteral> curAssign(){
        List<SignedLiteral> res = new ArrayList<>();
        assignment.forEach((k,v)->{
            if(!k.startsWith(EXT)){
                res.add(new SignedLiteral(k,v));
            }
        });
        return res;
    }

    /**
     * 1.传播原子，传播到不动点
     * 2.扩展nogood
     * 所有出参都以成员变量表示，调用前注意处理
     * input:   \Pi \nabla \A
     * output:  A \nabla
     */
    private void propagation(){
        while(true){
            while(resultUnits.size()>0){
                SignedLiteral unit = resultUnits.poll();
                Boolean cur = assignment.get(unit.getLiteral());
                if(cur==null){
                    assign(unit.getLiteral(),unit.isSign());
                    //还没分配
                    getResultUnit(unit.getLiteral(),ltnCompletion,nogoodCompletion);
                    if(conflict){
                        resultUnits.clear();
                        return;
                    }
                    getResultUnit(unit.getLiteral(),ltnDynamic,nogoodDynamic);
                    if(conflict){
                        resultUnits.clear();
                        return;
                    }
                }else if(!cur.equals(unit.isSign())){
                    //分配冲突
                    conflict = true;
                    resultUnits.clear();
                    return;
                }
            }

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

            u.forEach(lit->{
                resultUnits.add(new SignedLiteral(lit,false));
            });
        }
    }

    /**
     * 分配使用此函数，处理首尾
     * @param lit 文字
     * @param val TF
     */
    private void assign(String lit,boolean val){
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
    private void resign(String lit){
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
    private void unfoundedSet(){
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
                            if(cPiAccessor.get(a).contains(pb)&&s.contains(pb)){
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

    private void putInDynamic(Nogood n){
        for (String k : n.getKeySet()) {
            List<Integer> nogoodIdx;
            if(ltnDynamic.containsKey(k)){
                nogoodIdx = ltnDynamic.get(k);
            }else{
                nogoodIdx = new ArrayList<>();
                ltnDynamic.put(k,nogoodIdx);
            }
            nogoodIdx.add(nogoodDynamic.size());
        }
        nogoodDynamic.add(n);
    }

    private boolean inAF(String a){
        return assignment.containsKey(a)&&!assignment.get(a);
    }

    private Set<String> getS(){
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

    private Integer source(String a){
        return sourcePtr.get(a);
    }

    /**
     * 计算ru
     * @param key 查找索引，文字
     * @param map 从哪些nogood里找
     * @param nogoodList map对应的nogoodlist
     */
    private void getResultUnit(String key,Map<String,List<Integer>> map,List<Nogood> nogoodList){
        if(!map.containsKey(key)){
            return;
        }
        for (Integer idx : map.get(key)) {
            Nogood tocheck = nogoodList.get(idx);
            SignedLiteral ru = tocheck.getResultUnit(assignment);
            if(ru!=null){
                if(ru.getLiteral().equals(EXT_FALSE)){
                    conflictNogood = tocheck;
                    conflict = true;
                    return;
                }
                resultUnits.add(new SignedLiteral(ru.getLiteral(),!ru.isSign()));
            }
        }
    }

    private static String SHOW = "#show ";
    private void generateMeta(){
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

    private void init(){
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
    }

    private void initCPi(){
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

    private void establishSourcePointer(){
        sourcePtr.clear();
        literals.forEach(lit->{
            if(cPiAccessor.get(lit).size()>1){
                //o
                sourcePtr.put(lit,null);
            }
        });
    }

    private void initLiterals(){
        rules.forEach(r->{
            literals.addAll(r.getHead());
            literals.addAll(r.getPositiveBody());
            r.getNegativeBody().forEach(nb-> literals.add(nb.substring(NOT.length())));
        });
    }

    private void initNogood(){
        //initRuleSatisfy();
        initComp();
    }

    private void constructMap(List<Nogood> nogoods,Map<String,List<Integer>> map){
        for(int i=0;i<nogoods.size();i++){
            Nogood nogood = nogoods.get(i);
            for (String k : nogood.getKeySet()) {
                List<Integer> idxs;
                if(map.containsKey(k)){
                    idxs = map.get(k);
                }else{
                    idxs = new ArrayList<>();
                    map.put(k,idxs);
                }
                idxs.add(i);
            }
        }
    }

    private void initComp(){
        nogoodCompletion.clear();
        ltnCompletion.clear();
        literals.forEach(lit->{
            supporters.put(lit,new ArrayList<>());
            supportRule.put(lit,new HashSet<>());
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
                        n1.add(h, true);
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
                    n2.add(nb, true);
                    nogoodCompletion.add(n2);
                });

                //规则满足
                if(LPMLNApp.semantics.equals(SEMANTICS_WEAK)&&!r.isSoft()){
                    r.getHead().forEach(h -> {
                        Nogood n3 = new Nogood();
                        n3.add(sup, true);
                        //TODO:考虑头部not
                        n3.add(h, false);
                        nogoodCompletion.add(n3);
                    });
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
                n2.add(nb, true);
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

    private String getAtomSupport(int i,String atom){
        return EXT+i+'_'+SUP+atom;
    }

    private String getVB(int i){
        return EXT+VB+i;
    }

    /**
     * 所有出参都以成员变量表示，调用前注意处理
     * input:   \delta \Pi \nabla \A
     * output:  \varepsilon \k
     */
    private void analysisAndUndo(){
        analysis();
        //dl提前赋值
        while(dlLevel(assignStack.peek())>dl){
            resign(assignStack.poll());
        }
    }

    /**
     * 回溯
     * @return T:continue F:end loop
     */
    private boolean backTrackAndFlip(){
        while (assignStack.size()>0&&!toFlip.contains(assignStack.peek())){
            resign(assignStack.poll());
        }
        if (assignStack.size()>0){
            String flip = assignStack.poll();
            resultUnits.add(new SignedLiteral(flip,true));
            resign(flip);
            toFlip.remove(flip);
            return true;
        }
        return false;
    }

    /**
     * conflict analysis
     */
    private void analysis(){
        PriorityQueue<SignedLiteral> delta = new PriorityQueue<>((o1, o2) -> stackPosition.get(o2.getLiteral())-stackPosition.get(o1.getLiteral()));
        delta.addAll(conflictNogood.getSignedLiterals());
        int k;
        while(true){
            //TODO:这边方式可能需要修改下
            SignedLiteral sigma = delta.poll();
            SignedLiteral remainMax = delta.peek();
            k = dlLevel(remainMax);
            if(dlLevel(sigma) == k){
                resign(sigma.getLiteral());
                delta.addAll(findSourceNogoodItems(sigma));
            }else{
                //analysis return
                break;
            }
        }
        dl = k;
        Nogood toAdd = new Nogood();
        delta.forEach(sl->toAdd.add(sl.getLiteral(),sl.isSign()));
        nogoodDynamic.add(toAdd);
    }

    /**
     * 查找文字的来源nogood，去除文字本身后返回剩余文字列表
     * @param ru 文字
     * @return 剩余列表
     */
    private List<SignedLiteral> findSourceNogoodItems(SignedLiteral ru){
        List<SignedLiteral> result = new ArrayList<>();
        SignedLiteral temp;
        for (Integer idx : ltnCompletion.get(ru.getLiteral())) {
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
        assert false;
        return result;
    }

    private int dlLevel(String sigma){
        return dlMap.get(sigma);
    }

    private int dlLevel(SignedLiteral sigma){
        return dlMap.get(sigma.getLiteral());
    }

//    private void cdnl(Set<String> c){
//
//    }

    private WeightedAnswerSet generate(){
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

    private boolean isViolated(Rule r){
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
            if(assignment.get(nb)){
                return false;
            }
        }
        return true;
    }

}