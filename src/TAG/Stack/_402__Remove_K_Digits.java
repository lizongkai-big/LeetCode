package TAG.Stack;

import java.util.Stack;

// 从一个数num中删除k位，使num最小
// 规律：寻找第一个左边的数大于右边的数，删掉
// Greedy
public class _402__Remove_K_Digits {
    public String removeKdigits(String num, int k) {
        if(num == null || num.length() == 0)
            return "";
        if(k >= num.length())
            return "0";
        char[] chars = num.toCharArray();
        int del = 0;
        Stack<Character> stack = new Stack<>();
        stack.push(chars[0]);
        int i = 1;
        for (; i < chars.length && del < k; i++) {
            // 不断更新栈
            //whenever meet a digit which is less than the previous digit,
            // discard the previous one
            while (del < k && !stack.isEmpty() && stack.peek() > chars[i]) {
                stack.pop();
                del ++;
            }
            stack.push(chars[i]);
        }
        while (i < chars.length)
            stack.push(chars[i++]);
        // corner case like "1111"
        while (del < k && !stack.isEmpty()) {
            stack.pop();
            del ++;
        }
        //construct the number from the stack
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }
        stringBuilder.reverse();
        //remove all the 0 at the head
        while (stringBuilder.length() > 1 && stringBuilder.charAt(0) == '0')
            stringBuilder.deleteCharAt(0);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        _402__Remove_K_Digits removeKDigits = new _402__Remove_K_Digits();
        System.out.println(removeKDigits.removeKdigits("1432219", 3));
    }
}
