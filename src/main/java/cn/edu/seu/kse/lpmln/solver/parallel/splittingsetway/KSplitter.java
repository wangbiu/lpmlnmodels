package cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway;

import cn.edu.seu.kse.lpmln.model.DecisionUnit;
import cn.edu.seu.kse.lpmln.model.HeuristicAugmentedSubset;
import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNCDNLSolver;
import cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper;
import cn.edu.seu.kse.lpmln.util.UnionFindSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

import static cn.edu.seu.kse.lpmln.util.CommonStrings.NOT;
import static cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper.reachableToLitSets;

/**
 * @author 许鸿翔
 * @date 2018/4/7
 */
public class KSplitter extends Splitter{
    private double k;
    private LpmlnProgram lpmlnprogram;
    private Map<String,Set<String>> dependency;
    private Map<String,Set<String>> reachable;
    private Set<String> programLiterals;
    private List<DecisionUnit> mdus;
    private SplittingSolver.SPLIT_TYPE policy;
    private Set<String> assertAtoms = new HashSet<>();
    private Set<String> strongNegation = new HashSet<>();
    private Comparator<DecisionUnit> comparatorLit = new Comparator<DecisionUnit>() {
        @Override
        public int compare(DecisionUnit o1, DecisionUnit o2) {
            return o2.getWl()-o1.getWl();
        }
    };
    private Comparator<DecisionUnit> comparatorRule = new Comparator<DecisionUnit>() {
        @Override
        public int compare(DecisionUnit o1, DecisionUnit o2) {
            return o2.getWr()-o1.getWr();
        }
    };
    private Logger logger = LogManager.getLogger(KSplitter.class.getName());
    private int aimBotSize = Runtime.getRuntime().availableProcessors();
    private boolean skipConstruct = false;
    private Set<Integer> in = new HashSet<>();
    private Set<Integer> out = new HashSet<>();
    private boolean splittingSet = false;

    public KSplitter(){

    }
    public KSplitter(LpmlnProgram program){this.lpmlnprogram = program;}

    public KSplitter(SplittingSolver.SPLIT_TYPE policy){
        this.policy = policy;
    }

    @Override
    public void split(LpmlnProgram program, double k) {
        this.k = k;
        this.aimBotSize = (int)k;
        if(!skipConstruct){
            this.lpmlnprogram = program;
            this.dependency = LpmlnProgramHelper.getDependency(program);

            this.mdus = generateMDUs();

            lpmlnprogram.getRules().forEach(r->{
                r.getHead().forEach(l->{ if (l.startsWith("-")) { strongNegation.add(getLiteral(l)); } });
                r.getPositiveBody().forEach(l->{ if (l.startsWith("-")) { strongNegation.add(getLiteral(l)); } });
            });
            buildRelations();
        }

        generateU();

        generateInOut();

        generateTopAndBottom();
    }

    private void generateInOut(){
        for(int i=0;i<lpmlnprogram.getRules().size();i++){
            Rule r = lpmlnprogram.getRules().get(i);
            if(inRule(r)){
                in.add(i);
            }
            if(outRule(r)){
                out.add(i);
            }
        }
    }

    private boolean outRule(Rule r){
        Set<String> headLits = new HashSet<>();
        r.getHead().forEach(l->headLits.add(getLiteral(l)));
        Set<String> bodyPHeadLits = new HashSet<>(headLits);
        r.getPositiveBody().forEach(l->bodyPHeadLits.add(getLiteral(l)));

        headLits.removeAll(U);
        if(headLits.size()==0){
            return false;
        }

        bodyPHeadLits.retainAll(U);
        if(bodyPHeadLits.size()==0){
            return false;
        }

        return true;
    }

    private boolean inRule(Rule r){
        Set<String> headLits = new HashSet<>();
        r.getHead().forEach(l->headLits.add(getLiteral(l)));
        Set<String> bodyPHeadLits = new HashSet<>(headLits);
        r.getPositiveBody().forEach(l->bodyPHeadLits.add(getLiteral(l)));

        headLits.retainAll(U);
        if(headLits.size()==0){
            return false;
        }

        bodyPHeadLits.removeAll(U);
        if(bodyPHeadLits.size()==0){
            return false;
        }

        return true;
    }

