package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 合并两个有序链表
 *
 * @author zengxi.song
 * @date 2024/8/30
 */
public class TwoOne {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 迭代
        ListNode pre = new ListNode();
        ListNode head = pre;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }
        head.next = list1 == null ? list2 : list1;
        return pre.next;
    }

    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        // 递归
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        // 只需要返回较小的数即可
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
