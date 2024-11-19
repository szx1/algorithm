package sorts;

/**
 * @author zengxi.song
 * @date 2024/3/28
 */
public class RecursionMergeSort extends MergeSort {
    @Override
    public void sort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
    }

    private void mergeSort(int[] nums, int start, int end, int[] helper) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid, helper);
            mergeSort(nums, mid + 1, end, helper);
            merge(nums, start, mid, end, helper);
        }
    }
}
