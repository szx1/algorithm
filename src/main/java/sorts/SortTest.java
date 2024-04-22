package sorts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author zengxi.song
 * @date 2024/3/27
 */
public class SortTest {

    private final static Map<SortEnum, Sort> INIT_MAP = initMap();

    public static void main(String[] args) {
        int[] nums = initNums(100020);

        test(nums, SortEnum.QUICK);
        test(nums, SortEnum.HEAP);
        test(nums, SortEnum.RECURSION_MERGE);
        test(nums, SortEnum.BUBBLE);
        test(nums, SortEnum.INSERTION);
        test(nums, SortEnum.SELECTION);
    }

    private static boolean checkNums(int[] source, int[] nums) {
        int[] copy = Arrays.copyOf(source, source.length);
        Arrays.sort(copy);
        return Arrays.equals(copy, nums);
    }

    private static int[] initNums(int length) {
        int[] nums = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            nums[i] = random.nextInt(length);
        }
        return nums;
    }

    private static void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static Map<SortEnum, Sort> initMap() {
        Map<SortEnum, Sort> map = new HashMap<>(16);
        map.put(SortEnum.QUICK, new QuickSort());
        map.put(SortEnum.HEAP, new HeapSort());
        map.put(SortEnum.BUBBLE, new BubbleSort());
        map.put(SortEnum.RECURSION_MERGE, new RecursionMergeSort());
        map.put(SortEnum.INSERTION, new InsertionSort());
        map.put(SortEnum.SELECTION, new SelectionSort());
        return map;
    }

    private static void test(int[] nums, SortEnum sortEnum) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        long l = System.currentTimeMillis();
        INIT_MAP.get(sortEnum).sort(copy);
        long l1 = System.currentTimeMillis();
        System.out.println(sortEnum + "  " + (l1 - l) + "ms" + " " + checkNums(nums, copy));
    }
}
