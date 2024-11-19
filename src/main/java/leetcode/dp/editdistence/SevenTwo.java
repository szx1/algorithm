package leetcode.dp.editdistence;

/**
 * 编辑距离
 *
 * @author zengxi.song
 * @date 2024/9/3
 */
public class SevenTwo {

    public int minDistance(String word1, String word2) {
        // 动态规划 时间复杂度O(MN) 空间复杂度O(MN)
        // 设dp[i][j]为word1的前i个字符需要变为word2的前j个字符的最小改动
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word2.length(); i++) {
            // 添加
            dp[0][i] = i;
        }
        for (int i = 0; i <= word1.length(); i++) {
            // 删除
            dp[i][0] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            // 注意这里如果要比较字符的话 要使用下标减一
            char c = word1.charAt(i - 1);
            for (int j = 1; j <= word2.length(); j++) {
                if (c == word2.charAt(j - 1)) {
                    // 相等 不需要进行操作
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 新增 替换 删除 中找最小的
                    // 新增:dp[i][j-1]
                    // 替换:dp[i-1][j-1]
                    // 删除:dp[i-1][j]
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
