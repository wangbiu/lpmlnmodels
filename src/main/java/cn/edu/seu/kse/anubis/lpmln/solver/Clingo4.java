package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.SolverStats;
import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.syntax.SyntaxMoudle;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class Clingo4 extends LPMLNBaseSolver {
    private JSONObject times;
//    private SolverStats sta=new SolverStats();

    @Override
    public List<WeightedAnswerSet> call(String rulefile) {
        StringBuilder cmd=new StringBuilder();
        cmd.append("clingo 0 --opt-mode enum ").append(rulefile);
        return super.call(cmd.toString());
    }

    public List<WeightedAnswerSet> solverJsonResultProcess(String result) {
        List<WeightedAnswerSet> was=new ArrayList<>();
        JSONObject obj=JSONObject.fromObject(result);
        JSONArray witnesses=obj.getJSONArray("Call").getJSONObject(0).getJSONArray("Witnesses");
        times=obj.getJSONObject("Time");
        int maxlevel2=0;

        int size=witnesses.size();
//        System.out.println(witnesses);
        JSONObject jobj=null;
        JSONArray jas=null;
        JSONArray jweight=null;
        int vsize=0;
        WeightedAnswerSet as=null;
        int jtmpweight=0;
        List<WeightedAnswerSet> tmpwas=new ArrayList<>();
        for(int i=0;i<size;i++){
            as=new WeightedAnswerSet();
            jobj=witnesses.getJSONObject(i);
            jas=jobj.getJSONArray("Value");
            jweight=jobj.getJSONArray("Costs");

            vsize=jas.size();
            for(int j=0;j<vsize;j++){
                as.getAnswerSet().add(jas.getString(j));
            }

            vsize=jweight.size();
            for(int j=vsize-1;j>=0;j--){

                //减去 trick part 增加的权值1
                //TODO 权重变换以后需要注意
                jtmpweight=jweight.getInt(j)-1;

                as.getWeights().add(jtmpweight);
                if(j == 0){
                    if(jtmpweight > maxlevel2){
                        maxlevel2=jtmpweight;
                    }
                }
            }
//            System.out.println(as);
            tmpwas.add(as);
        }

//        System.out.println("max level 2 "+maxlevel2);

        for(WeightedAnswerSet tas:tmpwas){
//            System.out.printf("tas weight %d, maxlevel2 %d%n",tas.getWeights().get(1),maxlevel2);
            if(tas.getWeights().get(1) == maxlevel2){
                was.add(tas);
            }
        }
        tmpwas.clear();
        tmpwas=null;
        return was;
    }

    @Override
    public List<WeightedAnswerSet> solverResultProcess(String result) {
        SyntaxMoudle sm=new SyntaxMoudle();
        int posstart=result.indexOf("Answer: 1");
        if(posstart <0){
            return new ArrayList<>();
        }
        int posend=result.indexOf("OPTIMUM FOUND");
//        System.out.println(result);
        String asresult=result.substring(posstart,posend);
        String statinfo=result.substring(posend);
        result=null;
        List<WeightedAnswerSet> was=sm.parseClingoResult(asresult);
        sm=null;
//        System.out.println("was: "+was);
        // 抽取时间信息


        String[] stats=statinfo.split(System.lineSeparator());
        statinfo=stats[stats.length-2];
//        System.out.println(statinfo);
        posstart=statinfo.indexOf(":")+1;
        posend=statinfo.indexOf("s");
        String time=statinfo.substring(posstart,posend).trim();
//        System.out.println("statinfo "+statinfo);
//        System.out.println("time: "+time);
        sta.setTotal(Double.valueOf(time));
        totalSolverTime=sta.getTotal();
        return was;
    }

    @Override
    public SolverStats genSolverStatisticsInfo() {
//        sta.setTotal(times.getDouble("Total"));
//        sta.setSolve(times.getDouble("Solve"));
//        sta.setModel(times.getDouble("Model"));
//        sta.setUnsat(times.getDouble("Unsat"));
//        sta.setCpu(times.getDouble("CPU"));
        return super.genSolverStatisticsInfo();
    }
}
