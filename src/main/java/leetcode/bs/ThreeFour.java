package leetcode.bs;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * @author zengxi.song
 * @date 2024/7/23
 */
public class ThreeFour {

    public int[] searchRange(int[] nums, int target) {
        // 本题要求时间复杂度为O(logN) 排序数组 故理所当然想到二分查找空间复杂度O(1)
        int leftRange = searchLeftRange(nums, target);
        int rightRange = searchRightRange(nums, target);
        if (leftRange != -1 || rightRange != -1) {
            return new int[]{leftRange, rightRange};
        }
        return new int[]{-1, -1};
    }

    private int searchLeftRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 找左边界 等于也要缩小right
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    private int searchRightRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 找右边界 等于也要缩小left
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }

    public int[] searchRange1(int[] nums, int target) {
        // 双指针 时间复杂度O(N) 空间复杂度O(1)
        int left = 0, right = nums.length - 1;
        while (left <= right && nums[left] < target) {
            left++;
        }
        while (left <= right && nums[right] > target) {
            right--;
        }
        return left <= right ? new int[]{left, right} : new int[]{-1, -1};
    }
}
