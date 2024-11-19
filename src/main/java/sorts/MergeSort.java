package sorts;

/**
 * @author zengxi.song
 * @date 2024/9/18
 */
public abstract class MergeSort implements Sort {

    public void merge(int[] nums, int start, int mid, int end, int[] helper) {
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
