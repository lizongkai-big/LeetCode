package TAG.DP;

import java.util.Arrays;

/**
 * 精进案例
 */
public class _62__Unique_Paths {
    public int uniquePaths_mine(int m, int n) {
        if(m == 0 || n == 0) return 0;
        int[][] pathNums = getPaths(m, n);
        return pathNums[m-1][n-1];
    }

    /**
     * 事实上，getPaths(m, n)计算pathNums时，只用到了上一行和本行的前一列，所以只需要两行就行了
     * O(2*min(m, n)) space complexity
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths_optimization_1(int m, int n) {
        /*好评，以前总想着要交换n, m*/
        if (m > n) return uniquePaths_optimization_1(n, m);
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        int[] pre = new int[n];
        Arrays.fill(pre, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] = cur[j - 1] + pre[j];
            }
            // swap cur-pre
            pre = cur;
        }
        return cur[n-1];
    }

    /**
     * 两行，本质上就是一行嘛
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths_optimization_2(int m, int n) {
        if(m == 0 || n == 0) return 0;
        if (m > n) return uniquePaths_optimization_2(n, m);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 1; i < m; i ++) { // 从第二行开始
            for(int j = 1; j < n; j ++) {
                dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
    }

    /**
     * 函数依据P[i][j] = P[i - 1][j] + P[i][j - 1]的特点，但是需要O(n^2)的空间
     * @param m
     * @param n
     * @return
     */
    public int[][] getPaths(int m, int n) {
        int[][] pathNums = new int[m][n];
        pathNums[0][0] = 1;
        for(int i = 0; i < m; i ++) {
            for(int j = 0; j < n; j ++) {
                if(i == 0 || j == 0) {
                    pathNums[i][j] = 1;
                }
                else {
                    pathNums[i][j] = pathNums[i][j-1] + pathNums[i-1][j];
                }
            }
        }
        return pathNums;
    }
}
