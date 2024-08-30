package leetcode.linkedlist;

import leetcode.auxclass.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K个升序链表
 * 可参考21题
 *
 * @author zengxi.song
 * @date 2024/8/12
 */
public class TwoThree {

    public ListNode mergeKLists(ListNode[] lists) {
        // 借助优先队列 但是并不是将所有节点放入队列 而是只放入当前节点的下一个节点
        // 时间复杂度O(kn*log k) 空间复杂度O(logk)
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                priorityQueue.add(new Pair(list.val, list));
            }
        }
        ListNode pre = new ListNode();
        ListNode head = pre;
        while (!priorityQueue.isEmpty()) {
            Pair poll = priorityQueue.poll();
            head.next = poll.node;
            head = head.next;
            if (poll.node.next != null) {
                priorityQueue.add(new Pair(poll.node.next.val, poll.node.next));
            }
        }
        return pre.next;
    }

    static class Pair {
        Integer val;
        ListNode node;

        public Pair(Integer val, ListNode node) {
            this.val = val;
            this.node = node;
        }
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        // 采用分治的思想 递归两两合并
        // 时间复杂度O(kn*log k) 空间复杂度O(logk)
        return divide(lists, 0, lists.length - 1);
    }

    private ListNode divide(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) >> 1;
        return mergeTwoList(divide(lists, start, mid), divide(lists, mid + 1, end));
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        // 循环顺序两两合并
        // 时间复杂度O(k*k*n) 空间复杂度O(1) n为链表的最大长度
        ListNode res = null;
        for (ListNode list : lists) {
            res = mergeTwoList(res, list);
        }
        return res;
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode pre = new ListNode();
        ListNode head = pre;
        while (l1 != null & l2 != null) {
            if (l1.val < l2.val) {
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
}
