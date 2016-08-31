package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import org.junit.Test;

import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class TestSolver2 {

    @Test
    public void testClingo(){
        String rulefile="G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\experiment\\clingo\\bird.txt";
        Clingo4 clingo4=new Clingo4();
        List<WeightedAnswerSet> was=clingo4.call(rulefile);
        clingo4.findMaxWeightedAs();
        List<WeightedAnswerSet> maxWeightAs=clingo4.getMaxWeightAs();
        String maxWeight=clingo4.getMaxWeight();

        System.out.printf("Weighted Answer Set : %s%n maxWeightAs : %s%n maxWeight : %s%n",was,maxWeightAs,maxWeight);
        String marginal=clingo4.marginalDistribution(1);
        System.out.println("marginal results : "+marginal);
    }

    @Test
    public void testDLV(){
        String rulefile="G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\experiment\\dlv\\bird.txt";
        DLV dlv=new DLV();
        List<WeightedAnswerSet> was=dlv.call(rulefile);
        dlv.findMaxWeightedAs();
        List<WeightedAnswerSet> maxWeightAs=dlv.getMaxWeightAs();
        String maxWeight=dlv.getMaxWeight();
        System.out.printf("Weighted Answer Set : %s%n maxWeightAs : %s%n maxWeight : %s%n",was,maxWeightAs,maxWeight);
        String marginal=dlv.marginalDistribution(1);
        System.out.println("marginal results : "+marginal);
    }
}
