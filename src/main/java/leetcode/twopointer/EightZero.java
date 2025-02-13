package leetcode.twopointer;

/**
 * 删除有序数组中的重复项Ⅱ
 *
 * @author zengxi.song
 * @date 2025/2/12
 */
public class EightZero {

    public int removeDuplicates(int[] nums) {
        // 双指针一次遍历原地修改 时间复杂度O(N) 空间复杂度O(1)
        int i = 2, j = 2;
        while (j < nums.length) {
            // 此时i代表的是即将要填入的下标 nums[i-2]即上一次的组合中的值 所以不允许nums[j]==nums[i-2]
            while (nums[j] == nums[i - 2]) {
                j++;
                if (j == nums.length) {
                    return i;
                }
            }
            nums[i++] = nums[j++];
        }
        return i;
    }

    public static void main(String[] args) {
        new EightZero().removeDuplicates(new int[]{1, 1, 1, 2, 2, 3});
    }
}
