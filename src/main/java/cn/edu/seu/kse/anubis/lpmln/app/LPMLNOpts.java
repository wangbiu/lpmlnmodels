package cn.edu.seu.kse.anubis.lpmln.app;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.HashMap;

/**
 * Created by 王彬 on 2016/10/14.
 */
public class LPMLNOpts {
    public static Options getCommandLineOptions(){
        //帮助选项
        Option help=new Option("h", "help", false, "optional, print help");
        help.setRequired(false);

        //边缘概率推理
        Option margianl =new Option("p","marginal-probability-reasoning",false,"optional, compute marginal probability distribution");
        margianl.setRequired(false);

        //最大可能稳定模型推理
        Option max=new Option("m", "maximal-weight-stable-models",false,"optional, compute maximal weighted stable models");
        max.setRequired(false);

        //输出全部概率不为0的可能世界
        Option outputall=new Option("a", "show-all-stable-models",false,"optional, output all stable models those satisfy most hard rules");
        outputall.setRequired(false);

        //选择使用的推理机 DLV 或 Clingo
        Option reasoner=new Option("r", "asp-solver",true,"required, specify used ASP solver, dlv and clingo are available");
        reasoner.setValueSeparator(' ');
        reasoner.setRequired(false);
        reasoner.setOptionalArg(false);
        reasoner.setArgName("reasonername");

        //指定输入的LPMLN文件
        Option inputfile=new Option("i", "input-file",true, "required, specify input lpmln file");
        inputfile.setRequired(false);
        inputfile.setValueSeparator(' ');
        inputfile.setOptionalArg(false);
        inputfile.setArgName("inputfile");

        //指定转化输出的文件
        Option transoutfile=new Option("o", "translation-output-file", true,"optional, specify output file of translation");
        transoutfile.setRequired(false);
        transoutfile.setArgName("translation-output-file");
        transoutfile.setValueSeparator(' ');
        transoutfile.setOptionalArg(false);

        //指定LPMLN推理语义 strong weak
        Option semantics=new Option("s", "lpmln-semantics", true, "required, specify used lpmln semantics, strong and weak are available");
        semantics.setRequired(false);
        semantics.setValueSeparator(' ');
        semantics.setArgName("semantics");
        semantics.setOptionalArg(false);


        Options opts=new Options();
        opts.addOption(help);
        opts.addOption(margianl);
        opts.addOption(max);
        opts.addOption(outputall);
        opts.addOption(reasoner);
        opts.addOption(inputfile);
        opts.addOption(semantics);
        opts.addOption(transoutfile);

        return opts;
    }

    public static HashMap<String,String> getRuntimeOptions(){
        HashMap<String,String> opts=new HashMap<>();


        return opts;
    }
}
