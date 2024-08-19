package leetcode.array;

/**
 * 合并两个有序数组
 *
 * @author zengxi.song
 * @date 2024/7/11
 */
public class EightEight {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 从后往前逆向双指针 不适用额外数组 时间复杂度O(m+n) 空间复杂度O(1)
        int i = m - 1, j = n - 1;
        int index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[index--] = nums1[i--];
            } else {
                nums1[index--] = nums2[j--];
            }
        }
        // nums1没排完不用管 只需要关心nums2
        if (j >= 0) {
            System.arraycopy(nums2, 0, nums1, 0, j + 1);
        }
    }

    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        // 使用辅助数组加双指针 时间复杂度O(m+n) 空间复杂度O(m)
        if (n == 0) {
            return;
        }
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        int[] temp = new int[m];
        System.arraycopy(nums1, 0, temp, 0, m);
        int i = 0, j = 0;
        int index = 0;
        while (i < m && j < n) {
            if (temp[i] > nums2[j]) {
                nums1[index++] = nums2[j++];
            } else {
                nums1[index++] = temp[i++];
            }
        }
        if (i < m) {
            System.arraycopy(temp, i, nums1, i + j, m - i);
        }
        if (j < n) {
            System.arraycopy(nums2, j, nums1, i + j, n - j);
        }
    }

    public static void main(String[] args) {
        new EightEight().merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
    }
}
