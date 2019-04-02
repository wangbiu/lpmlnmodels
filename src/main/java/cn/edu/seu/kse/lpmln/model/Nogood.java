package cn.edu.seu.kse.lpmln.model;

import cn.edu.seu.kse.lpmln.exception.solveexception.AssignConflictException;

import java.util.*;

import static cn.edu.seu.kse.lpmln.util.CommonStrings.EXT_FALSE;
import static cn.edu.seu.kse.lpmln.util.CommonStrings.NEGATION;

/**
 * @author 许鸿翔
 * @date 2019/3/27
 */
public class Nogood{
    private Map<String,Boolean> signedLiterals = new HashMap<>();
    private String w1;
    private String w2;
    private List<String> watcherTobeRemoved = new ArrayList<>();
    /**
     *所属规则id，避免重复
     */
    private Integer ruleId = null;

    private boolean watcherChanged = false;

    /**
     *
     */
    public Nogood(){
    }

    public void add(String literal,boolean sign){
        signedLiterals.put(literal,sign);
    }

    /**
     * 解释一致性产出的nogood
     * @param atom atom
     */
    public Nogood(String atom){
        signedLiterals.put(atom,true);
        signedLiterals.put(NEGATION+atom,true);
    }

    public Set<String> getKeySet(){
        return signedLiterals.keySet();
    }

    public SignedLiteral check(Map<String,Boolean> assignment){
        if(signedLiterals.size()==0){
            return null;
        }else if(signedLiterals.size()==1){
            Map.Entry<String,Boolean> entry = signedLiterals.entrySet().iterator().next();
            return new SignedLiteral(entry.getKey(),entry.getValue());
        }else{
            Iterator<Map.Entry<String,Boolean>> sgIterator = signedLiterals.entrySet().iterator();
            int uncertainCount = 0;
            SignedLiteral result = null;
            while(sgIterator.hasNext()){
                Map.Entry<String,Boolean> ent = sgIterator.next();
                Boolean val = assignment.get(ent.getKey());
                if(val==null){
                    //不确定项
                    uncertainCount++;
                    if(result==null){
                        result = new SignedLiteral(ent.getKey(),ent.getValue());
                    }
                }else if(val.equals(ent.getValue())){
                    //此项满足
                }else{
                    //!val.equals(ent.getValue())
                    //nogood已经违反
                    return null;
                }
            }
            if(uncertainCount==1){
                return result;
            }else if(uncertainCount==0){
                throw new AssignConflictException("assign conflict");
            }
        }
        return null;
    }

    public SignedLiteral getResultUnitUsingSignedLiteral(Map<String,Boolean> assignment){
        assert signedLiterals.size()>0;
        if(signedLiterals.size()==1){
            Map.Entry<String,Boolean> entry = signedLiterals.entrySet().iterator().next();
            return new SignedLiteral(entry.getKey(),entry.getValue());
        }else{
            Iterator<Map.Entry<String,Boolean>> sgIterator = signedLiterals.entrySet().iterator();
            int uncertainCount = 0;
            SignedLiteral result = null;
            while(sgIterator.hasNext()){
                Map.Entry<String,Boolean> ent = sgIterator.next();
                Boolean val = assignment.get(ent.getKey());
                if(val==null){
                    //不确定项
                    uncertainCount++;
                    if(result==null){
                        result = new SignedLiteral(ent.getKey(),ent.getValue());
                    }
                }else if(val.equals(ent.getValue())){
                    //此项满足
                }else{
                    //!val.equals(ent.getValue())
                    //nogood已经违反
                    return null;
                }
            }
            if(uncertainCount==1){
                return result;
            }else if(uncertainCount==0){
                return new SignedLiteral(EXT_FALSE,true);
            }else{
                //not ready
                return null;
            }
        }
    }

