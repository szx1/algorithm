package cache;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存 采用双向链表加hash
 * 如果单纯采用链表实现 则get的时间复杂度为O(N) 所以我们借助Map来优化get的时间复杂度
 *
 * @author zengxi.song
 * @date 2024/9/27
 */
public class LRUCache<K, V> {

    /**
     * 缓存容量
     */
    private int capacity;
    /**
     * 当前缓存数量
     */
    private int size = 0;

    /**
     * 作为链表的虚拟头结点
     */
    private DeListNode<K, V> dumpHead = new DeListNode<>();
    /**
     * 作为链表的虚拟尾节点 删除尾节点时间复杂度O(1)
     */
    private DeListNode<K, V> dumpTail = new DeListNode<>();

    private Map<K, DeListNode<K, V>> map = new HashMap<>();

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The capacity must be greater than 0");
        }
        this.capacity = capacity;
        dumpHead.next = dumpTail;
        dumpTail.pre = dumpHead;
    }

    private static class DeListNode<K, V> {
        K key;
        V val;
        DeListNode<K, V> pre;
        DeListNode<K, V> next;

        public DeListNode() {
        }

        public DeListNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }


    public V get(K key) {
        DeListNode<K, V> node = map.get(key);
        if (node == null) {
            return null;
        }
        removeNode(node);
        move2Head(node);
        return node.val;
    }

    public void put(K key, V value) {
        DeListNode<K, V> node = map.get(key);
        if (node == null) {
            DeListNode<K, V> newNode = new DeListNode<>(key, value);
            move2Head(newNode);
            map.put(key, newNode);
            if (++size > capacity) {
                map.remove(dumpTail.pre.key);
                removeNode(dumpTail.pre);
                size--;
            }
        } else {
            node.val = value;
            removeNode(node);
            move2Head(node);
        }
    }

    private void move2Head(DeListNode<K, V> node) {
        // move to head
        node.next = dumpHead.next;
        node.next.pre = node;
        node.pre = dumpHead;
        dumpHead.next = node;
    }

    private void removeNode(DeListNode<K, V> node) {
        // 要求容量必须大于0 即只剩一个的时候 我们不用删除
        if (size <= 1) {
            return;
        }
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }


    public static void main(String[] args) {
        LRUCache<Integer, Integer> lruCache = new LRUCache<>(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        System.out.println(1);
    }
}
