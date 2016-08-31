package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class Clingo4 extends BaseSolver {
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

        int maxlevel2=0;

        int size=witnesses.size();
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
                if(j == 1){
                    if(jtmpweight > maxlevel2){
                        maxlevel2=jtmpweight;
                    }
                }
            }
            tmpwas.add(as);
        }

        for(WeightedAnswerSet tas:tmpwas){
            if(tas.getWeights().get(1) == maxlevel2){
                was.add(tas);
            }
        }

        return was;
    }


}
