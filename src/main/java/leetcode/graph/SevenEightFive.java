package leetcode.graph;

import leetcode.auxclass.graph.UnionFind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二分图
 *
 * @author zengxi.song
 * @date 2025/1/10
 */
public class SevenEightFive {

    public boolean isBipartite(int[][] graph) {
        // 并查集  时间复杂度O(m+n) 空间复杂度O(n) m为边的个数 n是点的个数
        // 通过并查集分成两个分组 如果两个分组中存在交集则返回false
        UnionFind unionFind = new UnionFind(graph.length);
        for (int i = 0; i < graph.length; i++) {
            for (int num : graph[i]) {
                // 连线的两端要在两个集合里 所以这里不能connect
                if (unionFind.connect(i, num)) {
                    return false;
                }
                // 一个点所有连接的边都应该在一个集合里
                unionFind.union(num, graph[i][0]);
            }
        }
        return true;
    }

    public boolean isBipartite2(int[][] graph) {
        // bfs 时间复杂度O(m+n) 空间复杂度O(n) m为边的个数 n是点的个数
        int n = graph.length;
        // visited只有-1,1和0三个取值
        int[] visited = new int[n];
        // 由于可能存在多个联通区域 所以要循环
        for (int i = 0; i < n; i++) {
            if (visited[i] != 0) {
                // 说明已经搜索过了 直接跳过
                continue;
            }
            // 和dfs就这里不同
            Queue<Integer> queue = new LinkedList<>();
            visited[i] = 1;
            queue.add(i);
            while (!queue.isEmpty()) {
                Integer poll = queue.poll();
                for (int num : graph[poll]) {
                    if (visited[num] != 0) {
                        if (visited[num] == visited[poll]) {
                            return false;
                        }
                        // 再次访问
                        continue;
                    }
                    visited[num] = -visited[poll];
                    queue.add(num);
                }
            }
        }
        return true;
    }

    public boolean isBipartite1(int[][] graph) {
        // dfs 时间复杂度O(m+n) 空间复杂度O(n) m为边的个数 n是点的个数
        int n = graph.length;
        // visited只有-1,1和0三个取值
        int[] visited = new int[n];
        // 由于可能存在多个联通区域 所以要循环
        for (int i = 0; i < n; i++) {
            if (visited[i] != 0) {
                continue;
            }
            visited[i] = 1;
            if (!dfs(graph, visited, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int[] visited, int i) {
        for (int num : graph[i]) {
            if (visited[num] != 0) {
                if (visited[num] == visited[i]) {
                    return false;
                }
                continue;
            }
            visited[num] = -visited[i];
            if (!dfs(graph, visited, num)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new SevenEightFive().isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));
        System.out.println(new SevenEightFive().isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
    }
}
