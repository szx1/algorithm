package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 删除链表的倒数第N个节点
 *
 * @author zengxi.song
 * @date 2024/8/30
 */
public class OneNine {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 其实我们可以通过快慢指针进行一次遍历
        // 令fast指针领先slow指针n个位置，则fast.next为null的时候，slow即为要删除的节点的前一个节点
        // 为了方便编码 我们还是要定义一个pre节点
        if (head == null || (head.next == null && n == 1)) {
            return null;
        }
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode slow = pre, fast = pre;
        while (head != null && n-- > 0) {
            fast = head;
            head = head.next;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;

        return pre.next;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        // 删除倒数第n个节点 很容易想到先遍历链表长度 然后第二次遍历的时候再进行删除
        if (head == null || (head.next == null && n == 1)) {
            return null;
        }
        int length = 0;
        ListNode dump = head;
        while (dump != null) {
            length++;
            dump = dump.next;
        }
        ListNode pre = new ListNode();
        pre.next = head;
        dump = pre;
        // 被删除节点的上一个节点即为 正数length-n(对于只有一个节点的前面已经返回了)
        int count = 0;
        while (dump != null) {
            count++;
            if (count == length - n + 1) {
                if (dump.next != null) {
                    dump.next = dump.next.next;
                }
                break;
            }
            dump = dump.next;
        }
        return pre.next;
    }
}
