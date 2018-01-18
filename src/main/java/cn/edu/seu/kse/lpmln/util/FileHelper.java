package cn.edu.seu.kse.lpmln.util;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 许鸿翔
 * @date 2018/1/15
 */
public class FileHelper {
    public static List<File> tempFiles = new ArrayList<>();

    public static void cleanFiles(){
        if(LPMLNApp.iskeeptranslation){
            tempFiles.forEach(file -> {
                if(file.getName().endsWith(".tmp")){
                    file.delete();
                }
            });
        }else{
            tempFiles.forEach(File::delete);
        }

    }

    public static File randomFile(){
        return new File(UUID.randomUUID()+".tmp");
    }

    /**
     * 以.tmp后缀的文件会在cleanFiles被清空，iskeeptranslation可以保留非此后缀的文件
     * @param file 要写的文件
     * @param out 要写的内容
     */
    public static void writeFile(File file, String out){
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"))){
            tempFiles.add(file);
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
