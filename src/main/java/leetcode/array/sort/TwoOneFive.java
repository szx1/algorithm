package leetcode.array.sort;

import util.ArrayUtil;

import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 *
 * @author zengxi.song
 * @date 2024/9/6
 */
public class TwoOneFive {

    public int findKthLargest(int[] nums, int k) {
        // 自己实现堆 构建大顶堆 时间复杂度O(klogN) 空间复杂度O(logN) 不借用额外数组 只有递归的栈空间
        // 自底向上构建堆
        for (int i = nums.length / 2; i >= 0; i--) {
            adjustHeap(i, nums.length, nums);
        }
        for (int i = nums.length - 1; i > nums.length - k; i--) {
            ArrayUtil.swap(nums, i, 0);
            adjustHeap(0, i, nums);
        }
        return nums[0];
    }

    private void adjustHeap(int index, int length, int[] nums) {
        int l = 2 * index + 1;
        int r = 2 * index + 2;
        int largeIndex = index;
        if (l < length && nums[l] > nums[largeIndex]) {
            largeIndex = l;
        }
        if (r < length && nums[r] > nums[largeIndex]) {
            largeIndex = r;
        }
        if (largeIndex != index) {
            ArrayUtil.swap(nums, index, largeIndex);
            adjustHeap(largeIndex, length, nums);
        }
    }

    public int findKthLargest3(int[] nums, int k) {
        // 使用内置的优先队列 时间复杂度O(klogN) 空间复杂度O(N)
        // 优化一下正序还是倒序(应该能减少下时间)
        PriorityQueue<Integer> priorityQueue;
        if (k > nums.length / 2) {
            priorityQueue = new PriorityQueue<>((o1, o2) -> o1 - o2);
            k = nums.length - k + 1;
        } else {
            priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        }
        for (int num : nums) {
            priorityQueue.add(num);
        }
        int res = 0;
        while (k > 0 && !priorityQueue.isEmpty()) {
            res = priorityQueue.poll();
            k--;
        }
        return res;
    }

    public int findKthLargest2(int[] nums, int k) {
        // 快速选择算法 即快速排序思想 时间复杂度O(N)(期望为线性的选择算法,平均O(N),最坏O(N^2)) 空间复杂度O(logN)
        // 优化存在大量重复数字的情况 这种又叫做双路快速排序
        return quickSelect1(nums, 0, nums.length - 1, k);
    }

    private int quickSelect1(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[start];
        }
        int key = nums[start];
        int left = start + 1, right = end;
        while (left <= right) {
            // 这里要用left<=right 因为我们打算使用right作为基准 则必须让当前right处于小于等于key
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
        if (end - right == k - 1) {
            return nums[right];
        }
        if (end - right > k - 1) {
            return quickSelect1(nums, right + 1, end, k);
        } else {
            return quickSelect1(nums, start, right - 1, k - (end - right + 1));
        }
    }

    public int findKthLargest1(int[] nums, int k) {
        // 快速选择算法 即快速排序思想 时间复杂度O(N)(期望为线性的选择算法,平均O(N),最坏O(N^2)) 空间复杂度O(logN)
        // 和官解思路一致但是时间是他的200倍 .....肯定有特殊用例恶心人 存在大量重复数字
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[start];
        }
        int key = nums[start];
        int left = start, right = end;
        while (left < right) {
            while (left < right && nums[right] >= key) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= key) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = key;
        if (end - left == k - 1) {
            return nums[left];
        }
        if (end - left > k - 1) {
            return quickSelect(nums, left + 1, end, k);
        } else {
            return quickSelect(nums, start, left - 1, k - (end - left + 1));
        }
    }

    public static void main(String[] args) {
        new TwoOneFive().findKthLargest2(new int[]{3,2,3,1,2,4,5,5,6}, 4);
    }
}
