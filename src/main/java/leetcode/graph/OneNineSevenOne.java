package leetcode.graph;

import leetcode.auxclass.graph.UnionFind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 寻找图中是否存在路径
 * 这道题是dfs/bfs/并查集的入门题 图论的基础题
 *
 * @author zengxi.song
 * @date 2024/8/8
 */
public class OneNineSevenOne {

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // 并查集
        if (source == destination) {
            return true;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.connect(source, destination);
    }

    public boolean validPath2(int n, int[][] edges, int source, int destination) {
        // bfs 使用map记录双向图 时间复杂度O(N+M) 空间复杂度O(N+M)
        if (source == destination) {
            return true;
        }
        Map<Integer, Set<Integer>> graphMap = new HashMap<>();
        for (int[] edge : edges) {
            graphMap.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            graphMap.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        boolean[] visited = new boolean[n];
        visited[source] = true;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            for (Integer i : graphMap.getOrDefault(poll, new HashSet<>())) {
                if (i == destination) {
                    return true;
                }
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        return false;
    }

    public boolean validPath1(int n, int[][] edges, int source, int destination) {
        // dfs 使用map记录双向图 时间复杂度O(N+M) 空间复杂度O(N+M)
        if (source == destination) {
            return true;
        }

        Map<Integer, Set<Integer>> graphMap = new HashMap<>();
        for (int[] edge : edges) {
            graphMap.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            graphMap.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }
        return dfs(graphMap, new HashSet<>(), source, destination);
    }

    private boolean dfs(Map<Integer, Set<Integer>> graphMap, Set<Integer> visited, int source, int destination) {
        visited.add(source);
        for (Integer i : graphMap.getOrDefault(source, new HashSet<>())) {
            if (i == destination) {
                return true;
            }
            if (!visited.contains(i) && dfs(graphMap, visited, i, destination)) {
                return true;
            }
        }
        return false;
    }
}
