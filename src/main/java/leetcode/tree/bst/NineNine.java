package leetcode.tree.bst;

import leetcode.auxclass.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 恢复二叉搜索树
 *
 * @author zengxi.song
 * @date 2024/7/30
 */
public class NineNine {

    public void recoverTree(TreeNode root) {
        // 利用二叉搜索树中序遍历的性质 递增序列 使用Morris遍历 时间复杂度O(N) 空间复杂度O(1)
        TreeNode cur = root;
        TreeNode mostRight = null;
        TreeNode pre = null, one = null, two = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight == null) {
                if (pre != null && pre.val >= cur.val) {
                    one = one == null ? pre : one;
                    two = cur;
                }
                pre = cur;
                cur = cur.right;
            } else {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    if (pre != null && pre.val >= cur.val) {
                        one = one == null ? pre : one;
                        two = cur;
                    }
                    pre = cur;
                    cur = cur.right;
                    mostRight.right = null;
                }
            }
        }
        int temp = one.val;
        one.val = two.val;
        two.val = temp;
    }

    public void recoverTree2(TreeNode root) {
        // 利用二叉搜索树中序遍历的性质 递增序列 不使用额外数组储存 时间复杂度O(N) 空间复杂度O(N)
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        TreeNode one = null;
        TreeNode two = null;
        boolean first = true;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode poll = stack.poll();
            if (pre != null && pre.val >= poll.val) {
                if (first) {
                    one = pre;
                    two = poll;
                    first = false;
                } else {
                    two = poll;
                }
            }
            pre = poll;
            root = poll.right;
        }
        int temp = one.val;
        one.val = two.val;
        two.val = temp;
    }

    public void recoverTree1(TreeNode root) {
        // 利用二叉搜索树中序遍历的性质 递增序列 额外数组储存 时间复杂度O(N) 空间复杂度O(N)
        List<Integer> inorder = new ArrayList<>();
        inorder(inorder, root);
        int x = -1, y = -1;
        for (int i = 0; i < inorder.size() - 1; i++) {
            if (inorder.get(i) >= inorder.get(i + 1)) {
                y = i + 1;
                if (x == -1) {
                    x = i;
                }
            }
        }
        // 所以要交换的两个数字为inorder.get(x)和inorder.get(y)
        // 再次进行遍历(可采用任意遍历)进行交换
        recover(root, inorder.get(x), inorder.get(y), 2);
    }

    private void recover(TreeNode node, int num1, int num2, int count) {
        if (node == null || count == 0) {
            return;
        }
        // 这里可以提速 使用count可以提前终止递归遍历
        recover(node.left, num1, num2, count);
        if (node.val == num1) {
            node.val = num2;
            count--;
        } else if (node.val == num2) {
            node.val = num1;
            count--;
        }
        recover(node.right, num1, num2, count);
    }

    private void inorder(List<Integer> inorder, TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(inorder, node.left);
        inorder.add(node.val);
        inorder(inorder, node.right);
    }

}
