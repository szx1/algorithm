package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 重排链表
 *
 * @author zengxi.song
 * @date 2025/2/20
 * @see EightSevenSix
 * @see TwoZeroSix
 */
public class OneFourThree {

    public void reorderList(ListNode head) {
        // 双指针 时间复杂度O(N) 空间复杂度(1)
        // 先找到中间节点 然后翻转后面的链表 然后两两夹杂合并链表
        ListNode mid = findMid(head);
        ListNode reverse = reverse(mid);
        ListNode pre = new ListNode();
        while (head != null && reverse != null) {
            ListNode headNext = head.next;
            pre.next = head;
            pre = pre.next;
            pre.next = reverse;
            pre = pre.next;

            head = headNext;
            reverse = reverse.next;
        }
        // head.size>=reverse.size
        if (head != null) {
            pre.next = head;
        }
    }

    /**
     * 也可以不借助pre节点
     *
     * @param head
     * @param reverse
     */
    private void merge(ListNode head, ListNode reverse) {
        // head.size>=reverse.size 所以最后reverse.next=headNext收尾即可
        while (head != null && reverse != null) {
            ListNode headNext = head.next;
            head.next = reverse;
            ListNode reNext = reverse.next;
            reverse.next = headNext;

            head = headNext;
            reverse = reNext;
        }
    }

    private ListNode findMid(ListNode head) {
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            head = head.next;
            fast = fast.next.next;
        }
        ListNode res = head.next;
        // 注意截断上一部分
        head.next = null;
        return res;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public void reorderList1(ListNode head) {
        // 队列和栈  时间复杂度O(N) 空间复杂度O(N)
        // 这个方法有点蠢 看看有没有更好的方法
        Queue<ListNode> queue = new ArrayDeque<>();
        Deque<ListNode> stack = new ArrayDeque<>();
        int len = 0;
        ListNode tmp = head;
        while (head != null) {
            len++;
            head = head.next;
        }
        int index = 0;
        head = tmp;
        while (head != null) {
            if (index < (len >> 1)) {
                queue.add(head);
            } else {
                stack.push(head);
            }
            index++;
            head = head.next;
        }
        ListNode dummy = new ListNode();
        while (!queue.isEmpty() && !stack.isEmpty()) {
            dummy.next = queue.poll();
            dummy = dummy.next;
            dummy.next = stack.poll();
            dummy = dummy.next;
        }
        if (!queue.isEmpty()) {
            dummy.next = queue.poll();
            dummy = dummy.next;
        }
        if (!stack.isEmpty()) {
            dummy.next = stack.poll();
            dummy = dummy.next;
        }
        dummy.next = null;
    }

    public static void main(String[] args) {
        new OneFourThree().reorderList(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))));
    }
}
