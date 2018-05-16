package cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway;

import cn.edu.seu.kse.lpmln.model.DecisionUnit;
import cn.edu.seu.kse.lpmln.model.HeuristicAugmentedSubset;
import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper;
import cn.edu.seu.kse.lpmln.util.UnionFindSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

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
    private SplittingSolver.SPLIT_TYPE policy = SplittingSolver.SPLIT_TYPE.SPLIT_LIT;
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

    public KSplitter(){

    }

    public KSplitter(SplittingSolver.SPLIT_TYPE policy){
        this.policy = policy;
    }

    @Override
    public void split(LpmlnProgram program, double k) {
        this.k = k;
        this.lpmlnprogram = program;
        this.dependency = LpmlnProgramHelper.getDependency(program);

        this.mdus = generateMDUs();
        buildRelations();

        generateU();

        generateTopAndBottom();
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
            default:
                generateULit();
                break;
        }

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
            addMDUToU(du,nextQueue);
            if(U.size()>0.7*programLiterals.size()){
                System.out.println("size of U too large:"+size);
                U.clear();
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
        return lpmlnprogram.getRules().stream().filter(this::isBotRule).collect(Collectors.toList());
    }

    private int wasSize(List<Rule> bottomRules){
        LpmlnProgram bottom = new LpmlnProgram(bottomRules, lpmlnprogram.getFactor(), lpmlnprogram.getHerbrandUniverse(), "");
        LPMLNBaseSolver baseSolver = new LPMLNBaseSolver();
        baseSolver.setCalculatePossibility(false);
        baseSolver.setFiltResult(false);
        baseSolver.solveProgram(bottom);
        return baseSolver.getAllWeightedAs().size();
    }

    private void generateULit(){
        U = new HashSet<>();
        int currentLits=0;
        int limit=10;
        PriorityQueue<DecisionUnit> nextQueue = new PriorityQueue<>(comparatorLit);
        mdus.forEach(mdu->{
            if(mdu.getTo().size()==0){
                nextQueue.offer(mdu);
            }
        });
        while(nextQueue.size()>0){
            DecisionUnit next = nextQueue.poll();
            if(next.getWl()+currentLits<Math.min(k*programLiterals.size(),limit)){
                U.addAll(next.getLit());
                currentLits += next.getLit().size();
                next.getFrom().forEach(father->{
                    father.getTo().remove(next);
                    if(father.getTo().size()==0){
                        nextQueue.offer(father);
                    }
                });
                limit-=1;
            }
        }
        logger.debug("splitting set: lit weight:{}",currentLits);
    }

    private void generateUBot(){
        U = new HashSet<>();
        int currentRules=0;
        int limit=10;
        PriorityQueue<DecisionUnit> nextQueue = new PriorityQueue<>(comparatorLit);
        mdus.forEach(mdu->{
            if(mdu.getTo().size()==0){
                nextQueue.offer(mdu);
            }
        });
        while(nextQueue.size()>0){
            DecisionUnit next = nextQueue.poll();
            if(next.getWr()+currentRules<Math.min(k* lpmlnprogram.getRules().size(),limit)){
                U.addAll(next.getLit());
                currentRules += next.getWr();
                next.getFrom().forEach(father->{
                    father.getTo().remove(next);
                    if(father.getTo().size()==0){
                        nextQueue.offer(father);
                    }
                });
                limit-=1;
            }
        }
        logger.debug("splitting set: bot weight:{}",currentRules);
    }

    private void generateTopAndBottom(){
        List<Rule> bottomRules = new ArrayList<>();
        List<Rule> topRules = new ArrayList<>();
        for (Rule rule : lpmlnprogram.getRules()) {
            if(isBotRule(rule)){
                bottomRules.add(rule);
            }else{
                topRules.add(rule);
            }
        }
        bottom = new LpmlnProgram(bottomRules, lpmlnprogram.getFactor(), lpmlnprogram.getHerbrandUniverse(), "");
        top = new LpmlnProgram(topRules, lpmlnprogram.getFactor(), lpmlnprogram.getHerbrandUniverse(), lpmlnprogram.getMetarule());
    }

    public boolean isBotRule(Rule rule){
        boolean bot = false;
        for (String headLit : rule.getHead()) {
            if(U.contains(LpmlnProgramHelper.getLiteral(headLit))){
                bot = true;
                break;
            }
        }
        if(rule.getHead().size()==0){
            bot = true;
            for (String bodyLit : rule.getNegativeBody()){
                if(!U.contains(LpmlnProgramHelper.getLiteral(bodyLit))){
                    bot = false;
                    break;
                }
            }
            for (String bodyLit : rule.getPositiveBody()){
                if(!U.contains(LpmlnProgramHelper.getLiteral(bodyLit))){
                    bot = false;
                    break;
                }
            }
        }
        return bot;
    }

    public int getAimBotSize() {
        return aimBotSize;
    }

    public void setAimBotSize(int aimBotSize) {
        this.aimBotSize = aimBotSize;
    }
}
