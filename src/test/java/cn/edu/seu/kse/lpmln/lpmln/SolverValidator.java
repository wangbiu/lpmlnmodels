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
        toValidate.solve(testFile);
        basesolver.solve(testFile);
        Map<String,String> ans1 = getMPL(basesolver);
        Map<String,String> ans2 = getMPL(toValidate);
        assert toValidate.getAllWeightedAs().size()==basesolver.getAllWeightedAs().size();
        assert ans1.size()==ans2.size();
        System.out.println("answer set size of "+testFile.getName()+":"+toValidate.getAllWeightedAs().size());
        ans1.forEach((k,v)->{
            assert ans2.get(k).equals(v);
        });
        FileHelper.cleanFiles();
        return true;
    }

    public Map<String,String> getMPL(LPMLNSolver solver){
        String[] results = solver.getMarginalDistribution().split(System.lineSeparator());
        Map<String,String> ans = new HashMap<>();
        for (String result : results) {
            String[] lit = result.split("  ");
            assert lit.length==2;
            ans.put(lit[0],df.format(Double.valueOf(lit[1])));
        }
        return ans;
    }
}
