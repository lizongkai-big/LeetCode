package SwordOffer;

import java.util.Stack;

public class Main {
    public boolean valid(String string) {
        char[] chars = string.toCharArray();
        int len = chars.length;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            if(c == '{' ) {
                if(!stack.isEmpty())
                    return false;
                stack.push(c);
            }
            else if(c == '[') {
                // 中括号内可以包含中括号
                if(!stack.isEmpty() && !(stack.peek() != '{' || stack.peek() == '['))
                    return false;
                else {
                    stack.push(c);
                }
            }
            else if(c == '(') {
                // 小括号内可以包含小括号
                if(!stack.isEmpty() && !(stack.peek() == '{' || stack.peek() == '[' || stack.peek() == '(')  )
                    return false;
                else {
                    stack.push(c);
                }
            }
            if(c == '}') {
                if(stack.isEmpty() || stack.peek() != '}')
                    return false;
                stack.pop();
            }
            if(c == ']') {
                if(stack.isEmpty() || stack.peek() != ']')
                    return false;
                stack.pop();
            }
            if(c == ')') {
                if(stack.isEmpty() || stack.peek() != ')')
                    return false;
                stack.pop();
            }
        }
        return true;
    }
}
