package Ordered;

import java.util.*;

public class _49__Group_Anagrams {

    public List<List<String>> groupAnagrams_optimization(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        // hash表嘛，读取、存放都是以key为目的
        Map<String, Integer> dict = new HashMap<>();
        int i = 0;
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            // s = ca.toString(); 可不能从 char[] --> String
            String key = String.valueOf(ca);
            if(dict.containsKey(key)) {
                int inx = dict.get(key);
                result.get(inx).add(s);
            }
            else {
                // dict读取，一般以key为中心啊
                dict.put(key, i++);
                List<String> newList = new ArrayList<>();
                newList.add(s);
                result.add(newList);
            }
        }
        return result;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        List<String> dict = new ArrayList<>();
        for (String s: strs) {
            // check s in dict or not
            int inx = getInx(dict, s);
            // if not, 分解s set in dict, and add s into result
            if(inx == -1) {
                dict.add(s);
                List<String> newList = new ArrayList<String>();
                newList.add(s);
                result.add(newList);
            }
            // if in, get its inx, add s into result,
            else {
                result.get(inx).add(s);
            }
        }
        return result;
    }



    public static void main(String[] args) {
        char[] ca = (new String("abd")).toCharArray();
        System.out.println(ca);
        System.out.println(ca.toString());
        System.out.println(String.valueOf(ca));
    }

    public int getInx(List<String> dict, String str) {
        if(dict.isEmpty()) return -1;
        for (int i = 0; i < dict.size(); i++) {
            String t = dict.get(i);
            if(t.length() == str.length() && check(t, str)) {
                return i;
            }
        }
        return -1;
    }

    public boolean check(String s1, String s2) {
        char[] alpha = new char[26];
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for (int i = 0; i < c1.length; i++) {
            alpha[c1[i] - 'a'] += 1;
        }
        for (int i = 0; i < c2.length; i++) {
            if(alpha[c2[i] - 'a'] == 0) {
                return false;
            }
            else {
                alpha[c2[i] - 'a'] -= 1;
            }
        }
        return true;
    }
}
