package Ordered;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _43__Multiply_Strings {
    public String multiply_optimization(String num1, String num2) {
        char[] n1 = num1.toCharArray();
        char[] n2 = num2.toCharArray();
        if(n1.length == 1 && n1[0] == '0' || n2.length == 1 && n2[0] == '0') return "0";
        int[] result = new int[n1.length + n2.length];
        for (int i = n1.length - 1; i >= 0; i--) {
            for (int j = n2.length - 1; j >= 0; j--) {
                int p1 = i + j; int p2 = i + j + 1;
                int n = (n1[i] - '0') * (n2[j] - '0') + result[p2];
                result[p2] = n % 10;
                result[p1] += n / 10;
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int p : result) if(!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public String multiply(String num1, String num2) {
        char[] n1 = num1.toCharArray();
        char[] n2 = num2.toCharArray();
        if(n1.length == 1 && n1[0] == '0' || n2.length == 1 && n2[0] == '0') return "0";
        if(n1.length < n2.length) {
            char[] t = n1;
            n1 = n2;
            n2 = t;
        }
        // char[] middle = new char[n1.length + 1];
        char[] middle = new String("0").toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int j = n2.length - 1; j >= 0; j--) {
            middle = sumChar(multiple_mine(n1, n2[j]), middle, n2.length - 1 -j);
        }
        return new String(middle);
    }

    public char[] multiple_mine(char[] a, char b) {
        int product = 0;
        StringBuffer result = new StringBuffer();
        for (int i = a.length - 1; i >= 0; i--) {
            product += (a[i] - '0') * (b - '0');
            result.append((char)(product % 10 + '0'));
            product /= 10;
        }
        if(product != 0)
            result.append(product);
        // System.out.println(result);
        // 有没有更好的反转字符串的方式？
        return result.reverse().toString().toCharArray();
    }

    public char[] sumChar(char[] a, char[] b, int dislocation) {
        if(a.length == 0 || a.length == 1 && a[0] == '0') return b;
        if (b.length == 0 || b.length == 1 && b[0] == '0') return a;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < dislocation; i++) {
            sb.append(b[b.length - 1 - i]);
        }
        int inx = a.length - 1;
        int sum = 0;
        for (int i = b.length - 1 - dislocation; i >= 0 || inx >= 0; i--, inx --) {
            if(i >= 0)
                sum += (b[i] - '0');
            if(inx >= 0)
                sum += (a[inx] - '0');
            sb.append(sum % 10);
            sum /= 10;
        }
        if(sum != 0)
            sb.append(sum);
        return sb.reverse().toString().toCharArray();
    }




    public static void main(String[] args) {
        _43__Multiply_Strings multiply_strings = new _43__Multiply_Strings();

        /*System.out.println(multiply_strings.sumChar((new String("123")).toCharArray(),
                (new String("99232")).toCharArray(),
                1));
        System.out.println(multiply_strings.multiple(
                (new String("923")).toCharArray(),
                '9'
        ));
        System.out.println(multiply_strings.multiply(
                (new String("123")),
                (new String("456"))
        ));*/
        System.out.println(multiply_strings.multiply_optimization("2", "3"));
    }
}
