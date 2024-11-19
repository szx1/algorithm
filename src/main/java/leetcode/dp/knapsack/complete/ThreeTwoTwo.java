package leetcode.dp.knapsack.complete;

/**
 * 零钱兑换
 *
 * @author zengxi.song
 * @date 2024/8/17
 */
public class ThreeTwoTwo {

    public int coinChange(int[] coins, int amount) {
        // 动态规划优化
        // 我们可以看到dp数组的变化只和i-1和i有关 所以可以使用一维数组优化
        int[] dp = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[i] = i / coins[0];
            } else {
                dp[i] = amount + 1;
            }
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChange3(int[] coins, int amount) {
        // 动态规划
        // 定义dp[i][j]为前i个硬币能凑够j容量的最小数目
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[0][i] = i / coins[0];
            } else {
                dp[0][i] = amount + 1;
            }
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i]] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length - 1][amount] > amount ? -1 : dp[coins.length - 1][amount];
    }

    public int coinChange2(int[] coins, int amount) {
        // 记忆化搜索
        return memoSearch(coins, amount, new int[amount + 1]);
    }

    private int memoSearch(int[] coins, int amount, int[] memo) {
        // 记忆化搜索
        if (amount == 0) {
            return 0;
        }
        if (memo[amount] != 0) {
            return memo[amount];
        }
        int res = amount + 1;
        for (int coin : coins) {
            if (amount >= coin) {
                int change = memoSearch(coins, amount - coin, memo);
                if (change >= 0) {
                    res = Math.min(res, change + 1);
                }
            }
        }
        memo[amount] = res > amount ? -1 : res;
        return res > amount ? -1 : res;
    }

    public int coinChange1(int[] coins, int amount) {
        // 尝试用递归写一下 leetCode超时 可优化为记忆化搜索
        if (amount == 0) {
            return 0;
        }
        int res = amount + 1;
        for (int coin : coins) {
            if (amount >= coin) {
                int change = coinChange1(coins, amount - coin);
                if (change >= 0) {
                    res = Math.min(res, change + 1);
                }
            }
        }
        return res > amount ? -1 : res;
    }

    public static void main(String[] args) {
        new ThreeTwoTwo().coinChange1(new int[]{1, 2, 5}, 11);
    }
}
