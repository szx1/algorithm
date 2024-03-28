package sorts;

import java.util.Random;

/**
 * @author zengxi.song
 * @date 2024/3/27
 */
public class QuickSort implements Sort {

    private static final Random RANDOM = new Random();

    @Override
    public void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int randomIndex = RANDOM.nextInt(end - start + 1) + start;
        swap(nums, randomIndex, start);
        int key = nums[start];
        int i = start, j = end;
        while (start < end) {
            while (start < end && nums[end] >= key) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && nums[start] <= key) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = key;
        quickSort(nums, i, start - 1);
        quickSort(nums, start + 1, j);
    }
}
