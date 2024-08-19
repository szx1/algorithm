package leetcode.graph;

import leetcode.auxclass.graph.UnionFind;

/**
 * 验证二叉树
 *
 * @author zengxi.song
 * @date 2024/8/9
 */
public class OneThreeSixOne {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        // 并查集 判断节点全部联通且无环且没有入度大于1的节点即可
        UnionFind uf = new UnionFind(n);
        int[] inDeg = new int[n];
        for (int i = 0; i < n; i++) {
            if (checkChildren(leftChild, uf, inDeg, i) || checkChildren(rightChild, uf, inDeg, i)) {
                return false;
            }
        }
        return uf.diffCount() == 1;
    }

    private boolean checkChildren(int[] child, UnionFind uf, int[] inDeg, int i) {
        if (child[i] != -1) {
            if (++inDeg[child[i]] > 1) {
                return true;
            }
            if (uf.connect(i, child[i])) {
                return true;
            }
            uf.union(i, child[i]);
        }
        return false;
    }
}
