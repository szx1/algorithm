package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 反转链表
 *
 * @author zengxi.song
 * @date 2024/9/6
 */
public class TwoZeroSix {

    public ListNode reverseList(ListNode head) {
        // 递归
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseList1(ListNode head) {
        // 迭代
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }
}
