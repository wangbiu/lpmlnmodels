package cn.edu.seu.kse.lpmln.solver.parallel;

import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.translator.BaseTranslator;

import java.util.List;

/**
 * Created by 许鸿翔 on 2017/10/10.
 */
public class BaseParallelSolver extends LPMLNBaseSolver {
    private int threadNums;
    protected List<Rule> rules;
    protected BaseTranslator translator;
    public BaseParallelSolver(){
        prepare();
    }

    public BaseParallelSolver(BaseTranslator translator,List<Rule> rules){
        this.translator = translator;
        this.rules = rules;
        prepare();
    }

    @Override
    public List<WeightedAnswerSet> call(String rulefile){
        return null;
    }

    public void prepare(){
        init();
    }

    public void init(){

    }

    public int getThreadNums() {
        return threadNums;
    }

    public void setThreadNums(int threadNums) {
        this.threadNums = threadNums;
    }
}
