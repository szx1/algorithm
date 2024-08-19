package leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿数量
 *
 * @author zengxi.song
 * @date 2024/7/22
 */
public class TwoZeroZero {

    int[][] aux = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numIslands(char[][] grid) {
        // 广度优先搜索
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] used = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !used[i][j]) {
                    res++;
                    bfs(grid, i, j, m, n, used);
                }
            }
        }
        return res;
    }

    private void bfs(char[][] grid, int x, int y, int m, int n, boolean[][] used) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        used[x][y] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int[] ints : aux) {
                int x1 = poll[0] + ints[0];
                int y1 = poll[1] + ints[1];
                if (check(grid, x1, y1, m, n, used)) {
                    used[x1][y1] = true;
                    queue.add(new int[]{x1, y1});
                }
            }
        }
    }

    private boolean check(char[][] grid, int x, int y, int m, int n, boolean[][] used) {
        return x >= 0 && x < m && y >= 0 && y < n && !used[x][y] && grid[x][y] == '1';
    }

    public int numIslands1(char[][] grid) {
        // 深度优先搜索 使用辅助数组记录遍历过的节点
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] used = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !used[i][j]) {
                    res++;
                    dfs(grid, i, j, m, n, used);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int x, int y, int m, int n, boolean[][] used) {
        if (x > m || y > n || x < 0 || y < 0 || grid[x][y] == '0' || used[x][y]) {
            return;
        }
        used[x][y] = true;
        dfs(grid, x - 1, y, m, n, used);
        dfs(grid, x + 1, y, m, n, used);
        dfs(grid, x, y - 1, m, n, used);
        dfs(grid, x, y + 1, m, n, used);
    }

    public static void main(String[] args) {
        new TwoZeroZero().numIslands(new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}});
    }
}
