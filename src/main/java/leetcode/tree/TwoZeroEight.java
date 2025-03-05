package leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现Trie(前缀树)
 *
 * @author zengxi.song
 * @date 2024/8/21
 */
public class TwoZeroEight {

    /**
     * 迭代版本 比递归版本少了递归栈，少点空间复杂度
     */
    class Trie {

        private Node dummy = new Node();

        public Trie() {

        }

        class Node {
            boolean end;
            Map<Character, Node> children = new HashMap<>();
        }

        public void insert(String word) {
            Node node = dummy, pre = dummy;
            int i = 0;
            while (i < word.length() && (node = node.children.get(word.charAt(i))) != null) {
                pre = node;
                i++;
            }
            if (i == word.length() && node != null) {
                node.end = true;
            }
            while (i < word.length()) {
                Node newNode = new Node();
                if (i == word.length() - 1) {
                    newNode.end = true;
                }
                pre.children.put(word.charAt(i), newNode);
                pre = newNode;
                i++;
            }
        }

        public boolean search(String word) {
            Node node = searchNode(word);
            return node != null && node.end;
        }

        public boolean startsWith(String prefix) {
            return searchNode(prefix) != null;
        }

        public Node searchNode(String word) {
            Node node = dummy;
            int i = 0;
            while (i < word.length() && (node = node.children.get(word.charAt(i))) != null) {
                i++;
            }
            return node;
        }
    }

    /**
     * 递归版本
     */
    class Trie1 {

        class Node {
            boolean end;
            Character val;
            Map<Character, Node> children;

            public Node(Character val) {
                this.val = val;
                this.children = new HashMap<>();
            }
        }

        private Map<Character, Node> tree;

        public Trie1() {
            tree = new HashMap<>();
        }

        public void insert(String word) {
            add(0, word, tree);
        }

        public boolean search(String word) {
            Node search = search(0, word, tree);
            return search != null && search.end;
        }

        public boolean startsWith(String prefix) {
            return search(0, prefix, tree) != null;
        }

        private void add(int index, String word, Map<Character, Node> tree) {
            if (index >= word.length()) {
                return;
            }
            char c = word.charAt(index);
            Node node = tree.computeIfAbsent(c, k -> new Node(c));
            if (index == word.length() - 1) {
                node.end = true;
            }
            add(index + 1, word, node.children);
        }

        private Node search(int index, String word, Map<Character, Node> tree) {
            if (index >= word.length()) {
                return null;
            }
            Node node;
            if (tree == null || (node = tree.get(word.charAt(index))) == null) {
                return null;
            }
            if (index == word.length() - 1) {
                return node;
            }
            return search(index + 1, word, tree.get(word.charAt(index)).children);
        }
    }
}
