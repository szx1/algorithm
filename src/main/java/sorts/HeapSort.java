package sorts;

/**
 * @author zengxi.song
 * @date 2024/3/27
 */
public class HeapSort implements Sort {
    @Override
    public void sort(int[] nums) {
        // 构建大顶堆
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustHeap(nums, nums.length, i);
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, 0, i);
            adjustHeap(nums, i, 0);
        }
    }

    private void adjustHeap(int[] nums, int n, int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest = i;

        if (l < n && nums[l] > nums[largest]) {
            largest = l;
        }
        if (r < n && nums[r] > nums[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(nums, i, largest);
            // 因为和i交换 所以以largest为顶的堆可能不满足 需要重新调整
            adjustHeap(nums, n, largest);
        }
    }
}
