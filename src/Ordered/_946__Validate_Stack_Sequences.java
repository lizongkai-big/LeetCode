package Ordered;

import java.util.Stack;

/**
 * 入栈、出栈顺序，有时候要入栈，有时候不需要入栈（入栈之后又出栈），而且也可能会连续出栈
 * 入栈的时候可以直接出栈，出栈的时候可以连续出栈
 */
public class _946__Validate_Stack_Sequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int len = pushed.length;
        int j = 0;
        // 遍历pushed
        for (int k = 0; k < len; k++) {
            // 不进stack
            if(pushed[k] == popped[j]) {
                j ++;
                // 出stack
                while(!stack.isEmpty() && stack.peek() == popped[j]) {
                    stack.pop();
                    j ++;
                }
            }
            // 进stack
            else {
                stack.push(pushed[k]);

            }
        }

        return j == len;
    }
}
