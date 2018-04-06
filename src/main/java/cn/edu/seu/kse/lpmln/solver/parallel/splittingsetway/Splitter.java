package cn.edu.seu.kse.lpmln.solver.parallel.splittingsetway;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.Rule;

import java.util.*;

public class Splitter {
    private LpmlnProgram bottom;
    private LpmlnProgram top;
    private Set<String> U;
    private Map<String, Integer> lit2int;
    private Map<Integer, String> int2lit;
    private int count;

    public Splitter() {
        U = new HashSet<>();
        lit2int = new HashMap<>();
        int2lit = new HashMap<>();
        count = 0;
    }

    public void split(LpmlnProgram program, double k) {
        // 1. 生成依赖图
        Map<Integer, Set<Integer>> originalGraph = generateOriginalGraph(program);

        // 2. 求强连通分量
        Set<Integer>[] SCCS = getSCCS(originalGraph, count);

        // 3. 生成无环图 （是否必要？）
        Map<Integer, Set<Integer>> newGraph = generateNewGraph(originalGraph, SCCS);

        // 4. 生成分割集
        Map<Integer, Set<Integer>> splittingSets = generateSSTs(newGraph, SCCS);

        // 5. 选择分割集
        // TODO: 确定k值以及组合策略
        Set<Integer> chosenSST = chooseSplittingSet(splittingSets, k);
        formatSplittingSet(chosenSST);

        // 6. 生成bottom和top
        generateTopAndBottom(chosenSST, program);

    }

    private void generateTopAndBottom(Set<Integer> U, LpmlnProgram program) {
        List<Rule> bottomRules = new ArrayList<>();
        List<Rule> topRules = new ArrayList<>();
        for (Rule rule: program.getRules()) {
            if (rule.getHead().size()==0) {
                topRules.add(rule);
            }
            for (String h: rule.getHead()) {
                // without considering head with not
                if (U.contains(lit2int.get(h))) {
                    bottomRules.add(rule);
                }
                else {
                    topRules.add(rule);
                }
            }
        }
        bottom = new LpmlnProgram(bottomRules, program.getFactor(), program.getHerbrandUniverse(), "");
        top = new LpmlnProgram(topRules, program.getFactor(), program.getHerbrandUniverse(), program.getMetarule());
    }

    private void formatSplittingSet(Set<Integer> sst) {
        for (Integer i: sst) {
            U.add(int2lit.get(i));
        }
    }

    private Set<Integer> chooseSplittingSet(Map<Integer, Set<Integer>> SplittingSets,
                                            double rate) {
        int targetSize = (int)rate * lit2int.size();
        int minDiff = Integer.MAX_VALUE;
        Integer targetK = 0;
        for (Integer k: SplittingSets.keySet()) {
            Set<Integer> t = new HashSet<>(SplittingSets.get(k));
            if (t.size() == targetSize) {
                return t;
            }
            if (Math.abs(t.size() - targetSize) < minDiff) {
                targetK = k;
                minDiff = Math.abs(t.size() - targetSize);
            }
        }
        return new HashSet<>(SplittingSets.get(targetK));
    }

    private Map<Integer, Set<Integer>> generateSSTs(Map<Integer, Set<Integer>> graph,
                                                    Set<Integer>[] sccs) {
        Map<Integer, Set<Integer>> ssts = new HashMap<>();
        boolean[] added = new boolean[sccs.length];
        for (Integer key: graph.keySet()) {
            if (added[key]) continue;
            Set<Integer> t = new HashSet<>(sccs[key]);
            for (Integer childNode: graph.get(key)) {
                GSSTsHelper(graph, added, childNode, ssts, sccs);
                t.addAll(new HashSet<>(ssts.get(childNode)));
            }
            if (t.size() != int2lit.size()) {
                ssts.put(key, t);
            }
            added[key] = true;
        }
        return ssts;
    }

