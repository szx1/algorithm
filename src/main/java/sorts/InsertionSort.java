package sorts;

/**
 * 插入排序通过构建有序序列，对于未排序的数据，依次将其插入到有序序列中的适当位置，从而得到一个新的有序序列。
 *
 * @author zengxi.song
 * @date 2024/3/28
 */
public class InsertionSort implements Sort {
    @Override
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int val = nums[i], j = i;
            // 认为i前面的是已经排序过的
            while (j > 0 && val < nums[j - 1]) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = val;
        }
    }
}
