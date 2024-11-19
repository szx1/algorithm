package leetcode.dp.tree;

/**
 * 不同的二叉搜索树
 *
 * @author zengxi.song
 * @date 2024/9/4
 */
public class NineSix {

    public int numTrees(int n) {
        // 树形的动态规划
        // 以x为root,则左侧为1~x-1右侧为x+1~n
        // 设dp[i]为i个数字能组成二叉搜索树的个数
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // j就代表左子树的个数 则右侧为i-j-1
                dp[i] += dp[i - j - 1] * dp[j];
            }
        }
        return dp[n];
    }
}
