package cn.edu.seu.kse.lpmln.util.commandline;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author 许鸿翔
 * @date 2018/5/22
 */
public class CommonCmd extends BaseCommandLine{
    private StringBuilder output;

    @Override
    protected void stopResultProcess(){
        threadPool.waitDone();
    }

    @Override
    protected void startResultProcess(BufferedReader br) throws IOException, InterruptedException {
        output = new StringBuilder();
        String line;
        while((line=br.readLine())!=null){
            output.append(line).append("\r\n");
        }
    }

    public StringBuilder getOutput() {
        return output;
    }

    public void setOutput(StringBuilder output) {
        this.output = output;
    }
}
