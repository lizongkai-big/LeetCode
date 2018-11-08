package Easy;

public class _8__String_to_Integer {
    public int myAtoi(String str) {
        int i = 0;
        int len = str.length();
        char[] c = str.toCharArray();
        for(;i < len && str.charAt(i) == ' '; i ++);
        if(i == len || !('0' <= c[i] && c[i] <= '9' || c[i] == '-' || c[i] == '+')) return 0;
        boolean negative = false;
        int num = 0;
        if(c[i] == '-')  negative = true;
        else if (c[i] == '+'){negative = false;}
        else {
            num = c[i] - '0';
        }
        i ++;
        while(i < len) {
            if(!('0' <= c[i] && c[i] <= '9')) {
                break;
            }
            int t = c[i] - '0';
            int newN = num * 10 + t;
            /*两种情形判断是否越界*/
            if(newN < 0 || (newN - t) / 10 != num) {
                if(negative)
                    return -2147483648;
                else
                    return 2147483647;
            }
            num = newN;
            i ++;
        }
        return num * (negative ? -1 : 1);
    }

    public static void main(String[] args) {
        _8__String_to_Integer atoi = new _8__String_to_Integer();
        System.out.println(atoi.myAtoi("2147483649"));
    }
}
