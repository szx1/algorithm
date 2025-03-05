package leetcode.dp;

/**
 * 交错字符串
 *
 * @author zengxi.song
 * @date 2025/2/16
 */
public class NineSeven {

    public boolean isInterleave(String s1, String s2, String s3) {
        // 动态规划空间优化版 时间复杂度O(MN) 空间复杂度O(N)
        // 通过方法1可以看到dp[i][j]和i-1 以及j-1有关 其中j-1是本层刚更新的不用管 滚动数组优化
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        char[] char1 = s1.toCharArray(), char2 = s2.toCharArray(), char3 = s3.toCharArray();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i > 0) {
                    dp[j] = dp[j] && char1[i - 1] == char3[i + j - 1];
                }
                if (dp[j]) {
                    continue;
                }
                if (j > 0) {
                    dp[j] = dp[j - 1] && char2[j - 1] == char3[i + j - 1];
                }
            }
        }
        return dp[n];
    }

    public boolean isInterleave1(String s1, String s2, String s3) {
        // 动态规划 时间复杂度O(MN) 空间复杂度O(MN)
        // 设dp[i][j]为s1前i个字符 s2前j个字符 是否可以交错组成s3前i+j个字符
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        char[] char1 = s1.toCharArray(), char2 = s2.toCharArray(), char3 = s3.toCharArray();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (dp[i][j]) {
                    continue;
                }
                if (i > 0) {
                    dp[i][j] = dp[i - 1][j] && char1[i - 1] == char3[i + j - 1];
                }
                if (dp[i][j]) {
                    continue;
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j - 1] && char2[j - 1] == char3[i + j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new NineSeven().isInterleave("a", "", "c"));
    }
}
