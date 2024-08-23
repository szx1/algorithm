package leetcode.dp.knapsack.zeroone;

/**
 * 分割等和子集
 *
 * @author zengxi.song
 * @date 2024/8/20
 */
public class FourOneSix {

    public boolean canPartition(int[] nums) {
        // 经典的0-1背包问题
        // 分割等和子集即为找到sum的一半
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        // 可优化为一维数组
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            if (num > target) {
                return false;
            }
        }
        for (int num : nums) {
            // 需要注意的是内部循环需要从大到小 防止上一行的j-num先被更新
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[target];
    }

    public boolean canPartition1(int[] nums) {
        // 经典的0-1背包问题
        // 分割等和子集即为找到sum的一半
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        // 定义dp[i][j]为i个物品能否组成j的重量
        int n = nums.length;
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            if (nums[i] > target) {
                return false;
            }
            dp[i][nums[i]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length - 1][target];
    }
}
