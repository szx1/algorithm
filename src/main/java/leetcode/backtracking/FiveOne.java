package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * N皇后
 *
 * @author zengxi.song
 * @date 2024/8/22
 */
public class FiveOne {

    public List<List<String>> solveNQueens(int n) {
        char[][] arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(arr[i], '.');
        }
        List<List<String>> res = new ArrayList<>();
        backTracking(res, new LinkedList<>(), arr, 0);
        return res;
    }

    private void backTracking(List<List<String>> res, LinkedList<String> subList, char[][] arr, int index) {
        if (index == arr.length) {
            res.add(new ArrayList<>(subList));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (isValid(arr, index, i)) {
                arr[index][i] = 'Q';
                subList.add(this.arr2Str(arr[index]));
                backTracking(res, subList, arr, index + 1);
                subList.removeLast();
                arr[index][i] = '.';
            }
        }
    }

    private boolean isValid(char[][] arr, int i, int j) {
        // 检查列 大于等于i的不需要检查
        for (int k = 0; k < i; k++) {
            if (arr[k][j] == 'Q') {
                return false;
            }
        }
        // 检查斜线 斜线要包括两部分
        for (int m = i, n = j; m >= 0 && n >= 0; m--, n--) {
            if (arr[m][n] == 'Q') {
                return false;
            }
        }

        for (int m = i, n = j; m >= 0 && n < arr.length; m--, n++) {
            if (arr[m][n] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private String arr2Str(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            sb.append(c);
        }
        return sb.toString();
    }
}
