package util;

/**
 * @author zengxi.song
 * @date 2024/4/12
 */
public class ArrayUtil {

    /**
     * 交换数组元素
     *
     * @param nums
     * @param i
     * @param j
     */
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
