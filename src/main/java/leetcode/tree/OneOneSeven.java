package leetcode.tree;

import leetcode.auxclass.bt.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针Ⅱ
 *
 * @author zengxi.song
 * @date 2024/8/2
 */
public class OneOneSeven {

    public Node connect(Node root) {
        // 像116题一样 使用next指针需要部分修改 时间复杂度O(N) 空间复杂度O(1)
        if (root == null) {
            return null;
        }
        Node mostLeft = root, head;
        while (mostLeft != null) {
            while (mostLeft != null && mostLeft.left == null && mostLeft.right == null) {
                mostLeft = mostLeft.next;
            }
            if (mostLeft == null) {
                break;
            }
            head = mostLeft;
            Node pre = null;
            while (head != null) {
                if (head.left != null && head.right != null) {
                    head.left.next = head.right;
                }
                if (pre == null) {
                    pre = head.right == null ? head.left : head.right;
                }
                if (head.next != null && pre != null) {
                    if (head.next.left != null || head.next.right != null) {
                        pre.next = head.next.left == null ? head.next.right : head.next.left;
                        pre = null;
                    }
                }
                head = head.next;
            }
            mostLeft = mostLeft.left == null ? mostLeft.right : mostLeft.left;
        }
        return root;
    }

    public Node connect1(Node root) {
        // 像116题一样 使用队列进行层序遍历完全兼容该题 时间复杂度O(N) 空间复杂度O(N) 但是不符合时间复杂度要求
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node pre = null;
            for (int i = queue.size() - 1; i >= 0; i--) {
                Node poll = queue.poll();
                if (pre != null) {
                    pre.next = poll;
                }
                pre = poll;
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        new OneOneSeven().connect(new Node(3,
                new Node(9),
                new Node(20, new Node(15), new Node(7))));
    }
}
