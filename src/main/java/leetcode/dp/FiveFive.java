package leetcode.dp;

/**
 * 跳跃游戏
 *
 * @author zengxi.song
 * @date 2024/9/2
 */
public class FiveFive {

    public boolean canJump(int[] nums) {
        // 贪心 时间复杂度O(N) 空间复杂度O(1)
        // 只要最终可到达的位置大于nums的长度 即返回true
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            // 说明可以到达
            if (i <= max) {
                max = Math.max(max, i + nums[i]);
            }
            if (max >= nums.length) {
                return true;
            }
        }
        return false;
    }

    public boolean canJump2(int[] nums) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
        // 定义dp[i]为经过i节点最远可以到达的距离
        if (nums.length == 1 || nums[0] >= nums.length - 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + i);
            if (dp[i] >= n - 1) {
                return true;
            }
            if (dp[i] == i) {
                return false;
            }
        }
        return true;
    }

    public boolean canJump1(int[] nums) {
        // 动态规划?有点怀疑是暴力了 时间复杂度O(NM) M为数字的大小 空间复杂度O(N)
        // 定义dp[i]为是否可以从0到达i处
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            if (dp[i]) {
                for (int j = 1; j <= nums[i]; j++) {
                    if (i + j < n) {
                        dp[i + j] = true;
                    }
                }
                // 提前终止
                if (dp[n - 1]) {
                    return true;
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        new FiveFive().canJump(new int[]{2, 3, 1, 1, 4});
    }
}
