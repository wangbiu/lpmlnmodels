package cn.edu.seu.kse.lpmln.lpmln;

import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.util.FileHelper;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 许鸿翔
 * @date 2018/4/16
 */
public class SolverValidator {
    public LPMLNSolver basesolver = new LPMLNBaseSolver();
    public LPMLNSolver toValidate;
    public DecimalFormat df = new DecimalFormat("#.000");
    public SolverValidator(LPMLNSolver toValidate){
        this.toValidate = toValidate;
    }

    public static void main(String[] aregs){
        SolverValidator sv = new SolverValidator(new LPMLNBaseSolver());
        sv.validate("./src/test/resources/benchmark/bird/b-3.txt");
    }

    public boolean validate(String filepath){
        File testFile = new File(filepath);
        basesolver.solve(testFile);
        toValidate.solve(testFile);
        Map<String,String> ans1 = getMPL(basesolver);
        Map<String,String> ans2 = getMPL(toValidate);
        try {
            assert toValidate.getAllWeightedAs().size()==basesolver.getAllWeightedAs().size();
            assert ans1.size()==ans2.size();
            System.out.println("answer set size of "+testFile.getName()+":"+toValidate.getAllWeightedAs().size());
            ans1.forEach((k,v)->{
                if(!ans2.get(k).equals(v)){
                    System.out.println("k:"+k+"\t"+"v:"+v);
                    System.out.println("k:"+k+"\t"+"ans2.get(k):"+ans2.get(k));
                    assert false;
                }
            });
        }catch (AssertionError assertionError){
            System.out.println("test file: "+filepath);
            System.out.println(toValidate.getClass().getSimpleName());
            System.out.println("toValidate.getAllWeightedAs().size(): "+toValidate.getAllWeightedAs().size());
            System.out.println("basesolver.getAllWeightedAs().size(): "+basesolver.getAllWeightedAs().size());
            System.out.println("ans1.size(): "+ans1.size());
            System.out.println("ans2.size(): "+ans2.size());
            throw assertionError;
        }

        FileHelper.cleanFiles();
        return true;
    }

    public Map<String,String> getMPL(LPMLNSolver solver){
        String[] results = solver.getMarginalDistribution().split(System.lineSeparator());
        Map<String,String> ans = new HashMap<>();
        String[] lit = null;
        try {
            for (String result : results) {
                lit = result.split("  ");
                assert lit.length==2;
                ans.put(lit[0],df.format(Double.valueOf(lit[1])));
            }
        }catch (AssertionError assertionError){
            System.out.println(solver.getClass().getSimpleName());
            if(lit==null){
                System.out.println("lit==null.");
            }else{
                System.out.println("lit.length: "+lit.length);
            }
            throw assertionError;
        }

        return ans;
    }
}
