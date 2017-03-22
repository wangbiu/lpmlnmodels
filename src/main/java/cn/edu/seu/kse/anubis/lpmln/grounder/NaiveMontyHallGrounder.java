package cn.edu.seu.kse.anubis.lpmln.grounder;

import cn.edu.seu.kse.anubis.lpmln.model.Rule;
import cn.edu.seu.kse.anubis.lpmln.syntax.SyntaxModule;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/22.
 */
public class NaiveMontyHallGrounder {
    private int boxNumber=0;

    public NaiveMontyHallGrounder(int boxNumber) {
        this.boxNumber = boxNumber;
    }

    public String ground(){
        StringBuilder rule=new StringBuilder();
        rule.append(groundHasKeyRule()).append(System.lineSeparator());
        rule.append(groundSelectRule()).append(System.lineSeparator());
        rule.append(groundOpenRule()).append(System.lineSeparator());
        rule.append(groundSwitchRule()).append(System.lineSeparator());
        rule.append(groundWinRule()).append(System.lineSeparator());
        rule.append(groundSwitchRestriction()).append(System.lineSeparator());
        rule.append(groundRestrictionRule()).append(System.lineSeparator());
        rule.append(groundBoxRule()).append(System.lineSeparator());
        return rule.toString();
    }

    public String groundSelectRule(){
        String template="1 : select({{1}}) :- box({{1}}).";
        return groundTemplate(template,1);
    }

    public String groundHasKeyRule(){
        String template="1 : has_key({{1}}) :- box({{1}}).";
        return groundTemplate(template,1);
    }

    public String groundOpenRule(){
        StringBuilder rule=new StringBuilder();
        String template="cannot_open({{1}}) :- select({{1}}).";
        String tmprule=groundTemplate(template,1);
        rule.append(tmprule);
        template="cannot_open({{1}}) :- has_key({{1}}).";
        tmprule=groundTemplate(template,1);
        rule.append(tmprule);
        template="1 : open({{1}}) :- box({{1}}), not cannot_open({{1}}).";
        tmprule=groundTemplate(template,1);
        rule.append(tmprule);
        return rule.toString();
    }

    public String groundSwitchRule(){
        StringBuilder rule=new StringBuilder();
        String template="1 : can_switch({{1}}) :- box({{1}}), not select({{1}}), not open({{1}}).";
        String tmprule=groundTemplate(template,1);
        rule.append(tmprule);
        template="1 : switch({{1}}) :- can_switch({{1}}).";
        tmprule=groundTemplate(template,1);
        rule.append(tmprule);
        return rule.toString();
    }

    public String groundSwitchRestriction(){
        StringBuilder rule=new StringBuilder();
        String template="-switch({{1}}) :- switch({{2}}), can_switch({{1}}), {{1}} != {{2}}.";
        String tmprule=groundTemplate(template,2);
        rule.append(tmprule);
        template=":- can_switch({{1}}), not switch({{1}}), not -switch({{1}}).";
        tmprule=groundTemplate(template,1);
        rule.append(tmprule);
        return rule.toString();
    }

    public String groundWinRule(){
        StringBuilder rule=new StringBuilder();
        String template="win_stay :- select({{1}}), has_key({{1}}).";
        String tmprule=groundTemplate(template,1);
        rule.append(tmprule);
        template="win_switch :- switch({{1}}), has_key({{1}}).";
        tmprule=groundTemplate(template,1);
        rule.append(tmprule);
        return rule.toString();
    }

    public String groundRestrictionRule(){
        StringBuilder rule=new StringBuilder();
        String template="-select({{1}}) :- select({{2}}), box({{1}}), {{1}} != {{2}}.";
        String tmprule=groundTemplate(template,2);
        rule.append(tmprule);
        template=":- box({{1}}), not select({{1}}), not -select({{1}}).";
        tmprule=groundTemplate(template,1);
        rule.append(tmprule);

        template="-open({{1}}) :- open({{2}}), box({{1}}), {{1}} != {{2}}.";
        tmprule=groundTemplate(template,2);
        rule.append(tmprule);
        template=":- box({{1}}), not open({{1}}), not -open({{1}}).";
        tmprule=groundTemplate(template,1);
        rule.append(tmprule);

        template="-has_key({{1}}) :- has_key({{2}}), box({{1}}), {{1}} != {{2}}.";
        tmprule=groundTemplate(template,2);
        rule.append(tmprule);
        template=":- box({{1}}), not has_key({{1}}), not -has_key({{1}}).";
        tmprule=groundTemplate(template,1);
        rule.append(tmprule);
        return rule.toString();
    }

    public String groundBoxRule(){
        String template="box({{1}}).";
        return groundTemplate(template,1);
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
            if(vars[i] <= boxNumber){
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

    public int getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }
}
