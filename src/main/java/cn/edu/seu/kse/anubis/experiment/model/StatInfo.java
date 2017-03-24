package cn.edu.seu.kse.anubis.experiment.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 王彬 on 2017/3/24.
 */
public class StatInfo {
    public Date now;
    protected int precise=4;

    public String formatDate(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public String formatDouble(double num, int precise){
        DecimalFormat df= (DecimalFormat) NumberFormat.getInstance();
        df.setMaximumFractionDigits(precise);
        return df.format(num);
    }

    public static String getTitle(){
        return null;
    }

    public String toCSVString(){return null;}
}
