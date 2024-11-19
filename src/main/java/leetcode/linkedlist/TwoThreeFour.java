package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表
 *
 * @author zengxi.song
 * @date 2024/9/9
 */
public class TwoThreeFour {

    public boolean isPalindrome(ListNode head) {
        // 题目要求时间复杂度O(N) 空间复杂度O(1)
        // 可以使用快慢指针找到中间节点 然后倒转后半部分链表进行比较 比较完毕后再复原
        // 为了减少空间复杂度 容易吗
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // fast为null 和fast.next为null表明总长度两种情况
        // fast为null:偶数 fast.next为null:奇数
        boolean res;
        // 这里其实也可以不做分类讨论 一切以 后半部分while是否为null来判断是否终止 可囊括奇偶
        if (fast == null) {
            res = palindrome(head, slow, reverseListNode(slow));
        } else {
            res = palindrome(head, slow.next, reverseListNode(slow));
        }
        reverseListNode(slow);
        return res;
    }

    private ListNode reverseListNode(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;

            pre = head;
            head = next;
        }
        return pre;
    }

    private boolean palindrome(ListNode l1, ListNode tail1, ListNode l2) {
        while (l1 != tail1 && l2 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }

    public boolean isPalindrome1(ListNode head) {
        // 简单做法数组+双指针 时间复杂度O(N) 空间复杂度O(N)
        List<Integer> auxList = new ArrayList<>();
        while (head != null) {
            auxList.add(head.val);
            head = head.next;
        }
        int left = 0, right = auxList.size() - 1;
        while (left < right) {
            if (!auxList.get(left).equals(auxList.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
