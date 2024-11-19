package sorts;

/**
 * 迭代版本的归并排序
 * <br>使用双层循环 第一层遍历本次需要合并的子数组大小 二层进行数组的合并
 * 优点是节省了递归的栈调用 空间复杂度O(1)
 *
 * @author zengxi.song
 * @date 2024/4/1
 */
public class IterationMergeSort extends MergeSort {
    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        int[] helper = new int[nums.length];
        for (int curSize = 1; curSize < n; curSize *= 2) {
            for (int start = 0; start < n; start += 2 * curSize) {
                // 划分两个数组
                int leftEnd = Math.min(n - 1, start + curSize - 1);
                int rightEnd = Math.min(n - 1, start + 2 * curSize - 1);
                merge(nums, start, leftEnd, rightEnd, helper);
            }
        }
    }
}
