package Ordered;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0.
 * 精进案例
 * 1. A straight forward solution using O(mn) space is probably a bad idea.
 * 新建一个与matrix同样大小的数组newArray，以matrix为参照操作newArray
 * 2. A simple improvement uses O(m + n) space, but still not the best solution.
 * 问题本身是设置某行和列为0，so, 新建一个一行大小和一个一列大小的数组，分别表示该列和行是否要置零
 * 3. Could you devise a constant space solution?
 * 直接使用matrix的首行和首列来标识该列和行是否置零，至于首行和首列是否置零单独用变量标识
 */
public class _73__Set_Matrix_Zeroes {
    public void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        boolean fr = false, fc = false;
        int m = matrix.length;
        int n = matrix[0].length;
        // 如果matrix[i,j]==0，就让列首、行首为0（标记），如果行首、列首存在为0的情形，则让fr，fc为true
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) {
                    if(i == 0) fr = true;
                    if(j == 0) fc = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for(int i = 1; i < m; i ++) {
            for(int j = 1; j < n; j ++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if(fr)
            for(int j = 0; j < n; j ++) {
                matrix[0][j] = 0;
            }
        if(fc)
            for(int i = 0; i < m; i ++) {
                matrix[i][0] = 0;
            }
    }
}
