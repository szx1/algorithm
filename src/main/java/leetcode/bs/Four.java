package leetcode.bs;

/**
 * 寻找两个正序数组的中位数
 *
 * @author zengxi.song
 * @date 2024/8/23
 */
public class Four {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 最牛的一个解法 时间复杂度O(log min(m,n)) 空间复杂度O(1)
        // 确保nums1.length<=nums2.length
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length, n = nums2.length;
        // 我们要找到一个分界点 即nums1的i和nums2的j 使得i和j的左边的数量大于等于(m+n奇数则左侧多一个)右边的数量
        // 易得m+n奇数 i+j=(m+n+1)/2 偶数则为i+j=(m+n)/2 基于计算机整数的除法 二者可以合并 即i+j=(m+n+1)/2
        // 即我们只要找到教短的数组的分界点i即可确定j
        // 排序数组 使用二分查找
        // 注意这里right是m 因为我们找的是分界点不是数组中的数字 分界点总数sum=m+1
        int left = 0, right = m;
        while (left <= right) {
            int i = left + ((right - left) >> 1);
            // 我们要确定i和谁比
            // 这里可以交叉对比
            // 即如果nums1[i-1]>nums2[j] 则证明i大了 因为左侧始终要小于等于右侧
            // 如果nums1[i]<nums2[j-1] 则证明i小了 因为左侧始终要小于等于右侧
            // 还需要考虑边界点(好麻烦) 即i==m i==0...这种情况
            int j = (m + n + 1) / 2 - i;
            if (i != 0 && j != n && nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else if (i != m && j != 0 && nums1[i] < nums2[j - 1]) {
                left = i + 1;
            } else {
                // 一定能进来
                // 找到正确的分界点了
                // 首先判断边界值
                int leftMax = 0;
                if (i == 0) {
                    leftMax = nums2[j - 1];
                } else if (j == 0) {
                    leftMax = nums1[i - 1];
                } else {
                    leftMax = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                boolean odd = ((m + n) & 1) == 1;
                if (odd) {
                    return leftMax;
                }
                // 偶数还要求rightMin 依然先从边界值开始
                int rightMin = 0;
                if (i == m) {
                    rightMin = nums2[j];
                } else if (j == n) {
                    rightMin = nums1[i];
                } else {
                    rightMin = Math.min(nums1[i], nums2[j]);
                }
                return ((double) (leftMax + rightMin)) / 2;
            }
        }
        return 0;
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
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
         * 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1](下标k/2-1即为第k2/大的数) 和 pivot2 = nums2[k/2-1] 进行比较
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
