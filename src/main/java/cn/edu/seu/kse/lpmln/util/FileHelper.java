package cn.edu.seu.kse.lpmln.util;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 许鸿翔
 * @date 2018/1/15
 */
public class FileHelper {
    public static List<File> tempFiles = new CopyOnWriteArrayList<>();

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

    /**
     * 获取文件内容，注意获取后的文件行尾符固定为\r\n
     * @param file 要获取内容的文件
     * @return 文件内容
     */
    public static String getFileContent(File file){
        StringBuilder result = new StringBuilder();
        String line;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))) {
            while ((line = br.readLine())!=null){
                result.append(line).append("\r\n");
            }
        }catch (IOException e) {
            e.printStackTrace();

        }
        return result.toString();
    }
}
