package leetcode.backtracking;

import java.util.Arrays;

/**
 * N皇后Ⅱ
 *
 * @author zengxi.song
 * @date 2025/2/14
 */
public class FiveTwo {

    int res = 0;

    public int totalNQueens(int n) {
        int[][] arr = new int[n][n];
        // 1代表皇后 0代表坑
        for (int i = 0; i < n; i++) {
            Arrays.fill(arr[i], 0);
        }
        backTracking(arr, 0);
        return res;
    }

    private void backTracking(int[][] arr, int row) {
        if (row == arr.length) {
            res++;
            return;
        }
        int n = arr[0].length;
        for (int i = 0; i < n; i++) {
            if (arr[row][i] == 0 && valid(arr, row, i)) {
                arr[row][i] = 1;
                backTracking(arr, row + 1);
                arr[row][i] = 0;
            }
        }
    }

    private boolean valid(int[][] arr, int row, int column) {
        int m = arr.length, n = arr[0].length;
        // 是否列重复 这个最多只需要迭代到row
        for (int i = 0; i < row; i++) {
            if (arr[i][column] == 1) {
                return false;
            }
        }
        // 是否行重复 不需要检查行 因为可以确保行是每行递增的
//        for (int i = 0; i < n; i++) {
//            if (arr[row][i] == 1) {
//                return false;
//            }
//        }
        // 是否斜线重复 斜线包括两部分
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            if (arr[i][j] == 1) {
                return false;
            }
        }
        for (int i = row - 1, j = column + 1; i >= 0 && j < n; i--, j++) {
            if (arr[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new FiveTwo().totalNQueens(4));
    }
}
