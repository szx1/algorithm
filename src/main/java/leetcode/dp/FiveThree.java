package leetcode.dp;

/**
 * 最大子数组和
 *
 * @author zengxi.song
 * @date 2024/9/2
 */
public class FiveThree {

    public int maxSubArray(int[] nums) {
        // 分治的解法 引出线段树 渐进时间复杂度O(N) 空间复杂度O(logN) 递归栈的空间
        return divide(nums, 0, nums.length - 1).mSum;
    }

    private Aux divide(int[] nums, int start, int end) {
        if (start == end) {
            return new Aux(nums[start], nums[start], nums[start], nums[start]);
        }
        int mid = start + ((end - start) >> 1);
        Aux left = divide(nums, start, mid);
        Aux right = divide(nums, mid + 1, end);
        int lSum = Math.max(left.lSum, left.aSum + right.lSum);
        int rSum = Math.max(right.rSum, left.rSum + right.aSum);
        // 这里要注意下 mSum有两种情况 1.跨越中间点 2.不跨越中间点
        int mSum = Math.max(Math.max(left.mSum, right.mSum), left.rSum + right.lSum);
        int aSum = left.aSum + right.aSum;
        return new Aux(lSum, rSum, mSum, aSum);
    }

    private static class Aux {
        /**
         * 表示区间[l,r]以l为左端点的数组的最大和
         */
        private final int lSum;
        /**
         * 表示区间[l,r]以r为右端点的数组的最大和
         */
        private final int rSum;
        /**
         * 表示区间[l,r]中的最大子数组和 也为我们最终要求的东西
         */
        private final int mSum;
        /**
         * 表示区间[l,r]中所有数字的和
         */
        private final int aSum;

        public Aux(int lSum, int rSum, int mSum, int aSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.aSum = aSum;
        }
    }

    public int maxSubArray2(int[] nums) {
        // 动态规划优化版 时间复杂度O(N) 空间复杂度O(1)
        // 结合代码发现根本不需要dp数组
        int res = nums[0];
        int preMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            preMax = Math.max(nums[i], preMax + nums[i]);
            res = Math.max(res, preMax);
        }
        return res;
    }

    public int maxSubArray1(int[] nums) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
        // 定义dp[i]为以i下标为结尾的子数组的最大和
        int[] dp = new int[nums.length];
        int res = nums[0];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
