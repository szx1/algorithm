package leetcode;

/**
 * 盛最多水的容器
 *
 * @author zengxi.song
 * @date 2024/4/25
 */
public class OneOne {

    public int maxArea(int[] height) {
        // 时间复杂度 O(n)
        // 每次移动x坐标必减少，所以只有移动较短的y轴才不会错过最大值
        int res = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            res = Math.max(res, Math.min(height[left], height[right]) * (right - left));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    public int maxArea1(int[] height) {
        // 时间复杂度O(n2) 超时
        int res = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return res;
    }
}
