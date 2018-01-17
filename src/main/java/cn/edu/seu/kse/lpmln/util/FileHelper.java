package cn.edu.seu.kse.lpmln.util;

import java.io.*;
import java.util.UUID;

/**
 * @author 许鸿翔
 * @date 2018/1/15
 */
public class FileHelper {
    public static File randomFile(){
        //TODO:处理一下临时文件
        return new File(UUID.randomUUID()+".tmp");
    }

    public static void writeFile(File file, String out){
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"))){
            bw.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileContent(File file){
        StringBuilder result = new StringBuilder();
        String line;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))) {
            while ((line = br.readLine())!=null){
                result.append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();

        }
        return result.toString();
    }
}
