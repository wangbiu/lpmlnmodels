package cn.edu.seu.kse.anubis.lpmln;

import cn.edu.seu.kse.anubis.lpmln.app.LPMLNOpts;
import org.apache.commons.cli.*;

/**
 * Created by 王彬 on 2016/10/14.
 */
public class LPMLNApp {
    public static void main(String args[]){
        Options opts= LPMLNOpts.getOptions();
        CommandLineParser cmdParser=new DefaultParser();
        try {
            CommandLine cmd=cmdParser.parse(opts,args);
            if(cmd.hasOption("help")){
                HelpFormatter formatter=new HelpFormatter();
                formatter.printHelp("lpmlnmodels <params>",opts);
            }

            if(cmd.hasOption("marginal-probability-reasoning")){
                System.out.println("marginal-probability-reasoning");
            }

            if(cmd.hasOption("maximal-weight-stable-models")){
                System.out.println("maximal-weight-stable-models");
            }

            if(cmd.hasOption("show-all-stable-models")){
                System.out.println("show-all-stable-models");
            }

            if(cmd.hasOption("asp-solver")){
                System.out.println("asp-solver " + cmd.getOptionValue("asp-solver"));
            }

            if(cmd.hasOption("lpmln-semantics")){
                System.out.println("lpmln-semantics "+cmd.getOptionValue("lpmln-semantics"));
            }

            if(cmd.hasOption("input-file")){
                System.out.println("input-file "+cmd.getOptionValue("input-file"));
            }

        } catch (ParseException e) {
            System.err.println("wrong parameter");
        }
    }
}
