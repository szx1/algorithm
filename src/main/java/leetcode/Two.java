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
        // 递归 时间复杂度O(max(m,n)) 空间复杂度O(max(m,n)) 递归的栈空间
        return addWithCarry(l1, l2, 0);
    }

    private ListNode addWithCarry(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            return carry > 0 ? new ListNode(carry) : null;
        }
        // 可能存在其中一个链表为null
        // 如果出现null 这里始终让l2为null 简化代码编写
        if (l1 == null) {
            l1 = l2;
            l2 = null;
        }
        int sum = l1.val + (l2 == null ? 0 : l2.val) + carry;
        ListNode node = new ListNode(sum % 10);
        node.next = addWithCarry(l1.next, l2 == null ? null : l2.next, sum / 10);
        return node;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        // 迭代 时间复杂度O(max(m,n)) 空间复杂度O(1)
        // 不用考虑大数问题
        int carry = 0;
        ListNode head = new ListNode();
        ListNode dump = head;
        while (l1 != null && l2 != null) {
            int newV = l1.val + l2.val + carry;
            head.next = new ListNode(newV % 10);
            head = head.next;
            carry = newV / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        appendTail(l1 == null ? l2 : l1, head, carry);
        return dump.next;
    }

    private void appendTail(ListNode l, ListNode head, int carry) {
        while (l != null) {
            int newV = l.val + carry;
            head.next = new ListNode(newV % 10);
            head = head.next;
            carry = newV / 10;
            l = l.next;
        }
        if (carry != 0) {
            head.next = new ListNode(carry);
        }
    }
}
