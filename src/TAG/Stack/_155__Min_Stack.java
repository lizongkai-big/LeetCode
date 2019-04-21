package TAG.Stack;

import java.util.Stack;

/**
 * 以O(1)的时间获取栈中的最小值，
 * 最朴素的想法是getMin()方法调用时，遍历栈来获得最小值，getMin()时间复杂度为O(n)；
 * 为了让getMin()时间复杂度是O(1)，可以用两个栈，一个存储数据，另一个存储当前数据集下的最小值，两个栈大小相同，
 * 缺点是空间复杂度为O(n)；
 * 还可以用一个栈，如果新值是最小值，栈会记录一个之前的最小值，再记录新值，时间和空间复杂度分别是O(1)和O(n)；
 */
public class _155__Min_Stack {

}



/**
 * 该类使用一个栈来实现
 */
class MinStack {
    Stack<Integer> stack;
    int min = Integer.MAX_VALUE;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int x) {
        // why =？因为对于栈顶是最小值的情况，pop()会弹出两次，如果新值等于最小值，如果只放如入栈一次，则pop()会出错
        if(x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {

        if(min == stack.pop())
            min = stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

class MinStack_TwoStack {
    Stack<Integer> stack;
    Stack<Integer> min;
    /** initialize your data structure here. */
    public MinStack_TwoStack() {
        stack = new Stack<>();
        min = new Stack<>();
    }

    public void push(int x) {
        if(min.isEmpty() || x <= min.peek()) {
            min.push(x);
        }
        else {
            min.push(min.peek());
        }
        stack.push(x);
    }

    public void pop() {
        min.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }
}

class MinStack_Worst {
    private int[] items;
    private int n = 20;
    private int count = 0;
    /** initialize your data structure here. */
    public MinStack_Worst() {
        items = new int[n];
    }

    public void push(int x) {
        if(count == n) {
            int[] newItems = new int[n * 2];
            for(int i = 0; i < n; i ++) {
                newItems[i] = items[i];
            }
            items = newItems;
            n *= 2;
        }
        items[count ++] = x;
    }

    public void pop() {
        if(count == 0) return;
        count --;
    }

    public int top() {
        if(count == 0) return 0;
        return items[count - 1];
    }

    public int getMin() {
        int min = items[count - 1];
        for(int i = 0; i < count; i ++) {
            if(items[i] < min) min = items[i];
        }
        return min;
    }
}