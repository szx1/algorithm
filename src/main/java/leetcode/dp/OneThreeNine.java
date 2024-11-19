package leetcode.dp;

import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 单词拆分
 *
 * @author zengxi.song
 * @date 2024/9/5
 */
public class OneThreeNine {

    public boolean wordBreak(String s, List<String> wordDict) {
        // 动态规划 时间复杂度O(MN) 空间复杂度O(M+N)
        // 设dp[i]为s的前i个字符是否可以被wordDict组成
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            // 官方题解是从0遍历到i 这样也可以
            // 取决于wordDict的长度和s的长度二者谁小 建议遍历wordDict 题意中这个比较短
            for (String word : wordSet) {
                if (i < word.length()) {
                    continue;
                }
                int start = i - word.length();
                dp[i] = dp[start] && wordSet.contains(s.substring(start, i));
                if (dp[i]) {
                    // 如果已经可以组成 直接中断即可
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        // 纯递归容易超时 所以我们要采用记忆化 方法一的优化 真正的记忆化 只需要O(N)的空间
        // 定义Boolean[i]为s.subString(i,*)在wordDict中存在
        return contains(s, 0, new HashSet<>(wordDict), new Boolean[s.length() + 1]);
    }

    private boolean contains(String s, int start, Set<String> wordSet, Boolean[] memo) {
        if (memo[start] != null) {
            return memo[start];
        }
        if (start >= s.length()) {
            return true;
        }
        // 注意subString前闭后开 这里要用<=’
        boolean res = false;
        for (int i = start; i <= s.length(); i++) {
            if (wordSet.contains(s.substring(start, i))) {
                boolean contains = contains(s, i, wordSet, memo);
                if (contains) {
                    res = true;
                    break;
                }
            }
        }
        memo[start] = res;
        return memo[start];
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        // 纯递归容易超时 所以我们要采用记忆化
        // 定义Boolean[i][j]为s.subString(i,j)在wordDict中存在
        return contains(s, 0, new HashSet<>(wordDict), new Boolean[s.length() + 1][s.length() + 1]);
    }

    private boolean contains(String s, int start, Set<String> wordSet, Boolean[][] visited) {
        if (start >= s.length()) {
            return true;
        }
        // 注意subString前闭后开 这里要用<=
        for (int i = start; i <= s.length(); i++) {
            if (visited[start][i] != null) {
                return visited[start][i];
            }
            boolean contains = wordSet.contains(s.substring(start, i));
            visited[start][i] = contains;
            if (contains) {
                if (contains(s, i, wordSet, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new OneThreeNine().wordBreak("applepenapple", Lists.newArrayList("apple", "pen"));
    }
}
