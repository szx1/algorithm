package leetcode.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 *
 * @author zengxi.song
 * @date 2024/8/30
 */
public class OneFive {

    public List<List<Integer>> threeSum(int[] nums) {
        // 排序加双指针 时间复杂度O(n^2) 空间复杂度O(n)
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0 || (left > i + 1 && nums[left] == nums[left - 1])) {
                    left++;
                } else if (sum > 0 || (right < nums.length - 1 && nums[right] == nums[right + 1])) {
                    right--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new OneFive().threeSum(new int[]{0, 0, 0, 0});
    }
}
