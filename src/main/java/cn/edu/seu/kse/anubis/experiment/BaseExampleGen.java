package cn.edu.seu.kse.anubis.experiment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class BaseExampleGen {
    protected String basepath="G:\\my_thesis\\asp_lpmln\\experiment\\";
    protected String originpart="";
    protected String examplename="";
    protected HashMap<String,Integer> factsort;
    protected String alphabet="abcdefghijklmnopqrstuvwxyz";

    public void generate(int factnums, String type) throws IOException {
        StringBuilder sb=new StringBuilder();
        File outf=new File(basepath+type+"\\"+examplename+"-"+factnums+".txt");
        BufferedWriter bw=new BufferedWriter(new FileWriter(outf));
        bw.write(originpart);
        bw.write(System.lineSeparator());

        addFact(bw,factnums);

        bw.close();
    }

    public void addFact(BufferedWriter bw, int factnums) throws IOException {
        if(factsort != null){
            for(HashMap.Entry<String,Integer> entry : factsort.entrySet()){
                genFact4Sort(entry.getKey(),entry.getValue(),factnums,bw);
            }
        }
    }

    public void genFact4Sort(String factname, int arity, int factnums, BufferedWriter bw) throws IOException {
        int base='a';
        for(int i=0;i<factnums;i++){
            bw.write(factname);
            bw.write("(");

            for(int j=0;j<arity;j++){
                System.out.println(alphabet.charAt(j));
                bw.write(""+alphabet.charAt(j));
                bw.write(""+i);
                if(j!=arity-1){
                    bw.write(",");
                }
            }
            bw.write(").");
            bw.write(System.lineSeparator());
        }

    }
}
