package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

/**
 * 相交链表
 * leetCode评论区的骚话：
 * 朋友们，请一定要珍惜身边的那个 ta 啊！你们之所以相遇，正是因为你走了 ta 走过的路，而 ta 也刚好走了你走过的路。这是何等的缘分！
 * 而当你们携手继续走下去时，你会慢慢变成 ta 的样子，ta 也会慢慢变成你的样子。
 *
 * @author zengxi.song
 * @date 2024/9/5
 */
public class OneSixZero {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 简单的哈希表解法 这里不赘述了
        // 使用双指针让两者都走x+y+z相遇的位置即为相交节点 如果不相交 则最终二者都为null
        // x,y分别为两个链表的长度 z为相交后的长度
        // 时间复杂度O(M+N) 空间复杂度O(1)
        ListNode l1 = headA, l2 = headB;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                l1 = headB;
            }
            if (l2 == null) {
                l2 = headA;
            }
            if (l1 == l2) {
                break;
            }
            l1 = l1.next;
            l2 = l2.next;

        }
        return l1;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        // copy的官方的简洁写法
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
