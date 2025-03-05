package leetcode.tree;

import leetcode.auxclass.TreeNode;

/**
 * 二叉树展开为链表
 *
 * @author zengxi.song
 * @date 2024/9/4
 */
public class OneOneFour {

    public void flatten(TreeNode root) {
        // 可以采用先序遍历生成队列 然后再重构链表 需要额外空间保存全部节点 代码很简单不写了
        // 直接提供空间复杂度为O(1)的代码
        TreeNode cur = root;
        while (cur != null) {
            // 只有左子树不为空才需要特殊处理
            if (cur.left != null) {
                TreeNode next = cur.left;
                TreeNode pre = next;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 此时pre为左子树最右边的节点 它即为cur的前驱结点
                pre.right = cur.right;
                // 调整当前节点
                cur.left = null;
                cur.right = next;
            }
            // 继续处理下一个节点
            cur = cur.right;
        }
    }
}
