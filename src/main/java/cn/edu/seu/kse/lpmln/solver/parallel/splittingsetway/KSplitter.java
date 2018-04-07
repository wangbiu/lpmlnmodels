package cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway;

import cn.edu.seu.kse.lpmln.model.DecisionUnit;
import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper;

import java.util.*;

/**
 * @author 许鸿翔
 * @date 2018/4/7
 */
public class KSplitter extends Splitter{
    private double k;
    private LpmlnProgram program;
    private Map<String,Set<String>> dependency;
    private List<DecisionUnit> mdus;
    private SplittingSolver.SPLIT_TYPE policy = SplittingSolver.SPLIT_TYPE.LIT;

    @Override
    public void split(LpmlnProgram program, double k) {
        this.k = k;
        this.program = program;
        this.dependency = LpmlnProgramHelper.getDependency(program);

        this.mdus = generateMDUs();
        buildRelations();

    }

    private List<DecisionUnit> generateMDUs(){
        List<DecisionUnit> mdu = new ArrayList<>();
        //已经被加到之前mdu里的元素
        Set<String> used = new HashSet<>();
        Set<String> litP = new HashSet<>(dependency.keySet());
        dependency.values().forEach(depend->litP.addAll(depend));
        litP.forEach(lit->{
            if(used.contains(lit)){
                return;
            }
            //当前的mdu有的元素
            Set<String> current = dfs(lit);
            used.addAll(current);
            mdu.add(new DecisionUnit(program,current));
        });
        return mdu;
    }

    private Set<String> dfs(String lit){
        Set<String> current = new HashSet<>();
        //深度优先，stack1记录顺序，stack记录目前访问的,path记录当前路径下
        LinkedList<String> stack1 = new LinkedList<>();
        LinkedList<Iterator<String>> stack2 = new LinkedList<>();
        Set<String> path = new HashSet<>();
        stack1.push(lit);
        stack2.push(dependency.get(lit).iterator());
        path.add(lit);
        while(stack2.size()>0){
            if(stack2.peek().hasNext()){
                String next = stack2.peek().next();
                if(path.contains(next)){
                    if(next.equals(lit)){
                        current.addAll(path);
                    }
                }else{
                    stack1.push(next);
                    stack2.push(dependency.get(next).iterator());
                    path.add(next);
                }
            }else{
                stack2.pop();
                path.remove(stack1.pop());
            }
        }
        return current;
    }

    private void buildRelations(){
        Map<String,DecisionUnit> map = new HashMap<>();
        mdus.forEach(mdu-> {
            mdu.getLit().forEach(lit-> {
                map.put(lit,mdu);
            });
        });
        dependency.forEach((lit,depends)->{
            depends.forEach(depend->{
                map.get(lit).getTo().add(map.get(depend));
                map.get(depend).getFrom().add(map.get(lit));
            });
        });
    }

}
