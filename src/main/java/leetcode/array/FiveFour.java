package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 *
 * @author zengxi.song
 * @date 2024/7/9
 */
public class FiveFour {

    public List<Integer> spiralOrder(int[][] matrix) {
        // 时间复杂度O(mn) 空间复杂度O(1)
        List<Integer> res = new ArrayList<>();
        int u = 0, b = matrix.length - 1, l = 0, r = matrix[0].length - 1;
        while (true) {
            for (int i = l; i <= r; i++) {
                res.add(matrix[u][i]);
            }
            if (++u > b) {
                break;
            }
            for (int i = u; i <= b; i++) {
                res.add(matrix[i][r]);
            }
            if (--r < l) {
                break;
            }
            for (int i = r; i >= l; i--) {
                res.add(matrix[b][i]);
            }
            if (--b < u) {
                break;
            }
            for (int i = b; i > u; i--) {
                res.add(matrix[i][l]);
            }
            if (++l > r) {
                break;
            }
        }
        return res;
    }

    public List<Integer> spiralOrder1(int[][] matrix) {
        // 时间复杂度O(mn) 空间复杂度O(1)
        List<Integer> res = new ArrayList<>();
        int u = 0, b = matrix.length - 1, l = 0, r = matrix[0].length - 1;
        while (u <= b && l <= r) {
            // 左->右+上->下 至少执行一次
            for (int i = l; i <= r; i++) {
                res.add(matrix[u][i]);
            }
            for (int i = u + 1; i < b; i++) {
                res.add(matrix[i][r]);
            }

            if (u < b && l < r) {
                for (int i = r; i > l; i--) {
                    res.add(matrix[b][i]);
                }
                for (int i = b; i > u; i--) {
                    res.add(matrix[i][l]);
                }
            }
            l++;
            u++;
            r--;
            b--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = new int[3][4];
        arr[0] = new int[]{1, 2, 3, 4};
        arr[1] = new int[]{5, 6, 7, 8};
        arr[2] = new int[]{9, 10, 11, 12};
        new FiveFour().spiralOrder(arr);
    }
}
