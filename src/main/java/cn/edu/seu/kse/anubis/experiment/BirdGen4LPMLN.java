package cn.edu.seu.kse.anubis.experiment;

import java.util.HashMap;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class BirdGen4LPMLN extends BaseExampleGen{

    public BirdGen4LPMLN(){
        originpart="bird(X) :- residentBird(X).\n" +
                "bird(X) :- migratoryBird(X).\n" +
                ":- residentBird(X), migratoryBird(X).\n" +
                "2:residentBird(jo).\n" +
                "1:migratoryBird(jo)." +
                "\n";
        examplename="bird";
        factsort=new HashMap<>();
        factsort.put("residentBird",1);
        factsort.put("residentBird",1);
    }
}
