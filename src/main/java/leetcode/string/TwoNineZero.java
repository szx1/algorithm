package leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 单词规律
 *
 * @author zengxi.song
 * @date 2024/7/24
 */
public class TwoNineZero {

    public boolean wordPattern(String pattern, String s) {
        // 不使用官方split方法 哈希表记录双向的映射 时间复杂度O(N+M) 空间复杂度O(N+M)
        Map<Character, String> c2StrMap = new HashMap<>();
        Map<String, Character> str2CMap = new HashMap<>();
        int start = 0;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            int end = start;
            if (end >= s.length()) {
                return false;
            }
            while (end < s.length() && s.charAt(end) != ' ') {
                end++;
            }
            String sub = s.substring(start, end);
            end++;
            start = end;
            if ((c2StrMap.containsKey(c) && !c2StrMap.get(c).equals(sub)) ||
                    str2CMap.containsKey(sub) && !str2CMap.get(sub).equals(c)) {
                return false;
            }
            c2StrMap.put(c, sub);
            str2CMap.put(sub, c);
        }
        return start > s.length();
    }

    public boolean wordPattern1(String pattern, String s) {
        // 使用官方split方法 哈希表记录双向的映射 时间复杂度O(N+M) 空间复杂度O(N+M)
        String[] arr = s.split(" ");
        if (pattern.length() != arr.length) {
            return false;
        }
        Map<Character, String> c2StrMap = new HashMap<>();
        Map<String, Character> str2CMap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if ((c2StrMap.containsKey(c) && !c2StrMap.get(c).equals(arr[i])) ||
                    str2CMap.containsKey(arr[i]) && !str2CMap.get(arr[i]).equals(c)) {
                return false;
            }
            c2StrMap.put(c, arr[i]);
            str2CMap.put(arr[i], c);
        }
        return true;
    }

    public static void main(String[] args) {
        new TwoNineZero().wordPattern("abba", "dog cat cat dog");
    }
}
