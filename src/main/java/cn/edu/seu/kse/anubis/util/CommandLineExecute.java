package cn.edu.seu.kse.anubis.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by 王彬 on 2016/1/20.
 */
public class CommandLineExecute {
    final public static int WINDOWS_NT=1;
    final public static int WINDOWS_95=2;
    final public static int UNIX_LIKE=0;


    public static int callShell(String[] command, int osType){
        String[] cmd =new String [3];
        String osName=System.getProperty("os.name");
        System.out.println(osName);
        if(osType==WINDOWS_NT){
            cmd[0]="cmd.exe";
            cmd[1]="/c";
        }
        else if(osType==WINDOWS_95){
            cmd[0]="command.exe";
            cmd[1]="/C";
        }
        else if(osType==UNIX_LIKE){
            cmd[0]="sh";
            cmd[1]="-c";

        }
        //cmd[2]=command;

        try {
            Process p=Runtime.getRuntime().exec(command);

            //标准错误流
            InputStream stderr=p.getErrorStream();
            InputStreamReader isr=new InputStreamReader(stderr);
            BufferedReader br=new BufferedReader(isr);
            String line=null;


            //标准输出
            stderr=p.getInputStream();
            isr=new InputStreamReader(stderr);
            br=new BufferedReader(isr);
            System.out.println("<OUTPUT>");
            //int exitVal = p.waitFor();
            while((line=br.readLine())!=null){
                System.out.print("line: ");
                System.out.println(line);
            }
            System.out.println("</OUTPUT>");

            int exitVal = p.waitFor();
            System.out.println("ProcessExitValue:"+exitVal);
            return exitVal;
        } catch (IOException ex) {
            Logger.getLogger(CommandLineExecute.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (InterruptedException ex) {
            Logger.getLogger(CommandLineExecute.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public static int callShell(String command, int osType){
        String[] cmd =new String [3];
        String osName=System.getProperty("os.name");
        System.out.println(osName);
        if(osType==WINDOWS_NT){
            cmd[0]="cmd.exe";
            cmd[1]="/c";
        }
        else if(osType==WINDOWS_95){
            cmd[0]="command.exe";
            cmd[1]="/C";
        }
        else if(osType==UNIX_LIKE){
            cmd[0]="sh";
            cmd[1]="-c";

        }
        cmd[2]=command;
        System.out.println("cmd: "+command);

        try {
            Process p=Runtime.getRuntime().exec(cmd);

            //标准错误流
            final InputStream stderr=p.getErrorStream();

            String line=null;


            //单独的读取错误流的进程
            new Thread(
                    new Runnable(){
                        public void run(){
                            try {
                                BufferedReader br=new BufferedReader(new InputStreamReader(stderr));
                                String eline=null;
                                Date now=new Date();
                                String nowdate=now.toString();
                                System.out.println("<ERROR "+nowdate+">");
                                while((eline=br.readLine()) != null){
                                    System.out.println(eline);
                                }
                                System.out.println("</ERROR>");
                            } catch (IOException ex) {
                                Logger.getLogger(CommandLineExecute.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
            ).start();


            //标准输出
            InputStream stdout=p.getInputStream();
            InputStreamReader isr=new InputStreamReader(stdout);
            BufferedReader br=new BufferedReader(isr);
            Date now=new Date();
            String nowdate=now.toString();


            System.out.println("<OUTPUT "+nowdate+">");
            //int exitVal = p.waitFor();
            while((line=br.readLine())!=null){
                System.out.print("line: ");
                System.out.println(line);
            }
            System.out.println("</OUTPUT>");

            int exitVal = p.waitFor();
            System.out.println("ProcessExitValue:"+exitVal);
            return exitVal;
        } catch (IOException ex) {
            Logger.getLogger(CommandLineExecute.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (InterruptedException ex) {
            Logger.getLogger(CommandLineExecute.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public static String[] callShellwithReturn(String command, int osType){
        String[] cmd =new String [3];

        final String[] results=new String[2];
        if(osType==WINDOWS_NT){
            cmd[0]="cmd.exe";
            cmd[1]="/c";
        }
        else if(osType==WINDOWS_95){
            cmd[0]="command.exe";
            cmd[1]="/C";
        }
        else if(osType==UNIX_LIKE){
            cmd[0]="sh";
            cmd[1]="-c";

        }
        cmd[2]=command;


        BufferedReader br=null;

        try {
            Process p=Runtime.getRuntime().exec(cmd);

            //标准错误流
            final InputStream stderr=p.getErrorStream();

            String line="";


            //单独的读取错误流的进程
            new Thread(
                    new Runnable(){
                        public void run(){
                            BufferedReader br=null;
                            try {

                                String errres="";
                                br=new BufferedReader(new InputStreamReader(stderr));
                                String eline=null;
                                while((eline=br.readLine()) != null){
                                    errres+=eline;
                                }

                                results[1]=errres;

                            } catch (IOException ex) {

                            }
                            finally {
                                if (br!=null)
                                    try {
                                        br.close();
                                    } catch (IOException e) {

                                    }
                            }
                        }
                    }
            ).start();

            //标准输出
            br=new BufferedReader(new InputStreamReader(p.getInputStream()));

            String res="";


            while((line=br.readLine())!=null){
                res+=line;
                res+=System.lineSeparator();
            }


            int exitVal = p.waitFor();
            results[0]=res;

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        finally {
            if (br!=null)
                try {
                    br.close();
                } catch (IOException e) {

                }
        }
        return results;
    }
}
