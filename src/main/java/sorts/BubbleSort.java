package sorts;

/**
 * @author zengxi.song
 * @date 2024/3/27
 */
public class BubbleSort implements Sort {
    @Override
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j - 1] > nums[j]) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }
}
