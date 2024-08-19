package leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 *
 * @author zengxi.song
 * @date 2024/7/19
 */
public class OneOneEight {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> sub = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    sub.add(1);
                    continue;
                }
                List<Integer> list = res.get(i - 1);
                sub.add(list.get(j - 1) + list.get(j));
            }
            res.add(sub);
        }
        return res;
    }
}
