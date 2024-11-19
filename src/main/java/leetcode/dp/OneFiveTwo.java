package leetcode.dp;

/**
 * 乘积最大子数组
 *
 * @author zengxi.song
 * @date 2024/4/16
 */
public class OneFiveTwo {

    public int maxProduct(int[] nums) {
        int length = nums.length;
        // 使用两个变量代替数组 减少空间复杂度
        int max = nums[0], min = nums[0];
        int preMax = max;
        int res = nums[0];
        for (int i = 1; i < length; i++) {
            // 将正负值分开处理
            if (nums[i] >= 0) {
                max = Math.max(max * nums[i], nums[i]);
                min = Math.min(min * nums[i], nums[i]);
            } else {
                max = Math.max(min * nums[i], nums[i]);
                min = Math.min(preMax * nums[i], nums[i]);
            }
            preMax = max;
            res = Math.max(max, res);
        }
        return res;
    }

    public int maxProduct2(int[] nums) {
        int length = nums.length;
        int[] max = new int[length];
        int[] min = new int[length];
        System.arraycopy(nums, 0, max, 0, length);
        System.arraycopy(nums, 0, min, 0, length);
        int res = nums[0];
        for (int i = 1; i < length; ++i) {
            // 正负值一起处理
            max[i] = Math.max(max[i - 1] * nums[i], Math.max(nums[i], min[i - 1] * nums[i]));
            min[i] = Math.min(min[i - 1] * nums[i], Math.min(nums[i], max[i - 1] * nums[i]));
            res = Math.max(max[i], res);
        }
        return res;
    }

    public int maxProduct1(int[] nums) {
        // 动态规划 时间复杂度度O(N) 空间复杂度O(1)
        // 设max[i]为以i下标为结尾的子数组的最大值
        // 设min[i]为以i下标为结尾的子数组的最小值
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= 0) {
                max[i] = Math.max(max[i - 1] * nums[i], nums[i]);
                min[i] = Math.min(min[i - 1] * nums[i], nums[i]);
            } else {
                max[i] = Math.max(min[i - 1] * nums[i], nums[i]);
                min[i] = Math.min(max[i - 1] * nums[i], nums[i]);
            }
            res = Math.max(res, max[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        OneFiveTwo oneFiveTwo = new OneFiveTwo();
        oneFiveTwo.maxProduct(new int[]{4, 3, 2});
    }
}
