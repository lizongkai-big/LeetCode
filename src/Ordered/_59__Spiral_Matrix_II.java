package Ordered;

public class _59__Spiral_Matrix_II {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int top = 0, bottom = n-1, left = 0, right = n-1;
        for (int i = 1; i <= n*n; ) {
            for (int j = left; j <= right; j++) {
                res[top][j] = i;
                i ++;
            }
            top ++;
            for (int j = top; j <= bottom; j++) {
                res[j][right] = i;
                i ++;
            }
            right --;
            for (int j = right; j >= left; j--) {
                res[bottom][j] = i;
                i ++;
            }
            bottom --;
            for (int j = bottom; j >= top; j--) {
                res[j][left] = i;
                i ++;
            }
            left ++;
        }
        return res;
    }
}
