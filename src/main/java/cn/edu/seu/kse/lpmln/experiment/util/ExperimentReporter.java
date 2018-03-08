package cn.edu.seu.kse.lpmln.experiment.util;

import cn.edu.seu.kse.lpmln.model.ExperimentReport;
import cn.edu.seu.kse.lpmln.util.FileHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * @author 许鸿翔
 * @date 2018/3/5
 */
public class ExperimentReporter {
    public static final String REPORT_PATH = "/lpmlnmodels/experiment/report/";
    private static Logger logger= LogManager.getLogger(ExperimentReporter.class.getName());

    public static void report(ExperimentReport report, String reportFileName){
        FileHelper.writeFile(new File(reportFileName),report.tojson());
    }

//    public static void report(ExperimentReport report,String reportFileName){
//        String filename = "report("+report.getExperimentName()+")"
//                +new SimpleDateFormat("_yyyy-MM-dd-HH-mm-ss").format(new Date())
//                +".txt";
//        File reportFile = new File(REPORT_PATH+filename);
//        try(BufferedWriter bw = new BufferedWriter(new FileWriter(reportFile))) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("experiment name: ").append(report.getExperimentName()).append(System.lineSeparator());
//            sb.append("status: ").append(report.getStatus()).append(System.lineSeparator());
//            if (report.getSolvers().size()!=report.getTime().size()){
//                logger.error("Report solver and time mismatch.");
//                return;
//            }
//
//            sb.append(System.lineSeparator());
//
//            for (int i=0;i<report.getSolvers().size();i++){
//                sb.append(report.getSolvers().get(i).getClass().getSimpleName()).append("\t")
//                        .append(report.getTime().get(i)).append(System.lineSeparator());
//            }
//            sb.append(System.lineSeparator());
//            sb.append(report.getSolvers().get(0).getMarginalDistribution()).append(System.lineSeparator());
//            sb.append(System.lineSeparator());
//
//            for (int i=0;i<report.getSolvers().size();i++){
//                sb.append(report.getSolvers().get(i).getClass().getSimpleName()).append(":").append(System.lineSeparator())
//                        .append(report.getSolvers().get(i).getExperimentInfo()).append(System.lineSeparator()).append(System.lineSeparator());
//            }
//
//            bw.write(sb.toString());
//        } catch (IOException e) {
//            logger.error("Fail to generate experiment report.");
//            e.printStackTrace();
//        }
//    }
}
