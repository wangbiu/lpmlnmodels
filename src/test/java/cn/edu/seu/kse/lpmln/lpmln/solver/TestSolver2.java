//package cn.edu.seu.kse.lpmln.lpmln.solver;
//
//import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
//import cn.edu.seu.kse.lpmln.solver.Clingo4;
//import cn.edu.seu.kse.lpmln.solver.DLV;
//import org.junit.Test;
//
//import java.util.List;
//
///**
// * Created by 王彬 on 2016/8/31.
// */
//public class TestSolver2 {
//
//    @Test
//    public void testClingo(){
//        String rulefile="G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\experiment\\clingo\\bird.txt";
//        Clingo4 clingo4=new Clingo4();
//        List<WeightedAnswerSet> was=clingo4.call(rulefile);
//        clingo4.findMaxWeightedAs();
//        List<WeightedAnswerSet> maxWeightAs=clingo4.getMaxWeightAs();
//        String maxWeight=clingo4.getMaxWeight();
//
//        System.out.printf("Weighted Answer Set : %s%n maxWeightAs : %s%n maxWeight : %s%n",was,maxWeightAs,maxWeight);
//        String marginal=clingo4.getMarginalDistribution(1);
//        System.out.println("marginal results : "+marginal);
//        System.out.println("stats "+clingo4.getStats());
//    }
//
//    @Test
//    public void testDLV(){
//        String rulefile="G:\\IdeaProjects\\lpmlnmodels\\src\\test\\resources\\experiment\\dlv\\friend.txt";
//        DLV dlv=new DLV();
//        List<WeightedAnswerSet> was=dlv.call(rulefile);
//        dlv.findMaxWeightedAs();
//        List<WeightedAnswerSet> maxWeightAs=dlv.getMaxWeightAs();
//        String maxWeight=dlv.getMaxWeight();
//        System.out.printf("Weighted Answer Set : %s%n maxWeightAs : %s%n maxWeight : %s%n",was,maxWeightAs,maxWeight);
//        String marginal=dlv.getMarginalDistribution(1);
//        System.out.println("marginal results : "+marginal);
//    }
//}
