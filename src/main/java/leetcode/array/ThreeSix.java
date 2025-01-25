package leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 有效的数独
 *
 * @author zengxi.song
 * @date 2025/1/13
 */
public class ThreeSix {

    public boolean isValidSudoku(char[][] board) {
        // 固定大小的使用数组会比hashMap快 时间复杂度O(n*n) 空间复杂度O(n) 但是由于单元格个数是固定的 所以也可以将时间和空间都认为O(1)
        boolean[][] row = new boolean[9][9];
        boolean[][] column = new boolean[9][9];
        boolean[][][] block = new boolean[3][3][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int index = board[i][j] - '1';
                if (row[i][index] || column[j][index] || block[i / 3][j / 3][index]) {
                    return false;
                }
                row[i][index] = column[j][index] = block[i / 3][j / 3][index] = true;
            }
        }
        return true;
    }

    public boolean isValidSudoku1(char[][] board) {
        // 需要三个Map 时间复杂度O(n*n) 空间复杂度O(n) 但是由于单元格个数是固定的 所以也可以将时间和空间都认为O(1)
        Map<Integer, Set<Character>> rowMap = new HashMap<>();
        Map<Integer, Set<Character>> columnMap = new HashMap<>();
        Map<Integer, Set<Character>> blockMap = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (!rowMap.computeIfAbsent(i, k -> new HashSet<>()).add(board[i][j])) {
                    return false;
                }
                if (!columnMap.computeIfAbsent(j, k -> new HashSet<>()).add(board[i][j])) {
                    return false;
                }
                if (!blockMap.computeIfAbsent(confirmBlockIndex(i, j), k -> new HashSet<>()).add(board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private int confirmBlockIndex(int i, int j) {
        // i的权重足够大 就能保证编号不重复 3就够了
        return 3 * (i / 3) + (j / 3);
    }
}
