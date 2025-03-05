package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 删除排序链表中的重复元素Ⅱ
 *
 * @author zengxi.song
 * @date 2025/2/14
 */
public class EightTwo {

    public ListNode deleteDuplicates(ListNode head) {
        // 一次遍历 时间复杂度O(N) 空间复杂度O(1)
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode dummy = pre;
        while (pre.next != null && head.next != null) {
            if (pre.next.val == head.next.val) {
                int tmp = pre.next.val;
                while (head != null && tmp == head.val) {
                    head = head.next;
                }
                pre.next = head;
            } else {
                pre = pre.next;
                head = head.next;
            }
        }
        return dummy.next;
    }
}
