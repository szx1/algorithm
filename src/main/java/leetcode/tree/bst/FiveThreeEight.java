package leetcode.tree.bst;

import leetcode.auxclass.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 把二叉搜索树转换为累加树
 *
 * @author zengxi.song
 * @date 2024/7/4
 */
public class FiveThreeEight {

    public TreeNode convertBST(TreeNode root) {
        // 通过异化的中序遍历即可以 中序遍历左中左  本题通过右中左遍历即可
        // 迭代 时间复杂度O(n) 空间复杂度O(n)
        int sum = 0;
        TreeNode point = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || point != null) {
            while (point != null) {
                stack.push(point);
                point = point.right;
            }
            TreeNode pop = stack.pop();
            int value = pop.val;
            pop.val += sum;
            sum += value;
            point = pop.left;

        }
        return root;
    }

    int sum = 0;

    public TreeNode convertBST1(TreeNode root) {
        // 通过异化的中序遍历即可以 中序遍历左中左  本题通过右中左遍历即可
        // 递归 时间复杂度O(n) 空间复杂度O(n)
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        int value = root.val;
        root.val += sum;
        sum += value;
        dfs(root.left);
    }
}
