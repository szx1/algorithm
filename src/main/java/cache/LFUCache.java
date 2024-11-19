package cache;

import java.util.HashMap;
import java.util.Map;

/**
 * LFU缓存
 *
 * @author zengxi.song
 * @date 2024/9/27
 */
public class LFUCache<K, V> {

    /**
     * 初始化缓存容量
     */
    private final int capacity;
    /**
     * 当前缓存大小
     */
    private int size = 0;
    /**
     * 保存key->node的映射 get方法时间复杂度O(1)
     */
    Map<K, DeListNode<K, V>> keyMap = new HashMap<>();
    /**
     * 保存频率->节点链表的映射
     */
    Map<Integer, DeListNodeList<K, V>> freqMap = new HashMap<>();
    /**
     * 记录最小频率 降低逐出的时间复杂度
     */
    private int minFreq = 1;


    private static class DeListNode<K, V> {
        K key;
        V val;
        int freq;
        DeListNode<K, V> pre;
        DeListNode<K, V> next;

        public DeListNode() {

        }

        public DeListNode(K key, V val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
        }
    }

    private static class DeListNodeList<K, V> {
        /**
         * 虚拟头结点
         */
        DeListNode<K, V> dumpHead = new DeListNode<>();
        /**
         * 虚拟尾节点
         */
        DeListNode<K, V> dumpTail = new DeListNode<>();

        public DeListNodeList() {
            // 构建双向链表
            dumpHead.next = dumpTail;
            dumpTail.pre = dumpHead;
        }

        public void addNodeHead(DeListNode<K, V> node) {
            dumpHead.next.pre = node;
            node.next = dumpHead.next;
            node.pre = dumpHead;
            dumpHead.next = node;
        }

        public boolean isEmpty() {
            return dumpHead.next == dumpTail && dumpTail.pre == dumpHead;
        }

        public DeListNode<K, V> removeTail() {
            DeListNode<K, V> remove = dumpTail.pre;
            remove.pre.next = dumpTail;
            dumpTail.pre = remove.pre;
            return remove;
        }
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(K key) {
        DeListNode<K, V> node = keyMap.get(key);
        if (node == null) {
            return null;
        }
        removeNode(node);
        // 更新当前最小频率 只有在minFreq等于时才判断
        if (minFreq == node.freq && freqMap.get(minFreq).isEmpty()) {
            minFreq++;
        }
        // 访问频率加一并放到对应的map中
        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new DeListNodeList<>()).addNodeHead(node);
        return node.val;
    }

    public void put(K key, V value) {
        DeListNode<K, V> node = keyMap.get(key);
        if (node == null) {
            DeListNode<K, V> newNode = new DeListNode<>(key, value, 1);
            keyMap.put(key, newNode);
            freqMap.computeIfAbsent(newNode.freq, k -> new DeListNodeList<>()).addNodeHead(newNode);
            if (++size > capacity) {
                // 移除频率最小的 内部保证minFreq对应的list肯定有值
                DeListNodeList<K, V> nodeList = freqMap.get(minFreq);
                DeListNode<K, V> removeNode = nodeList.removeTail();
                keyMap.remove(removeNode.key);
                size--;
            }
            // 因为不考虑新项 此时minFreq肯定是1
            minFreq = 1;
        } else {
            node.val = value;
            removeNode(node);
            // 更新当前最小频率 只有在minFreq等于时才判断
            if (minFreq == node.freq && freqMap.get(minFreq).isEmpty()) {
                minFreq++;
            }
            node.freq++;
            freqMap.computeIfAbsent(node.freq, k -> new DeListNodeList<>()).addNodeHead(node);
        }
    }

    private void removeNode(DeListNode<K, V> node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public static void main(String[] args) {
        LFUCache<Integer, Integer> lfu = new LFUCache<>(3);
        lfu.put(2, 2);
        lfu.put(1, 1);
        lfu.get(2);
        lfu.get(1);
        lfu.put(3, 3);
        lfu.put(4, 4);
        lfu.get(3);
        lfu.get(2);
        lfu.get(1);
        lfu.get(4);
    }
}
