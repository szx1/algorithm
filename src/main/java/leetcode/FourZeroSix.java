package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 根据身高重建队列
 *
 * @author zengxi.song
 * @date 2024/4/24
 */
public class FourZeroSix {


    public int[][] reconstructQueue(int[][] people) {
        // 由高到低排序
        Arrays.sort(people, (person1, person2) -> {
            if (person1[0] != person2[0]) {
                return person2[0] - person1[0];
            } else {
                return person1[1] - person2[1];
            }
        });
        List<int[]> ans = new ArrayList<>();
        for (int[] person : people) {
            // 先插入高的，然后插入矮的，高的逐渐往后移 因为矮的不会影响高的
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }


    public int[][] reconstructQueue1(int[][] people) {
        // 由低到高排序
        Arrays.sort(people, (person1, person2) -> {
            if (person1[0] != person2[0]) {
                return person1[0] - person2[0];
            } else {
                // 前面人多的先找位置
                return person2[1] - person1[1];
            }
        });
        int n = people.length;
        int[][] res = new int[n][];
        for (int[] person : people) {
            int spaces = person[1];
            for (int i = 0; i < n; ++i) {
                if (res[i] == null) {
                    if (spaces-- == 0) {
                        res[i] = person;
                        break;
                    }
                }
            }
        }
        return res;
    }
}
