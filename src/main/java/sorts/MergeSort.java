package sorts;

/**
 * @author zengxi.song
 * @date 2024/3/28
 */
public class MergeSort implements Sort {
    @Override
    public void sort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
    }

    private static void mergeSort(int[] nums, int start, int end, int[] helper) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid, helper);
            mergeSort(nums, mid + 1, end, helper);
            merge(nums, start, mid, end, helper);
        }
    }

    private static void merge(int[] nums, int start, int mid, int end, int[] helper) {
        System.arraycopy(nums, start, helper, start, end - start + 1);

        int i = start;
        int j = mid + 1;
        int k = start;

        while (i <= mid && j <= end) {
            if (helper[i] <= helper[j]) {
                nums[k++] = helper[i++];
            } else {
                nums[k++] = helper[j++];
            }
        }

        while (i <= mid) {
            nums[k++] = helper[i++];
        }

        while (j <= end) {
            nums[k++] = helper[j++];
        }
    }
}
