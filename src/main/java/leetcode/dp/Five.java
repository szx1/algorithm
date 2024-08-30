package leetcode.dp;

/**
 * 最长回文子串
 * 和647题类似
 *
 * @author zengxi.song
 * @date 2024/8/28
 */
public class Five {

    public String longestPalindrome(String s) {
        // 双指针中心扩展法 时间复杂度O(n^2) 空间复杂度O(1)
        // 以所有字符为中心进行扩展
        int x = 0, y = 0, max = 1;
        for (int i = 0; i < s.length(); i++) {
            Aux aux1 = expand(s, i, i);
            Aux aux2 = expand(s, i, i + 1);
            if (aux1.y - aux1.x + 1 > max) {
                max = aux1.y - aux1.x + 1;
                x = aux1.x;
                y = aux1.y;
            }

            if (aux2.y - aux2.x + 1 > max) {
                max = aux2.y - aux2.x + 1;
                x = aux2.x;
                y = aux2.y;
            }
        }

        return s.substring(x, y + 1);
    }

    private Aux expand(String s, int index1, int index2) {
        while (index1 >= 0 && index2 < s.length() && s.charAt(index1) == s.charAt(index2)) {
            index1--;
            index2++;
        }
        return new Aux(index1 + 1, index2 - 1);
    }

    private static class Aux {
        int x;
        int y;

        public Aux(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public String longestPalindrome1(String s) {
        // 动态规划 时间复杂度O(n^2) 空间复杂度O(n^2)
        // 设dp[i][j]表示下标i->j能否构成回文子串
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        int max = 1;
        int x = 0, y = 0;
        // 因为dp[i][j]和i+1、j-1有关
        // 所以应该从左下角开始遍历 但是j要大于i所以j从i+1开始
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                boolean equal = s.charAt(i) == s.charAt(j);
                if (j == i + 1) {
                    dp[i][j] = equal;
                } else {
                    dp[i][j] = equal && dp[i + 1][j - 1];
                }

                if (dp[i][j] && (j - i + 1) > max) {
                    max = j - i + 1;
                    x = i;
                    y = j;
                }
            }
        }
        return s.substring(x, y + 1);
    }
}
