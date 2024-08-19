package leetcode.graph;

/**
 * 岛屿的周长
 *
 * @author zengxi.song
 * @date 2024/7/22
 */
public class FourSixThree {

    int[][] direct = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int islandPerimeter(int[][] grid) {
        // 深度优先搜索 判断岛屿周围是否为水 时间复杂度O(mn) 空间复杂度O(mn)
        int m = grid.length, n = grid[0].length;
        int res = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                res += this.dfs(grid, i, j, m, n, visited);
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int x, int y, int m, int n, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0) {
            return 1;
        }
        if (visited[x][y]) {
            return 0;
        }
        visited[x][y] = true;
        int res = 0;
        for (int[] ints : direct) {
            int x1 = x + ints[0];
            int y1 = y + ints[1];
            res += this.dfs(grid, x1, y1, m, n, visited);
        }
        return res;
    }

    public int islandPerimeter1(int[][] grid) {
        // 迭代判断岛屿周围是否为水 时间复杂度O(mn) 空间复杂度O(1)
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                for (int[] ints : direct) {
                    int x = i + ints[0];
                    int y = j + ints[1];
                    if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0) {
                        res += 1;
                    }
                }
            }
        }
        return res;
    }
}
