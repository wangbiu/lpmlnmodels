package cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway;

import cn.edu.seu.kse.lpmln.model.DecisionUnit;
import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * @author 许鸿翔
 * @date 2018/4/7
 */
public class KSplitter extends Splitter{
    private double k;
    private LpmlnProgram program;
    private Map<String,Set<String>> dependency;
    private Set<String> programLiterals;
    private List<DecisionUnit> mdus;
    private SplittingSolver.SPLIT_TYPE policy = SplittingSolver.SPLIT_TYPE.LIT;
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

    public KSplitter(){

    }

    public KSplitter(SplittingSolver.SPLIT_TYPE policy){
        this.policy = policy;
    }

    @Override
    public void split(LpmlnProgram program, double k) {
        this.k = k;
        this.program = program;
        this.dependency = LpmlnProgramHelper.getDependency(program);

        this.mdus = generateMDUs();
        buildRelations();

        generateU();

        generateTopAndBottom();
    }

    private List<DecisionUnit> generateMDUs(){
        List<DecisionUnit> mdu = new ArrayList<>();
        //已经被加到之前mdu里的元素
        Set<String> used = new HashSet<>();
        programLiterals = new HashSet<>(dependency.keySet());
        dependency.values().forEach(depend->programLiterals.addAll(depend));
        programLiterals.forEach(lit->{
            if(used.contains(lit)){
                return;
            }
            //当前的mdu有的元素
            Set<String> current = dfs(lit);
            used.addAll(current);
            if(current.size()!=0){
                mdu.add(new DecisionUnit(program,current));
            }
        });
        return mdu;
    }

    private Set<String> dfs(String lit){
        Set<String> current = new HashSet<>();
        Set<String> visited = new HashSet<>();
        //TODO:修复这边
        if(lit.charAt(0)<='9'&&lit.charAt(0)>='0'){
            return current;
        }
        //深度优先，stack1记录顺序，stack记录目前访问的,path记录当前路径下
        LinkedList<String> stack1 = new LinkedList<>();
        LinkedList<Iterator<String>> stack2 = new LinkedList<>();
        Set<String> path = new HashSet<>();
        stack1.push(lit);
        stack2.push(dependency.get(lit).iterator());
        path.add(lit);
        visited.add(lit);
        while(stack2.size()>0){
            if(stack2.peek().hasNext()){
                String next = stack2.peek().next();
                if(path.contains(next)||visited.contains(next)){
                    if(next.equals(lit)){
                        current.addAll(path);
                    }
                }else{
                    stack1.push(next);
                    if(dependency.containsKey(next)){
                        stack2.push(dependency.get(next).iterator());
                        path.add(next);
                    }else{
                        stack1.pop();
                    }
                }
                visited.add(next);
            }else{
                stack2.pop();
                path.remove(stack1.pop());
            }
        }
        return current;
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
    }

    private void generateU(){
        switch (policy){
            case BOT:
                generateUBot();
                break;
            case LIT:
            default:
                generateULit();
                break;
        }

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
            if(next.getWl()+currentLits<Math.min(k*programLiterals.size(),350)){
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
            if(next.getWr()+currentRules<Math.min(k*program.getRules().size(),400)){
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
        for (Rule rule : program.getRules()) {
            boolean bot = false;
            for (String headLit : rule.getHead()) {
                if(U.contains(LpmlnProgramHelper.getLiteral(headLit))){
                    bot = true;
                }
            }
            if(bot){
                bottomRules.add(rule);
            }else{
                topRules.add(rule);
            }
        }
        bottom = new LpmlnProgram(bottomRules, program.getFactor(), program.getHerbrandUniverse(), "");
        top = new LpmlnProgram(topRules, program.getFactor(), program.getHerbrandUniverse(), program.getMetarule());
    }

}
