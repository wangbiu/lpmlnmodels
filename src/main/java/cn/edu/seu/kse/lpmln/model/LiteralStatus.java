package cn.edu.seu.kse.lpmln.model;

import java.util.LinkedList;

/**
 * @author 许鸿翔
 * @date 2018/4/17
 */
public class LiteralStatus {
    protected static final int TRUE=7;
    public String literal;
    /**
     * 共三位，4表示not，2表示+，1表示-
     * 1表示可行，0表示不可行
     * 010表示原子a只支持a
     * 101表示原子可以是not a或者-a
     */
    public int status;
    public LiteralStatus(String literal,int status){
        this.literal = literal;
        this.status = status;
    }

    public LiteralStatus(String literal){
        String realLit = literal.trim();
        //0:弱否定，1：强否定
        LinkedList<Integer> neg = new LinkedList<>();
        int status = 2;
        //not* -? lit
        while(realLit.startsWith("not")||realLit.startsWith("-")){
            if(realLit.startsWith("not")){
                neg.push(0);
                realLit = realLit.substring(3).trim();
            }else if(realLit.startsWith("-")){
                neg.push(1);
                realLit = realLit.substring(1).trim();
            }
        }
        while(neg.size()>0){
            int next = neg.pop();
            if(next==0){
                status = (TRUE^status);
            }else{
                status=1;
            }
        }
        this.status = status;
        this.literal = realLit;
    }
}
