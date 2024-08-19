package leetcode.array;

/**
 * 长度最小的子数组
 *
 * @author zengxi.song
 * @date 2024/7/5
 */
public class TwoZeroNine {

    public int minSubArrayLen(int target, int[] nums) {
        // 双指针 时间复杂度O(N) 空间复杂度O(1)
        int sum = 0, left = 0, right = 0;
        int res = Integer.MAX_VALUE;
        while (left < nums.length && right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public int minSubArrayLen1(int target, int[] nums) {
        // 前缀和加二分查找 时间复杂度O(NlogN) 空间复杂度O(N)
        int[] pre = new int[nums.length + 1];
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            int index = this.binarySearch(pre, i, nums.length, pre[i] + target);
            if (index <= nums.length) {
                res = Math.min(res, index - i);
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        // 利用二分查找 找出第一个大于等于target的值的下标
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    public static void main(String[] args) {
        TwoZeroNine twoZeroNine = new TwoZeroNine();
        twoZeroNine.minSubArrayLen(15, new int[]{1, 2, 3, 4, 5});
        twoZeroNine.minSubArrayLen(15, new int[]{5, 1, 3, 5, 10, 7, 4, 9, 2, 8});
    }
}
