package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 排序链表
 *
 * @author zengxi.song
 * @date 2024/9/5
 */
public class OneFourEight {

    public ListNode sortList(ListNode head) {
        // 不使用递归 时间复杂度O(NlogN) 空间复杂度O(1)
        // 首先要求出长度
        int length = 0;
        ListNode dump = head;
        while (dump != null) {
            length++;
            dump = dump.next;
        }
        ListNode pre = new ListNode(0, head);
        ListNode dumpPre;
        for (int curSize = 1; curSize < length; curSize *= 2) {
            dumpPre = pre;
            head = pre.next;
            // 内部循环根据是否遍历到最后来终止
            while (head != null) {
                int len = 0;

                ListNode l1 = head;
                while (head != null) {
                    len++;
                    if (len == curSize) {
                        break;
                    }
                    head = head.next;
                }
                if (head == null) {
                    // 剩下的节点只够一个有序链表
                    dumpPre.next = l1;
                    break;
                }
                ListNode l2 = head.next;
                // 让l1的尾部置为null
                head.next = null;
                head = l2;
                len = 0;
                while (head != null) {
                    len++;
                    if (len == curSize) {
                        break;
                    }
                    head = head.next;
                }
                if (head != null) {
                    // 继续下一次内部循环
                    ListNode next = head.next;
                    head.next = null;
                    head = next;
                }
                // 合并有序的l1和l2 使用dumpPre来连接链表
                dumpPre.next = merge(l1, l2);
                while (dumpPre.next != null) {
                    dumpPre = dumpPre.next;
                }
            }
        }
        return pre.next;
    }

    public ListNode sortList1(ListNode head) {
        // 题目进阶要求时间复杂度O(NlogN) 空间复杂度O(1)
        // 我们可以采用分治思想的归并排序 但是借助递归的归并排序依然需要O(logN)的栈空间
        // 这里先给出自顶向下递归的做法
        // 因为这是链表 存在next指针 所以不需要额外的辅助数组即可完成
        return divide(head, null);
    }

    private ListNode divide(ListNode head, ListNode tail) {
        if (head == null || head.next == tail) {
            if (head != null) {
                // 避免链表循环 截断掉
                head.next = null;
            }
            // 已经递归到只有一个元素或者没有元素 则可返回
            return head;
        }
        // 在数组中我们可以使用start+(end-start)/2来进行分治
        // 链表依然可以先遍历一次长度 然后取mid
        // 但是有一个更巧妙的方法 即快慢指针
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 此时的slow指针 我们即可认为处于链表中间
        ListNode l1 = divide(head, slow);
        ListNode l2 = divide(slow, tail);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        // 这里的合并 即合并两个排序链表 可参考21题
        ListNode pre = new ListNode();
        ListNode head = pre;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        head.next = l1 == null ? l2 : l1;
        return pre.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        ListNode listNode = new OneFourEight().sortList(head);
        System.out.println(1);
    }
}
