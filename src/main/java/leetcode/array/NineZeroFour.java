package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 水果成篮
 *
 * @author zengxi.song
 * @date 2024/7/8
 */
public class NineZeroFour {

    public int totalFruit(int[] fruits) {
        // 使用hash表优化后的双指针 时间复杂度O(N) 空间复杂度O(1) 固定节点 接近O(1)
        Map<Integer, Integer> countMap = new HashMap<>(4);
        int left = 0, right = 0;
        int res = 0;
        while (right < fruits.length) {
            countMap.merge(fruits[right++], 1, Integer::sum);
            while (countMap.size() > 2) {
                Integer merge = countMap.merge(fruits[left], 1, (o, n) -> o - n);
                if (merge == 0) {
                    countMap.remove(fruits[left]);
                }
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public int totalFruit1(int[] fruits) {
        // 双指针 时间复杂度不止O(N)应该是介于O(N)~O(2N)之间 空间复杂度O(1)
        int left = 0, right1 = 0, right2;
        int res = 0;
        while (right1 < fruits.length) {
            while (right1 < fruits.length && fruits[right1] == fruits[left]) {
                right1++;
            }
            if (right1 >= fruits.length) {
                res = Math.max(res, right1 - left);
                break;
            }
            right2 = right1;
            while (right2 < fruits.length && ((fruits[right2] == fruits[right1]) || fruits[right2] == fruits[left])) {
                right2++;
            }
            res = Math.max(res, right2 - left);
            left = right1;
        }
        return res;
    }

    public static void main(String[] args) {
        new NineZeroFour().totalFruit(new int[]{1, 2, 3, 2, 2});
    }
}
