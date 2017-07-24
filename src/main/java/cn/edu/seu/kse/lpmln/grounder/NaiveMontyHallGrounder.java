package cn.edu.seu.kse.lpmln.grounder;

/**
 * Created by 王彬 on 2017/3/22.
 */
public class NaiveMontyHallGrounder extends BaseGrounder {


    public NaiveMontyHallGrounder(int boxNumber) {
        this.varNumber = boxNumber;
    }

    @Override
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
        String template="can_switch({{1}}) :- box({{1}}), not select({{1}}), not open({{1}}).";
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



}
