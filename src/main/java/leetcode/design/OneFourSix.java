package leetcode.design;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU缓存
 *
 * @author zengxi.song
 * @date 2024/8/19
 */
public class OneFourSix {

    /**
     * 直接借助LinkedHashMap
     */
    static class LRUCache {
        // 利用LinkedHashMap

        class LinkedHashMapLRU extends LinkedHashMap<Integer, Integer> {

            private int maxNum;

            public LinkedHashMapLRU(int maxNum) {
                super(16, 0.75F, true);
                this.maxNum = maxNum;
            }

            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > maxNum;
            }
        }

        private LinkedHashMapLRU lru;

        public LRUCache(int capacity) {
            lru = new LinkedHashMapLRU(capacity);
        }

        public int get(int key) {
            return lru.get(key);
        }

        public void put(int key, int value) {
            lru.put(key, value);
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
    }
}
