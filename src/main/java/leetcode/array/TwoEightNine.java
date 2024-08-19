package leetcode.array;

/**
 * 生命游戏
 *
 * @author zengxi.song
 * @date 2024/7/11
 */
public class TwoEightNine {

    public void gameOfLife(int[][] board) {
        // 不使用辅助数组 时间复杂度O(mn) 空间复杂度O(1)
        // 需要记录之前的状态
        // 2标识1->0 3标识0->1
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int liveCount = liveCount(board, i, j, board.length, board[0].length);
                if (board[i][j] == 1) {
                    board[i][j] = liveCount < 2 || liveCount > 3 ? 0 : 1;
                    if (board[i][j] == 0) {
                        board[i][j] = 2;
                    }
                } else if (board[i][j] == 0) {
                    board[i][j] = liveCount == 3 ? 1 : 0;
                    if (board[i][j] == 1) {
                        board[i][j] = 3;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                } else if (board[i][j] == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }

    private int liveCount(int[][] board, int i, int j, int m, int n) {
        int count = 0;
        if (i - 1 >= 0) {
            count += board[i - 1][j] == 1 || board[i - 1][j] == 2 ? 1 : 0;
        }
        if (i + 1 < m) {
            count += board[i + 1][j] == 1 || board[i + 1][j] == 2 ? 1 : 0;
        }
        if (j - 1 >= 0) {
            count += board[i][j - 1] == 1 || board[i][j - 1] == 2 ? 1 : 0;
        }
        if (j + 1 < n) {
            count += board[i][j + 1] == 1 || board[i][j + 1] == 2 ? 1 : 0;
        }

        if (i - 1 >= 0 && j - 1 >= 0) {
            count += board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == 2 ? 1 : 0;
        }
        if (i - 1 >= 0 && j + 1 < n) {
            count += board[i - 1][j + 1] == 1 || board[i - 1][j + 1] == 2 ? 1 : 0;
        }
        if (i + 1 < m && j + 1 < n) {
            count += board[i + 1][j + 1] == 1 || board[i + 1][j + 1] == 2 ? 1 : 0;
        }
        if (i + 1 < m && j - 1 >= 0) {
            count += board[i + 1][j - 1] == 1 || board[i + 1][j - 1] == 2 ? 1 : 0;
        }

        return count;
    }

    public void gameOfLife1(int[][] board) {
        // 使用辅助数组 时间复杂度O(mn) 空间复杂度O(mn)
        int[][] temp = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, temp[i], 0, board[0].length);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int liveCount = liveCount1(temp, i, j, board.length, board[0].length);
                if (board[i][j] == 1) {
                    board[i][j] = liveCount < 2 || liveCount > 3 ? 0 : 1;
                } else if (board[i][j] == 0) {
                    board[i][j] = liveCount == 3 ? 1 : 0;
                }
            }
        }
    }

    private int liveCount1(int[][] board, int i, int j, int m, int n) {
        int count = 0;
        if (i - 1 >= 0) {
            count += board[i - 1][j] == 1 ? 1 : 0;
        }
        if (i + 1 < m) {
            count += board[i + 1][j] == 1 ? 1 : 0;
        }
        if (j - 1 >= 0) {
            count += board[i][j - 1] == 1 ? 1 : 0;
        }
        if (j + 1 < n) {
            count += board[i][j + 1] == 1 ? 1 : 0;
        }

        if (i - 1 >= 0 && j - 1 >= 0) {
            count += board[i - 1][j - 1] == 1 ? 1 : 0;
        }
        if (i - 1 >= 0 && j + 1 < n) {
            count += board[i - 1][j + 1] == 1 ? 1 : 0;
        }
        if (i + 1 < m && j + 1 < n) {
            count += board[i + 1][j + 1] == 1 ? 1 : 0;
        }
        if (i + 1 < m && j - 1 >= 0) {
            count += board[i + 1][j - 1] == 1 ? 1 : 0;
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] arr = new int[4][3];
        arr[0] = new int[]{0, 1, 0};
        arr[1] = new int[]{0, 0, 1};
        arr[2] = new int[]{1, 1, 1};
        arr[3] = new int[]{0, 0, 0};
        new TwoEightNine().gameOfLife(arr);
    }
}