    public boolean toSplit(){
        this.dependency = LpmlnProgramHelper.getDependency(lpmlnprogram);
        this.mdus = generateMDUs();
        buildRelations();
        int layers=0;
        Set<DecisionUnit> currentLayer = new HashSet<>();
        for (DecisionUnit mdu : mdus) {
            if(mdu.getFrom().size()==0) {
                currentLayer.add(mdu);
            }
        }
        Set<DecisionUnit> nextLayer;
        while(currentLayer.size()>0){
            layers++;
            nextLayer = new HashSet<>();
            for (DecisionUnit mdu : currentLayer) {
                nextLayer.addAll(mdu.getTo());
            }
            currentLayer = nextLayer;
        }
        return layers>2;
    }

    private List<DecisionUnit> generateMDUs(){
        List<DecisionUnit> currentMdu = new ArrayList<>();
        reachable = LpmlnProgramHelper.dependToReachable(dependency);
        programLiterals = new HashSet<>(dependency.keySet());
        dependency.values().forEach(programLiterals::addAll);
        UnionFindSet<String> litDependencys = new UnionFindSet<>(programLiterals);
        reachable.forEach((from,toSet)->{
            toSet.forEach(to->{
                if(reachable.keySet().contains(to)&&reachable.get(to).contains(from)){
                    litDependencys.join(from,to);
                }
            });
        });

        reachableToLitSets(reachable).forEach(litSet->{
            currentMdu.add(new DecisionUnit(lpmlnprogram,litSet));
        });
        return currentMdu;
    }

    private void buildRelations(){
        Map<String,DecisionUnit> map = new HashMap<>();
        mdus.forEach(mdu-> {
            mdu.getLit().forEach(lit-> {
                map.put(lit,mdu);
            });
        });
        dependency.forEach((lit,depends)->{
            if(!map.containsKey(lit)){
                return;
            }
            depends.forEach(depend->{
                if(!lit.equals(depend)&&map.containsKey(depend)){
                    map.get(lit).getTo().add(map.get(depend));
                    map.get(depend).getFrom().add(map.get(lit));
                }
            });
        });
        mdus.forEach(mdu->{
            mdu.getTo().remove(mdu);
            mdu.getFrom().remove(mdu);
        });
    }

    private void generateU(){
        switch (policy){
            case SPLIT_DYNAMIC:
                generateUDynamic();
                break;
            case SPLIT_BOT:
                generateUBot();
                break;
            case SPLIT_LIT:
                generateULit();
                break;
            case SPLIT_TEST:
            default:
                generateTest();
                break;
        }
    }

