package TAG.Stack;

import java.util.Stack;

/**
 * 根据入栈序列，判断出栈序列是否正确；序列中数字不重复
 */
public class Push_Pop_Sequence {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA.length == 0 || popA.length == 0)
            return false;
        int j = 0;
        int len = popA.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && j < len && stack.peek() == popA[j]) {
                stack.pop();
                j ++;
            }
        }
        return j == len && stack.isEmpty();
    }
}
