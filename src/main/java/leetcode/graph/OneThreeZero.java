package leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 被围绕的区域
 *
 * @author zengxi.song
 * @date 2024/7/19
 */
public class OneThreeZero {

    int[][] direct = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        // 广度优先搜索 遍历外围一圈
        // 将没有被包围的O转换为A 后续再转换回来
        for (int i = 0; i < m; i++) {
            bfs(board, i, 0, m, n);
            bfs(board, i, n - 1, m, n);
        }
        for (int i = 1; i < n - 1; i++) {
            bfs(board, 0, i, m, n);
            bfs(board, m - 1, i, m, n);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void bfs(char[][] board, int x, int y, int m, int n) {
        if (!check(board, x, y, m, n)) {
            return;
        }
        Queue<int[]> queue = new LinkedList<>();
        board[x][y] = 'A';
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int[] ints : direct) {
                int x1 = poll[0] + ints[0];
                int y1 = poll[1] + ints[1];
                if (check(board, x1, y1, m, n)) {
                    board[x1][y1] = 'A';
                    queue.add(new int[]{x1, y1});
                }
            }
        }
    }

    private boolean check(char[][] board, int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O';
    }

    public void solve1(char[][] board) {
        int m = board.length;
        if (m == 0) {
            return;
        }
        int n = board[0].length;
        // 深度优先搜索 遍历外围一圈
        // 将没有被包围的O转换为A 后续再转换回来
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0, m, n);
            dfs(board, i, m - 1, m, n);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i, m, n);
            dfs(board, n - 1, i, m, n);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y, int m, int n) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y, m, n);
        dfs(board, x - 1, y, m, n);
        dfs(board, x, y + 1, m, n);
        dfs(board, x, y - 1, m, n);
    }
}
