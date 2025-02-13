package leetcode.twopointer;

/**
 * 删除有序数组中的重复项
 *
 * @author zengxi.song
 * @date 2025/2/12
 */
public class TwoSix {

    public int removeDuplicates(int[] nums) {
        // 双指针一次遍历原地修改 时间复杂度O(N) 空间复杂度O(1)
        int i = 1, j = 1;
        while (j < nums.length) {
            while (nums[j] == nums[j - 1]) {
                j++;
                if (j == nums.length) {
                    return i;
                }
            }
            nums[i++] = nums[j++];
        }
        return i;
    }
}
