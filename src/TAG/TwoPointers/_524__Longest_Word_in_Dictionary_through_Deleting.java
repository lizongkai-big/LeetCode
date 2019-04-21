package TAG.TwoPointers;

import java.util.List;

public class _524__Longest_Word_in_Dictionary_through_Deleting {
    /**
     * 判断t是否为s的子序列
     * @param s
     * @param t
     * @return
     */
    private boolean isSubsequence(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        int j = 0;
        for (int i = 0; i < len1 && j < len2; i++) {
            if(s.charAt(i) == t.charAt(j)) j++;
        }
        return j == len2;
    }

    public String findLongestWord(String s, List<String> d) {
        String res = "";
        int resLen = 0;
        for(String tmp: d) {
            if((tmp.length() > resLen || tmp.length() == resLen && tmp.compareTo(res) < 0) && isSubsequence(s, tmp)) {
                resLen = tmp.length();
                res = tmp;
            }
        }
        return "";
    }
}
