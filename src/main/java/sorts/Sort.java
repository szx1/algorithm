package sorts;

/**
 * @author zengxi.song
 * @date 2024/3/27
 */
public interface Sort {

    void sort(int[] nums);

    default void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
