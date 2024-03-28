package sorts;

/**
 * @author zengxi.song
 * @date 2024/3/28
 */
public class SelectionSort implements Sort {
    @Override
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, minIndex, i);
        }
    }
}
