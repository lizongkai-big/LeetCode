package TAG.Brainteaser;

import java.util.Scanner;
import java.util.Stack;

/**
 * 给定一个字符串，字符串中1,0相遇会互相抵消，抵消之后的字符串长度
 * 如10101 --> 1 长度为1
 * 0000 --》 0000 长度为4
 * 1010 --》 "" 长度为0
 */
public class WhenOneMeetZero {
    public static int solveWithStack(int m, String str) {
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < m) {
            if(stack.isEmpty()) {
                stack.push(str.charAt(i));
                i++;
                continue;
            }
            while (!stack.isEmpty() && i < m) {
                char c = str.charAt(i);
                char top = stack.peek();
                if(top == '0' && c == '1' ||
                        top == '1' && c == '0') {
                    stack.pop();
                    i ++;
                }
                else {
                    stack.push(str.charAt(i));
                    i ++;
                    break;
                }
            }
        }
        return stack.size();
    }

    public static int solveWithTrick(int m, String str) {
        int ones = 0;
        int zeros = 0;
        for (int i = 0; i < m; i++) {
            if(str.charAt(i) == '0') zeros ++;
            else if(str.charAt(i) == '1') ones ++;
        }
        return Math.abs(ones-zeros);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int m = Integer.parseInt(sc.nextLine());
            String str = sc.nextLine();
            System.out.println(solveWithStack(m, str) == solveWithTrick(m, str));
        }
    }
}
