package leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Z字形变换
 *
 * @author zengxi.song
 * @date 2024/12/25
 */
public class Six {

    public String convert(String s, int numRows) {
        StringBuilder sb = new StringBuilder();
        List<StringBuilder> list = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        int index = 0, flag = 1;
        for (int i = 0; i < s.length(); i++) {
            list.get(index).append(s.charAt(i));
            if (index == list.size() - 1 || (i > 0 && index == 0)) {
                flag = -flag;
            }
            index += flag;
        }

        for (StringBuilder stringBuilder : list) {
            sb.append(stringBuilder);
        }
        return sb.toString();
    }
}
