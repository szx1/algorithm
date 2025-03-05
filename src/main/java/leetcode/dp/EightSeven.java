package leetcode.dp;

import java.util.Arrays;

/**
 * 扰乱字符串
 *
 * @author zengxi.song
 * @date 2025/2/15
 */
public class EightSeven {

    public boolean isScramble(String s1, String s2) {
        // 动态规划 时间复杂度O(N^4) 空间复杂度O(N^3)
        // 定义数组dp为s1从下标i开始 s2从下标j开始 的长度为k的字符串是否为扰乱字符串
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];
        // 从长度开始遍历
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                for (int j = 0; j + len <= n; j++) {
                    if (len == 1) {
                        // 长度为1则只需要判断是否相同
                        dp[i][j][len] = char1[i] == char2[j];
                    } else {
                        if (dp[i][j][len]) {
                            continue;
                        }
                        // 需要枚举分割点 有一个分割点为true即可终止
                        for (int k = 1; k < len; k++) {
                            // 不交换
                            if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                                dp[i][j][len] = true;
                                break;
                            }
                            // 交换
                            if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                                dp[i][j][len] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }

    public boolean isScramble1(String s1, String s2) {
        // 记忆化搜索 时间复杂度O(N^4) 空间复杂度O(N^3)
        // 定义数组memo为s1从下标i开始 s2从下标j开始 的长度为k的字符串是否为扰乱字符串
        if (s1.length() != s2.length()) {
            return false;
        }
        Boolean[][][] memo = new Boolean[s1.length()][s2.length()][s1.length() + 1];
        return recursion(s1, s2, 0, 0, s1.length(), memo);
    }

    private boolean recursion(String s1, String s2, int i, int j, int len, Boolean[][][] memo) {
        if (memo[i][j][len] != null) {
            return memo[i][j][len];
        }
        if (this.same(s1, s2, i, j, len)) {
            memo[i][j][len] = true;
            return true;
        }
        if (!this.charSimilarity(s1, s2, i, j, len)) {
            memo[i][j][len] = false;
            return false;
        }
        // 然后循环找分割点
        boolean res = false;
        for (int x = 1; x < len; x++) {
            // 不交换
            if (recursion(s1, s2, i, j, x, memo) && recursion(s1, s2, i + x, j + x, len - x, memo)) {
                res = true;
                break;
            }
            // 交换
            if (recursion(s1, s2, i, j + len - x, x, memo) && recursion(s1, s2, i + x, j, len - x, memo)) {
                res = true;
                break;
            }
        }
        memo[i][j][len] = res;
        return res;
    }

    private boolean same(String s1, String s2, int i, int j, int len) {
        for (int x = 0; x < len; x++) {
            if (s1.charAt(i + x) != s2.charAt(j + x)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断二者字符数是否相同 因为如果二者字符数不相同 则肯定不是扰乱字符串
     */
    private boolean charSimilarity(String s1, String s2, int i, int j, int len) {
        int[] char1 = new int[26];
        for (int x = 0; x < len; x++) {
            char1[s1.charAt(i + x) - 'a']++;
        }
        int[] char2 = new int[26];
        for (int x = 0; x < len; x++) {
            char2[s2.charAt(j + x) - 'a']++;
        }
        return Arrays.equals(char1, char2);
    }

    public static void main(String[] args) {
        System.out.println(new EightSeven().isScramble("abb", "bba"));
    }
}
