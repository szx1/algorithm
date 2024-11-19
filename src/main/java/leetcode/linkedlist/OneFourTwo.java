package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 环形链表Ⅱ
 *
 * @author zengxi.song
 * @date 2024/9/5
 */
public class OneFourTwo {

    public ListNode detectCycle(ListNode head) {
        // 关于哈希表的解法 就不写了 太简单了
        // 依然是快慢指针 时间复杂度O(N) 空间复杂度O(1)
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // 相遇
                // 因为fast走过的路程是slow的两倍 经过数学推断 此时让slow回到head，二者相遇的位置即入环点
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
