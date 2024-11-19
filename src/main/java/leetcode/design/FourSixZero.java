package leetcode.design;

import cache.LFUCache;

/**
 * LFU缓存
 *
 * @author zengxi.song
 * @date 2024/9/29
 */
public class FourSixZero {

    public static void main(String[] args) {
        LFUCache<Integer, Integer> lfu = new LFUCache<>(1);
    }
}
