package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 赎金信
 *
 * @author zengxi.song
 * @date 2024/4/24
 */
public class ThreeEightThree {

    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) {
            return false;
        }
        int[] magazineArr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            magazineArr[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (--magazineArr[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean canConstruct1(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) {
            return false;
        }
        Map<Character, Integer> ransomMap = new HashMap<>();
        Map<Character, Integer> magazineMap = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            ransomMap.merge(ransomNote.charAt(i), 1, (oldV, newV) -> oldV + 1);
        }
        for (int i = 0; i < magazine.length(); i++) {
            magazineMap.merge(magazine.charAt(i), 1, (oldV, newV) -> oldV + 1);
        }
        for (Map.Entry<Character, Integer> entry : ransomMap.entrySet()) {
            Integer count = magazineMap.get(entry.getKey());
            if (count == null || count < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
