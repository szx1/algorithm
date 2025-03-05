package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 分割链表
 *
 * @author zengxi.song
 * @date 2025/2/14
 */
public class EightSix {

    public ListNode partition(ListNode head, int x) {
        // 分割为两个链表 一次遍历 时间复杂度O(N) 空间复杂度O(1)
        if (head == null || head.next == null) {
            return head;
        }
        ListNode one = new ListNode();
        ListNode dummy = one;
        ListNode two = new ListNode();
        ListNode twoPre = two;
        while (head != null) {
            if (head.val < x) {
                one.next = head;
                one = one.next;
            } else {
                two.next = head;
                two = two.next;
            }
            head = head.next;
        }
        two.next = null;
        one.next = twoPre.next;
        return dummy.next;
    }
}
