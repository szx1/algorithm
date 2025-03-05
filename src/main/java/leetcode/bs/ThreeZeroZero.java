package leetcode.bs;

import java.util.Arrays;

/**
 * 最长递增子序列
 *
 * @author zengxi.song
 * @date 2024/9/10
 */
public class ThreeZeroZero {

    public int lengthOfLIS(int[] nums) {
        // 进阶 贪心+二分查找 时间复杂度O(NlogN) 空间复杂度O(N)
        // 想要求得尽可能长的递增序列 则每次加入的新值都要尽可能的小
        // 设数组dp[i]表示长度为i的递增序列的最小的结尾值 设len为此时的最大长度
        // 可得dp[i]是单调递增的的 对于nums[i]>dp[len],dp[++len]=nums[i];
        // 如果nums[i]<dp[len] 则遍历dp数组找到第一个大于nums[i]的位置index然后更新
        // 因为dp数组是单调递增的 所以可以二分查找
        int n = nums.length;
        int[] dp = new int[n + 1];
        int len = 1;
        dp[1] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == dp[len]) {
                continue;
            }
            if (nums[i] > dp[len]) {
                dp[++len] = nums[i];
            } else {
                // nums[i]<dp[len] 为了编码便利 dp[0]没用到 left要赋值为1
                int left = 1, right = len, index = 0;
                while (left <= right) {
                    int mid = left + ((right - left) >> 2);
                    if (dp[mid] >= nums[i]) {
                        index = mid;
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                dp[index] = nums[i];
            }
        }
        return len;
    }

    public int lengthOfLIS1(int[] nums) {
        // 动态规划 时间复杂度O(N^2) 空间复杂度O(N)
        // 设dp[i]为以i位置数字为结尾的自增子序列最大长度
        int[] dp = new int[nums.length];
        // 所有的单个数字都是1
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeZeroZero().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