    private SignedLiteral getResultUnitUsingWatcher(Map<String,Boolean> assignment){
        if(w2==null){
            if(w1==null||signedLiterals.get(w1).equals(assignment.get(w1))){
                return new SignedLiteral(EXT_FALSE,true);
            }else{
                return new SignedLiteral(w1,signedLiterals.get(w1));
            }
        }
        //T:死，F:活
        boolean eq1 = signedLiterals.get(w1).equals(assignment.get(w1));
        boolean eq2 = signedLiterals.get(w2).equals(assignment.get(w2));
        if(eq1 && eq2){
            //watcher 都挂了，表示nogood挂了(满足),重构watcher
            getWatcherTobeRemoved().clear();
            watcherChanged = false;
            for (Map.Entry<String,Boolean> ent : signedLiterals.entrySet()) {
                if(ent.getValue().equals(assignment.get(ent.getKey()))){
                    continue;
                }
                if(!watcherChanged){
                    w1 = ent.getKey();
                    watcherTobeRemoved.add(w1);
                }else{
                    w2 = ent.getKey();
                    watcherTobeRemoved.add(w2);
                }
                watcherChanged = true;

            }

            if(watcherTobeRemoved.size()==0){
                return new SignedLiteral(EXT_FALSE,true);
            }else if(watcherTobeRemoved.size()==1){
                return new SignedLiteral(w1,signedLiterals.get(w1));
            }else{
                return null;
            }
        }else if(!eq1 && !eq2){
            //俩watcher都活着，nogood没产出
            return null;
        }else{
            //1死1活
            //赋值w1活w2死
            if(eq1){
                String temp;
                temp = w1;
                w1 = w2;
                w2 = temp;
            }

            //尝试更换w2
            getWatcherTobeRemoved().clear();
            watcherChanged = false;
            for (Map.Entry<String,Boolean> ent : signedLiterals.entrySet()) {
                if(ent.getKey().equals(w1)||ent.getKey().equals(w2)){
                    continue;
                }
                if(!signedLiterals.get(ent.getKey()).equals(assignment.get(ent.getKey()))){
                    watcherTobeRemoved.add(w2);
                    w2 = ent.getKey();
                    //找到一个活的w2
                    watcherChanged = true;
                    break;
                }
            }

            if(signedLiterals.get(w2).equals(assignment.get(w2))){
                //还是死的，产出result unit
                return new SignedLiteral(w1,signedLiterals.get(w1));
            }else{
                //更换成功，现在2个活的
                return null;
            }
        }
    }

    public void findWatchers(Map<String,Boolean> assignment){
        for (Map.Entry<String,Boolean> ent : signedLiterals.entrySet()) {
            if(ent.getValue().equals(assignment.get(ent.getKey()))){
                continue;
            }
            if(w2==null){
                if(w1==null){
                    w1 = ent.getKey();
                    continue;
                }
                w2 = ent.getKey();
                return;
            }
        }
    }

    public SignedLiteral getResultUnit(Map<String,Boolean> assignment){
        //return getResultUnitUsingSignedLiteral(assignment);
        return getResultUnitUsingWatcher(assignment);
    }

    public List<SignedLiteral> getSignedLiterals(){
        List<SignedLiteral> sls = new ArrayList<>();
        signedLiterals.forEach((k,v)->sls.add(new SignedLiteral(k,v)));
        return sls;
    }

    public Map<String,Boolean> getCore(){
        return signedLiterals;
    }

    public void setWatchers(String w1,String w2){
        this.w1 = w1;
        this.w2 = w2;
    }

    public String getW1() {
        return w1;
    }

    public String getW2() {
        return w2;
    }

    public void setW1(String w1) {
        this.w1 = w1;
    }

    public void setW2(String w2) {
        this.w2 = w2;
    }

    public List<String> getWatcherTobeRemoved() {
        return watcherTobeRemoved;
    }

    @Override
    public String toString(){
        return signedLiterals.toString();
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public boolean isWatcherChanged() {
        return watcherChanged;
    }
}
