package cn.edu.seu.kse.anubis;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.LPMLNBaseSolver;
import cn.edu.seu.kse.anubis.lpmln.solver.Clingo4;
import cn.edu.seu.kse.anubis.lpmln.solver.DLV;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
@Deprecated
public class App 
{
    public static void main( String[] args )
    {
        Date enter=new Date();
        boolean isMarginal=false;
        boolean showall=false;
        int factor=1;
        String rulefile=args[args.length-1];
        String aspsolver=null;
        int cnt=0;
        for(String s:args){
            if(s.indexOf("marginal")==1){
                isMarginal=true;
            }else if(s.indexOf("showall") == 1){
                showall=true;
            }else if(s.indexOf("asp") == 1){
                aspsolver=args[cnt+1];
            }else if (s.indexOf("factor")==1){
                factor=Integer.valueOf(args[cnt+1]);
            }
            cnt++;
        }

        LPMLNBaseSolver solver=null;

        if(aspsolver.equals("clingo")){
            solver=new Clingo4();
        }else if(aspsolver.equals("dlv")){
            solver=new DLV();
        }else {
            System.out.println("can not recognize reasoner "+aspsolver);
            return;
        }

        List<WeightedAnswerSet> was=solver.call(rulefile);

        if(showall){
            System.out.println("all non-zero probability possible world ");
            System.out.println(was);

        }

        if(isMarginal){
            String marginal=solver.marginalDistribution(factor);
            System.out.println("marginal result ");
            System.out.println(marginal);
            System.out.println(solver.getMarginalTime());
        }else {
            List<WeightedAnswerSet> maxWas=null;
            maxWas=solver.findMaxWeightedAs();
            System.out.println(System.lineSeparator());
            System.out.println("maximal weight possible world ");
            System.out.println(maxWas);
            System.out.println("weight "+solver.getMaxWeight());
            System.out.println(solver.getMaximalTime());
        }

        printStatsInfo(solver);

        Date exit=new Date();

        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss.SSSS");
        System.out.printf("%n总用时%nenter %s, exit %s, cost %d ms %n", sdf.format(enter),sdf.format(exit),exit.getTime()-enter.getTime());
    }

    public static void printStatsInfo(LPMLNBaseSolver solver){
        System.out.println(solver.getStats());
        System.out.println(solver.getExecuteProfile());
    }
}
