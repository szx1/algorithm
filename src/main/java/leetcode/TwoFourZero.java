package leetcode;

/**
 * 搜索二维矩阵Ⅱ
 *
 * @author zengxi.song
 * @date 2024/4/12
 */
public class TwoFourZero {

    public boolean searchMatrix(int[][] matrix, int target) {
        // z字形查找 时间复杂度O(M+N) 空间复杂度O(1)
        int x = 0;
        int y = matrix[0].length - 1;
        while (x < matrix.length && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] < target) {
                x++;
            } else {
                y--;
            }
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        // 对每一行进行二分查找 时间复杂度O(MlogN) 空间复杂度O(1)
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] > target) {
                break;
            }
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (matrix[i][mid] == target) {
                    return true;
                } else if (matrix[i][mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] > target) {
                break;
            }
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == target) {
                    return true;
                } else if (matrix[i][j] > target) {
                    break;
                }
            }
        }
        return false;
    }
}
