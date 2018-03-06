package cn.edu.seu.kse.lpmln.solver.impl;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;
import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.SolverStats;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.AspSolver;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.translator.impl.LPMLN2ASPTranslator;
import cn.edu.seu.kse.lpmln.util.FileHelper;
import cn.edu.seu.kse.lpmln.experiment.util.TimeStatistics;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class LPMLNBaseSolver implements LPMLNSolver {
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
    protected static final String LPSUFFIX = ".lp";
    protected LPMLN2ASPTranslator translator;
    protected AspSolver aspSolver;
    protected LpmlnProgram lpmlnProgram;
    protected TimeStatistics times;
    protected String experimentInfo = "";
    //TODO:推理信息收集（时间）

    public LPMLNBaseSolver() {
        aspSolver = new ClingoSolver();
    }

    @Override
    public List<WeightedAnswerSet> solve(File ruleFile) {
        times = new TimeStatistics();
        //开始计时
        times.totalTime.start();

        List<WeightedAnswerSet> result;
        List<WeightedAnswerSet> aspResult;

        times.solveTime.start();
        //解析LPMLN程序
        lpmlnProgram = parse(ruleFile);

        //翻译为ASP程序
        translator = new LPMLN2ASPTranslator();
        String aspProgram = translator.translate(lpmlnProgram);

        //保留翻译后的文件
        FileHelper.writeFile(new File(LPMLNApp.translationFilePrefix + LPSUFFIX), aspProgram);

        //ASP求解
        aspResult = aspSolver.solve(aspProgram);

        result = calculateProbability(filtWas(aspResult));
        weightedAs = result;

        //结束计时
        times.solveTime.stop();
        times.totalTime.stop();
        return result;
    }

    @Override
    public List<WeightedAnswerSet> solve(String program) {
        File outf = FileHelper.randomFile();
        FileHelper.writeFile(outf, program);
        return solve(outf);
    }

    @Override
    public List<WeightedAnswerSet> solveTranslated(File translatedFile) {
        List<WeightedAnswerSet> result;
        List<WeightedAnswerSet> aspResult;

        lpmlnProgram = new LpmlnProgram();
        lpmlnProgram.setFactor(0);

        //ASP求解
        aspResult = aspSolver.solve(translatedFile);

        result = calculateProbability(filtWas(aspResult));
        weightedAs = result;
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

    @Override
    public boolean containsLiteral(String literal) {
        for (WeightedAnswerSet was : weightedAs) {
            if (was.getAnswerSet().getLiterals().contains(literal)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double getLiteralProbability(String literal) {
        double prob = 0;
        for (WeightedAnswerSet was : weightedAs) {
            if (was.getAnswerSet().getLiterals().contains(literal)) {
                prob += was.getProbability();
            }
        }
        return prob;
    }

    @Override
    public String getMarginalDistribution() {
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
        int factor = lpmlnProgram.getFactor();
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
        factor = -1;
        double wsum = 0;
        double expw = 0;
        for (WeightedAnswerSet as : origin) {
            expw = Math.exp(factor * as.getWeights().get(0) / Math.pow(10,as.getFactor()));
            wsum += expw;
            as.setProbability(expw);
        }

        for (WeightedAnswerSet as : origin) {
            expw = as.getProbability();
            as.setProbability(expw / wsum);
        }
        return origin;
    }

    @Override
    public List<WeightedAnswerSet> findMaxWeightedAs() {
        Date enter = new Date();
        int level2 = 0;
        int aimlevel = 0;
        int maxlevel1 = Integer.MIN_VALUE;
        int minlevel1 = Integer.MAX_VALUE;
        maxWeightAs = new ArrayList<>();
        for (WeightedAnswerSet as : weightedAs) {
            level2 = as.getWeights().get(1);
            maxlevel1 = Math.max(maxlevel1, as.getWeights().get(0));
            minlevel1 = Math.min(minlevel1, as.getWeights().get(0));
        }
        aimlevel = minlevel1;
        for (WeightedAnswerSet as : weightedAs) {
            if (as.getWeights().get(0) == aimlevel) {
                maxWeightAs.add(as);
            }
        }

        if (level2 != 0) {
            maxWeight = "" + level2 + "*alpha + " + maxlevel1;
        } else {
            maxWeight = String.valueOf(maxlevel1);
        }

        Date exit = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("求最大权重可能世界用时：").append(exit.getTime() - enter.getTime()).append(" ms");
        sb.append(System.lineSeparator());
        maximalTime = sb.toString();
        return maxWeightAs;
    }

    public String getExperimentInfo(){
        return experimentInfo;
    }

    @Override
    public List<WeightedAnswerSet> getAllWeightedAs() {
        return weightedAs;
    }

    public String getMarginalTime() {
        return marginalTime;
    }

    public void setMarginalTime(String marginalTime) {
        this.marginalTime = marginalTime;
    }

    public String getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getMaximalTime() {
        return maximalTime;
    }

    public void setMaximalTime(String maximalTime) {
        this.maximalTime = maximalTime;
    }

    public List<WeightedAnswerSet> getWeightedAs() {
        return weightedAs;
    }

    public void setWeightedAs(List<WeightedAnswerSet> weightedAs) {
        this.weightedAs = weightedAs;
    }

    public TimeStatistics getTimes() {
        return times;
    }
}
