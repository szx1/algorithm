package leetcode.dp;

/**
 * 正则表达式匹配
 * 属于动态规划中较难题目，状态转移方程有点难想
 * 视频解析:https://www.bilibili.com/video/BV1Br4y1v7SA/?spm_id_from=333.788&vd_source=f0cc9fb393ea8368ee6e72359708d425
 *
 * @author zengxi.song
 * @date 2024/8/19
 */
public class Ten {

    public boolean isMatch(String s, String p) {
        // 动态规划
        // 定义dp[i][j]为s的前i个字符和p的前j个字符是否可以匹配
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // 因为p的*允许0次 所以i从0开始迭代
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (i >= 1) {
                        dp[i][j] |= dp[i - 1][j] && equal(s, p, i, j - 1);
                    }
                } else {
                    if (equal(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    private boolean equal(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        return p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Ten().isMatch("aab", "c*a*b*"));
        System.out.println(new Ten().isMatch("mississippi", "mis*is*p*."));
    }
}
