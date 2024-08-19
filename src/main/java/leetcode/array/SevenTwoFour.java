package leetcode.array;

/**
 * 寻找数组的中心下标
 *
 * @author zengxi.song
 * @date 2024/7/22
 */
public class SevenTwoFour {

    public int pivotIndex(int[] nums) {
        // 前缀和 不使用辅助数组加提前终止 时间复杂度O(N) 空间复杂度O(1)
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }

    public int pivotIndex1(int[] nums) {
        // 前缀和 使用辅助数组 时间复杂度O(N) 空间复杂度O(N)
        int[] pre = new int[nums.length];
        pre[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            int sum = pre[nums.length - 1] - pre[i];
            if (i == 0) {
                if (sum == 0) {
                    res = i;
                    break;
                }
            } else if (pre[i - 1] == sum) {
                res = i;
                break;
            }
        }
        return res;
    }
}
