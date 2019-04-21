package Ordered;

import java.util.HashMap;
import java.util.Map;

public class _76__Minimum_Window_Substring {
    public String minWindow(String s, String t) {
        if(s.length() == 0 || t.length() == 0) {
            return "";
        }
        // 记录需要的各种字符，以及个数（字符可能会重复）
        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            int count = dict.getOrDefault(t.charAt(i), 0);
            dict.put(t.charAt(i), count + 1);
        }
        int required = dict.size();
        // 滑动窗口的左右指针，当l==r时，滑动窗口为空
        int l = 0, r = 0;
        int formed = 0;
        // 记录滑动窗口中各个字符的个数
        Map<Character, Integer> windowCounts = new HashMap<>();
        int[] ans = {-1, 0, 0};
        while (r < s.length()) {
            // 滑动窗口中增加右指针指向的字符
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);
            // 该字符是字典中需要的数字，并且数目达到dict要求
            if(dict.containsKey(c) && windowCounts.get(c).intValue() == dict.get(c).intValue()) {
                formed ++;
            }
            // 当滑动窗口中的字符个数达到要求时，左指针右移。
            // 要更新windowCounts, ans, 去掉左指针指向的字符。
            while (l <= r && formed == required) {
                c = s.charAt(l);
                if(ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r-l+1;
                    ans[1] = l;
                    ans[2] = r;
                }

                windowCounts.put(c, windowCounts.get(c)-1);
                if(dict.containsKey(c) && windowCounts.get(c).intValue() < dict.get(c).intValue())
                    formed--;

                l++;
            }
            r ++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
