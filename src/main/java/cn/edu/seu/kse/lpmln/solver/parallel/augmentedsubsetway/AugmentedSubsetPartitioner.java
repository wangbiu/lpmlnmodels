package cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway;

import cn.edu.seu.kse.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.lpmln.model.HeuristicAugmentedSubset;
import cn.edu.seu.kse.lpmln.model.LpmlnProgram;

import java.util.*;

/**
 * Created by 许鸿翔 on 2017/9/23.
 */
public class AugmentedSubsetPartitioner {
    protected TRANSLATION_TYPE policy = TRANSLATION_TYPE.HEURISTIC;

    /**
     * SPLIT_SIMPLE：二进制划分
     * SPLIT_RANDOM：随机划分
     * HEURISTIC：启发式划分
     */
    public enum TRANSLATION_TYPE{SPLIT_SIMPLE, SPLIT_RANDOM, HEURISTIC,TEST}

    //输入：原规则，翻译后的规则文本
    //输出：增强子集文件列表，子集对应的额外权重
    public List<AugmentedSubset> partition(LpmlnProgram lpmlnProgram,int count){
        List<AugmentedSubset> subsets;
        switch (policy){
            /**
             * 选择增强子集划分方式，后续可以思考怎么结合启发式信息
             */
            case SPLIT_SIMPLE:
                subsets = simplePartition(lpmlnProgram,count);
                break;
            case SPLIT_RANDOM:
                subsets = randomPartition(lpmlnProgram,count);
                break;
            case HEURISTIC:
                subsets = heuristicPartition(lpmlnProgram,count);
                break;
            default:
                subsets = simplePartition(lpmlnProgram,count);
                break;
        }
        return subsets;
    }

    public List<AugmentedSubset> randomPartition(LpmlnProgram lpmlnProgram,int count){
        List<AugmentedSubset> subsets = new ArrayList<>();
        List<AugmentedSubset> selectable = new ArrayList<>();
        Random rand = new Random();
        AugmentedSubset subset = new AugmentedSubset(lpmlnProgram);
        subsets.add(subset);
        if(subset.getUnknownIdx().size()>0){
            selectable.add(subset);
        }

        while(subsets.size()<count && selectable.size()>0){
            //选择一个增强子集
            int randomIdx = Math.abs(rand.nextInt())%selectable.size();
            AugmentedSubset toSubstitute = selectable.get(randomIdx);
            subsets.remove(toSubstitute);
            selectable.remove(toSubstitute);
            Set<Integer> unknownIdx = toSubstitute.getUnknownIdx();

            //选择一条要确定的规则
            randomIdx = Math.abs(rand.nextInt())%unknownIdx.size();
            AugmentedSubset positive = toSubstitute.clone();
            AugmentedSubset negative = toSubstitute.clone();
            int idx = new ArrayList<>(unknownIdx).get(randomIdx);

            positive.sat(idx);
            negative.unsat(idx);
            subsets.add(positive);
            subsets.add(negative);

            if(positive.getUnknownIdx().size()>0) {
                selectable.add(positive);
                selectable.add(negative);
            }
        }
        return subsets;
    }

    public List<AugmentedSubset> simplePartition(LpmlnProgram lpmlnProgram,int count){
        List<AugmentedSubset> subsets = new ArrayList<>();
        int corepow2 = (int)(Math.log(count)/Math.log(2));
        ArrayList<Integer> unknown = new ArrayList<>(new AugmentedSubset(lpmlnProgram).getUnknownIdx());
        corepow2 = Math.min(corepow2,unknown.size());
        for(int i=0;i<Math.pow(2,corepow2);i++){
            AugmentedSubset as = new AugmentedSubset(lpmlnProgram);
            int toConstruct = i;
            for(int j=0;j<corepow2;j++){
                int tochange = unknown.get(j);
                if(toConstruct%2==0){
                    as.sat(tochange);
                }else{
                    as.unsat(tochange);
                }
                toConstruct>>=1;
            }
            subsets.add(as);
        }
        return subsets;
    }

    public List<AugmentedSubset> heuristicPartition(LpmlnProgram lpmlnProgram,int count){
        List<AugmentedSubset> subsets = new ArrayList<>();
        List<HeuristicAugmentedSubset> selectable = new ArrayList<>();
        Random rand = new Random();
        HeuristicAugmentedSubset subset = new HeuristicAugmentedSubset(lpmlnProgram);
        subsets.add(subset);
        selectable.add(subset);

        while(subsets.size()<count && selectable.size()>0){
            //选择一个增强子集
            int randomIdx = Math.abs(rand.nextInt())%selectable.size();
            HeuristicAugmentedSubset toSubstitute = selectable.get(randomIdx);
            subsets.remove(toSubstitute);
            selectable.remove(toSubstitute);
            Set<Integer> enumrable = toSubstitute.getEnumerable();

            //选择一条要确定的规则
            int toEnum = randomPop(enumrable);
            while(toEnum>=0&&!toSubstitute.enumerable(toEnum)){
                toEnum = randomPop(enumrable);
            }
            if(toEnum==-1){
                subsets.add(toSubstitute);
                continue;
            }
            HeuristicAugmentedSubset positive = toSubstitute.clone();
            HeuristicAugmentedSubset negative = toSubstitute.clone();

            positive.sat(toEnum);
            negative.unsat(toEnum);
            subsets.add(positive);
            subsets.add(negative);

            if(positive.getUnknownIdx().size()>0) {
                selectable.add(positive);
                selectable.add(negative);
            }
        }
        return subsets;
    }

    private int randomPop(Set<Integer> set){
        if(set.size()<=0){
            return -1;
        }
        int randomIdx = Math.abs(new Random().nextInt())%set.size();
        int idx = new ArrayList<>(set).get(randomIdx);
        set.remove((Integer) idx);
        return idx;
    }
}
