package TAG.Stack;

import java.util.Stack;

public class _32__Longest_Valid_Parentheses {
    public int longestValidParentheses(String s) {
        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < len; i ++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            }
            else if(s.charAt(i) == ')') {
                if(!stack.isEmpty()) {
                    if(s.charAt(stack.peek()) == '(') {
                        stack.pop();
                    }
                    else {
                        stack.push(i);
                    }
                }
                else{
                    stack.push(i);
                }
            }
        }
        int a = len, b = 0;
        int longest = 0;
        if(stack.isEmpty()) longest = len;
        else{
            while(!stack.isEmpty()) {
                b = stack.pop();
                longest = Math.max(longest, a - b - 1);
                a = b;
            }
            longest = Math.max(longest, a);
        }


        return longest;
    }
}
