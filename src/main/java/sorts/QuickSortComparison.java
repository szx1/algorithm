package sorts;


import java.util.Arrays;
import java.util.Random;

/**
 * @author zengxi.song
 * @date 2024/9/13
 */
public class QuickSortComparison {


    // Hoare partition implementation
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
            swap(arr, i, j);
        }
    }

    // QuickSort using Hoare partition scheme
    public static void quickSortHoare(int[] arr, int low, int high) {
        if (low < high) {
            int p = hoarePartition(arr, low, high);
            quickSortHoare(arr, low, p);       // Sort the left side
            quickSortHoare(arr, p + 1, high);  // Sort the right side
        }
    }

    // Dual-pivot partition (for comparison)
    private static int partitionTwoWay(int[] arr, int low, int high) {
        int pivotIndex = low + (int) (Math.random() * (high - low + 1));
        swap(arr, low, pivotIndex);  // Choose a random pivot and swap with the first element
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
            swap(arr, i, j);
            i++;
            j--;
        }

        swap(arr, low, j);  // Place pivot at the correct position
        return j;  // Return pivot index
    }

    // QuickSort using two-way partition scheme
    public static void quickSortTwoWay(int[] arr, int low, int high) {
        if (low < high) {
            int p = partitionTwoWay(arr, low, high);
            quickSortTwoWay(arr, low, p - 1);   // Sort the left side
            quickSortTwoWay(arr, p + 1, high);  // Sort the right side
        }
    }

    // Helper method to swap elements in an array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Generate an array with random numbers
    private static int[] generateRandomArray(int size, int maxValue) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(maxValue);
        }
        return arr;
    }

    // Generate an array with many duplicate numbers
    private static int[] generateArrayWithDuplicates(int size, int duplicateValue, double ratio) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            if (rand.nextDouble() > ratio) {
                arr[i] = duplicateValue;  // 80% chance to have the same value
            } else {
                arr[i] = rand.nextInt(size / 100);  // Random other values
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();

        QuickSortComparison quickSortComparison = new QuickSortComparison();
        // Test both algorithms on random arrays
        int[] randomArray = generateRandomArray(100000, 1000);
        int[] duplicateArray = generateArrayWithDuplicates(10000000, 500, 1.0);

        // Timing for Hoare partition with random array
        int[] arr1 = Arrays.copyOf(randomArray, randomArray.length);
        long startTime = System.nanoTime();

        quickSortHoare(arr1, 0, arr1.length - 1);


        long endTime = System.nanoTime();
        System.out.println("Hoare QuickSort (random array) took: " + (endTime - startTime) / 1_000_000.0 + " ms");

        // Timing for Two-Way partition with random array
        int[] arr2 = Arrays.copyOf(randomArray, randomArray.length);
        startTime = System.nanoTime();

        quickSortTwoWay(arr2, 0, arr2.length - 1);

        endTime = System.nanoTime();
        System.out.println("Two-Way QuickSort (random array) took: " + (endTime - startTime) / 1_000_000.0 + " ms");

        // Timing for Hoare partition with duplicate array
        int[] arr3 = Arrays.copyOf(duplicateArray, duplicateArray.length);
        startTime = System.nanoTime();
        quickSortComparison.loop(arr -> quickSort.quickSortHoare(arr, 0, arr.length - 1), arr3);
        endTime = System.nanoTime();
        System.out.println("Hoare QuickSort (duplicate array) took: " + (endTime - startTime) / 1_000_000.0 + " ms");

        // Timing for Two-Way partition with duplicate array
        int[] arr4 = Arrays.copyOf(duplicateArray, duplicateArray.length);
        startTime = System.nanoTime();
        quickSortComparison.loop(arr -> quickSort.quickSortTwoWay(arr, 0, arr.length - 1), arr4);
        endTime = System.nanoTime();
        System.out.println("Two-Way QuickSort (duplicate array) took: " + (endTime - startTime) / 1_000_000.0 + " ms");

        int[] arr5 = Arrays.copyOf(duplicateArray, duplicateArray.length);
        startTime = System.nanoTime();
        quickSortComparison.loop(quickSort::sort, arr5);
        endTime = System.nanoTime();
        System.out.println(" QuickSort (duplicate array) took: " + (endTime - startTime) / 1_000_000.0 + " ms");

        int[] arr6 = Arrays.copyOf(duplicateArray, duplicateArray.length);
        startTime = System.nanoTime();
        quickSortComparison.loop((arr) -> quickSort.quickSelect1(arr, 0, arr6.length - 1), arr6);
        endTime = System.nanoTime();
        System.out.println(" QuickSort1 (duplicate array) took: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    private void loop(Test test, int[] arr) {
        for (int i = 0; i < 1; i++) {
            test.test(Arrays.copyOf(arr, arr.length));
        }
    }

    interface Test {
        void test(int[] arr);
    }
}

