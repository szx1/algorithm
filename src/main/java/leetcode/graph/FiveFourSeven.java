package leetcode.graph;

import leetcode.auxclass.graph.UnionFind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 省份数量
 * 和岛屿问题很像(但是完全不同，好坑，第一次做就中圈套了)
 *
 * @author zengxi.song
 * @date 2024/8/7
 */
public class FiveFourSeven {

    public int findCircleNum(int[][] isConnected) {
        // 并查集
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.diffCount();
    }

    public int findCircleNum2(int[][] isConnected) {
        // bfs
        int res = 0;
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                res++;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;
                while (!queue.isEmpty()) {
                    Integer poll = queue.poll();
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && isConnected[poll][j] == 1) {
                            queue.add(j);
                            visited[j] = true;
                        }
                    }
                }
            }
        }
        return res;
    }

    public int findCircleNum1(int[][] isConnected) {
        // dfs
        int res = 0;
        int n = isConnected.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    res++;
                    dfs(isConnected, i, n);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] isConnected, int i, int n) {
        for (int k = 0; k < n; k++) {
            if (isConnected[i][k] == 1) {
                // 将所有可以连接到的设置为0
                isConnected[i][k] = 0;
                dfs(isConnected, k, n);
            }
        }
    }

    public static void main(String[] args) {
        new FiveFourSeven().findCircleNum(new int[][]{{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}});
    }
}
