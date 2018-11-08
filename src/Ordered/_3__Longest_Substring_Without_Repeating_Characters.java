package Ordered;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class _3__Longest_Substring_Without_Repeating_Characters {
    public int lengthOfLongestSubstring1(String s) {
        if(s == null)
            return 0;
        int len = s.length();
        // 空字符串长度为0呀
        if(len == 1 || len == 0)
            return len;
        int max = 1;
        int cur = 1;
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        for(int i = 1; i < len; i ++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                int x = map.get(c);
                // 清空x之前的键值对
                for(Iterator<Map.Entry<Character, Integer>> it = map.entrySet().iterator(); it.hasNext() ;){
                    Map.Entry<Character, Integer> item = it.next();
                    if(item.getValue() < x) {
                        it.remove();
                    }
                }
                map.replace(c, i);
                // System.out.println(i);
                // for(Map.Entry entry: map.entrySet()){
                //     String k = (entry.getValue() + " ") + entry.getKey();
                //     System.out.print(k + ", ");
                // }
                // System.out.println("--------");
                cur = i - x; // 肯定是减小了，无需更新max
            }
            else {
                cur ++;
                max = cur > max ? cur : max;
                // add new node
                map.put(c, i);
            }
        }
        return max;
    }

    /**
     * 优化，但是map会不断扩大，但是也不会无限大
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if(s == null)
            return 0;
        int len = s.length();
        // 空字符串长度为0呀
        if(len == 1 || len == 0)
            return len;
        int max = 1;
        Map<Character, Integer> map = new HashMap<>();
        // substring's front point
        int x = 0;
        // substring's end point
        for(int i = 0; i < len; i ++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                x = Math.max(x, map.get(c) + 1);
            }
            // add or replace new node
            map.put(c, i);
            max = Math.max(i - x + 1, max);
        }
        return max;
    }

    /**
     * you can use fixed size (128) array to be the map
     * since key will always be a character value.
     * 因此，map的清空操作就对应了array值的减一
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringX(String s) {
        int[] map = new int[128];

        int start = 0, end = 0, len = 0;

        while (end < s.length()) {
            // 之前不存在
            if (map[s.charAt(end++)]++ == 0) {
                len = Math.max(len, end - start);
            }
            // 存在就要去重
            else {
                while (map[s.charAt(end-1)] > 1) {
                    map[s.charAt(start++)]--;
                }
            }
        }
        return len;
    }
}
