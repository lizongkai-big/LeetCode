package Ordered;

import java.util.HashSet;
import java.util.Set;

/**
 * 集合是一个不重复的列表
 */
public class _36__Valid_Sudoku {
    public boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        int row = board.length; // 9
        int col = board[0].length; // 9
        boolean flag = true;
        // 行 列，3*3单元格
        for(int i = 0; i < row && flag; i ++) {
            for(int j = 0; j < col; j ++) {
                char num = board[i][j];
                if(num != '.') { // character is a digit
                    if(!seen.add(num + "in row" + i) ||
                            !seen.add(num + "in col" + j) ||
                            !seen.add(num + "in block" + i / 3 + "," + j / 3) ) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
