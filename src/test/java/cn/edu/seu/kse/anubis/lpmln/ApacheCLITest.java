package cn.edu.seu.kse.anubis.lpmln;

import cn.edu.seu.kse.anubis.lpmln.app.LPMLNOpts;
import org.apache.commons.cli.*;
import org.junit.Test;

/**
 * Created by 王彬 on 2016/10/14.
 */
public class ApacheCLITest {

    @Test
    public void testOption() throws ParseException {
        Options opts= LPMLNOpts.getCommandLineOptions();
        String[] args={"--help","-p", "-m","-a","--asp-solver","clingo","-s","strong","--input-file","lpmln.lp"};

        CommandLineParser cmdParser=new DefaultParser();
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

        System.out.println(cmd.getArgList());


    }
}
