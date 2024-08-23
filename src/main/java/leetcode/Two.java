package leetcode;

import leetcode.auxclass.ListNode;

/**
 * 两数相加
 *
 * @author zengxi.song
 * @date 2024/8/23
 */
public class Two {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 时间复杂度O(max(m,n)) 空间复杂度O(1)
        // 不用考虑大数问题
        int remainder = 0;
        ListNode head = new ListNode();
        ListNode dump = head;
        while (l1 != null && l2 != null) {
            int newV = l1.val + l2.val + remainder;
            head.next = new ListNode(newV % 10);
            head = head.next;
            remainder = newV / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        appendTail(l1 == null ? l2 : l1, head, remainder);
        return dump.next;
    }

    private void appendTail(ListNode l, ListNode head, int remainder) {
        while (l != null) {
            int newV = l.val + remainder;
            head.next = new ListNode(newV % 10);
            head = head.next;
            remainder = newV / 10;
            l = l.next;
        }
        if (remainder != 0) {
            head.next = new ListNode(remainder);
        }
    }
}
