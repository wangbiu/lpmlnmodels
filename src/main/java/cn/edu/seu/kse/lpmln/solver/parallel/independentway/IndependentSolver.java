package cn.edu.seu.kse.lpmln.solver.parallel.independentway;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.util.LpmlnThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 许鸿翔
 * @date 2018/4/2
 */
public class IndependentSolver extends LPMLNBaseSolver{
    private LpmlnThreadPool threadPool;
    private static final String THREAD_POOL_NAME="IndependentSolver";
    private List<LPMLNSolver> solvers;
    private List<List<WeightedAnswerSet>> subWeightedAs;
    private static Logger logger = LogManager.getLogger(IndependentSolver.class.getName());
    private String arch;

    public IndependentSolver() {
        solvers = new ArrayList<>();
        this.arch = "";
    }

    public IndependentSolver(String arch){
        solvers = new ArrayList<>();
        this.arch = arch;
    }

    @Override
    public void executeSolving(){
        long start = System.currentTimeMillis();
        List<LpmlnProgram> subprograms = IndependentSplitter.split(lpmlnProgram);
        System.out.println("ind split cost: "+(System.currentTimeMillis()-start));
        if(subprograms==null||subprograms.size()==1){
            super.executeSolving();
            return;
        }
        threadPool = new LpmlnThreadPool(THREAD_POOL_NAME);
        if(subprograms==null){
            subprograms = new ArrayList<>();
            subprograms.add(lpmlnProgram);
        }
        logger.info("IndependentSolver into {} subprograms.",subprograms.size());
        solvers.clear();
        subprograms.forEach(subprogram -> {
            //TODO:使用反射创建
            LPMLNSolver solver = chooseSolver(arch);
            solver.setLpmlnProgram(subprogram);
            solvers.add(solver);
            threadPool.execute(solver);
        });

        threadPool.waitDone();
        mergeResult();
    }

    /**
     * 排列组合生成答案
     */
    //TODO:factor处理
    private void mergeResult(){
        System.out.println("1:"+System.currentTimeMillis());
        WeightedAnswerSet meta = new WeightedAnswerSet();
        meta.getWeights().add(0);
        meta.getWeights().add(0);
        if(solvers.size()==1){
            weightedAs = solvers.get(0).getAllWeightedAs();
            return;
        }
        subWeightedAs = new ArrayList<>();
        weightedAs = new ArrayList<>();
        solvers.forEach(solver->{
            if(solver.getAllWeightedAs().size()==1){
                merge(meta,solver.getAllWeightedAs().get(0));
            }else{
                subWeightedAs.add(solver.getAllWeightedAs());
            }
        });
        if(subWeightedAs.size()==0){
            weightedAs.add(meta);
            return;
        }
        String[][][] litss = new String[subWeightedAs.size()][][];
        int[][] weight0s = new int[subWeightedAs.size()][];
        int[][] weight1s = new int[subWeightedAs.size()][];

        for(int i=0;i<subWeightedAs.size();i++){
            List<WeightedAnswerSet> wass = subWeightedAs.get(i);
            litss[i] = new String[wass.size()][];
            weight0s[i] = new int[wass.size()];
            weight1s[i] = new int[wass.size()];
            for(int j=0;j<wass.size();j++){
                WeightedAnswerSet was = wass.get(j);
                Set<String> litSet = was.getAnswerSet().getLiterals();
                litss[i][j] = litSet.toArray(new String[litSet.size()]);
                weight0s[i][j] = was.getWeights().get(0);
                weight1s[i][j] = was.getWeights().get(1);
            }
        }

        List<int[]> permutations = new ArrayList<>();
        int[] permutation = new int[subWeightedAs.size()];
        do{
            permutations.add(permutation.clone());
        }while(nextPermutation(permutation));

        weightedAs = permutations.stream().parallel().map(p->{
            Set<String> metaLits = meta.getAnswerSet().getLiterals();
            String[] realLits = metaLits.toArray(new String[metaLits.size()]);
            String[][] litList = new String[p.length][];
            int weight0 = meta.getWeights().get(0);
            int weight1 = meta.getWeights().get(1);
            for(int i=0;i<permutation.length;i++){
                litList[i] = litss[i][p[i]];
                weight0 += weight0s[i][p[i]];
                weight1 += weight1s[i][p[i]];
            }
            realLits = concatAll(realLits,litList);
            return new WeightedAnswerSet(realLits,weight0,weight1);
        }).collect(Collectors.toList());
    }

    public static <T> T[] concatAll(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    private boolean merge(WeightedAnswerSet to,WeightedAnswerSet from){
        //factor子程序间一致,probability会重新计算
        //独立分割不会引发不一致
        to.getAnswerSet().getLiterals().addAll(from.getAnswerSet().getLiterals());
        to.getWeights().set(0,to.getWeights().get(0)+from.getWeights().get(0));
        to.getWeights().set(1,to.getWeights().get(1)+from.getWeights().get(1));
        return true;
    }

    private boolean nextPermutation(int[] permutation){
        int idx=permutation.length-1;
        while(idx>=0&&permutation[idx]==subWeightedAs.get(idx).size()-1){
            idx--;
        }
        if(idx<0){
            return false;
        }
        permutation[idx]++;
        for(int i=idx+1;i<permutation.length;i++){
            permutation[i]=0;
        }
        return true;
    }

}
