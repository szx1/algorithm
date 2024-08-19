package leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 扫雷游戏
 *
 * @author zengxi.song
 * @date 2024/7/30
 */
public class FiveTwoNine {

    int[][] direct = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        // 广度优先搜索 时间复杂度O(MN) 空间复杂度O(MN)
        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            bfs(board, x, y, board.length, board[0].length);
        }
        return board;
    }

    private void bfs(char[][] board, int x, int y, int m, int n) {
        if (board[x][y] != 'E') {
            return;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        // bfs需要记录被揭露过的点 因为周围8个肯定有重合的
        boolean[][] visited = new boolean[m][n];
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int count = 0;
            for (int[] ints : direct) {
                int x1 = poll[0] + ints[0];
                int y1 = poll[1] + ints[1];
                if (x1 >= m || y1 >= n || x1 < 0 || y1 < 0) {
                    continue;
                }
                if (board[x1][y1] == 'M') {
                    count++;
                }
            }
            if (count > 0) {
                // 不需要揭露周围方块
                board[poll[0]][poll[1]] = (char) (count + '0');
            } else {
                board[poll[0]][poll[1]] = 'B';
                for (int[] ints : direct) {
                    int x1 = poll[0] + ints[0];
                    int y1 = poll[1] + ints[1];
                    if (x1 >= m || y1 >= n || x1 < 0 || y1 < 0) {
                        continue;
                    }
                    if (board[x1][y1] == 'E' && !visited[x1][y1]) {
                        queue.add(new int[]{x1, y1});
                        visited[x1][y1] = true;
                    }
                }
            }
        }
    }

    public char[][] updateBoard1(char[][] board, int[] click) {
        // 深度优先搜索 时间复杂度O(MN) 空间复杂度O(MN)
        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            dfs(board, x, y, board.length, board[0].length);
        }
        return board;
    }

    private void dfs(char[][] board, int x, int y, int m, int n) {
        if (board[x][y] != 'E') {
            return;
        }
        // 记录周围雷的数量
        int count = 0;
        for (int[] ints : direct) {
            int x1 = x + ints[0];
            int y1 = y + ints[1];
            if (x1 >= m || y1 >= n || x1 < 0 || y1 < 0) {
                continue;
            }
            if (board[x1][y1] == 'M') {
                count++;
            }
        }
        if (count > 0) {
            // 不需要揭露周围方块
            board[x][y] = (char) (count + '0');
        } else {
            board[x][y] = 'B';
            for (int[] ints : direct) {
                int x1 = x + ints[0];
                int y1 = y + ints[1];
                if (x1 >= m || y1 >= n || x1 < 0 || y1 < 0) {
                    continue;
                }
                dfs(board, x1, y1, m, n);
            }
        }
    }

    public static void main(String[] args) {
        char[][] chars = {{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};
        new FiveTwoNine().updateBoard(chars, new int[]{3, 0});
    }

}
