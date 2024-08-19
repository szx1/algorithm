package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 奇偶链表
 *
 * @author zengxi.song
 * @date 2024/7/12
 */
public class ThreeTwoEight {

    public ListNode oddEvenList(ListNode head) {
        // 优化版 时间复杂度O(N) 空间复杂度O(1)
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;

        return head;
    }

    public ListNode oddEvenList1(ListNode head) {
        // 时间复杂度O(N) 空间复杂度O(1)
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode tempOdd = odd;
        ListNode even = head.next;
        ListNode tempEven = even;
        int index = 3;
        head = head.next.next;
        while (head != null) {
            if ((index & 1) == 1) {
                odd.next = head;
                odd = odd.next;
            } else {
                even.next = head;
                even = even.next;
            }
            head = head.next;
            index++;
        }
        even.next = null;
        odd.next = tempEven;

        return tempOdd;
    }
}
