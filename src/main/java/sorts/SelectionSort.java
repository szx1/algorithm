package sorts;

/**
 * 选择排序的思想是每次从未排序的部分中选出最小（或最大）的元素，将其放到已排序部分的末尾
 *
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
