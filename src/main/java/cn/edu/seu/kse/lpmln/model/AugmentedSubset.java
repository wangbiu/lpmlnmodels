package cn.edu.seu.kse.lpmln.model;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;
import cn.edu.seu.kse.lpmln.exception.solveexception.SolveException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/1/17
 */
public class AugmentedSubset implements Cloneable{
    protected Set<Integer> satIdx;
    protected Set<Integer> unsatIdx;
    private Set<Integer> unknownIdx;
    private LpmlnProgram lpmlnProgram;
    public AugmentedSubset(LpmlnProgram lpmlnProgram){
        satIdx = new HashSet<>();
        unsatIdx = new HashSet<>();
        unknownIdx = new HashSet<>();
        int unknownMax = lpmlnProgram.getRules().size();
        if("weak".equals(LPMLNApp.semantics)){
            for (Rule r : lpmlnProgram.getRules()) {
                if(!r.isSoft()){
                    unknownMax--;
                }
            }
        }

        for (int i=0;i<unknownMax;i++){
            unknownIdx.add(i);
        }
        this.lpmlnProgram = lpmlnProgram;
    }

    private AugmentedSubset(){
        satIdx = new HashSet<>();
        unsatIdx = new HashSet<>();
        unknownIdx = new HashSet<>();
    }

    public boolean sat(int idx){
        if(unknownIdx.contains(idx)){
            satIdx.add(idx);
            unknownIdx.remove(idx);
            return true;
        }
        throw new SolveException("partition fail, Idx error");
    }

    public boolean unsat(int idx){
        if(unknownIdx.contains(idx)){
            unsatIdx.add(idx);
            unknownIdx.remove(idx);
            return true;
        }
        throw new SolveException("partition fail, Idx error");
    }

    @Override
    public AugmentedSubset clone(){
        AugmentedSubset cloned = new AugmentedSubset();
        cloned.setLpmlnProgram(lpmlnProgram);
        cloned.satIdx.addAll(satIdx);
        cloned.unsatIdx.addAll(unsatIdx);
        cloned.unknownIdx.addAll(unknownIdx);
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
