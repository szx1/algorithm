package leetcode.tree.bst;

import leetcode.auxclass.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 不同的二叉搜索树Ⅱ
 *
 * @author zengxi.song
 * @date 2025/1/11
 */
public class NineFive {

    public List<TreeNode> generateTrees(int n) {
        // 方法1纯递归会存在很多重复计算
        // 可以采用记忆化搜索的方法提高效率
        return generateTrees(1, n, new HashMap<>());
    }

    private List<TreeNode> generateTrees(int start, int end, Map<List<Integer>, List<TreeNode>> treeMap) {
        List<Integer> key = new ArrayList<>();
        key.add(start);
        key.add(end);
        List<TreeNode> treeNodes = treeMap.get(key);
        if (treeNodes != null) {
            // 会导致某一个节点被多个父级引用 但是不影响结果
            return treeNodes;
        }
        List<TreeNode> trees = new ArrayList<>();
        if (start > end) {
            // 说明没有数字可用了 直接返回null即可
            trees.add(null);
            treeMap.put(key, trees);
            return trees;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTrees(start, i - 1, treeMap);
            List<TreeNode> rightTrees = generateTrees(i + 1, end, treeMap);
            // 将leftTrees和rightTrees交叉组合 构成以i为根节点的所有树
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode rootNode = new TreeNode(i);
                    rootNode.left = leftTree;
                    rootNode.right = rightTree;
                    trees.add(rootNode);
                }
            }
        }
        treeMap.put(key, trees);
        return trees;
    }

    public List<TreeNode> generateTrees1(int n) {
        // 递归回溯 1<=n<=8
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if (start > end) {
            // 说明没有数字可用了 直接返回null即可
            trees.add(null);
            return trees;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, end);
            // 将leftTrees和rightTrees交叉组合 构成以i为根节点的所有树
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode rootNode = new TreeNode(i);
                    rootNode.left = leftTree;
                    rootNode.right = rightTree;
                    trees.add(rootNode);
                }
            }
        }
        return trees;
    }
}
