package cn.edu.seu.kse.lpmln.grounder;

/**
 * Created by 王彬 on 2017/3/26.
 */
public class FriendInfluenceGrounder extends BaseGrounder {

    public FriendInfluenceGrounder(int friNums){
        this.varNumber=friNums;
    }

    @Override
    public String ground() {
        StringBuilder sb=new StringBuilder();
        sb.append(groundFriend()).append(System.lineSeparator());
        sb.append(groundInfluenceFriend()).append(System.lineSeparator());
        sb.append(groundInfluenceTransity()).append(System.lineSeparator());
        return sb.toString();
    }


    public String groundFriend(){
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<varNumber;i++){
            sb.append("friend(").append(i).append(",").append(i+1).append(").");
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String groundInfluenceFriend(){
        String temp="influence({{1}},{{2}}) :- friend({{1}},{{2}}).";
        return groundTemplate(temp,2);
    }

    public String groundInfluenceTransity(){
        String temp="1 : influence({{1}},{{2}}) :- influence({{1}},{{3}}), influence({{3}},{{2}}).";
        return groundTemplate(temp,3);
    }



    @Override
    public String groundTemplate(String template, int varNums) {
        return super.groundTemplate(template, varNums);
    }
}
