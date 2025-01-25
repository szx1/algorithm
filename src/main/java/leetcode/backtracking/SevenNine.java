package leetcode.backtracking;

/**
 * 单词搜索
 *
 * @author zengxi.song
 * @date 2024/9/4
 */
public class SevenNine {

    private final int[][] direct = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        // dfs回溯 在方法1的基础上进行优化 优化后20ms左右
        // 首先统计字母出现的次数 如果end出现的次数小于start则反转字符串
        // 本质是优先判断出现次数少的字母 为了减少递归的次数
        int m = board.length;
        int n = board[0].length;
        char start = word.charAt(0);
        char end = word.charAt(word.length() - 1);
        if (start != end) {
            int startCount = 0, endCount = 0;
            for (char[] chars : board) {
                for (int i = 0; i < n; i++) {
                    if (chars[i] == start) {
                        startCount++;
                    } else if (chars[i] == end) {
                        endCount++;
                    }
                }
            }
            if (endCount < startCount) {
                word = new StringBuilder(word).reverse().toString();
            }
        }
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word.toCharArray(), 0, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean exist2(char[][] board, String word) {
        // dfs回溯 将word转换为char数组 可以稍微优化一下速度 leetCode 170ms左右
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word.toCharArray(), 0, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] chars, int index, int i, int j, boolean[][] visited) {
        if (index >= chars.length) {
            return true;
        }
        if (board[i][j] != chars[index]) {
            return false;
        }
        if (index == chars.length - 1) {
            // 减少一次递归
            return true;
        }
        visited[i][j] = true;
        for (int[] arr : direct) {
            int x = arr[0] + i;
            int y = arr[1] + j;
            if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || visited[x][y]) {
                continue;
            }
            if (dfs(board, chars, index + 1, x, y, visited)) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }

    public boolean exist1(char[][] board, String word) {
        // dfs回溯 leetCode 180ms左右
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int index, int i, int j, boolean[][] visited) {
        if (index >= word.length()) {
            return true;
        }
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            // 减少一次递归
            return true;
        }
        visited[i][j] = true;
        for (int[] arr : direct) {
            int x = arr[0] + i;
            int y = arr[1] + j;
            if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || visited[x][y]) {
                continue;
            }
            if (dfs(board, word, index + 1, x, y, visited)) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }
}
