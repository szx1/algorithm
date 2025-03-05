package leetcode.dp;

/**
 * 通配符匹配
 *
 * @author zengxi.song
 * @date 2025/2/19
 */
public class FourFour {

    public boolean isMatch(String s, String p) {
        // 贪心 时间复杂度O(MN) s,p是随机字符串实际运行很快 空间复杂度O(1)
        int m = s.length(), n = p.length();
        int i = 0, j = 0;
        // indexS记录遇到*时s的位置 indexP记录最后一个*的位置
        int indexS = -1, indexP = -1;
        char[] charS = s.toCharArray();
        char[] charP = p.toCharArray();
        while (i < m) {
            if (j < n && (charS[i] == charP[j] || charP[j] == '?')) {
                // 当前字符可以匹配
                i++;
                j++;
            } else if (j < n && charP[j] == '*') {
                // 尝试从空字符串开始匹配
                indexS = i;
                indexP = j;
                j++;
            } else if (indexP != -1) {
                // 上一次的*没匹配上 则本次重新开始 但是indexS要+1 代表每次多匹配一个字符
                i = indexS + 1;
                indexS++;
                j = indexP + 1;
            } else {
                return false;
            }
        }
        while (j < p.length()) {
            if (p.charAt(j++) != '*') {
                return false;
            }
        }
        return true;
    }

    public boolean isMatch5(String s, String p) {
        // 动态规划
        // 方法4还可以再优化 可以看出dp和[i-1][j-1]以及[i][j-1]有关
        // 也就是说j-1需要记录两次 需要两个一维数组 达到空间复杂度O(N)
        int m = s.length(), n = p.length();
        boolean[] dp1 = new boolean[n + 1];
        boolean[] dp2 = new boolean[n + 1];
        char[] charS = s.toCharArray(), charP = p.toCharArray();
        // 初始化
        dp1[0] = true;
        for (int i = 1; i <= n; i++) {
            if (charP[i - 1] != '*') {
                break;
            }
            dp1[i] = true;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (charP[j - 1] == '*') {
                    // 一直保持这个公式 不需要在遍历了
                    dp2[j] = dp1[j] || dp2[j - 1];
                } else if (charP[j - 1] == '?') {
                    dp2[j] = dp1[j - 1];
                } else {
                    dp2[j] = charS[i - 1] == charP[j - 1] && dp1[j - 1];
                }
            }
            // 交换 准备下一行的计算
            boolean[] tmp = dp1;
            dp1 = dp2;
            dp2 = tmp;
            // 做一个特殊处理
            dp2[0] = false;
        }
        return dp1[n];
    }

    public boolean isMatch4(String s, String p) {
        // 动态规划 leetCode 14ms
        // 设dp[i][j]为s的前i个字符能够被p的前j个字符匹配
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        char[] charS = s.toCharArray(), charP = p.toCharArray();
        // 初始化
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (charP[i - 1] != '*') {
                break;
            }
            dp[0][i] = true;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (charP[j - 1] == '*') {
                    // 一直保持这个公式 不需要在遍历了
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (charP[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = charS[i - 1] == charP[j - 1] && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public boolean isMatch3(String s, String p) {
        // 动态规划 leetCode 88ms 没有记忆化搜索快 但是空间会小一些 没有递归栈
        // 设dp[i][j]为s的前i个字符能够被p的前j个字符匹配
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        char[] charS = s.toCharArray(), charP = p.toCharArray();
        // 初始化
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (charP[i - 1] != '*') {
                break;
            }
            dp[0][i] = true;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (charP[j - 1] == '*') {
                    // i之前的所有字符串 有一个和j-1可以完全匹配即可
                    for (int k = 0; k <= i; k++) {
                        if (dp[k][j - 1]) {
                            dp[i][j] = true;
                            break;
                        }
                    }
                } else if (charP[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = charS[i - 1] == charP[j - 1] && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public boolean isMatch2(String s, String p) {
        // 记忆化递归 leetCode 53ms
        return memoRecursion(s.toCharArray(), p.toCharArray(), 0, 0, new Boolean[s.length() + 1][p.length() + 1]);
    }

    private boolean memoRecursion(char[] s, char[] p, int i, int j, Boolean[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        // i到达尽头
        if (i >= s.length) {
            // 遍历j 只有都是*才有效
            int tmp = j;
            while (j < p.length) {
                if (p[j++] != '*') {
                    while (tmp++ < j) {
                        memo[i][tmp] = false;
                    }
                    return false;
                }
            }
            return memo[i][j] = true;
        }
        // 除了*外 p到达尽头则s必须到达尽头
        if (j >= p.length) {
            return memo[i][j] = (i >= s.length);
        }
        // 单一匹配
        if (p[j] == '?') {
            return memo[i][j] = memoRecursion(s, p, i + 1, j + 1, memo);
        }
        // 可变长度匹配
        if (p[j] == '*') {
            // 在最后直接无敌
            if (j == p.length - 1) {
                return memo[i][j] = true;
            }
            // 这个东西可以匹配任意长度的字符序列 有点变态啊
            for (int x = i; x < s.length; x++) {
                // 有一个下标可以与p的后续匹配均可
                if (memoRecursion(s, p, x, j + 1, memo)) {
                    return memo[i][j] = true;
                }
            }

        }
        return memo[i][j] = (s[i] == p[j] && memoRecursion(s, p, i + 1, j + 1, memo));
    }

    public boolean isMatch1(String s, String p) {
        // 先用递归试试超不超时 通过率 1616/1811
        // 换成charArray 通过率 1638/1811 比字符串好点 可见转成数组还是可以
        return recursion(s, p, 0, 0);
    }

    private boolean recursion(String s, String p, int i, int j) {
        // i到达尽头
        if (i >= s.length()) {
            // 遍历j 只有都是*才有效
            while (j < p.length()) {
                if (p.charAt(j++) != '*') {
                    return false;
                }
            }
        }
        // 除了*外 p到达尽头则s必须到达尽头
        if (j >= p.length()) {
            return i >= s.length();
        }
        // 单一匹配
        if (p.charAt(j) == '?') {
            return recursion(s, p, i + 1, j + 1);
        }
        // 可变长度匹配
        if (p.charAt(j) == '*') {
            // 在最后直接无敌
            if (j == p.length() - 1) {
                return true;
            }
            // 这个东西可以匹配任意长度的字符序列 有点变态啊
            for (int x = i; x < s.length(); x++) {
                // 有一个下标可以与p的后续匹配均可
                if (recursion(s, p, x, j + 1)) {
                    return true;
                }
            }

        }
        return s.charAt(i) == p.charAt(j) && recursion(s, p, i + 1, j + 1);
    }

    public static void main(String[] args) {
//        System.out.println(new FourFour().isMatch("adceb", "*a*b"));
        System.out.println(new FourFour().isMatch("bbbaab", "a**?***"));
    }
}
