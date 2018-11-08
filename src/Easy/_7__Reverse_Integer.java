package Easy;

public class _7__Reverse_Integer {

    public int reverse(int x) {
        int res=0;
        while(x != 0) {
            int nr = res * 10 + x % 10;
            // 越界判断
            /*恰好给的x是一个int类型，所以判断是否越界要简单*/
            if((nr - x % 10) / 10 != res) {
                return 0;
            }
            res = nr;
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        _7__Reverse_Integer reverseInteger = new _7__Reverse_Integer();
        // System.out.println(reverseInteger.reverse());
    }
}
