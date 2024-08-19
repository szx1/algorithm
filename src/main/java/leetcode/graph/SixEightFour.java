package leetcode.graph;

import leetcode.auxclass.graph.UnionFind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 冗余连接
 *
 * @author zengxi.song
 * @date 2024/8/7
 */
public class SixEightFour {

    public int[] findRedundantConnection(int[][] edges) {
        // 并查集
        int n = edges.length;
        // 注意题意节点值是1~n
        UnionFind uf = new UnionFind(n + 1);
        for (int[] edge : edges) {
            if (uf.connect(edge[0], edge[1])) {
                return edge;
            }
            uf.union(edge[0], edge[1]);
        }
        return edges[edges.length - 1];
    }

    public int[] findRedundantConnection1(int[][] edges) {
        // dfs
        // 找到第一个环即可 如果没有环 则删除最后一条边
        // 使用map表示图
        Map<Integer, Set<Integer>> graphMap = new HashMap<>();
        for (int[] edge : edges) {
            graphMap.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            graphMap.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        for (int i = edges.length - 1; i >= 0; i--) {
            int[] edge = edges[i];
            graphMap.get(edge[0]).remove(edge[1]);
            graphMap.get(edge[1]).remove(edge[0]);
            Set<Integer> visited = new HashSet<>();
            boolean dfs = dfs(graphMap, visited, edge[0], edge[1]);
            if (!dfs) {
                return edge;
            }
            graphMap.get(edge[0]).add(edge[1]);
            graphMap.get(edge[1]).add(edge[0]);
        }

        return edges[edges.length - 1];
    }

    private boolean dfs(Map<Integer, Set<Integer>> graphMap, Set<Integer> visited, int item, int target) {
        visited.add(item);
        for (Integer i : graphMap.getOrDefault(item, new HashSet<>())) {
            if (target == i) {
                return false;
            }
            if (!visited.contains(i)) {
                boolean dfs = dfs(graphMap, visited, i, target);
                if (!dfs) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        new SixEightFour().findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}});
    }
}
