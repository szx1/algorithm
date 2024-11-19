package leetcode.bs;

/**
 * 寻找两个正序数组的中位数
 *
 * @author zengxi.song
 * @date 2024/8/23
 */
public class Four {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 题目要求时间复杂度是log级别 再加上排序数组 可以使用二分查找
        // 时间复杂度O(log(m+n)) 空间复杂度O(1)
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            return getKthElement(nums1, nums2, midIndex + 1);
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            return (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /*
         * 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         *
         */
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // 先使用时间复杂度O(m+n) 空间复杂度O(m+n)的算法
        // 我们的目标是求(m+n)/2和(m+n)/2+1的值
        int m = nums1.length;
        int n = nums2.length;
        int target = 0, target1 = 0;
        int x = 0, y = 0;
        boolean odd = ((m + n) & 1) == 1;
        for (int i = 0; i < (m + n) / 2 + 1; i++) {
            target = target1;
            if (x >= m) {
                target1 = nums2[y++];
            } else if (y >= n) {
                target1 = nums1[x++];
            } else {
                if (nums1[x] > nums2[y]) {
                    target1 = nums2[y++];
                } else {
                    target1 = nums1[x++];
                }
            }
        }
        return odd ? target1 : (target + target1) / 2.0;
    }

    public static void main(String[] args) {
        new Four().findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
    }
}