    private void GSSTsHelper(Map<Integer, Set<Integer>> graph,
                             boolean[] added,
                             Integer node,
                             Map<Integer, Set<Integer>> SplittingSets,
                             Set<Integer>[] sccs) {
        if (added[node]) return;
        if (!graph.containsKey(node)) {
            SplittingSets.put(node, sccs[node]);
            added[node] = true;
            return;
        }
        Set<Integer> t = sccs[node];
        for (Integer childNode: graph.get(node)) {
            GSSTsHelper(graph, added, childNode, SplittingSets, sccs);
            t.addAll(SplittingSets.get(childNode));
        }
        SplittingSets.put(node, t);
        added[node] = true;
    }

    private Map<Integer, Set<Integer>> generateNewGraph(Map<Integer, Set<Integer>> graph,
                                                   Set<Integer>[] sccs) {
        Map<Integer, Set<Integer>> newGraph = new HashMap<>();
        for (int i = 0; i < sccs.length; i++) {
            for (Integer j: sccs[i]) {
                if (graph.containsKey(j)) {
                    for (Integer node: graph.get(j)) {
                        for (int k = 0; k < sccs.length; k++) {
                            if (k == i) continue;
                            if (sccs[k].contains(node)) {
                                if (!newGraph.containsKey(i)) {
                                    newGraph.put(i, new HashSet<>());
                                }
                                newGraph.get(i).add(k);
                            }
                        }
                    }
                }
            }
        }
        return newGraph;
    }

    @SuppressWarnings("unchecked")
    private Set<Integer>[] getSCCS(Map<Integer, Set<Integer>> graph, int size) {
        Stack<Integer> stack = new Stack<>();
        int[] dfn = new int[size];
        int[] low = new int[size];
        boolean[] visited = new boolean[size];
        List<Set<Integer>> sccs = new ArrayList<>();
        for (Integer node: graph.keySet()) {
            if (visited[node]) continue;
            tarjan(graph, node, stack, dfn, low, visited, 1, sccs);
        }
        Set<Integer>[] result = new Set[sccs.size()];
        return sccs.toArray(result);
    }

    private void tarjan(Map<Integer, Set<Integer>> graph,
                        Integer node,
                        Stack<Integer> stack,
                        int[] dfn,
                        int[] low,
                        boolean[] visited,
                        int time,
                        List<Set<Integer>> sccs) {
        dfn[node] = low[node] = time;
        stack.push(node);
        visited[node] = true;
        if (graph.get(node) != null) {
            for (Integer i: graph.get(node)) {
                if (!visited[i]) {
                    tarjan(graph, i, stack, dfn, low, visited, time + 1, sccs);
                    low[node] = Math.min(low[node], low[i]);
                }
                else if (stack.contains(i)) {
                    low[node] = Math.min(dfn[i], low[node]);
                }
            }
        }
        Set<Integer> scc = new HashSet<>();
        if (dfn[node] == low[node]) {
            while (true) {
                Integer a = stack.pop();
                scc.add(a);
                if (dfn[a] == low[a]) break;
            }
            sccs.add(scc);
        }
    }

    private Map<Integer, Set<Integer>> generateOriginalGraph(LpmlnProgram program) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (Rule rule: program.getRules()) {
            Set<Integer> head = new HashSet<>();
            rule.getHead().forEach(lit -> {
                checkAndAdd(lit);
                head.add(lit2int.get(lit));
            });
            Set<Integer> body = new HashSet<>();
            rule.getPositiveBody().forEach(lit -> {
                checkAndAdd(lit);
                body.add(lit2int.get(lit));
            });
            rule.getNegativeBody().forEach(lit -> {
                String originalLit = lit.substring(4);
                checkAndAdd(originalLit);
                body.add(lit2int.get(originalLit));
            });

            head.forEach(lit -> {
                if (graph.containsKey(lit)) {
                    graph.get(lit).addAll(body);
                } else {
                    graph.put(lit, body);
                }
            });
        }
        return graph;
    }

    private void checkAndAdd(String lit) {
        if (!lit2int.containsKey(lit)) {
            lit2int.put(lit, count);
            int2lit.put(count, lit);
            count++;
        }
    }

    public LpmlnProgram getBottom() {
        return bottom;
    }

    public LpmlnProgram getTop() {
        return top;
    }

    public Set<String> getU() {
        return U;
    }
}
