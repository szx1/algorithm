package leetcode.dp;

/**
 * 最长有效括号
 *
 * @author zengxi.song
 * @date 2024/8/19
 */
public class ThreeTwo {

    public int longestValidParentheses(String s) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(N)
        // 定义dp[i]为s的以下标i为结尾的字符的最长有效括号长度
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                continue;
            }
            // 只对')'结尾做更新
            if (s.charAt(i - 1) == '(') {
                dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
            } else {
                // i-1为) 比较复杂
                // 需要判断i减去dp[i-1]是否为(
                int left = i - dp[i - 1] - 1;
                if (left >= 0 && s.charAt(left) == '(') {
                    dp[i] = dp[i - 1] + 2;
                    // 还需要判断前面是否能续上
                    if (left - 1 >= 0) {
                        dp[i] += dp[left - 1];
                    }
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
