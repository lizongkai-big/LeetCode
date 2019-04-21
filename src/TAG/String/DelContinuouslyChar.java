package TAG.String;


import java.util.Stack;

// #stack
public class DelContinuouslyChar {
    // 删除str中连续的字符 如AAABA->BA，返回字符串最终的长度
    public int del_Recur(String str) {
        int len = str.length();
        int lent;
        if (len == 0) return 0;
        StringBuilder stringBuilder = new StringBuilder();
        str += "#";
        int l = 0;
        for (int i = 1; i <= len; i++) {
            if (str.charAt(i) != str.charAt(i - 1)) {
                if (l == i - 1)
                    stringBuilder.append(str.charAt(i - 1));
                l = i;
            }
        }
        if ((lent = stringBuilder.length()) == len) return 0;
        return len - lent + del_Recur(stringBuilder.toString());
    }

    public int del_Iter(String str) {
        int len = str.length();
        if (len == 0) return 0;
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < len) {
            if (stack.isEmpty()) {
                stack.push(str.charAt(i));
                i++;
                continue;
            }
            while (!stack.isEmpty() && i < len) {
                char c = str.charAt(i);
                char peek = stack.peek();
                if (peek == c) {
                    stack.pop();
                    i++;
                } else {
                    stack.push(c);
                    i++;
                    break;
                }
            }
        }
        return stack.size();
    }

    public int del_Iter_optimize(String str) {
        int len = str.length();
        if (len == 0) return 0;
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < len) {
            // 栈不空 && 字符串下标不越界 && 满足一定条件
            while (!stack.isEmpty() && i < len && str.charAt(i) == stack.peek()) {
                stack.pop();
                i++;
            }
            stack.push(str.charAt(i));
            i++;
        }
        return stack.size();
    }

}
