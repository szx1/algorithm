package leetcode.bs;

/**
 * 搜索插入位置
 *
 * @author zengxi.song
 * @date 2024/7/23
 */
public class ThreeFive {

    public int searchInsert(int[] nums, int target) {
        // 时间复杂度O(N)的算法很简单 就不做了
        // 排序数组 很容易就能想到使用二分查找 时间复杂度O(logN) 空间复杂度O(1)
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
