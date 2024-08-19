package leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿的最大面积
 *
 * @author zengxi.song
 * @date 2024/7/22
 */
public class SixNineFive {

    int[][] direct = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maxAreaOfIsland(int[][] grid) {
        // 广度优先搜素 使用辅助数组记录遍历过的节点 全局变量记录每次bfs的结果
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] used = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !used[i][j]) {
                    res = Math.max(res, this.bfs(grid, i, j, m, n, used));
                }
            }
        }
        return res;
    }

    private int bfs(int[][] grid, int x, int y, int m, int n, boolean[][] used) {
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        used[x][y] = true;
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            count++;
            for (int[] ints : direct) {
                int x1 = poll[0] + ints[0];
                int y1 = poll[1] + ints[1];
                if (check(grid, x1, y1, m, n, used)) {
                    used[x1][y1] = true;
                    queue.add(new int[]{x1, y1});
                }
            }
        }
        return count;
    }

    private boolean check(int[][] grid, int x, int y, int m, int n, boolean[][] used) {
        return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && !used[x][y];
    }

    int count = 0;

    public int maxAreaOfIsland1(int[][] grid) {
        // 深度优先搜素 使用辅助数组记录遍历过的节点 全局变量记录每次dfs的结果
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] used = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !used[i][j]) {
                    count = 0;
                    dfs(grid, i, j, m, n, used);
                    res = Math.max(res, count);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int x, int y, int m, int n, boolean[][] used) {
        if (x > m || y > n || x < 0 || y < 0 || grid[x][y] == 0 || used[x][y]) {
            return;
        }
        used[x][y] = true;
        count++;
        dfs(grid, x - 1, y, m, n, used);
        dfs(grid, x + 1, y, m, n, used);
        dfs(grid, x, y - 1, m, n, used);
        dfs(grid, x, y + 1, m, n, used);
    }

    public static void main(String[] args) {
        new SixNineFive().maxAreaOfIsland(new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}});
    }
}
