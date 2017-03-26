package cn.edu.seu.kse.anubis.experiment;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by 王彬 on 2017/3/24.
 */
public class Experiment {
//    protected String basepath="G:/expriment/parallel_reasoning/monty_hall";
    protected String experimentName="monty_hall";
    protected String programPrefix="m-";

    protected String basepath="/home/wangbin/experiments/parallel_reasoning/"+experimentName;
    protected String logfile=basepath+"/log/"+experimentName+"-single";
    protected String threadLogFile=basepath+"/log/"+experimentName+"-thread-log";
    protected String email_addr="wangbiu@foxmail.com";
    protected String testId= UUID.randomUUID().toString();


    public void emailAlert(String title, String text,String address) throws Exception {
        Properties props=new Properties();
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", "smtp.126.com");   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");
        final String smtpPort = "25";
        props.setProperty("mail.smtp.port", smtpPort);
//        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.setProperty("mail.smtp.socketFactory.fallback", "false");
//        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        Session session=Session.getDefaultInstance(props);
        MimeMessage msg=new MimeMessage(session);

        msg.setFrom(new InternetAddress("seukse@126.com","kse lab","UTF-8"));
        msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(address,"王彬", "UTF-8"));
        msg.setSubject(title,"UTF-8");
        msg.setContent(text,"text/html;charset=UTF-8");
        msg.setSentDate(new Date());
        msg.saveChanges();
//        OutputStream out=new FileOutputStream()

        Transport transport=session.getTransport("smtp");
        transport.connect("seukse@126.com","kse123");
        transport.sendMessage(msg,msg.getAllRecipients());
        transport.close();
    }

    public String getBasepath() {
        return basepath;
    }

    public void setBasepath(String basepath) {
        this.basepath = basepath;
    }

    public String getLogfile() {
        return logfile;
    }

    public void setLogfile(String logfile) {
        this.logfile = logfile;
    }

    public String getThreadLogFile() {
        return threadLogFile;
    }

    public void setThreadLogFile(String threadLogFile) {
        this.threadLogFile = threadLogFile;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }


}
