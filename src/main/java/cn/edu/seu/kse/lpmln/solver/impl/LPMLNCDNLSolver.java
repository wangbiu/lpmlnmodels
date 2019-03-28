package cn.edu.seu.kse.lpmln.solver.impl;

import cn.edu.seu.kse.lpmln.exception.solveexception.AssignConflictException;
import cn.edu.seu.kse.lpmln.model.*;

import java.util.*;

import static cn.edu.seu.kse.lpmln.util.CommonStrings.*;
import static cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper.dependToReachable;
import static cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper.getLiteralPostiveDependency;
import static cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper.reachableToLitSets;

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
     * 剩余需要分配的原子
     */
    private Set<String> toassign = new HashSet<>();

    /**
     * 标识当前是否冲突
     */
    private boolean conflict = false;

    /**
     * \delta
     */
    private Nogood conflictNogood = null;

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
     * C_{\Pi}^{\vee}
     */
    private List<Set<String>> cPi_HCF = new ArrayList<>();


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

    /**
     * \sigma
     */
    private List<SignedLiteral> resultUnits = new LinkedList<>();

    public WeightedAnswerSet getSingleAs(LpmlnProgram program){
        init();
        while(true){
            propagation();
            if(conflict){
                if(dl==0){
                    return null;
                }
                analysisAndUndo();
            }else if(toassign.size()==0){
                u.clear();
                for (Set<String> c : cPi) {
                    cdnl(c);
                }
                if(u.size()>0){

                }else{
                    return generate();
                }
            }
            else{

            }
        }
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
                for (SignedLiteral unit: resultUnits) {
                    Boolean cur = assignment.get(unit.getLiteral());
                    if(cur==null){
                        //还没分配
                        assignment.put(unit.getLiteral(),unit.isSign());

                        try {
                            getResultUnit(unit.getLiteral(),ltnCompletion,nogoodCompletion);
                            getResultUnit(unit.getLiteral(),ltnDynamic,nogoodDynamic);
                        }catch(AssignConflictException a){
                            //nogood引发冲突
                            conflict = true;
                            return;
                        }
                    }else if(!cur.equals(unit.isSign())){
                        //分配冲突
                        conflict = true;
                        return;
                    }
                }
            }

            if(cPi.size()==0){
                return;
            }

            u.removeIf(p->assignment.containsKey(p)&& !assignment.get(p));

            if(u.size()==0){
                unfoundedSet();
            }

        }
    }

    private void unfoundedSet(){

    }

    private void getResultUnit(String key,Map<String,List<Integer>> map,List<Nogood> nogoodList) throws AssignConflictException {
        map.get(key).forEach(idx->{
            Nogood tocheck = nogoodList.get(idx);
            SignedLiteral ru = tocheck.check(assignment);
            if(ru!=null){
                resultUnits.add(new SignedLiteral(ru.getLiteral(),!ru.isSign()));
            }
        });
    }

    private void init(){
        assignment = new HashMap<>();
        toassign = new HashSet<>();
        conflict = false;
        dl = 0;
        conflictNogood = null;
        u = new HashSet<>();
        cPi = new ArrayList<>();
        rules = lpmlnProgram.getRules();

        initCPi();

        initLiterals();

        initNogood();
    }

    private void initCPi(){
        cPi.clear();
        Set<Set<String>> loops = reachableToLitSets(dependToReachable(getLiteralPostiveDependency(lpmlnProgram)));
        loops.removeIf(loop -> loop.size() < 2);
        cPi.addAll(loops);
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
        Map<String,List<String>> supported = new HashMap<>(literals.size());
        literals.forEach(lit->supported.put(lit,new ArrayList<>()));

        for(int i=0;i<rules.size();i++){
            Rule r = rules.get(i);
            List<String> head = r.getHead();
            for (String curLit : head) {
                String sup = getAtomSupport(i, curLit);
                supported.get(curLit).add(sup);

                //支持辅助谓词
                //项都成立但支持不成立
                Nogood n1 = new Nogood();
                n1.add(sup, false);
                r.getPositiveBody().forEach(pb -> n1.add(pb, false));
                r.getPositiveBody().forEach(nb -> n1.add(nb.substring(NOT.length()), true));
                r.getHead().forEach(h -> {
                    if (!h.equals(curLit)) {
                        n1.add(h, true);
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
                r.getHead().forEach(h -> {
                    Nogood n3 = new Nogood();
                    n3.add(sup, true);
                    //TODO:考虑头部not
                    n3.add(h, false);
                    nogoodCompletion.add(n3);
                });
            }
        }

        //原子支持
        supported.forEach((lit,supporter)->{
            Nogood n4 = new Nogood();
            n4.add(lit,true);
            supporter.forEach(p->n4.add(p,false));
            nogoodCompletion.add(n4);
        });

        constructMap(nogoodCompletion,ltnCompletion);
    }

    private String getAtomSupport(int i,String atom){
        return EXT+i+SUP+atom;
    }

    /**
     * 所有出参都以成员变量表示，调用前注意处理
     * input:   \delta \Pi \nabla \A
     * output:  \varepsilon \k
     */
    private void analysisAndUndo(){

    }

    private void analysis(){

    }

    private void undo(){

    }

    private void cdnl(Set<String> c){

    }

    private WeightedAnswerSet generate(){
        return null;
    }

}