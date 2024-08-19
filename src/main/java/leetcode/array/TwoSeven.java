package leetcode.array;

/**
 * 移除元素
 *
 * @author zengxi.song
 * @date 2024/7/4
 */
public class TwoSeven {

    public int removeElement(int[] nums, int val) {
        // 双指针 时间复杂度O(n) 空间复杂度O(1)
        int i = 0, j = 0;
        while (j < nums.length) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
            j++;
        }
        return i;
    }
}
