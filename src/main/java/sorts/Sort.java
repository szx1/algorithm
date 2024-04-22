package sorts;

import util.ArrayUtil;

/**
 * @author zengxi.song
 * @date 2024/3/27
 */
public interface Sort {

    void sort(int[] nums);

    default void swap(int[] nums, int i, int j) {
        ArrayUtil.swap(nums, i, j);
    }
}
