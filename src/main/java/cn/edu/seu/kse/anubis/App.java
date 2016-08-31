package cn.edu.seu.kse.anubis;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.BaseSolver;
import cn.edu.seu.kse.anubis.lpmln.solver.Clingo4;
import cn.edu.seu.kse.anubis.lpmln.solver.DLV;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        boolean isMarginal=false;
        boolean showall=false;
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
            }
            cnt++;
        }

        BaseSolver solver=null;

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
            String marginal=solver.marginalDistribution(1);
            System.out.println("marginal result ");
            System.out.println(marginal);
        }else {
            List<WeightedAnswerSet> maxWas=null;
            maxWas=solver.findMaxWeightedAs();
            System.out.println(System.lineSeparator());
            System.out.println("maximal weight possible world ");
            System.out.println(maxWas);
            System.out.println("weight "+solver.getMaxWeight());
        }
    }
}
