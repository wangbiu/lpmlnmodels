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

        //使用转换的文件直接推理
        Option transinfile=new Option("I", "translation-input-file", true, "optinal, use a translated rulefile to reason");
        transinfile.setRequired(false);
        transinfile.setArgName("translation-input-file");
        transinfile.setValueSeparator(' ');
        transinfile.setOptionalArg(false);

        //是否进行实验
        Option experiment=new Option("e","experiment", false,"optinal, carry out experiment");
        experiment.setRequired(false);

//        private int round=1;
        Option exp_round=new Option("er","exp-round",true,"optinal, round");
        exp_round.setRequired(false);
        exp_round.setArgName("exp-round");
        exp_round.setValueSeparator(' ');
        exp_round.setOptionalArg(false);

//        private int cores=1;
        Option exp_cores=new Option("ec","exp-cores",true,"cores");
        exp_cores.setRequired(false);
        exp_cores.setArgName("exp-cores");
        exp_cores.setValueSeparator(' ');
        exp_cores.setOptionalArg(false);

//        private int maxCores=16;
        Option exp_maxCores=new Option("emc","exp-max-cores",true,"max cores");
        exp_maxCores.setRequired(false);
        exp_maxCores.setArgName("exp-max-cores");
        exp_maxCores.setValueSeparator(' ');
        exp_maxCores.setOptionalArg(false);

//        private int problemN=3;
        Option exp_problemN=new Option("epn","exp-problem-n",true,"problem N");
        exp_problemN.setRequired(false);
        exp_problemN.setArgName("exp-problem-n");
        exp_problemN.setValueSeparator(' ');
        exp_problemN.setOptionalArg(false);

//        private int maxProblemN=3;
        Option exp_maxPN=new Option("empn","exp-max-problem-n",true,"max problem N");
        exp_maxPN.setRequired(false);
        exp_maxPN.setArgName("exp-max-problem-n");
        exp_maxPN.setValueSeparator(' ');
        exp_maxPN.setOptionalArg(false);


        Option exp_taskId=new Option("et","exp-task-id",true,"taskId: 0 = MAP, 1=MPD, 2 = ALL");
        exp_taskId.setRequired(false);
        exp_taskId.setArgName("exp-task-id");
        exp_taskId.setValueSeparator(' ');
        exp_taskId.setOptionalArg(false);


        Option exp_parallel=new Option("epa","parallel",false,"parallel");
        exp_parallel.setRequired(false);



        Options opts=new Options();
        opts.addOption(help);
        opts.addOption(margianl);
        opts.addOption(max);
        opts.addOption(outputall);
        opts.addOption(reasoner);
        opts.addOption(inputfile);
        opts.addOption(semantics);
        opts.addOption(transoutfile);
        opts.addOption(transinfile);

        opts.addOption(experiment);
        opts.addOption(exp_cores);
        opts.addOption(exp_round);
        opts.addOption(exp_maxCores);
        opts.addOption(exp_problemN);
        opts.addOption(exp_maxPN);
        opts.addOption(exp_taskId);
        opts.addOption(exp_parallel);

        return opts;
    }

    public static HashMap<String,String> getRuntimeOptions(){
        HashMap<String,String> opts=new HashMap<>();


        return opts;
    }
}
