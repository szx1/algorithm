package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 链表的中间节点
 *
 * @author zengxi.song
 * @date 2025/2/20
 */
public class EightSevenSix {

    public ListNode middleNode(ListNode head) {
        // 快慢指针 时间复杂度O(N) 空间复杂度O(1)
        // 快指针一次走两格 慢指针一次走一格
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            head = head.next;
            fast = fast.next.next;
        }
        return head;
    }
}
