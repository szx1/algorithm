package leetcode.tree.traversal;

import leetcode.auxclass.TreeNode;

import java.util.Collections;
import java.util.List;

/**
 * 二叉树的层序遍历Ⅱ
 *
 * @author zengxi.song
 * @date 2024/6/26
 */
public class OneZeroSeven {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 和102题类似 只需要最后reverse一下
        OneZeroTwo oneZeroTwo = new OneZeroTwo();
        List<List<Integer>> res = oneZeroTwo.levelOrder(root);
        Collections.reverse(res);
        return res;
    }
}
