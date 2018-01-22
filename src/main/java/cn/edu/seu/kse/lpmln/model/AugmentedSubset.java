package cn.edu.seu.kse.lpmln.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/1/17
 */
public class AugmentedSubset implements Cloneable{
    private Set<Integer> satIdx;
    private Set<Integer> unsatIdx;
    private Set<Integer> unknownIdx;
    private LpmlnProgram lpmlnProgram;
    public AugmentedSubset(LpmlnProgram lpmlnProgram){
        satIdx = new HashSet<>();
        unsatIdx = new HashSet<>();
        unknownIdx = new HashSet<>();
        for (int i=0;i<lpmlnProgram.getRules().size();i++){
            unknownIdx.add(i);
        }
        this.lpmlnProgram = lpmlnProgram;
    }

    @Override
    public AugmentedSubset clone(){
        AugmentedSubset cloned = new AugmentedSubset(lpmlnProgram);
        cloned.getSatIdx().addAll(satIdx);
        cloned.getUnsatIdx().addAll(unsatIdx);
        return cloned;
    }

    public Set<Integer> getSatIdx() {
        return satIdx;
    }

    public void setSatIdx(Set<Integer> satIdx) {
        this.satIdx = satIdx;
    }

    public Set<Integer> getUnsatIdx() {
        return unsatIdx;
    }

    public void setUnsatIdx(Set<Integer> unsatIdx) {
        this.unsatIdx = unsatIdx;
    }

    public LpmlnProgram getLpmlnProgram() {
        return lpmlnProgram;
    }

    public void setLpmlnProgram(LpmlnProgram lpmlnProgram) {
        this.lpmlnProgram = lpmlnProgram;
    }

    public Set<Integer> getUnknownIdx() {
        return unknownIdx;
    }

    public void setUnknownIdx(Set<Integer> unknownIdx) {
        this.unknownIdx = unknownIdx;
    }
}
