package cn.edu.seu.kse.lpmln.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/3/31
 */
public class HeuristicAugmentedSubset extends AugmentedSubset {
    /**
     * 对于原子的限制，描述于规则和AS上
     * 共三位，4表示not，2表示+，1表示-
     * 1表示可行，0表示不可行
     * 010表示原子a只支持a
     * 101表示原子可以是not a或者-a
     */
    protected HashMap<String,Integer> headRestrict;
    protected HashMap<String,Integer> bodyRestrict;
    protected HashMap<String,Integer>[] headRestrictList;
    protected HashMap<String,Integer>[] bodyRestrictList;
    /**
     * lit所有状态都可行
     */
    protected static final int TRUE=7;

    public HeuristicAugmentedSubset(LpmlnProgram lpmlnProgram) {
        super(lpmlnProgram);
        headRestrict = new HashMap<>();
        bodyRestrict = new HashMap<>();
        headRestrictList = new HashMap[lpmlnProgram.getRules().size()];
        bodyRestrictList = new HashMap[lpmlnProgram.getRules().size()];
        List<Rule> rules = lpmlnProgram.getRules();
        for (int idx : unknownIdx) {
            Rule r = rules.get(idx);
            HashMap<String,Integer> rheadRestrict = new HashMap<>();
            HashMap<String,Integer> rbodyRestrict = new HashMap<>();
            r.getHead().forEach(lit->restrict(lit,rheadRestrict,true));
            r.getNegativeBody().forEach(lit->restrict(lit,rbodyRestrict,false));
            r.getPositiveBody().forEach(lit->restrict(lit,rbodyRestrict,false));
            headRestrictList[idx] = rheadRestrict;
            bodyRestrictList[idx] = rbodyRestrict;
        }
    }

    /**
     * 生成对于文字的要求
     * @param lit   not* -? lit,比如not -a
     * @param restrict  当前要求的map
     * @param type  true：头部合取，false：体部析取
     */
    protected void restrict(String lit,HashMap<String,Integer> restrict,boolean type){
        String realLit = lit.trim();
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
        if(restrict.containsKey(realLit)){
            if(type){
                restrict.put(realLit,status|restrict.get(realLit));
            }else{
                restrict.put(realLit,status&restrict.get(realLit));
            }
        }else{
            restrict.put(realLit,status);
        }

    }

    HeuristicAugmentedSubset(){
        super();
        headRestrict = new HashMap<>();
        bodyRestrict = new HashMap<>();
    }

    @Override
    public HeuristicAugmentedSubset clone(){
        HeuristicAugmentedSubset cloned = new HeuristicAugmentedSubset();
        cloned.setLpmlnProgram(lpmlnProgram);
        cloned.satIdx.addAll(satIdx);
        cloned.unsatIdx.addAll(unsatIdx);
        cloned.unknownIdx.addAll(unknownIdx);
        cloned.headRestrictList = headRestrictList;
        cloned.bodyRestrictList = bodyRestrictList;
        cloned.headRestrict = new HashMap<>(headRestrict);
        cloned.bodyRestrict = new HashMap<>(bodyRestrict);
        return cloned;
    }

}
