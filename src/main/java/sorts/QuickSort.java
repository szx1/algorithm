package sorts;

import util.ArrayUtil;

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

    public void quickSortHoare(int[] arr, int low, int high) {
        if (low < high) {
            int p = hoarePartition(arr, low, high);
            quickSortHoare(arr, low, p);       // Sort the left side
            quickSortHoare(arr, p + 1, high);  // Sort the right side
        }
    }

    private static int hoarePartition(int[] arr, int low, int high) {
        int pivot = arr[low]; // Choose the first element as the pivot
        int i = low - 1;
        int j = high + 1;

        while (true) {
            // Find the first element greater than or equal to pivot
            do {
                i++;
            } while (arr[i] < pivot);

            // Find the first element smaller than or equal to pivot
            do {
                j--;
            } while (arr[j] > pivot);

            if (i >= j) {
                return j;  // Return the partition point
            }

            // Swap the elements at i and j
            ArrayUtil.swap(arr, i, j);
        }
    }

    // Dual-pivot partition (for comparison)
    private static int partitionTwoWay(int[] arr, int low, int high) {
        int pivotIndex = low + (int) (Math.random() * (high - low + 1));
        ArrayUtil.swap(arr, low, pivotIndex);  // Choose a random pivot and swap with the first element
        int pivot = arr[low];
        int i = low + 1;
        int j = high;

        while (true) {
            while (i <= high && arr[i] < pivot) {
                i++;
            }
            while (j >= low + 1 && arr[j] > pivot) {
                j--;
            }

            if (i > j) {
                break;
            }
            ArrayUtil.swap(arr, i, j);
            i++;
            j--;
        }

        ArrayUtil.swap(arr, low, j);  // Place pivot at the correct position
        return j;  // Return pivot index
    }

    // QuickSort using two-way partition scheme
    public void quickSortTwoWay(int[] arr, int low, int high) {
        if (low < high) {
            int p = partitionTwoWay(arr, low, high);
            quickSortTwoWay(arr, low, p - 1);   // Sort the left side
            quickSortTwoWay(arr, p + 1, high);  // Sort the right side
        }
    }

    /**
     * 双路快速排序 在重复数据较多的时候拥有较稳定的性能
     *
     * @param nums
     * @param start
     * @param end
     */
    public void quickSelect1(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int key = nums[start];
        int left = start + 1, right = end;
        while (left <= right) {
            while (left <= right && nums[right] > key) {
                right--;
            }
            while (left <= right && nums[left] < key) {
                left++;
            }
            if (left <= right) {
                // 这里兼容数字重复的情况 也就是说即使数字重复left和right依然会更新
                ArrayUtil.swap(nums, left, right);
                left++;
                right--;
            }

        }
        ArrayUtil.swap(nums, start, right);
        quickSelect1(nums, right + 1, end);
        quickSelect1(nums, start, right - 1);
    }

    public static void main(String[] args) {
        int[] nums = {7, 6, 5, 4, 3, 2, 1};
        new QuickSort().quickSelect1(nums, 0, nums.length - 1);
        System.out.println(1);
    }
}
