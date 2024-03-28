package sorts;

/**
 * @author zengxi.song
 * @date 2024/3/28
 */
public class InsertionSort implements Sort {
    @Override
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int val = nums[i], j = i;
            while (j > 0 && val < nums[j - 1]) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = val;
        }
    }
}
