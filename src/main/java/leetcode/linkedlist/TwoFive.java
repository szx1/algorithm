package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * K个一组翻转链表
 *
 * @author zengxi.song
 * @date 2025/1/11
 */
public class TwoFive {

    ListNode nextHead;

    public ListNode reverseKGroup(ListNode head, int k) {
        nextHead = head;
        ListNode pre = new ListNode();
        ListNode dump = pre;
        do {
            ListNode tmp = nextHead;
            pre.next = reverseListNode(k);
            // pre链表尾应该就是之前的链表头
            pre = tmp;
        } while (nextHead != null);
        return dump.next;
    }

    private ListNode reverseListNode(int k) {
        int count = 0;
        // 记录该分组的链表头
        ListNode head = nextHead;
        // 计数 看看是否可以达到分组个数
        while (nextHead != null) {
            nextHead = nextHead.next;
            if (++count == k) {
                break;
            }
        }
        if (count < k) {
            // 无需翻转 此时nextHead为null
            return head;
        }
        // 进行链表翻转
        ListNode pre = null;
        while (head != nextHead) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }
}
