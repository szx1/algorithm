package leetcode.bit;

/**
 * 比特位计数
 *
 * @author zengxi.song
 * @date 2024/9/11
 */
public class ThreeThreeEight {

    public int[] countBits(int n) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(1)
        // dp数组就是结果数组 不增加空间复杂度
        // 最低设置位 在做191题的时候我们已经知道n&n-1可以去掉最后一个1
        // dp[i]可以通过dp[i-(i&i-1)]+1推出
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            res[i] = res[i & (i - 1)] + 1;
        }
        return res;
    }

    public int[] countBits3(int n) {
        // 动态规划 时间复杂度O(N) 空间复杂度O(1)
        // dp数组就是结果数组 不增加空间复杂度
        // 最低有效位 dp[i]可以通过dp[i/2]推出 只需要在最后面加上1或者0
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }

    public int[] countBits2(int n) {
        // 位运算 时间复杂度O(NlogN) 空间复杂度O(1)
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int temp = i;
            int count = 0;
            while (temp != 0) {
                count++;
                temp &= temp - 1;
            }
            res[i] = count;
        }
        return res;
    }

    public int[] countBits1(int n) {
        // 位运算 时间复杂度O(Nk) 空间复杂度O(1) 这里的k是最高1的位数 可参照191题
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int temp = i;
            int count = 0;
            while (temp > 0) {
                if ((temp & 1) == 1) {
                    count++;
                }
                temp = temp >> 1;
            }
            res[i] = count;
        }
        return res;
    }

    public static void main(String[] args) {
        new ThreeThreeEight().countBits(2);
    }
}
