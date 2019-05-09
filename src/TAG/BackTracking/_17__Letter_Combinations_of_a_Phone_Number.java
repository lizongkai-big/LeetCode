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
            String s = getLetter(digits.charAt(i));
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

    public List<String> letterCombinations_backtracking(String digits) {
        List<String> res = new ArrayList<>();
        if(digits == null || digits.length() == 0) return res;
        lbRecur(digits, 0, "", res);
        return res;
    }

    public void lbRecur(String digits, int inx, String had, List<String> res) {
        int len = digits.length();
        if(inx == len) {
            res.add(had);
            return;
        }

        String letter = getLetter(digits.charAt(inx));
        int letterLen = letter.length();
        for (int j = 0; j < letterLen; j++) {
            lbRecur(digits, inx+1, had+letter.charAt(j), res);
        }

    }

    public String getLetter(char num) {
        String res = "";
        switch (num) {
            case '1': break;
            case '2':
                res = "abc";
                break;
            case '3':
                res = "def";
                break;
            case '4':
                res = "ghi";
                break;
            case '5':
                res = "jkl";
                break;
            case '6':
                res = "mno";
                break;
            case '7':
                res = "pqrs";
                break;
            case '8':
                res = "tuv";
                break;
            case '9':
                res = "wxyz";
                break;
        }
        return res;
    }
}
