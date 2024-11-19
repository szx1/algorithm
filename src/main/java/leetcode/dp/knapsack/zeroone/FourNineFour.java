package leetcode.dp.knapsack.zeroone;

/**
 * 目标和
 *
 * @author zengxi.song
 * @date 2024/8/20
 */
public class FourNineFour {

    public int findTargetSumWays(int[] nums, int target) {
        // 动态规划 空间优化版本 时间复杂度O(N) 空间复杂度O(N)
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // target无效
        if (Math.abs(target) > sum) {
            return 0;
        }
        if (((sum + target) & 1) == 1) {
            return 0;
        }
        // 计算出来的target一定大于等于0
        target = (sum + target) >> 1;
        // 定义dp[i] 为目标和为i的个数
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j - num] + dp[j];
            }
        }
        return dp[target];
    }

    public int findTargetSumWays3(int[] nums, int target) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N^2)
        // 设left-right=target left+right=sum 得left=(sum+target)/2 类似于416题
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // target无效
        if (Math.abs(target) > sum) {
            return 0;
        }
        if (((sum + target) & 1) == 1) {
            return 0;
        }
        // 计算出来的target一定大于等于0
        target = (sum + target) >> 1;
        // 定义dp[i][j]为前i个元素可以组成j的个数
        int[][] dp = new int[nums.length][target + 1];
        dp[0][0] = 1;
        if (nums[0] <= target) {
            dp[0][nums[0]] = 1;
        }
        // 由测试用例[0,0,0,0,0,0,0,0,1]&1
        // 还必须初始化dp[i][0] 因为dp[i][0]的值不确定
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            }
            dp[i][0] = (int) Math.pow(2, zeroCount);
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length - 1][target];
    }

    public int findTargetSumWays2(int[] nums, int target) {
        // 另一种回溯 时间复杂度O(2^n) 空间复杂度O(n)
        backTracking(nums, target, 0);
        return res;
    }

    private void backTracking(int[] nums, int target, int index) {
        if (index == nums.length) {
            if (target == 0) {
                res++;
            }
            return;
        }

        backTracking(nums, target - nums[index], index + 1);
        backTracking(nums, target + nums[index], index + 1);
    }

    private int res = 0;

    public int findTargetSumWays1(int[] nums, int target) {
        // 回溯 时间复杂度O(2^n) 空间复杂度O(n)
        backTracking(nums, target, 0, 0);
        return res;
    }

    private void backTracking(int[] nums, int target, int index, int curV) {
        if (index == nums.length) {
            if (curV == target) {
                res++;
            }
            return;
        }
        backTracking(nums, target, index + 1, curV + nums[index]);
        backTracking(nums, target, index + 1, curV - nums[index]);
    }

    public static void main(String[] args) {
        new FourNineFour().findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
    }
}
