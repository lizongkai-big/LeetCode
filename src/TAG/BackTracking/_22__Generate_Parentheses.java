package TAG.BackTracking;

import java.util.*;

public class _22__Generate_Parentheses {
    /**
     * 遍历所有情况，通过剪枝来减少无效情况的发生
     * 通过判断左右括号的个数，以及是有效括号字符串来加入结果集
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis_Mine(int n) {
        List<String> res = new ArrayList<>();
        // find all situations, keep valid parenthes
        findAllParenthes(res, "", 0, 0, n);
        return res;
    }

    // 剪枝
    public void findAllParenthes(List<String> res, String s, int leftParenthesNum, int rightParenthesNum, int total) {
        /*递归的返回条件放在第一行，说明需要多一次递归；可以把判断条件放在内部，减少一次递归*/
        // if(leftParenthesNum > total || rightParenthesNum > total) return;
        if (leftParenthesNum == total && rightParenthesNum == total && isValid(s)) { //
            res.add(s);
            return;
        }
        /************************************KEY DIFFERENCE*************************************/
        // 只限制左右括号的个数，而忽略两者之间的关系，就需要另外判断形成的字符串是否有效
        if (leftParenthesNum < total)
            findAllParenthes(res, s + "(", leftParenthesNum + 1, rightParenthesNum, total);
        if (rightParenthesNum < total)
            findAllParenthes(res, s + ")", leftParenthesNum, rightParenthesNum + 1, total);
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(')
                stack.push(s.charAt(i));
            else { // ')'
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        if (stack.isEmpty())
            return true;
        return false;
    }

    /**
     * 回溯，带有剪枝
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis_Others(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public void backtrack(List<String> list, String str, int open, int close, int max) {
        if (str.length() == max * 2) { // equal to (open == max && close == max)
            list.add(str);
            return;
        }
        /************************************KEY DIFFERENCE*************************************/
        // 既考虑左右括号的个数，也考虑两者之间的关系，就能够保证最终形成的字符串是有效的
        if (open < max)
            // str要和“(”相加，以对带(的情况进行回溯，同时又要保持本色，以对别的情形进行回溯，所以str + "("正合适；open+1同理
            backtrack(list, str + "(", open + 1, close, max);
        if (close < open)
            backtrack(list, str + ")", open, close + 1, max);
    }


    public static void main(String[] args) {
        _22__Generate_Parentheses generateParentheses = new _22__Generate_Parentheses();
        System.out.println(generateParentheses.generateParenthesis_Mine(2));
        System.out.println(generateParentheses.generateParenthesis_Others(2));
    }
}
