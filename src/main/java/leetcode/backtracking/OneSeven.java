package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字母组合
 *
 * @author zengxi.song
 * @date 2024/8/30
 */
public class OneSeven {

    public List<String> letterCombinations(String digits) {
        // 经典的回溯求组合问题
        Map<Character, List<Character>> auxMap = buildMap();
        List<String> res = new ArrayList<>();
        backTracking(auxMap, res, new StringBuilder(), digits, 0);
        return res;
    }

    private void backTracking(Map<Character, List<Character>> auxMap, List<String> res, StringBuilder sb, String digits, Integer index) {
        if (sb.length() == digits.length()) {
            if (sb.length() > 0) {
                res.add(sb.toString());
            }
            return;
        }
        char c = digits.charAt(index);
        for (Character item : auxMap.get(c)) {
            sb.append(item);
            backTracking(auxMap, res, sb, digits, index + 1);
            sb.setLength(sb.length() - 1);
        }
    }

    private Map<Character, List<Character>> buildMap() {
        Map<Character, List<Character>> map = new HashMap<>(16);
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        return map;
    }
}
