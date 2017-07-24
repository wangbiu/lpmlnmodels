package cn.edu.seu.kse.lpmln.grounder;

import java.util.Arrays;

/**
 * Created by 王彬 on 2017/3/26.
 */
public class BaseGrounder {
    protected int varNumber =0;

    public String ground(){
        return null;
    }

    /**
     *
     * @param template  {{1}} 占位符
     * @param varNums
     * @return
     */
    public String groundTemplate(String template, int varNums){
        StringBuilder rule=new StringBuilder();
        int[] vars=new int[varNums];
        Arrays.fill(vars,1);
        boolean isStop=false;
        String tmp=null;
        boolean isFirst=true;

        while (!isStop){
            tmp=template;
            for(int i=1;i<=varNums;i++){
                tmp=tmp.replaceAll("\\{\\{"+i+"\\}\\}",""+vars[i-1]);
            }
            rule.append(tmp).append(System.lineSeparator());
            isStop=move2Next(vars,isFirst);
            if(isFirst){
                isFirst=false;
            }
        }


        return rule.toString();
    }

    public boolean move2Next(int[] vars,boolean isFirst){
        boolean isStop=true;
        int len=vars.length;
        for(int i=0;i<len;i++){
            vars[i]+=1;
            if(vars[i] <= varNumber){
                break;
            }else {
                vars[i] = 1;
            }
        }

        if(!isFirst){
            for(int v:vars){
                if(v!=1){
                    isStop=false;
                }
            }
        }else {
            isStop=false;
        }
        return isStop;
    }

    public int getVarNumber() {
        return varNumber;
    }

    public void setVarNumber(int varNumber) {
        this.varNumber = varNumber;
    }

}
