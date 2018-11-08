package Ordered;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 如果是把words重新组合，则没有考虑到words中字符串都是相同长度的条件
 * 感觉这道题比较没营养，可以学到map 的使用、越界的判断、break的条件
 */
public class _30__Substring_with_Concatenation_of_All_Words {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList<>();
        int wordNum = words.length;
        int slen = s.length();
        if(slen == 0 || wordNum == 0)
            return res;
        int wordLen = words[0].length();
        Map<String ,Integer> wordMap = new HashMap<>();
        for (int i = 0; i < wordNum; i++) {
            if(words[i].length() != wordLen) {
                return res;
            }
            wordMap.put(words[i], wordMap.getOrDefault(words[i], 0) + 1);
        }
        for (int i = 0; i < slen - wordLen * wordNum + 1; i++) {
            Map<String, Integer> tmpMap = new HashMap<>();
            int j = 0;
            for (; j < wordNum; j++) {
                String tmp = s.substring(i + j * wordLen, i + (j+1) * wordLen);
                if(!wordMap.containsKey(tmp)) {
                    break;
                }
                tmpMap.put(tmp, tmpMap.getOrDefault(tmp, 0) + 1);
                if(tmpMap.get(tmp) > wordMap.get(tmp)) {
                    break;
                }
            }
            if(j == wordNum) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _30__Substring_with_Concatenation_of_All_Words substringWithConcatenationOfAllWords = new _30__Substring_with_Concatenation_of_All_Words();
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        System.out.println(substringWithConcatenationOfAllWords.findSubstring(s, words));
    }
}
