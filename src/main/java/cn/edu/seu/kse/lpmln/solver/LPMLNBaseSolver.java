package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.SolverStats;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.translator.LPMLN2ASPTranslator;
import cn.edu.seu.kse.lpmln.util.FileHelper;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;
import cn.edu.seu.kse.lpmln.util.syntax.lpmln.LPMLNTranslationVisitor;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class LPMLNBaseSolver {
    protected List<WeightedAnswerSet> weightedAs = null;
    protected List<WeightedAnswerSet> maxWeightAs = null;
    protected String maxWeight;
    protected SolverStats stats;
    protected String executeProfile;
    protected String marginalTime;
    protected String maximalTime;
    protected List<Long> executeTime = new ArrayList<>();
    protected SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSSS");
    final protected int WIN_NT = 1;
    final protected int UNIX = 0;
    protected double totalSolverTime;
    protected SolverStats sta = new SolverStats();
    public static final String LPSUFFIX = ".lp";
    protected List<File> tempFiles;
    protected LPMLN2ASPTranslator translator;
    protected AspSolver aspSolver;
    //TODO:推理信息收集（时间）

    public LPMLNBaseSolver() {
        tempFiles = new ArrayList<>();
        translator = new LPMLN2ASPTranslator();
        aspSolver = new ClingoSolver();
    }

    public List<WeightedAnswerSet> solve(File ruleFile) {
        List<WeightedAnswerSet> result;
        List<WeightedAnswerSet> aspResult;

        //解析LPMLN程序
        LpmlnProgram lpmlnProgram = parse(ruleFile);

        //翻译为ASP程序
        String aspProgram = translator.translate(lpmlnProgram);

        //ASP求解
        aspResult = aspSolver.solve(aspProgram);

        result = calculateProbability(filtWas(aspResult));

        return result;
    }

    public LpmlnProgram parse(File ruleFile) {
        LpmlnProgram lpmlnProgram = null;
        try {
            lpmlnProgram = SyntaxModule.parseLPMLN(ruleFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lpmlnProgram;
    }

    public List<WeightedAnswerSet> solve(String program) {
        String filename = UUID.randomUUID().toString() + LPSUFFIX;
        File outf = new File(filename);
        FileHelper.writeFile(outf, program);
        tempFiles.add(outf);
        return solve(outf);
    }

    public String marginalDistribution(int factor) {
        Date enter = new Date();
        HashMap<String, Double> result = new HashMap<>();
        double wsum = 0;
        double expw = 0;


        HashSet<String> literals = null;
        for (WeightedAnswerSet as : weightedAs) {
            expw = as.getProbability();
            literals = as.getAnswerSet().getLiterals();
            for (String lit : literals) {
                if (result.containsKey(lit)) {
                    wsum = result.get(lit);
                } else {
                    wsum = 0;
                }
                wsum += expw;
                result.put(lit, wsum);
            }
        }

        String res = formatMarginalResult(result);
        Date exit = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("求边缘分布用时：").append(exit.getTime() - enter.getTime()).append(" ms");
        sb.append(System.lineSeparator());
        marginalTime = sb.toString();
        return res;
    }

    public String formatMarginalResult(HashMap<String, Double> result) {
        StringBuilder fres = new StringBuilder();
        for (HashMap.Entry<String, Double> entry : result.entrySet()) {
            fres.append(entry.getKey()).append("  ").append(entry.getValue()).append(System.lineSeparator());
        }
        return fres.toString();
    }

    public List<WeightedAnswerSet> filtWas(List<WeightedAnswerSet> origin) {
        List<WeightedAnswerSet> ans = new ArrayList<>(origin.size());
        //再次筛选level2上的最小值，并且将除factor以保证小数位数
        int minLevel2 = Integer.MAX_VALUE;
        int maxLevel2 = Integer.MIN_VALUE;
        int aimLevel2 = 0;
        int factor = LPMLNTranslationVisitor.getFactor();
        for (WeightedAnswerSet was : origin) {
            minLevel2 = Math.min(was.getWeights().get(1), minLevel2);
            maxLevel2 = Math.max(was.getWeights().get(1), maxLevel2);
        }
        aimLevel2 = minLevel2;
        for (WeightedAnswerSet was : origin) {
            if (was.getWeights().get(1) == aimLevel2) {
                was.setFactor(factor);
                ans.add(was);
            }
        }
        return ans;
    }

    public List<WeightedAnswerSet> calculateProbability(List<WeightedAnswerSet> origin) {
        int factor = 1;
        //TODO:考虑pow(10,n)
        factor = -1;
        double wsum = 0;
        double expw = 0;
        for (WeightedAnswerSet as : origin) {
            expw = Math.exp(factor * as.getWeights().get(0) * 1.0);
            wsum += expw;
            as.setProbability(expw);
        }

        for (WeightedAnswerSet as : origin) {
            expw = as.getProbability();
            as.setProbability(expw / wsum);
        }
        return origin;
    }
}
