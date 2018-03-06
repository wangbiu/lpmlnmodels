package cn.edu.seu.kse.lpmln.experiment.util;

import cn.edu.seu.kse.lpmln.model.ExperimentReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 许鸿翔
 * @date 2018/3/5
 */
public class ExperimentReporter {
    public static final String REPORT_PATH = "/lpmlnmodels/experiment/report/";
    private static Logger logger= LogManager.getLogger(ExperimentReporter.class.getName());

    public static void report(ExperimentReport report){
        String filename = "report_"
                +new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())
                +".txt";
        File reportFile = new File(REPORT_PATH+filename);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(reportFile))) {
            StringBuilder sb = new StringBuilder();
            sb.append("experiment name: ").append(report.getExperimentName()).append(System.lineSeparator());
            sb.append("status: ").append(report.getStatus()).append(System.lineSeparator());
            if (report.getSolvers().size()!=report.getTime().size()){
                logger.error("Report solver and time mismatch.");
                return;
            }

            sb.append(System.lineSeparator());

            for (int i=0;i<report.getSolvers().size();i++){
                sb.append(report.getSolvers().get(i).getClass().getSimpleName()).append("\t")
                        .append(report.getTime().get(i)).append(System.lineSeparator());
            }
            sb.append(System.lineSeparator());
            sb.append(report.getSolvers().get(0).getMarginalDistribution()).append(System.lineSeparator());
            bw.write(sb.toString());
        } catch (IOException e) {
            logger.error("Fail to generate experiment report.");
            e.printStackTrace();
        }
    }
}