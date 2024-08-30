package leetcode.dp;

/**
 * 回文子串
 *
 * @author zengxi.song
 * @date 2024/8/28
 */
public class SixFourSeven {

    public int countSubstrings(String s) {
        // 动态规划 时间复杂度O(n^2) 空间复杂度O(n^2)
        // 设dp[i][j]为下标i->j的字符是否为回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            res++;
        }
        // 左下角开始遍历
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                boolean equal = s.charAt(i) == s.charAt(j);
                if (j == i + 1) {
                    dp[i][j] = equal;
                } else {
                    dp[i][j] = equal && dp[i + 1][j - 1];
                }
                if (dp[i][j]) {
                    res++;
                }
            }
        }
        return res;
    }
}
