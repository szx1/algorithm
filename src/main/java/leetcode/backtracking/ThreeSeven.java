package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 解数独
 *
 * @author zengxi.song
 * @date 2024/8/22
 */
public class ThreeSeven {

    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<>();

    public void solveSudoku(char[][] board) {
        // 官方答案1 使用多个boolean数组 优化检查数字是否可以填入的时间复杂度
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }
        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }
        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }

    public void solveSudoku1(char[][] board) {
        // 因为检查填入的数字的时候 循环导致时间复杂度较高
        backTracking(board, 0, 0);
    }

    private boolean backTracking(char[][] board, int row, int column) {
        if (row == board.length || column == board.length) {
            // 遍历完了
            return true;
        }

        if (board[row][column] == '.') {
            for (char num = '1'; num <= '9'; num++) {
                if (!isValid(board, row, column, num)) {
                    continue;
                }
                // 找到有效的值
                board[row][column] = num;
                boolean success;
                if (column == board.length - 1) {
                    success = backTracking(board, row + 1, 0);
                } else {
                    success = backTracking(board, row, column + 1);
                }
                if (success) {
                    return true;
                }
                board[row][column] = '.';
            }
        } else {
            boolean success;
            if (column == board.length - 1) {
                success = backTracking(board, row + 1, 0);
            } else {
                success = backTracking(board, row, column + 1);
            }
            return success;
        }
        return false;
    }

    private boolean isValid(char[][] board, int i, int j, char num) {
        // 行
        for (int row = 0; row < 9; row++) {
            if (board[row][j] == num) {
                return false;
            }
        }
        // 列
        for (int column = 0; column < 9; column++) {
            if (board[i][column] == num) {
                return false;
            }
        }
        // 方格
        int i1 = 3 * (i / 3);
        int j1 = 3 * (j / 3);
        for (int row = i1; row < i1 + 3; row++) {
            for (int column = j1; column < j1 + 3; column++) {
                if (board[row][column] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