    private void generateTest(){
        PriorityQueue<DecisionUnit> nextQueue = new PriorityQueue<>(comparatorLit);

        //初始化U为事实集合
        LPMLNCDNLSolver solver = new LPMLNCDNLSolver();
        solver.setLpmlnProgram(lpmlnprogram);
        solver.init();
        U = new HashSet<>();
        U.retainAll(solver.getFacts());
        Set<String> facts = solver.getLiterals();
        facts.retainAll(solver.getFacts());

        mdus.forEach(mdu->{
            if(truthMdu(mdu,U)){
                addMDUToU(mdu,nextQueue);
            }else if(mdu.getTo().size()==0){
                nextQueue.offer(mdu);
            }
        });

        int size=1;
        List<Rule> botRules;
        while(size<aimBotSize&&nextQueue.size()>0){
            DecisionUnit du = nextQueue.poll();
            Set<String> unitLiterals = du.getLit();
            Map<String,Set<String>> subGraph = getSubDependency(unitLiterals);
            LinkedList<String> remainNodes = new LinkedList<>(unitLiterals);
            Set<String> enumSet = new HashSet<>();
            Map<String,Integer> eval = new HashMap<>();
            Comparator<String> graphCompare = new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return eval.get(o1)-eval.get(o2);
                }
            };
            unitLiterals.forEach(lit->{
                if(facts.contains(lit)){
                    U.add(lit);
                    enumSet.addAll(subGraph.get(lit));
                    removeFromGraph(lit,subGraph);
                    remainNodes.remove(lit);
                }
            });
            if(remainNodes.size()>0){
                splittingSet = false;
            }
            while(!remainNodes.isEmpty()&&size<aimBotSize){
                Set<String> lastU = new HashSet<>(U);
                refreshEval(eval,subGraph,enumSet);
                remainNodes.sort(graphCompare);
                //remain->U,enumSet
                String cur = remainNodes.poll();
                U.add(cur);
                enumSet.addAll(subGraph.get(cur));
                //graph remove
                removeFromGraph(cur,subGraph);

                refreshEval(eval,subGraph,enumSet);
                remainNodes.sort(graphCompare);
                while(remainNodes.size()>0&&eval.get(remainNodes.peek())<=0){
                    //<0则肯定被enum过
                    cur = remainNodes.poll();
                    U.add(cur);
                    enumSet.remove(cur);
                    enumSet.addAll(subGraph.get(cur));
                    //graph remove
                    removeFromGraph(cur,subGraph);
                }


                if(U.size()>0.5*programLiterals.size()){
                    System.out.println("size of U too large:"+size);
                    U = lastU;
                    if(remainNodes.size()==0){
                        splittingSet = true;
                    }
                    return;
                }
                botRules = getBotRules();
                size = wasSize(botRules);
            }
            if(remainNodes.size()==0){
                splittingSet = true;
            }

