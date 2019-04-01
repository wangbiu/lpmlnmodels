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
    /**
     *所属规则id，避免重复
     */
    private Integer ruleId = null;

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

    public SignedLiteral getResultUnit(Map<String,Boolean> assignment){
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

    public List<SignedLiteral> getSignedLiterals(){
        List<SignedLiteral> sls = new ArrayList<>();
        signedLiterals.forEach((k,v)->sls.add(new SignedLiteral(k,v)));
        return sls;
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
}
