package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 单词的压缩编码
 *
 * @author zengxi.song
 * @date 2024/9/20
 */
public class EightTwoZero {

    public int minimumLengthEncoding(String[] words) {
        // 字典树 优化
        // 将字符串反转 建立字典树的森林 然后找到每颗字典树的最大子节点长度然后求和即为最终结果
        Map<Character, Node> trees = new HashMap<>();
        // 先用set去下重
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        // 记录叶子结点 避免后续再耗费时间搜索
        List<Node> leaf = new ArrayList<>();
        for (String word : wordSet) {
            leaf.add(buildTree(trees, word, word.length() - 1));
        }
        int res = 0;
        for (Node node : leaf) {
            if (node.leaf) {
                res += node.length;
            }
        }
        return res;
    }

    private Node buildTree(Map<Character, Node> trees, String s, int index) {
        char c = s.charAt(index);
        Node node = trees.computeIfAbsent(c, k -> new Node(index == 0));
        if (index == 0) {
            node.length = s.length();
        } else {
            // 可能变为非叶子节点
            node.leaf = false;
        }
        return index == 0 ? node : buildTree(node.children, s, index - 1);
    }

    public int minimumLengthEncoding3(String[] words) {
        // 字典树
        // 将字符串反转 建立字典树的森林 然后找到每颗字典树的最大子节点长度然后求和即为最终结果
        Map<Character, Node> trees = new HashMap<>();
        // 先用set去下重
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        for (String word : wordSet) {
            buildTree1(trees, word, word.length() - 1);
        }
        int res = 0;
        for (Node node : trees.values()) {
            res += calcDepth(node, 1);
        }
        return res;
    }

    private void buildTree1(Map<Character, Node> trees, String s, int index) {
        if (index < 0) {
            return;
        }
        char c = s.charAt(index);
        buildTree1(trees.computeIfAbsent(c, k -> new Node(c)).children, s, index - 1);
    }

    /**
     * 要计算所有叶子结点的高度
     *
     * @param node
     * @return
     */
    private int calcDepth(Node node, int curDepth) {
        if (node == null) {
            return 0;
        }
        if (node.children.isEmpty()) {
            // 根节点要加上#
            return curDepth + 1;
        }
        int sum = 0;
        for (Node value : node.children.values()) {
            sum += calcDepth(value, curDepth + 1);
        }
        return sum;
    }


    private static class Node {
        private Character character;
        private Map<Character, Node> children;
        private boolean leaf;
        private int length;

        public Node(boolean leaf) {
            this.leaf = leaf;
            this.children = new HashMap<>();
        }

        public Node(Character character) {
            this.character = character;
            this.children = new HashMap<>();
        }
    }

    public int minimumLengthEncoding2(String[] words) {
        Set<String> good = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            for (int k = 1; k < word.length(); ++k) {
                good.remove(word.substring(k));
            }
        }

        int ans = 0;
        for (String word : good) {
            ans += word.length() + 1;
        }
        return ans;
    }


    public int minimumLengthEncoding1(String[] words) {
        // set去重 然后找到所有可以覆盖后缀的元素
        // 时间复杂度不止O(N^2) 空间复杂度O(N)
        Set<String> wordSet = new HashSet<>(words.length);
        for (String word : words) {
            wordSet.add(word);
        }

        Set<String> removeSet = new HashSet<>();
        for (String word : wordSet) {
            for (String word1 : wordSet) {
                if (word.length() != word1.length() && word.endsWith(word1)) {
                    removeSet.add(word1);
                }
            }
        }
        wordSet = wordSet.stream().filter(item -> !removeSet.contains(item)).collect(Collectors.toSet());
        return wordSet.stream().mapToInt(String::length).sum() + wordSet.size();
    }

    public static void main(String[] args) {
        new EightTwoZero().minimumLengthEncoding(new String[]{"time", "atime", "btime"});
    }
}
