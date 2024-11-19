package leetcode.backtracking;

/**
 * 单词搜索
 *
 * @author zengxi.song
 * @date 2024/9/4
 */
public class SevenNine {

    private final int[][] direct = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        // dfs回溯
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int index, int i, int j, boolean[][] visited) {
        if (index >= word.length()) {
            return true;
        }
        if (board[i][j] != word.charAt(index)) {
            return false;
        } else if (index == word.length() - 1) {
            // index不可能越界
            return true;
        }
        visited[i][j] = true;
        for (int[] arr : direct) {
            int x = i + arr[0];
            int y = j + arr[1];
            if (valid(x, y, board, visited)) {
                if (dfs(board, word, index + 1, x, y, visited)) {
                    return true;
                }
            }
        }
        visited[i][j] = false;
        return false;
    }

    private boolean valid(int i, int j, char[][] board, boolean[][] visited) {
        return i >= 0 && j >= 0 && i < board.length && j < board[0].length && !visited[i][j];
    }
}
