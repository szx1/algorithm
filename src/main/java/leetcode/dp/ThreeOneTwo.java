package leetcode.dp;

/**
 * 戳气球
 *
 * @author zengxi.song
 * @date 2024/8/12
 */
public class ThreeOneTwo {

    public int maxCoins(int[] nums) {
        // 动态规划 时间复杂度O(n^3) 空间复杂度O(n^2)
        int n = nums.length;
        int[] val = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        val[0] = val[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    dp[i][j] = Math.max(dp[i][j], sum + dp[i][k] + dp[k][j]);
                }
            }
        }
        return dp[0][n + 1];
    }

    public int maxCoins1(int[] nums) {
        // 记忆化搜索 时间复杂度O(n^3) 空间复杂度O(n^2)
        int n = nums.length;
        int[] val = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        val[0] = val[n + 1] = 1;
        int[][] helper = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                helper[i][j] = -1;
            }
        }
        return findMax(val, helper, 0, n + 1);
    }

    private int findMax(int[] val, int[][] helper, int start, int end) {
        int max = 0;
        if (helper[start][end] != -1) {
            return helper[start][end];
        }
        for (int k = start + 1; k < end; k++) {
            int sum = val[start] * val[k] * val[end] + findMax(val, helper, start, k) + findMax(val, helper, k, end);
            max = Math.max(sum, max);
        }
        helper[start][end] = max;
        return max;
    }
}
