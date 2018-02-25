package cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;
import cn.edu.seu.kse.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.translator.impl.AugmentedSubsetTranslator;
import cn.edu.seu.kse.lpmln.util.FileHelper;

import java.io.File;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/1/17
 */
public class AugmentedSubsetSolver extends LPMLNBaseSolver implements Runnable {
    protected AugmentedSubset subset;
    public AugmentedSubsetSolver(AugmentedSubset subset){
        this.subset = subset;
    }

    @Override
    public void run() {
        solve();
    }

    public List<WeightedAnswerSet> solve()
    {
        List<WeightedAnswerSet> result;
        List<WeightedAnswerSet> aspResult;

        lpmlnProgram = subset.getLpmlnProgram();

        //翻译为ASP程序
        translator = new AugmentedSubsetTranslator(subset.getSatIdx(),subset.getUnsatIdx());
        String aspProgram = translator.translate(lpmlnProgram);

        //System.out.println(subset.getSatIdx().toString()+subset.getUnsatIdx()+Thread.currentThread().getId());

        //保留翻译后的文件
        FileHelper.writeFile(new File(LPMLNApp.translationFilePrefix+"-"+Thread.currentThread().getId()+LPSUFFIX),aspProgram);

        //ASP求解
        aspResult = aspSolver.solve(aspProgram);

        result = calculateProbability(filtWas(aspResult));
        weightedAs = result;

        return result;
    }


}
