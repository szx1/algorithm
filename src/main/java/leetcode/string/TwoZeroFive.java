package leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 同构字符串
 *
 * @author zengxi.song
 * @date 2024/7/24
 */
public class TwoZeroFive {

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        // 使用哈希表 时间复杂度O(N) 空间复杂度O(K) K是字符个数
        Map<Character, Character> sMap = new HashMap<>();
        Map<Character, Character> tMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if ((sMap.containsKey(cs) && sMap.get(cs) != ct) || (tMap.containsKey(ct) && tMap.get(ct) != cs)) {
                return false;
            }
            sMap.put(cs, ct);
            tMap.put(ct, cs);
        }
        return true;
    }
}
