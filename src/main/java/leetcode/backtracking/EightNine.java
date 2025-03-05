package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 格雷编码
 *
 * @author zengxi.song
 * @date 2025/2/14
 */
public class EightNine {

    boolean interrupt = false;
    int n;
    int count;

    public List<Integer> grayCode(int n) {
        // 回溯
        // 题中不会超出int范围
        this.n = n;
        count = (int) Math.pow(2, n);
        List<Integer> res = new ArrayList<>();
        res.add(0);
        boolean[] used = new boolean[count];
        used[0] = true;
        backTacking(res, 0, used);
        return res;
    }

    private void backTacking(List<Integer> res, int cur, boolean[] used) {
        if (res.size() == count) {
            if (Integer.bitCount(cur) == 1) {
                interrupt = true;
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            // 在cur的每一位上我们都与0异或即为原值 特殊的一位与1的异或会进行取反
            int next = cur ^ (1 << i);
            if (used[next]) {
                continue;
            }
            res.add(next);
            used[next] = true;
            backTacking(res, next, used);
            // 在下一次remove之前 进行中断检查
            if (interrupt) {
                return;
            }
            res.remove(res.size() - 1);
            used[next] = false;
        }
    }

}
