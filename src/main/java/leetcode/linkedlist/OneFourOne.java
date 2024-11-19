package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 环形链表
 *
 * @author zengxi.song
 * @date 2024/9/4
 */
public class OneFourOne {

    public boolean hasCycle(ListNode head) {
        // 关于哈希表的解法 就不写了 太简单了
        // 环形链表相关的题 快慢指针即可 时间复杂度O(N) 空间复杂度O(1)
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
