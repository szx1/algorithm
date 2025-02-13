package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 旋转链表
 *
 * @author zengxi.song
 * @date 2025/2/12
 */
public class SixOne {

    public ListNode rotateRight(ListNode head, int k) {
        // 迭代 时间复杂度O(N) 空间复杂度O(1)
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        // 第一遍扫描计算链表长度
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        // 求余数
        k = k % len;
        if (k == 0) {
            return head;
        }
        // 此时的倒数第k个节点即为最终的head
        temp = head;
        // 使用快慢指针 找到倒数第k个节点以及尾节点
        ListNode fast = head;
        int count = 0;
        while (count++ < k) {
            fast = fast.next;
        }
        while (fast.next != null) {
            // head就是慢指针
            head = head.next;
            fast = fast.next;
        }
        ListNode res = head.next;
        // 作为尾节点 防止出现环
        head.next = null;
        fast.next = temp;
        return res;
    }
}
