package leetcode.dp;

/**
 * 回文子串
 * 马拉车算法依然可以使用
 *
 * @author zengxi.song
 * @date 2024/8/28
 */
public class SixFourSeven {

    public int countSubstrings(String s) {
        // 马拉车算法 时间复杂度O(N) 空间复杂度O(N)
        int res = 0;
        // len[i]记录以i为中心的最大半径
        s = this.preprocess(s);
        int[] len = new int[s.length()];
        int center = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i < right) {
                len[i] = Math.min(len[2 * center - i], right - i);
            }
            // 把自己加进来 注意添加的#
            if (s.charAt(i) != '#') {
                res++;
            }
            int x = i - len[i] - 1, y = i + len[i] + 1;
            while (x >= 0 && y < s.length() && s.charAt(x) == s.charAt(y)) {
                len[i]++;
                x--;
                y++;
            }
            // 半径为多少代表有多少个回文串
            // 这里/2即可是因为 回文串永远以#结尾 #会与字母数相等或者大一个 /2保证就是字母的数量
            res += len[i] / 2;
            if (i + len[i] > right) {
                right = i + len[i];
                center = i;
            }
        }
        return res;
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

    public int countSubstrings2(String s) {
        // 中心扩展法 时间复杂度O(N^2) 空间复杂度O(1)
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += expand(s, i, i);
            // 可以直接调用 expand会校验范围
            res += expand(s, i, i + 1);
        }
        return res;
    }

    private int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left--) == s.charAt(right++)) {
                count++;
            }
        }
        return count;
    }

    public int countSubstrings1(String s) {
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

    public static void main(String[] args) {
        new SixFourSeven().countSubstrings("abc");
    }
}
