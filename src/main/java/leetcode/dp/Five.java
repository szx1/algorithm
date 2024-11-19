package leetcode.dp;

/**
 * 最长回文子串
 * 和647题类似
 * 马拉车算法(Manacher)可以在线性时间计算回文串
 *
 * @author zengxi.song
 * @date 2024/8/28
 */
public class Five {

    public String longestPalindrome(String s) {
        // 马拉车算法 时间复杂度O(N) 空间复杂度O(N)
        // 奇偶统一处理
        String str = this.preprocess(s);
        // len[i]记录下标为i的字符最长回文字符串的半径(不包括自身) 避免重复计算
        int[] len = new int[str.length()];
        int start = 0, end = 0;
        // center表示当前最大右边界回文串的中心 right表示当前最大右边界
        int center = 0, right = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i < right) {
                len[i] = Math.min(len[2 * center - i], right - i);
            }
            // 从i+len[i]和i-len[i]开始中心扩展
            int x = i - len[i] - 1, y = i + len[i] + 1;
            while (y < str.length() && x >= 0 && str.charAt(x) == str.charAt(y)) {
                len[i]++;
                x--;
                y++;
            }
            if (i + len[i] > right) {
                center = i;
                right = i + len[i];
            }
            if (2 * len[i] + 1 > end - start) {
                end = i + len[i];
                start = i - len[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (str.charAt(i) != '#') {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    private String preprocess(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }
        return sb.toString();
    }

    public String longestPalindrome2(String s) {
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

    public static void main(String[] args) {
        new Five().longestPalindrome("abb");
    }
}
