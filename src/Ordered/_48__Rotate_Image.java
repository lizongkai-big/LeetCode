package Ordered;
// 一次旋转90度有点复杂，可以分两步嘛！
public class _48__Rotate_Image {
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        // reverse
        for(int i = 0; i < len / 2; i ++) {
            // i <--> len - i - 1
            for(int j = 0; j < len; j ++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[len - 1 - i][j];
                matrix[len - 1 - i][j] = t;
            }
        }
        // swap
        for(int i = 0; i < len; i ++) {
            for(int j = i; j < len; j ++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
    }
}
