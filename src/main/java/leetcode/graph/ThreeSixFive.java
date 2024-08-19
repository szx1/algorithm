package leetcode.graph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 水壶问题
 *
 * @author zengxi.song
 * @date 2024/7/29
 */
public class ThreeSixFive {

    public boolean canMeasureWater(int x, int y, int target) {
        return dfs(x, y, 0, 0, target, new HashSet<>());
    }

    private boolean dfs(int x, int y, int xr, int yr, int target, Set<Aux> appeared) {
        if (xr == target || yr == target || xr + yr == target) {
            return true;
        }
        if (!appeared.add(new Aux(xr, yr))) {
            return false;
        }
        return  // 装满x
                dfs(x, y, x, yr, target, appeared)
                        // 装满y
                        || dfs(x, y, xr, y, target, appeared)
                        // x倒入y
                        || dfs(x, y, Math.max(xr - (y - yr), 0), Math.min(yr + xr, y), target, appeared)
                        // y倒入x
                        || dfs(x, y, Math.min(xr + yr, x), Math.max(yr - (x - xr), 0), target, appeared)
                        // 清空x
                        || dfs(x, y, 0, yr, target, appeared)
                        // 清空y
                        || dfs(x, y, xr, 0, target, appeared);
    }

    static class Aux {
        int x;
        int y;

        public Aux(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Aux aux = (Aux) o;
            return x == aux.x && y == aux.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        new ThreeSixFive().canMeasureWater(3, 5, 4);
    }
}
