package leetcode.bs;

/**
 * 搜索旋转排序数组
 *
 * @author zengxi.song
 * @date 2024/9/2
 */
public class ThreeThree {

    public int search(int[] nums, int target) {
        // 二分查找 另一种写法、先判断哪边单调递增 看代码即可 时间复杂度O(logN)
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 判断左右哪边是单调递增
            if (nums[left] <= nums[mid]) {
                // 左侧单调递增
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target && nums[left] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右侧单调递增
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public int search1(int[] nums, int target) {
        // 题目要求使用O(logN)时间复杂度的代码 自然想到二分
        // 还原数组的时间复杂度为O(n) 故我们只能在旋转数组中搜索
        // 我们可以思考 当旋转数组进行二分时
        // 1.两边都递增 此时可以单纯的使用二分
        // 2.一边单调递增 一边依然维持旋转数组 此时我们可以递归的处理下去 直至找到target或者遍历结束
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // 需要判断左侧是否是单调递增
                if (nums[left] <= nums[mid]) {
                    if (nums[left] <= target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    right = mid - 1;
                }
            } else {
                // 需要判断左侧是否是单调递增
                if (nums[left] <= nums[mid]) {
                    left = mid + 1;
                } else {
                    if (nums[right] < target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new ThreeThree().search(new int[]{3, 1}, 1);
    }
}
