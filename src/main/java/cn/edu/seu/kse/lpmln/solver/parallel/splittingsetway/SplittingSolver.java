package cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;

import java.util.List;
import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/4/2
 */
public class SplittingSolver extends LPMLNBaseSolver {
    private LPMLNSolver bottomSolver;
    private List<LPMLNSolver> topSolvers;
    public static double k=0.5;
    public SplittingSolver(){
        bottomSolver = new LPMLNBaseSolver();
    }

    @Override
    public List<WeightedAnswerSet> solveProgram(LpmlnProgram program){
        lpmlnProgram = program;
        return weightedAs;
    }

    public static Set<String> getSplittingSet(LpmlnProgram program){
//        Map<String,Set<String>> dependency = LpmlnProgramHelper.getDependency(program);
//        Map<String,Set<String>> reachable = new HashMap<>();
//        Set<String> u;
//        int minDiff=program.getRules().size();
//        int kpie = k*program.getRules().set();
//        dependency.keySet().forEach(from->{
//            Set<String> visited = new HashSet<>();
//            LinkedList<String> togo = new LinkedList<>();
//            visited.add(from);
//            togo.offer(from);
//            while(togo.size()>0){
//                String next = togo.poll();
//                if(!visited.contains(next)){
//                    dependency.get(next).forEach(tovisit->togo.offer(tovisit));
//                    visited.add(next);
//                }
//            }
//            reachable.put(from,visited);
//        });
//        reachable.values().forEach(tempu->{
//        });
        //TODO:next
        return null;
    }
}
