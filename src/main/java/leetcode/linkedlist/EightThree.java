package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 删除排序链表中的重复元素Ⅱ
 *
 * @author zengxi.song
 * @date 2025/2/14
 */
public class EightThree {

    public ListNode deleteDuplicates(ListNode head) {
        // 一次遍历 时间复杂度O(N) 空间复杂度O(1)
        // 快慢指针 让快指针永远大一步
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head, res = head;
        head = head.next;
        while (head != null) {
            if (cur.val != head.val) {
                cur.next = head;
                cur = cur.next;
            }
            head = head.next;
        }
        cur.next = null;
        return res;
    }
}
