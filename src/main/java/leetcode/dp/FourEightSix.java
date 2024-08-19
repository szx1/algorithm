package leetcode.dp;

/**
 * 预测赢家
 *
 * @author zengxi.song
 * @date 2024/8/16
 */
public class FourEightSix {

    public boolean predictTheWinner(int[] nums) {
        // 动态规划
        // 定义dp[i][j]代表从下标i到j能获取到的最大净胜分
        int n = nums.length;
        int[][] dp = new int[n][n];
        // dp数组初始化 当下标相等时 只有先手方可以拿
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        // 只有j>i才有意义
        // 递推公式决定遍历顺序
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 递归公式之所以为减，是因为当前获取的最大净胜分=当前选取的分数-之后能获取的最大净胜分(因为后手方也不是傻子，肯定拿最优的)
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }

    public boolean predictTheWinner1(int[] nums) {
        // 试试递归会不会超时
        // 时间复杂度O(2^n) 空间复杂度O(n)
        return recursion(nums, 0, nums.length - 1) >= 0;
    }

    /**
     * 定义递归函数的返回值为两个选手所拿元素的差值
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int recursion(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int selectLeft = nums[start] - recursion(nums, start + 1, end);
        int selectRight = nums[end] - recursion(nums, start, end - 1);
        return Math.max(selectLeft, selectRight);
    }

    public static void main(String[] args) {
        System.out.println(new FourEightSix().predictTheWinner(new int[]{1, 5, 233, 7}));
        System.out.println(new FourEightSix().predictTheWinner(new int[]{1, 5, 2}));
    }
}
