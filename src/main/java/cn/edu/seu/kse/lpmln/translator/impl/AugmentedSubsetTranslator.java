package cn.edu.seu.kse.lpmln.translator.impl;

import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/1/18
 */
public class AugmentedSubsetTranslator extends LPMLN2ASPTranslator {
    protected Set<Integer> satIdx;
    protected Set<Integer> unsatIdx;

    public AugmentedSubsetTranslator(Set<Integer> satIdx,Set<Integer> unsatIdx){
        this.satIdx = satIdx;
        this.unsatIdx = unsatIdx;
    }

    @Override
    public String getText(){
        StringBuilder aspProgramBuilder = new StringBuilder();
        for (int i = 0; i < unknownRules.size(); i++) {
            if (satIdx.contains(i)) {
                aspProgramBuilder.append(satRules.get(i));
            } else if (unsatIdx.contains(i)) {
                aspProgramBuilder.append(unsatRules.get(i));
            } else {
                aspProgramBuilder.append(unknownRules.get(i));
            }
        }
        return aspProgramBuilder.toString();
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
}
