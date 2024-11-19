package leetcode.twopointer;

/**
 * 移动零
 *
 * @author zengxi.song
 * @date 2024/9/9
 */
public class TwoEightThree {

    public void moveZeroes(int[] nums) {
        // 本题要求原地操作 可以采用双指针
        // 时间复杂度O(N) 空间复杂度O(1)
        int left = 0;
        int right = 0;
        // 另一种方式 right不为0相当于原地交换 left只有在为0的时候交换
        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }

    public void moveZeroes1(int[] nums) {
        // 本题要求原地操作 可以采用双指针
        // 时间复杂度O(N) 空间复杂度O(1)
        int i = 0, j = 0;
        while (j < nums.length) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
            j++;
        }
        for (int k = i; k < nums.length; k++) {
            nums[k] = 0;
        }
    }
}
