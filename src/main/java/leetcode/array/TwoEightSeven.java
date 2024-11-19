package leetcode.array;

/**
 * 寻找重复数
 * <br>题目要求不可修改原数组且只达到O(1)的空间复杂度
 *
 * @author zengxi.song
 * @date 2024/9/10
 */
public class TwoEightSeven {

    public int findDuplicate(int[] nums) {
        // 将数组转换为链表 时间复杂度O(N) 空间复杂度O(1)
        // 将数组映射为nums[i]->nums[nums[i]的链表 由于存在重复数字 所以必然存在环
        // 只有两种情况存在入度大于等于2的节点：
        // 1.存在自环 比如nums[1]=1,但是如果1不是重复数字的话 这个节点不会出现链表中 自己玩去吧
        // 2.入度大于等于2的节点即重复数字 也即环的入口 使用快慢指针求值
        int slow = 0, fast = 0;
        while ((slow == 0 && fast == 0) || slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public int findDuplicate1(int[] nums) {
        // 二分查找 时间复杂度O(NlogN) 空间复杂度O(1)
        // 设cnt[i]为小于等于i的数的数量 则此时只有重复数的开始往后cnt[i]会大于i
        // 我们可以通过二分查找找到第一个大于i的数 即为重复数
        int left = 1, right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid - 1;
            }
        }
        return res;
    }
}
