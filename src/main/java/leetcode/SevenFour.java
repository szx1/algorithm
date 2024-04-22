package leetcode;

/**
 * 搜索二维矩阵
 *
 * @author zengxi.song
 * @date 2024/4/12
 */
public class SevenFour {

    public boolean searchMatrix(int[][] matrix, int target) {
        // 对整个数组进行二分查找
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int i = mid / n;
            int j = mid % n;
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        int left = 0, right = matrix.length - 1;
        // 先找到最后一个小于target的行  然后对该行进行二分查找
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (left == 0) {
            return false;
        }
        int i = left - 1;
        left = 0;
        right = matrix[0].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[i][mid] == target) {
                return true;
            } else if (matrix[i][mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

}
