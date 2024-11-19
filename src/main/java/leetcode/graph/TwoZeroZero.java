package leetcode.graph;

import com.sun.tools.internal.xjc.generator.bean.field.UnboxedField;
import leetcode.auxclass.graph.UnionFind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿数量
 *
 * @author zengxi.song
 * @date 2024/7/22
 */
public class TwoZeroZero {

    private final int[][] direct = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int numIslands(char[][] grid) {
        // 并查集
        int m = grid.length;
        int n = grid[0].length;
        ExtendUnionFind unionFind = new ExtendUnionFind(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    // 只能通过visited来判断最后的count
                    unionFind.visited(n * i + j);
                    grid[i][j] = '0';
                    for (int[] arr : direct) {
                        int x = i + arr[0];
                        int y = j + arr[1];
                        if (isValid(grid, m, n, x, y)) {
                            unionFind.union(n * i + j, n * x + y);
                        }
                    }
                }
            }
        }
        return unionFind.getCount();
    }

    static class ExtendUnionFind extends UnionFind {
        private final boolean[] visited;

        public ExtendUnionFind(int n) {
            super(n);
            visited = new boolean[n];
        }

        @Override
        public void union(int x, int y) {
            super.union(x, y);
            visited[x] = visited[y] = true;
        }

        public void visited(int x) {
            visited[x] = true;
        }

        public int getCount() {
            int count = 0;
            for (int i = 0; i < parent.length; i++) {
                if (visited[i] && parent[i] == i) {
                    count++;
                }
            }
            return count;
        }
    }

    public int numIslands3(char[][] grid) {
        // 广度优先搜索 时间复杂度O(MN) 空间复杂度O(min(MN))
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
            for (int[] ints : direct) {
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

    public int numIslands2(char[][] grid) {
        // dfs 时间复杂度O(MN) 空间复杂度O(MN)
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, m, n, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int m, int n, int i, int j) {
        if (!isValid(grid, m, n, i, j)) {
            return;
        }
        // 将访问过的置为0 不过不允许的修改原数据的话 可以使用辅助boolean数组
        grid[i][j] = '0';
        for (int[] arr : direct) {
            dfs(grid, m, n, i + arr[0], j + arr[1]);
        }
    }

    private boolean isValid(char[][] grid, int m, int n, int i, int j) {
        return i >= 0 && j >= 0 && i < m && j < n && grid[i][j] == '1';
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
