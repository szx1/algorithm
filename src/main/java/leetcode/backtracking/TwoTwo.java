package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 *
 * @author zengxi.song
 * @date 2024/8/30
 */
public class TwoTwo {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backTracking(res, new StringBuilder(), n, 0, 0);
        return res;
    }

    private void backTracking(List<String> res, StringBuilder sb, int n, int leftCount, int rightCount) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        if (leftCount < n) {
            sb.append('(');
            backTracking(res, sb, n, leftCount + 1, rightCount);
            sb.setLength(sb.length() - 1);
        }
        // 只有右括号使用次数小于左括号时 添加右括号才会有效
        if (rightCount < leftCount) {
            sb.append(')');
            backTracking(res, sb, n, leftCount, rightCount + 1);
            sb.setLength(sb.length() - 1);
        }
    }
}
