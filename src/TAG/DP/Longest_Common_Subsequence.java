package TAG.DP;

import java.util.Arrays;

// 最长公共子序列

/**
 *              0                   i = 0 || j == 0
 * LCS[i, j] =  LCS[i-1, j-1] + 1   i,j > 0 && Xi == Yj
 *              Max{LCS[i-1,j],     i,j > 0 && Xi != Yj
 *              LCS[i,j-1]}
 */
public class Longest_Common_Subsequence {
    public String lcs(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // return dp[m][n];
        StringBuilder sb = new StringBuilder();
        for (int i = m, j = n; i >= 1 && j >= 1;) {
            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                sb.append(s.charAt(i - 1));
                j --;
                i --;
            } else {
                if (dp[i][j - 1] >= dp[i - 1][j]) {
                    sb.append(s.charAt(j - 1));
                    j --;
                } else {
                    sb.append(s.charAt(i - 1));
                    i --;
                }
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Longest_Common_Subsequence longest_common_subsequence = new Longest_Common_Subsequence();
        String s = "abababa";
        System.out.println(longest_common_subsequence.lcs(s, s));
    }
}
