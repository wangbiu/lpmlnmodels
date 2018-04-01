package cn.edu.seu.kse.lpmln.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    protected HashMap<String,Integer> atomRestrict;
    protected HashMap<String,Integer>[] headRestrictList;
    protected HashMap<String,Integer>[] bodyRestrictList;
    protected HashMap<String,Integer>[] unsatRestrictList;
    /**
     * lit所有状态都可行
     */
    protected static final int TRUE=7;

    public HeuristicAugmentedSubset(LpmlnProgram lpmlnProgram) {
        super(lpmlnProgram);
        atomRestrict = new HashMap<>();
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
            unsatRestrictList[idx] = combineRestrict(rheadRestrict,rbodyRestrict);
        }
    }

    private HashMap<String,Integer> combineRestrict(HashMap<String,Integer> rheadRestrict,HashMap<String,Integer> rbodyRestrict){
        HashMap<String,Integer> restrict = new HashMap<>(rbodyRestrict);
        rheadRestrict.forEach((key,value)->{
            if(restrict.containsKey(key)){
                restrict.put(key,(TRUE-value)&restrict.get(key));
            }else{
                restrict.put(key,TRUE-value);
            }
        });
        return restrict;
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
        atomRestrict = new HashMap<>();
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
        cloned.atomRestrict = new HashMap<>(atomRestrict);
        return cloned;
    }

    /**
     * 根据单原子事实或者约束对原子进行约束
     * @param idx   规则下标
     * @return sat结果
     */
    @Override
    public boolean sat(int idx){
        if(headRestrictList[idx].size()+bodyRestrictList[idx].size()==1){
            if(headRestrictList[idx].size()==1){
                Map.Entry<String,Integer> ent = headRestrictList[idx].entrySet().iterator().next();
                if(atomRestrict.containsKey(ent.getKey())){
                    atomRestrict.put(ent.getKey(),atomRestrict.get(ent.getKey())&(TRUE-ent.getValue()));
                }else{
                    atomRestrict.put(ent.getKey(),ent.getValue());
                }
            }else{
                Map.Entry<String,Integer> ent = bodyRestrictList[idx].entrySet().iterator().next();
                if(atomRestrict.containsKey(ent.getKey())){
                    atomRestrict.put(ent.getKey(),atomRestrict.get(ent.getKey())&ent.getValue());
                }else{
                    atomRestrict.put(ent.getKey(),ent.getValue());
                }
            }
        }
        return super.sat(idx);
    }

    /**
     * 根据规则unsat对原子进行约束，body and ！head
     * @param idx   规则下标
     * @return sat结果
     */
    @Override
    public boolean unsat(int idx){
        HashMap<String,Integer> restrict = unsatRestrictList[idx];
        for (Map.Entry<String,Integer> ent : restrict.entrySet()) {
            if(atomRestrict.containsKey(ent.getKey())){
                atomRestrict.put(ent.getKey(),atomRestrict.get(ent.getKey())&ent.getValue());
            }else{
                atomRestrict.put(ent.getKey(),ent.getValue());
            }
        }
        return super.unsat(idx);
    }

    /**
     * 判断规则是否可以枚举
     * * @param idx   规则下标
     * @return  true：可 false：不可
     */
    public boolean enumerable(int idx){
        //是否肯定无法满足:放到unsat之后没影响
        //是否肯定满足:放到unsat之后有谓词为0
        boolean change = false;
        HashMap<String,Integer> restrict = unsatRestrictList[idx];
        for (Map.Entry<String,Integer> ent : restrict.entrySet()) {
            if(atomRestrict.containsKey(ent.getKey())){
                if((atomRestrict.get(ent.getKey())&ent.getValue())==0){
                    return false;
                }else if((atomRestrict.get(ent.getKey())&ent.getValue())==atomRestrict.get(ent.getKey())){
                    change = true;
                }
            }else{
                if(ent.getValue()==0){
                    return false;
                }
                change = true;
            }
        }
        return change;
    }

}
