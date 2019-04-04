package cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;
import cn.edu.seu.kse.lpmln.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 许鸿翔 on 2017/9/23.
 */
public class AugmentedSubsetPartitioner {
    protected SPLIT_TYPE policy = SPLIT_TYPE.DIVIDE_HEURISTIC;

    private static Logger logger = LogManager.getLogger(AugmentedSubsetPartitioner.class.getName());

    /**
     * DIVIDE_SIMPLE：二进制划分
     * DIVIDE_RANDOM：随机划分
     * DIVIDE_HEURISTIC：启发式划分
     * DIVIDE_NOGOOD:使用nogood进行启发式划分
     */
    public enum SPLIT_TYPE{
        DIVIDE_SIMPLE, DIVIDE_RANDOM, DIVIDE_HEURISTIC,DIVIDE_NOGOOD
    }

    //输入：原规则，翻译后的规则文本
    //输出：增强子集文件列表，子集对应的额外权重
    public List<AugmentedSubset> partition(LpmlnProgram lpmlnProgram,int count){
        List<AugmentedSubset> subsets;
        switch (policy){
            /**
             * 选择增强子集划分方式，后续可以思考怎么结合启发式信息
             */
            case DIVIDE_SIMPLE:
                subsets = simplePartition(lpmlnProgram,count);
                break;
            case DIVIDE_RANDOM:
                subsets = randomPartition(lpmlnProgram,count);
                break;
            case DIVIDE_HEURISTIC:
                subsets = heuristicPartition(lpmlnProgram,count);
                break;
            case DIVIDE_NOGOOD:
                subsets = nogoodPartition(lpmlnProgram,count);
                break;
            default:
                subsets = simplePartition(lpmlnProgram,count);
                break;
        }
        if(LPMLNApp.isDebugging()){
            System.out.println("partition cost: "+(System.currentTimeMillis()-LPMLNApp.enter.getTime()));
        }
        return subsets;
    }

    public List<AugmentedSubset> randomPartition(LpmlnProgram lpmlnProgram,int count){
        if(LPMLNApp.isDebugging()){
            System.out.println("start randomPartition.");
        }
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
        if(LPMLNApp.isDebugging()){
            System.out.println("start simplePartition.");
        }
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
        if(LPMLNApp.isDebugging()){
            System.out.println("start heuristicPartition.");
        }
        List<AugmentedSubset> subsets = new ArrayList<>();
        PriorityQueue<HeuristicAugmentedSubset> selectable = new PriorityQueue<>(Comparator.comparing(HeuristicAugmentedSubset::getWeight));
        HeuristicAugmentedSubset subset = new HeuristicAugmentedSubset(lpmlnProgram);
        subsets.add(subset);
        selectable.offer(subset);

        long start = System.currentTimeMillis();
        while(subsets.size()<count && selectable.size()>0){
            //选择一个增强子集
            HeuristicAugmentedSubset toSubstitute = selectable.poll();
            int toEnum;
            //选择一条要确定的规则
            while((toEnum = toSubstitute.getRuleIdx())!=-1){
                HeuristicAugmentedSubset positive = toSubstitute.clone();
                HeuristicAugmentedSubset negative = toSubstitute.clone();
                if(positive.sat(toEnum)&&negative.unsat(toEnum)){
                    subsets.add(positive);
                    subsets.add(negative);
                    subsets.remove(toSubstitute);
                    if(positive.getUnknownIdx().size()>0) {
                        selectable.offer(positive);
                        selectable.offer(negative);
                    }
                    break;
                }else{
                    toSubstitute.setUnenumerable(toEnum);
                }
            }
        }
        //System.out.println("Partition Cost:"+(System.currentTimeMillis()-start));
        return subsets;
    }

    public List<AugmentedSubset> nogoodPartition(LpmlnProgram lpmlnProgram,int count){
        if(LPMLNApp.isDebugging()){
            System.out.println("start nogoodPartition.");
        }

        LinkedList<NogoodAugmentedSubset> load = new LinkedList<>();
        List<NogoodAugmentedSubset> result = new ArrayList<>();
        Set<Integer> bannedIdx = new HashSet<>();
        List<Object> creator = new ArrayList<>(count*2);
        for(int i=0;i<count*2;i++){
            creator.add(new Object());
        }
        List<NogoodAugmentedSubset> pool = creator.parallelStream().map(n->new NogoodAugmentedSubset(lpmlnProgram)).collect(Collectors.toList());
        pool.forEach(item->{
            item.setAimCount(count);
            item.setPool(pool);
        });
        load.offer(pool.remove(pool.size()-1));
        Comparator<NogoodAugmentedSubset> comparator = new Comparator<NogoodAugmentedSubset>() {
            @Override
            public int compare(NogoodAugmentedSubset o1, NogoodAugmentedSubset o2) {
                return o1.getEvaluation()-o2.getEvaluation();
            }
        };

        //TODO:这里没考虑无法划分的情况
        while (load.size()+result.size()<count&&load.size()>0){
            load.sort(comparator);
            NogoodAugmentedSubset tosplit = load.peekLast();
            Pair<NogoodAugmentedSubset,NogoodAugmentedSubset> splitResult = tosplit.split(load);
            if(splitResult==null){
                if(tosplit.getEvaluation()==-1){
                    if(LPMLNApp.isDebugging()){
                        logger.debug("当前增强子集规则已被划分完毕，队列剩余:{}", load.size());
                    }

                    result.add(tosplit);
                    continue;
                }
                load.offer(tosplit);

            }else{
                load.offer(splitResult.getKey());
                load.offer(splitResult.getValue());
            }
        }

        result.addAll(load);

        return new ArrayList<>(result);
    }

    private void printStatus(List<AugmentedSubset> augmentedSubsets){
        augmentedSubsets.forEach(augmentedSubset -> {
            String[] status = new String[augmentedSubset.getLpmlnProgram().getRules().size()];
            augmentedSubset.getUnknownIdx().forEach(unIdx->{
                status[unIdx] = "1";
            });
            augmentedSubset.getSatIdx().forEach(unIdx->{
                status[unIdx] = "2";
            });
            augmentedSubset.getUnsatIdx().forEach(unIdx->{
                status[unIdx] = "3";
            });
            System.out.println(String.join(" ",status));
        });
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

    public SPLIT_TYPE getPolicy() {
        return policy;
    }

    public void setPolicy(SPLIT_TYPE policy) {
        this.policy = policy;
    }
}
