package leetcode.dp;

/**
 * 正则表达式匹配
 * 属于动态规划中较难题目，状态转移方程有点难想<br>
 * <a href="https://www.bilibili.com/video/BV1Br4y1v7SA/?spm_id_from=333.788&vd_source=f0cc9fb393ea8368ee6e72359708d425">视频解析</a>
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
                    if (dp[i][j]) {
                        continue;
                    }
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

    public boolean isMatch2(String s, String p) {
        // 记忆化递归 效率和dp不相上下 某些情况比dp还快
        return memoMatch(s, p, 0, 0);
    }

    private Boolean[][] memo;

    public boolean memoMatch(String s, String p, int x, int y) {
        // 初始化 memo 数组
        if (memo == null) {
            memo = new Boolean[s.length() + 1][p.length() + 1];
        }

        // 如果已经计算过，直接返回存储的结果
        if (memo[x][y] != null) {
            return memo[x][y];
        }

        if (y >= p.length()) {
            memo[x][y] = x >= s.length();
            return memo[x][y];
        }

        boolean b = y < p.length() - 1 && p.charAt(y + 1) == '*';
        boolean result;

        if (x >= s.length()) {
            result = b && memoMatch(s, p, x, y + 2);
        } else {
            boolean isMatch = p.charAt(y) == '.' || s.charAt(x) == p.charAt(y);
            if (b) {
                result = memoMatch(s, p, x, y + 2) || (isMatch && memoMatch(s, p, x + 1, y));
            } else {
                result = isMatch && memoMatch(s, p, x + 1, y + 1);
            }
        }

        // 将结果存储在 memo 数组中
        memo[x][y] = result;
        return result;
    }

    public boolean isMatch1(String s, String p) {
        // 递归 有可能超时
        // 这里如果把String转换为字符数组可以提高效率 少一层的方法调用栈
        // leetCode表现 str:540ms charArr:285ms
        return match(s, p, 0, 0);
    }

    public boolean match(String s, String p, int x, int y) {
        if (y >= p.length()) {
            return x >= s.length();
        }
        // 保证y不会为*
        boolean b = y < p.length() - 1 && p.charAt(y + 1) == '*';
        if (x >= s.length()) {
            return b && match(s, p, x, y + 2);
        }
        boolean match = p.charAt(y) == '.' || s.charAt(x) == p.charAt(y);
        if (b) {
            // 兼容后面带* 可以0次匹配
            return match(s, p, x, y + 2) || (match && match(s, p, x + 1, y));
        }
        return match && match(s, p, x + 1, y + 1);
    }

    public static void main(String[] args) {
        System.out.println(new Ten().isMatch("aab", "c*a*b*"));
        System.out.println(new Ten().isMatch("mississippi", "mis*is*p*."));
        System.out.println(new Ten().match("aa", "a", 0, 0));
    }
}
