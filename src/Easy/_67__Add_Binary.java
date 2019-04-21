package Easy;

public class _67__Add_Binary {
    public String addBinary(String a, String b) {
        int len1 = a.length() - 1;
        int len2 = b.length() - 1;
        char[] res = new char[Math.max(len1, len2) + 2];
        int len = res.length-1;
        int t = 0;
        while(len1 >= 0 || len2 >= 0) {
            if(len1 >= 0) {
                t += a.charAt(len1)-'0';
                len1--;
            }
            if(len2 >= 0) {
                t += b.charAt(len2)-'0';
                len2 --;
            }
            res[len--] = (char)(t % 2 + '0');

            t /= 2;
        }
        if(t == 1) res[len--] = '1';

        return new String(res, len+1, res.length-len-1);
    }
}
