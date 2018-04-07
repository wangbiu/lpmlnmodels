package cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper;

import java.util.Map;
import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/4/7
 */
public class KSplitter extends Splitter{
    private double k;
    private LpmlnProgram program;
    private Map<String,Set<String>> dependency;
    private SplittingSolver.SPLIT_TYPE policy = SplittingSolver.SPLIT_TYPE.LIT;

    @Override
    public void split(LpmlnProgram program, double k) {
        this.k = k;
        this.program = program;
        this.dependency = LpmlnProgramHelper.getDependency(program);

    }


}
