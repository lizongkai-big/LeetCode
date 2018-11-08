package Ordered;

/**
 * 太鸡儿难了，放弃吧~
 */
public class _10__Regular_Expression_Matching {
    /**
     * isMatch_Mine 方法中可以做到s是否为p的一部分匹配，也就是The matching should cover the entire input string (not partial).
     * 但是其中的‘*’无法做到非贪婪匹配，所以s = "aaaa", p = "a*aa"的情况总是false；
     * 所以The the entire input string should NOT cover the matching.
     * 如果硬是要接着做，可能增加若干个状态可以解决，但是不够简洁通用
     * 因为有一个择优的过程，这道题应该使用DP解法
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch_Mine(String s, String p) {
        if(s.equals(""))
            return s.equals(p);
        int len1 = s.length();
        int len2 = p.length();
        char[] c1 = s.toCharArray();
        char[] c2 = p.toCharArray();
        int i = 0, j = 0;
        /*error: empty character literal*/
        char pre = '0';
        // --》 以p匹配s
        while(j < len2) {
            // 单字符匹配
            if(i < len1 && c1[i] == c2[j] || c2[j] == '.'){
                pre = c2[j];
                i ++;
                j ++;
                continue;
            }
            // 和之前字符相同
            else if(c2[j] == '*') {
                // 匹配之后的元素0+次
                while(i < len1 && (c1[i] == pre || pre == '.')) i ++;
                j ++;
                continue;
            }
            // 对当前的一个元素匹配0次
            else if(j + 1 < len2 && c2[j + 1] == '*') {
                // pre = c1[i];
                j += 2;
                continue;
            }
            /*
            // 前面的‘*’的匹配多了，无法确定
            else if(j - 1 >= 0 && c2[j-1] == '*' && pre == c2[j]) {
                pre = '*';
                j ++;
            }*/
            else {
                break;
            }
        }

        if(j == len2 && i == len1) // s、p 互相完美匹配
            return true;
        return false;
    }

    public boolean isMatch1(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch1(text, pattern.substring(2)) ||
                    (first_match && isMatch1(text.substring(1), pattern)));
        } else {
            return first_match && isMatch1(text.substring(1), pattern.substring(1));
        }
    }

    public boolean isMatch_DP(String s, String p) {
        if(s.equals(""))
            return s.equals(p);
        int len1 = s.length();
        int len2 = p.length();
        char[] c1 = s.toCharArray();
        char[] c2 = p.toCharArray();
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        // ？？
        for (int i = 0; i < len2; i++) {
            if(c2[i] == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if(c2[j] == '.' || c2[j] == c1[i]){
                    // 匹配，和之前的状态相同
                    dp[i+1][j+1] = dp[i][j];
                }
                if(c2[j] == '*') {
                    // x* --> empty
                    if(c2[j-1] != c1[i] && c2[j-1] != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    }
                    else {
                        if(dp[i+1][j]) //single one
                            dp[i+1][j+1] = true;
                        if(dp[i][j+1]) // multiple
                            dp[i+1][j+1] = true;
                        if(dp[i+1][j-1]) // empty
                            dp[i+1][j+1] = true;
                        /*dp[i+1][j+1] = dp[i+1][j]
                                || dp[i][j+1]
                                || dp[i+1][j-1];*/
                    }
                }
            }
        }
        for (int i = 0; i <= len2; i++) {
            for (int j = 0; j <= len1; j++) {
                System.out.print(dp[j][i] + "\t");
            }
            System.out.println();
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        _10__Regular_Expression_Matching regular_expression_matching =
                new _10__Regular_Expression_Matching();
        String s = "aaaa";
        String p = "a*aa";
        System.out.println(regular_expression_matching.isMatch_DP(s, p));
    }
}
