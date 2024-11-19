package leetcode.presum;

/**
 * 除自身以外数组的乘积
 * 要求时间复杂度O(N)
 *
 * @author zengxi.song
 * @date 2024/9/9
 */
public class TwoThreeEight {

    public int[] productExceptSelf(int[] nums) {
        // 前缀加后缀和 进阶 要求空间复杂度O(1) 其中不算结果数组
        // 我们借助结果数组来保存前缀乘积 然后借助一个常量记录后缀 再从后往前来覆盖res数组
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int suffix = 1;
        for (int i = nums.length - 1; i >= 1; i--) {
            res[i] = res[i] * suffix;
            suffix *= nums[i];
        }
        res[0] = suffix;
        return res;
    }

    public int[] productExceptSelf1(int[] nums) {
        // 前缀加后缀和 空间复杂度O(N)
        int[] pre = new int[nums.length];
        pre[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }
        int[] suffix = new int[nums.length];
        suffix[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = pre[i] * suffix[i];
        }
        return res;
    }

    public static void main(String[] args) {
        new TwoThreeEight().productExceptSelf(new int[]{1, 2, 3, 4});
    }
}