            du.getFrom().forEach(father->{
                father.getTo().remove(du);
                if(father.getTo().size()==0){
                    nextQueue.offer(father);
                }
            });
        }
        while(nextQueue.size()>0){
            DecisionUnit du = nextQueue.poll();
            if(truthMdu(du,facts)){
                addMDUToU(du,nextQueue);
            }
        }
    }

    private void removeFromGraph(String cur,Map<String,Set<String>> subGraph){
        //graph remove
        for (String to : subGraph.get(cur)) {
            subGraph.get(to).remove(cur);
        }
        subGraph.remove(cur);
    }

    private void refreshEval(Map<String,Integer> eval,Map<String,Set<String>> subGraph,Set<String> enumSet){
        eval.clear();
        subGraph.forEach((k,v)->{
            Set<String> extra = new HashSet<>(v);
            extra.removeAll(enumSet);
            if(enumSet.contains(k)){
                eval.put(k,extra.size()-1);
            }else{
                eval.put(k,extra.size());
            }
        });
    }

    private Map<String,Set<String>> getSubDependency(Set<String> subset){
        Map<String,Set<String>> subGraph = new HashMap<>();
        subset.forEach(lit->subGraph.put(lit,new HashSet<>()));
        subset.forEach(from->{
            dependency.get(from).forEach(to->{
                if(subset.contains(to)&&!from.equals(to)){
                    subGraph.get(from).add(to);
                }
            });
        });
        return subGraph;
    }

    private void generateUDynamic(){
        PriorityQueue<DecisionUnit> nextQueue = new PriorityQueue<>(comparatorLit);
        mdus.forEach(mdu->{
            if(mdu.getTo().size()==0){
                nextQueue.offer(mdu);
            }
        });
        //抽取程序中的事实，事实不会增加底部回答集数量，考虑换种方式抽事实
        //TODO:涉及的事实是在若语义下的，强语义需要适配
        HeuristicAugmentedSubset heuristicAugmentedSubset = new HeuristicAugmentedSubset(lpmlnprogram);
        Set<String> truth = heuristicAugmentedSubset.getTruthRes().keySet();
        List<Rule> botRules = getBotRules();
        int size = wasSize(botRules);
        while(nextQueue.size()>0&&size<aimBotSize){
            DecisionUnit du = nextQueue.poll();
            Set<String> lastU = new HashSet<>(U);
            addMDUToU(du,nextQueue);
            if(U.size()>0.5*programLiterals.size()){
                System.out.println("size of U too large:"+size);
                U = lastU;
                return;
            }
            if(!truthMdu(du,truth)){
                botRules = getBotRules();
                size = wasSize(botRules);
            }
        }
        while(nextQueue.size()>0){
            DecisionUnit du = nextQueue.poll();
            if(truthMdu(du,truth)){
                addMDUToU(du,nextQueue);
            }
        }
        if(size>320){
            System.out.println("size of bot too large:"+size);
            U.clear();
        }
    }

    private boolean truthMdu(DecisionUnit mdu,Set<String> truthLits){
        for (String lit : mdu.getLit()) {
            if(!truthLits.contains(lit)){
                return false;
            }
        }
        return true;
    }

    private void addMDUToU(DecisionUnit mdu,PriorityQueue<DecisionUnit> nextQueue){
        U.addAll(mdu.getLit());
        mdu.getFrom().forEach(father->{
            father.getTo().remove(mdu);
            if(father.getTo().size()==0){
                nextQueue.offer(father);
            }
        });
    }

    private List<Rule> getBotRules(){
        List<Rule> bottomRules = lpmlnprogram.getRules().stream().filter(this::isBotRule).collect(Collectors.toList());
        expandBotRules(bottomRules);
        List<Rule> ecu = getECU(bottomRules);
        bottomRules.addAll(ecu);

        for(int i=0;i<bottomRules.size();i++){
            bottomRules.get(i).setId(i);
        }
        return bottomRules;
    }

    private int wasSize(List<Rule> bottomRules){
        LpmlnProgram bottom = new LpmlnProgram(bottomRules, lpmlnprogram.getFactor(), lpmlnprogram.getHerbrandUniverse(), "",new HashSet<>());
        LPMLNBaseSolver baseSolver = new LPMLNBaseSolver();
        baseSolver.setCalculatePossibility(false);
        baseSolver.setFiltResult(false);
        baseSolver.solveProgram(bottom);
        return baseSolver.getAllWeightedAs().size();
    }

    private void generateULit(){
        U = new HashSet<>();
        int currentLits=0;
        PriorityQueue<DecisionUnit> nextQueue = new PriorityQueue<>(comparatorLit);
        mdus.forEach(mdu->{
            if(mdu.getTo().size()==0){
                nextQueue.offer(mdu);
            }
        });
        while(nextQueue.size()>0){
            DecisionUnit next = nextQueue.poll();
            if(next.getWl()+currentLits<k*programLiterals.size()){
                U.addAll(next.getLit());
                currentLits += next.getLit().size();
                next.getFrom().forEach(father->{
                    father.getTo().remove(next);
                    if(father.getTo().size()==0){
                        nextQueue.offer(father);
                    }
                });
            }
        }
        logger.debug("splitting set: lit weight:{}",currentLits);
    }

    private void generateUBot(){
        U = new HashSet<>();
        int currentRules=0;
        PriorityQueue<DecisionUnit> nextQueue = new PriorityQueue<>(comparatorLit);
        mdus.forEach(mdu->{
            if(mdu.getTo().size()==0){
                nextQueue.offer(mdu);
            }
        });
        while(nextQueue.size()>0){
            DecisionUnit next = nextQueue.poll();
            if(next.getWr()+currentRules<k* lpmlnprogram.getRules().size()){
                U.addAll(next.getLit());
                currentRules += next.getWr();
                next.getFrom().forEach(father->{
                    father.getTo().remove(next);
                    if(father.getTo().size()==0){
                        nextQueue.offer(father);
                    }
                });
            }
        }
        logger.debug("splitting set: bot weight:{}",currentRules);
    }

    private void generateTopAndBottom(){
        List<Rule> bottomRules = new ArrayList<>();
        List<Rule> topRules = new ArrayList<>();
        for(int i=0;i<lpmlnprogram.getRules().size();i++){
            Rule rule = lpmlnprogram.getRules().get(i);
            if(isBotRule(rule)){
                bottomRules.add(rule);
            }else if(!out.contains(i)){
                topRules.add(rule);
            }
        }
        expandBotRules(bottomRules);
        List<Rule> ecu = getECU(bottomRules);
        bottomRules.addAll(ecu);

        for(int i=0;i<bottomRules.size();i++){
            bottomRules.get(i).setId(i);
        }

        bottom = new LpmlnProgram(bottomRules, lpmlnprogram.getFactor(), lpmlnprogram.getHerbrandUniverse(), "",lpmlnprogram.getSolversUsed());
        top = new LpmlnProgram(topRules, lpmlnprogram.getFactor(), lpmlnprogram.getHerbrandUniverse(), lpmlnprogram.getMetarule(),lpmlnprogram.getSolversUsed());
    }

    private void expandBotRules(List<Rule> bottomRules){
        Set<String> literals = new HashSet<>();
        bottomRules.forEach(r->{
            r.getHead().forEach(l->literals.add(getLiteral(l)));
            r.getPositiveBody().forEach(l->literals.add(getLiteral(l)));
            r.getNegativeBody().forEach(l->literals.add(getLiteral(l)));
        });
        lpmlnprogram.getRules().forEach(rule->{
            if(rule.getHead().size()==0){
                for (String bodyLit : rule.getNegativeBody()){
                    String lit = getLiteral(bodyLit);
                    if(programLiterals.contains(lit)&&!literals.contains(lit)){
                        return;
                    }
                }
                for (String bodyLit : rule.getPositiveBody()){
                    String lit = getLiteral(bodyLit);
                    if(programLiterals.contains(lit)&&!literals.contains(lit)){
                        return;
                    }
                }
                bottomRules.add(rule);
            }
        });
    }

    private List<Rule> getECU(List<Rule> bottomRules){
        List<Rule> ecu = new ArrayList<>();
        assertAtoms = new HashSet<>();
        bottomRules.forEach(r->{
            r.getHead().forEach(l->{
                assertAtoms.add(getLiteral(l));
            });
            r.getPositiveBody().forEach(l->{
                assertAtoms.add(getLiteral(l));
            });
            r.getNegativeBody().forEach(l->{
                assertAtoms.add(getLiteral(l));
            });
        });
        assertAtoms.removeAll(U);
        assertAtoms.forEach(l->{
            Rule r = new Rule();
            r.setSoft(false);
            r.getHead().add(l);
            r.getHead().add(NOT+l);
            r.setOriginalrule(l+" | "+NOT+l+".");
            ecu.add(r);
            if(strongNegation.contains(l)){
                r = new Rule();
                r.setSoft(false);
                r.getHead().add("-"+l);
                r.getHead().add(NOT+"-"+l);
                r.setOriginalrule("-"+l+" | "+NOT+"-"+l+".");
                ecu.add(r);
            }
        });
        return ecu;
    }

    public boolean isBotRule(Rule rule){
        boolean bot = false;
        for (String headLit : rule.getHead()) {
            if(U.contains(getLiteral(headLit))){
                bot = true;
                break;
            }
        }
        return bot;
    }

    private String getLiteral(String lit){
        return LpmlnProgramHelper.getLiteral(lit);
    }

    public int getAimBotSize() {
        return aimBotSize;
    }

    public void setAimBotSize(int aimBotSize) {
        this.aimBotSize = aimBotSize;
    }

    public boolean isSkipConstruct() {
        return skipConstruct;
    }

    public void setSkipConstruct(boolean skipConstruct) {
        this.skipConstruct = skipConstruct;
    }

    @Override
    public Set<String> getAssertAtoms() {
        return assertAtoms;
    }

    @Override
    public Set<Integer> getIn() {
        return in;
    }

    @Override
    public Set<Integer> getOut() {
        return out;
    }

    public boolean isSplittingSet() {
        return splittingSet;
    }
}
