package cn.edu.seu.kse.lpmln.model;

import java.util.HashMap;
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
    protected HashMap<String,Short> headRestrict;
    protected HashMap<String,Short> bodyRestrict;
    protected HashMap<String,Short>[] headRestrictList;
    protected HashMap<String,Short>[] bodyRestrictList;

    public HeuristicAugmentedSubset(LpmlnProgram lpmlnProgram) {
        super(lpmlnProgram);
        headRestrict = new HashMap<>();
        bodyRestrict = new HashMap<>();
        headRestrictList = new HashMap[lpmlnProgram.getRules().size()];
        bodyRestrictList = new HashMap[lpmlnProgram.getRules().size()];
        List<Rule> rules = lpmlnProgram.getRules();
        for (int idx : unknownIdx) {
            Rule r = rules.get(idx);
            HashMap<String,Short> rheadRestrict = new HashMap<>();
            HashMap<String,Short> rbodyRestrict = new HashMap<>();
            r.getHead().forEach(lit->restrict(lit,rheadRestrict));
            r.getNegativeBody().forEach(lit->restrict(lit,rbodyRestrict));
            r.getPositiveBody().forEach(lit->restrict(lit,rbodyRestrict));
            headRestrictList[idx] = rheadRestrict;
            bodyRestrictList[idx] = rbodyRestrict;
        }
    }

    protected void restrict(String lit,HashMap<String,Short> restrict){

    }

    HeuristicAugmentedSubset(){
        super();
        headRestrict = new HashMap<>();
        bodyRestrict = new HashMap<>();
        headRestrictList = new HashMap[lpmlnProgram.getRules().size()];
        bodyRestrictList = new HashMap[lpmlnProgram.getRules().size()];
    }

    @Override
    public HeuristicAugmentedSubset clone(){
        HeuristicAugmentedSubset cloned = new HeuristicAugmentedSubset();
        cloned.setLpmlnProgram(lpmlnProgram);
        cloned.satIdx.addAll(satIdx);
        cloned.unsatIdx.addAll(unsatIdx);
        cloned.unknownIdx.addAll(unknownIdx);
        return cloned;
    }

}
