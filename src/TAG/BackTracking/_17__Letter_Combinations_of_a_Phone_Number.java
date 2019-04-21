package TAG.BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _17__Letter_Combinations_of_a_Phone_Number {
    /**
     * 改进点：1. 数字字符与字符串的匹配，由if...else改为数组  2. 使用queue更为优雅
     * @param digits
     * @return
     */
    public List<String> letterCombinations_Mine(String digits) {
        List<String> res = new ArrayList<>();
        int len = digits.length();
        for(int i = 0; i < len; i ++) {
            // Main
            String s = "";
            if(digits.charAt(i) == '2') {
                s = "abc";
            }
            else if(digits.charAt(i) == '3') {
                s = "def";
            }
            else if(digits.charAt(i) == '4') {
                s = "ghi";
            }
            else if(digits.charAt(i) == '5') {
                s = "jkl";
            }
            else if(digits.charAt(i) == '6') {
                s = "mno";
            }
            else if(digits.charAt(i) == '7') {
                s = "pqrs";
            }
            else if(digits.charAt(i) == '8') {
                s = "tuv";
            }
            else if(digits.charAt(i) == '9') {
                s = "wxyz";
            }
            // second
            if(res.size() == 0) {
                for(int j = 0; j < s.length(); j ++) {
                    res.add(s.charAt(j) + "");
                }
            }
            else {
                List<String> newRes = new ArrayList<>();
                for(int j = 0; j < res.size(); j ++) {
                    String t = res.get(j);
                    for(int z = 0; z < s.length(); z ++) {
                        newRes.add(t + s.charAt(z));
                    }
                }
                res = newRes;
            }
        }
        return res;
    }

    public List<String> letterCombinations_Others(String digits) {
        LinkedList<String> res = new LinkedList<>();
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        int len = digits.length();
        res.add("");
        for(int i = 0; i < len; i ++) {
            // Main optimization point
            String s = mapping[digits.charAt(i) - '0'];
            // second optimization point
            while(res.peek().length() == i) {
                String t = res.remove();
                for (char c: s.toCharArray()) {
                    res.addLast(t + s.charAt(c));
                }
            }
        }
        return res;
    }
}
