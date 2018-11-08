package Ordered;

/**
 * 寻找最长的回文数，回文数长度可能为偶数，可能是奇数
 */
public class _5__Longest_Palindromic_Substring {
    static int startInx = 0, validLen = 0;
    static int left = 0, right = 0;
    /**
     * 中心扩展算法 时间复杂度O(n^2) 空间复杂度O(n)
     * @param s
     * @return
     */
    public static String longestPalindrome_CenterExtend(String s) {
        int len = s.length();
        char[] c = s.toCharArray();
        int i = 0;
        /*循环记得要自增，不然就是死循环咯*/
        while(i < len - 1) {
            // assume even length.
            if(c[i] == c[i + 1]) {
                left = i;
                right = i + 1;
                findMaxPalindromic(c, left, right);
            }
            // assume odd length, try to extend Palindrome as possible
            left = right = i;
            findMaxPalindromic(c, left, right);
            i++;
        }
        /*substring(beginIndex, endInx)*/
        return s.substring(startInx, startInx+validLen);
    }
    /**
     * 以left、right为起点，向两侧寻找最长的回文数，并记录startInx, validLen
     * 左右起点可能相同
     * @param c  数组
     * @param left 左起点
     * @param right 右起点
     */
    public static void findMaxPalindromic(char[] c, int left, int right) {
        int len = c.length;
        // 向左右延伸，判断是否认为回文数
        while(left >= 0 && right < len && c[left] == c[right]) {
            // 变化left\right即可，不需要一个step
            left --;
            right ++;
        }
        /*此时的left, right正好指向回文数之外的位置，所以有效的wide为right-left-1*/
        int wide = right - left - 1;
        // 更新validLen, startInx
        if(wide > validLen) {
            validLen = wide;
            startInx = left + 1;
        }
    }

    /**
     * dp[j][i]表示s区间[j,i]是否回文字符串，j,i指向一个字符串的首尾
     * when j == i, dp[j][i] == true
     * when j<i, if s[j]==s[i]（字符串的首尾不相等必然不是回文字符串）
     *              &&
     *              (i - j < 3 （字符串的首尾相邻（i-j==1）或者首尾之间只隔了一个元素（i-j==2））
     *              ||
     *              dp[j+1][i+1]) （看首尾各减1形成的字符串是否为回文字符串；如果是的话，就相当于回文字符串得到了扩展）
     * @param s
     * @return
     */
    public static String longestPalindrome_DP(String s) {
        int len = s.length();
        /* 特例判断 */
        if(len == 0)
            return s;
        /*len>=1的情况，dp算法就可以执行，而且substring可以剪切出一个子串*/
        char[] c = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        for(int i = 0; i < len; i ++) {
            for(int j = 0; j < i; j ++) {
                // j+1, i-1不怕越界吗？
                // because j<i && max(j+1)==i，而i<len； min(j+1)==1 ;所以1 <= j+1 <= len 不会越界
                // min(i) == 1, max(i) == len -1, so 0 <= i-1 <= len-2 不会越界
                /**/
                dp[j][i] = (c[i] == c[j] && (i - j < 3 || dp[j + 1][i - 1]));
                // 更新right, left
                // 当len==2时候真正的长度，并不是validLen，所以为了使代码更为简洁，validLen只是为了长度的比较，
                // 并不是真正的长度，该if语句是为了更新left,right，真正的长度=right-left+1
                if(dp[j][i] && i-j+1 > validLen) {
                    validLen = i - j + 1;
                    left = j;
                    right = i;
                }
            }
            /*放在j的循环前后都无所谓啦*/
            dp[i][i] = true;
        }
        return s.substring(left, right + 1);
    }

    /**
     * 马拉车算法 O(n)
     * 最长回文字符串的字符个数 = resLen - 1
     * 起始位置 = (resCenter-resLen) / 2
     * @param s
     * @return
     */
    public static String longestPalindrome_Manacher(String s) {
        StringBuilder t = new StringBuilder("$#");
        for(char c: s.toCharArray()) {
            t.append(c+"#");
        }
        int len = t.length();
        // p[i] 表示以s[i]为字符中心的回文子串的半径
        int[] p = new int[len];
        for(int i = 0; i < len; i ++) {
            p[i] = 0;
        }
        char[] c = t.toString().toCharArray();
        // mx表示已知回文字符串的最靠右的位置，id表示mx对应回文字符串的中心位置；
        // resLen最长的回文字符串长度，resCenter是最长的回文字符串的中心位置在c中的下标
        int mx = 0, id = 0, resLen = 0, resCenter = 0;
        // 从第一个 # 开始
        for(int i = 1; i < len; i ++) {
            // 根据mx和i的关系，得到一个回文字符串半径的经验值
            p[i] = mx > i ? Math.min(mx - i, p[id + id - i]) : 1;
            /*在下标不越界的情况下，向两侧延伸回文字符串*/
            while(i + p[i] < len && i - p[i] >= 0 && c[i + p[i]] == c[i - p[i]]) p[i] ++;
            // 更新mx和id
            if(mx < i + p[i]) {
                mx = i + p[i];
                id = i;
            }
            // 更新resLen 和 resCenter
            if(resLen < p[i]) {
                resLen = p[i];
                resCenter = i;
            }
        }
        return s.substring((resCenter-resLen)/2, (resCenter-resLen)/2 + resLen - 1);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome_Manacher("babadada"));
    }
}
