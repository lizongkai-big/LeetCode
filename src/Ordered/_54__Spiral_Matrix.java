package Ordered;

import java.util.ArrayList;
import java.util.List;

public class _54__Spiral_Matrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int row = matrix.length;
        if(row == 0) return res;
        int col = matrix[0].length;
        int top = 0, left = 0, bottom = row - 1, right = col - 1;
        while(true) {
            // r row
            for(int j = left; j <= right; j++) {
                res.add(matrix[top][j]);
            }
            top++;
            if(left>right || top>bottom) break;
            // col-c-1 colum
            for(int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if(left>right || top>bottom) break;
            // row-r-1 row
            for(int j = right; j >= left; j--) {
                res.add(matrix[bottom][j]);
            }
            bottom--;
            if(left>right || top>bottom) break;
            // c colum
            for(int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            if(left>right || top>bottom) break;
        }
        return res;
    }
}
