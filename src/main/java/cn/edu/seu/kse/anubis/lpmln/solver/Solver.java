package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.util.CommandLineExecute;
import net.sf.json.JSONObject;

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

    public static String callDLV(String rulefile){
        StringBuilder cmd=new StringBuilder();
        cmd.append("dlv -costbound=_,_  -stats -v ").append(rulefile);
        String[] cmdres=CommandLineExecute.callShellwithReturn(cmd.toString(),1);
        System.out.println(cmdres[1]);
        return cmdres[0];
    }

}
