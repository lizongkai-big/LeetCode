package TAG.StringMatching;

import java.util.ArrayList;
import java.util.Arrays;

public class KMP {
    /*public static int kmp(char[] a, int n, char[] b, int m) {

        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && a[i] != b[j]) {
                j = next[j-1] + 1;
            }
            if(a[i] == b[j]) j ++;
            if(j == m)
                return i - m + 1;
        }
        return -1;
    }*/

    public int kk() {
        this.kk();
        main(new String[]{"1"});
        return 0;
    }

    public String addBinary(String a, String b) {
        int len1 = a.length() - 1;
        int len2 = b.length() - 1;
        char[] res = new char[Math.max(len1, len2) + 2];
        StringBuffer stringBuffer = new StringBuffer();
        int len = res.length-1;
        int t = 0;
        while(len1 >= 0 || len2 >= 0) {
            if(len1 >= 0) {
                t += a.charAt(len1);
                len1--;
            }
            if(len2 >= 0) {
                t += b.charAt(len2);
                len2 --;
            }
            res[len--] = (char)(t % 2 + '0');
            stringBuffer.append((char)(t % 2 + '0'));
            t /= 2;
        }
        if(t == 1) res[len--] = '1';
        return stringBuffer.reverse().toString();
    }
    public static void main(String[] args) {
        Integer i01 = 59;
        int i02 = 59;
        float f01 = 0.0f;
        Integer i03 =Integer.parseInt("59");
        Integer i04 = new Integer(59);

        System.out.println(i01 == i02);
        System.out.println(i01 == i03);
        System.out.println(i01 == i04);
        System.out.println(i02 == i03);
        System.out.println(i02 == i04);
        System.out.println(i03 == i04);
    }


}
