package SwordOffer;

import java.util.Stack;

public class TwoStacksToQueue {
    // 进栈
    Stack<Integer> stack1 = new Stack<Integer>();
    // 出栈
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        // 出栈为空
        if(stack2.isEmpty()) {
            // 将入栈的数据放入出栈
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        // 出栈仍为空，说明队列中没数据
        if(stack2.isEmpty())
            return 0;
        // 出栈的栈顶即是队首元素
        int item = stack2.pop();
        return item;
    }
}
