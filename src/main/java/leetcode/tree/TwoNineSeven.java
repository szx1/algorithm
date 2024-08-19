package leetcode.tree;

import leetcode.auxclass.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 *
 * @author zengxi.song
 * @date 2024/8/12
 */
public class TwoNineSeven {

    public static class Codec {

        private static final String COMMA = ",";
        private static final String NULL = "null";

        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            List<String> res = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    res.add(NULL);
                    continue;
                }
                res.add(String.valueOf(poll.val));
                queue.offer(poll.left);
                queue.offer(poll.right);
            }
            return String.join(COMMA, res);
        }

        public TreeNode deserialize(String data) {
            if (data == null || data.trim().length() == 0) {
                return null;
            }
            String[] split = data.split(COMMA);
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(split[0]));
            queue.add(root);
            int index = 1;
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                poll.left = buildNode(index, split);
                index++;
                poll.right = buildNode(index, split);
                index++;
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }

            }
            return root;
        }

        private TreeNode buildNode(int index, String[] split) {
            if (index < 0 || index >= split.length) {
                return null;
            }
            String nodeV = split[index];
            if (NULL.equals(nodeV)) {
                return null;
            }
            return new TreeNode(Integer.parseInt(nodeV));
        }
    }

    public static void main(String[] args) {
        Codec ser = new Codec();
        Codec deser = new Codec();
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4, new TreeNode(6), new TreeNode(7)), new TreeNode(5)));
        String serialize = ser.serialize(root);
        System.out.println(serialize);
        TreeNode ans = deser.deserialize(serialize);
        System.out.println(1);
    }
}


