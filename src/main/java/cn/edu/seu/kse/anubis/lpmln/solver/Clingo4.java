package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.SolverStats;
import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class Clingo4 extends BaseSolver {
    private JSONObject times;

    @Override
    public List<WeightedAnswerSet> call(String rulefile) {
        StringBuilder cmd=new StringBuilder();
        cmd.append("clingo 0 --opt-mode enum --outf 2 ").append(rulefile);
        return super.call(cmd.toString());
    }

    @Override
    public List<WeightedAnswerSet> solverResultProcess(String result) {
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
                jtmpweight=jweight.getInt(j);
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

        System.out.println("max level 2 "+maxlevel2);

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
    public SolverStats genSolverStatisticsInfo() {
        SolverStats sta=new SolverStats();
        sta.setTotal(times.getDouble("Total"));
        sta.setSolve(times.getDouble("Solve"));
        sta.setModel(times.getDouble("Model"));
        sta.setUnsat(times.getDouble("Unsat"));
        sta.setCpu(times.getDouble("CPU"));
        return sta;
    }
}
