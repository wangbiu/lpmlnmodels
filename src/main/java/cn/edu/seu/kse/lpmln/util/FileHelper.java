package cn.edu.seu.kse.lpmln.util;

import java.io.*;

/**
 * @author 许鸿翔
 * @date 2018/1/15
 */
public class FileHelper {
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
