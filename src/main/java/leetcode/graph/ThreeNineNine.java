package leetcode.graph;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 除法求值
 *
 * @author zengxi.song
 * @date 2024/8/6
 */
public class ThreeNineNine {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 带权并查集
        // 使用Map记录字符到下标的映射 方便后续编码
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        for (List<String> equation : equations) {
            if (!map.containsKey(equation.get(0))) {
                map.put(equation.get(0), index++);
            }
            if (!map.containsKey(equation.get(1))) {
                map.put(equation.get(1), index++);
            }
        }
        // 构建并查集需要的数组
        int[] parent = new int[index];
        // 存储该点到parent的比值
        double[] w = new double[index];
        for (int i = 0; i < index; i++) {
            parent[i] = i;
            w[i] = 1.0d;
        }
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            union(parent, w, map.get(equation.get(0)), map.get(equation.get(1)), values[i]);
        }
        double[] res = new double[queries.size()];
        index = 0;
        for (List<String> query : queries) {
            if (!map.containsKey(query.get(0)) || !map.containsKey(query.get(1))) {
                res[index++] = -1.0;
                continue;
            }
            Integer x = map.get(query.get(0));
            Integer y = map.get(query.get(1));
            if (find(parent, w, x) == find(parent, w, y)) {
                res[index++] = w[x] / w[y];
            } else {
                res[index++] = -1.0;
            }
        }
        return res;
    }

    private int find(int[] parent, double[] w, int n) {
        if (parent[n] != n) {
            int originalParent = parent[n];
            parent[n] = find(parent, w, parent[n]);
            w[n] = w[n] * w[originalParent];
        }
        return parent[n];
    }

    private void union(int[] parent, double[] w, int x, int y, double value) {
        int rootx = find(parent, w, x);
        int rooty = find(parent, w, y);
        if (rootx != rooty) {
            parent[rootx] = rooty;
            // 这里需要求的是rootx/rooty
            // 可由x/y=value x/rootx=w[x] y/rooty=w[y]推得
            w[rootx] = value * w[y] / w[x];
        }
    }

    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // bfs
        // 先将equations转换为Map的形式
        Map<String, Map<String, Double>> equationMap = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String a = equation.get(0);
            String b = equation.get(1);
            equationMap.computeIfAbsent(a, k -> new HashMap<>()).put(b, values[i]);
            equationMap.computeIfAbsent(b, k -> new HashMap<>()).put(a, 1d / values[i]);
        }
        List<Double> res = new ArrayList<>();
        for (List<String> query : queries) {
            String a = query.get(0);
            String b = query.get(1);
            if (!equationMap.containsKey(a) || !equationMap.containsKey(b)) {
                res.add(-1d);
                continue;
            }
            if (a.equals(b)) {
                res.add(1d);
                continue;
            }
            Map<String, Double> valueMap = equationMap.get(a);
            if (valueMap.containsKey(b)) {
                res.add(valueMap.get(b));
                continue;
            }
            // 开始进行以a为基础的bfs
            Queue<String> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            visited.add(a);
            queue.add(a);
            while (!queue.isEmpty()) {
                String poll = queue.poll();
                for (Map.Entry<String, Double> entry : equationMap.getOrDefault(poll, new HashMap<>()).entrySet()) {
                    if (visited.add(entry.getKey())) {
                        queue.add(entry.getKey());
                    }
                    if (valueMap.containsKey(poll)) {
                        valueMap.put(entry.getKey(), valueMap.get(poll) * entry.getValue());
                        if (entry.getKey().equals(b)) {
                            break;
                        }
                    }
                }
            }
            res.add(valueMap.getOrDefault(b, -1d));
        }
        return res.stream().mapToDouble(item -> item).toArray();
    }

    public double[] calcEquation1(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // dfs
        // 先将equations转换为Map的形式
        Map<String, Map<String, Double>> equationMap = new ConcurrentHashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String a = equation.get(0);
            String b = equation.get(1);
            equationMap.computeIfAbsent(a, k -> new ConcurrentHashMap<>()).put(b, values[i]);
            equationMap.computeIfAbsent(b, k -> new ConcurrentHashMap<>()).put(a, 1d / values[i]);
        }
        List<Double> res = new ArrayList<>();
        for (List<String> query : queries) {
            String a = query.get(0);
            String b = query.get(1);
            if (!equationMap.containsKey(a) || !equationMap.containsKey(b)) {
                res.add(-1d);
                continue;
            }
            if (a.equals(b)) {
                res.add(1d);
                continue;
            }
            Map<String, Double> valueMap = equationMap.get(a);
            if (valueMap.containsKey(b)) {
                res.add(valueMap.get(b));
                continue;
            }
            // 进行a的初始化
            // 该set用于判断每次遍历是否访问到 避免死循环
            Set<String> visitedMap = new HashSet<>();
            dfs(equationMap, visitedMap, a, b);
            res.add(valueMap.getOrDefault(b, -1d));
        }
        return res.stream().mapToDouble(item -> item).toArray();
    }

    private void dfs(Map<String, Map<String, Double>> equationMap, Set<String> visited, String a, String b) {
        if (!visited.add(a)) {
            return;
        }
        Map<String, Double> valueMap = equationMap.getOrDefault(a, new HashMap<>());
        for (Map.Entry<String, Double> entry : valueMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            if (!visited.contains(key)) {
                dfs(equationMap, visited, key, b);
            }
            Optional.ofNullable(equationMap.get(key)).ifPresent(map -> {
                for (Map.Entry<String, Double> valueEntry : map.entrySet()) {
                    valueMap.put(valueEntry.getKey(), value * valueEntry.getValue());
                }
            });
            if (key.equals(b)) {
                return;
            }
        }
    }

    public static void main(String[] args) {
//        List<List<String>> equations = new ArrayList<>();
//        equations.add(Lists.newArrayList("x1", "x2"));
//        equations.add(Lists.newArrayList("x2", "x3"));
//        equations.add(Lists.newArrayList("x1", "x4"));
//        equations.add(Lists.newArrayList("x2", "x5"));
//        List<List<String>> queries = new ArrayList<>();
//        queries.add(Lists.newArrayList("x2", "x4"));
//        queries.add(Lists.newArrayList("x1", "x5"));
//        queries.add(Lists.newArrayList("x1", "x3"));
//        queries.add(Lists.newArrayList("x5", "x5"));
//        queries.add(Lists.newArrayList("x5", "x1"));
//        queries.add(Lists.newArrayList("x3", "x4"));
//        queries.add(Lists.newArrayList("x4", "x3"));
//        queries.add(Lists.newArrayList("x6", "x6"));
//        queries.add(Lists.newArrayList("x0", "x0"));
//        new ThreeNineNine().calcEquation(equations, new double[]{3.0, 4.0, 5.0, 6.0}, queries);
        List<List<String>> equations = new ArrayList<>();
        equations.add(Lists.newArrayList("a", "b"));
        equations.add(Lists.newArrayList("b", "c"));
        List<List<String>> queries = new ArrayList<>();
        queries.add(Lists.newArrayList("a", "c"));
        queries.add(Lists.newArrayList("b", "a"));
        queries.add(Lists.newArrayList("a", "e"));
        queries.add(Lists.newArrayList("a", "a"));
        queries.add(Lists.newArrayList("x", "x"));
        new ThreeNineNine().calcEquation(equations, new double[]{2.0, 3.0}, queries);

    }
}
