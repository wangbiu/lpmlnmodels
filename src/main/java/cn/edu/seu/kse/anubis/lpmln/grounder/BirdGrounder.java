package cn.edu.seu.kse.anubis.lpmln.grounder;

/**
 * Created by 王彬 on 2017/3/26.
 */
public class BirdGrounder extends BaseGrounder {
    public BirdGrounder(int birdNums) {
        this.varNumber=birdNums;
    }

    @Override
    public String ground() {
        StringBuilder sb=new StringBuilder();
        sb.append(groundMRBird()).append(System.lineSeparator());
        sb.append(groundBird());
        return sb.toString();
    }

    public String groundBird(){
        StringBuilder rule=new StringBuilder();
        String template="bird({{1}}) :- residentBird({{1}}).";
        String tmprule=groundTemplate(template,1);
        rule.append(tmprule);
        template="bird({{1}}) :- migratoryBird({{1}}).";
        tmprule=groundTemplate(template,1);
        rule.append(tmprule);

        template=" :- migratoryBird({{1}}), residentBird({{1}}).";
        tmprule=groundTemplate(template,1);
        rule.append(tmprule);
        return rule.toString();
    }

    public String groundMRBird(){
        StringBuilder rule=new StringBuilder();
        String template="1 : residentBird({{1}}). \r\n2 : migratoryBird({{1}}).";
        String tmprule=groundTemplate(template,1);
        rule.append(tmprule);
        return rule.toString();
    }

    @Override
    public String groundTemplate(String template, int varNums) {
        return super.groundTemplate(template, varNums);
    }
}
