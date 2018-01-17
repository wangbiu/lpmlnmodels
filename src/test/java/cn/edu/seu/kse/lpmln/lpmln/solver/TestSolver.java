//package cn.edu.seu.kse.lpmln.lpmln.solver;
//
//import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
//import net.sf.json.JSONObject;
//import org.junit.Test;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by 王彬 on 2016/8/31.
// */
//public class TestSolver {
//
//    @Test
//    public void testCallClingo(){
//        Date start=new Date();
//        String rulefile="G:\\my_thesis\\asp_lpmln\\experiment\\clingo\\bird.txt";
//        JSONObject result= Solver.callClingo(rulefile);
//        Date end=new Date();
//        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss.SSS");
//        System.out.printf("begin %s, end %s %n", sdf.format(start),sdf.format(end));
//
//        System.out.println(result);
//    }
//
//    @Test
//    public void testCallDLV(){
//        Date start=new Date();
//        String rulefile="G:\\my_thesis\\asp_lpmln\\experiment\\dlv\\bird.txt";
//        List<WeightedAnswerSet> result= Solver.callDLV(rulefile);
//        Date end=new Date();
//        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss.SSS");
//        System.out.printf("begin %s, end %s %n", sdf.format(start),sdf.format(end));
//        System.out.println(result);
//    }
//
//}
