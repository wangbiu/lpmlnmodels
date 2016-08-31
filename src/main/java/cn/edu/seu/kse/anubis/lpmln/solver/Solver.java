package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.syntax.SyntaxMoudle;
import cn.edu.seu.kse.anubis.util.CommandLineExecute;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class Solver {
    public static JSONObject callClingo(String rulefile){
        JSONObject result=null;
        StringBuilder cmd=new StringBuilder();
        cmd.append("clingo 0 --opt-mode enum --outf 2 ").append(rulefile);
        String[] cmdres=CommandLineExecute.callShellwithReturn(cmd.toString(),1);

        result=JSONObject.fromObject(cmdres[0]);

        return result;
    }

    public static List<WeightedAnswerSet> callDLV(String rulefile){
        StringBuilder cmd=new StringBuilder();
        cmd.append("dlv -costbound=_,_  -stats -v ").append(rulefile);
        String[] cmdres=CommandLineExecute.callShellwithReturn(cmd.toString(),1);
        System.out.println(cmdres[1]);
        SyntaxMoudle sm=new SyntaxMoudle();
        int pos=cmdres[0].indexOf(System.lineSeparator());
        String rawdata=cmdres[0].substring(pos);
        List<WeightedAnswerSet> was=sm.parse(rawdata);
        return was;
    }

}
