package Ordered;

/**
 *
 */
public class _29__Divide_Two_Integers {
    public int divide(int dividend, int divisor) {
        if(divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
            return Integer.MAX_VALUE;
        int negative = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        // 因为每次都是把负数转换成正数，如果有最小负数值参与计算，直接转换会越界变成-2147483648；所以需要是long型
        // 而且不能在转long 先 dividend > 0 ? dividend : -dividend; 同样会造成越界
        // int 是可以直接转成 long，但是不能直接转成Long
        long dvd = dividend;
        long dvs = divisor;
        dvd = dvd > 0 ? dvd : -dvd;
        dvs = dvs > 0 ? dvs : -dvs;
        int res = 0;
        // 不能是每次减divisor，会超时
        while(dvd >= dvs) {
            long tmp = dvs, multiple = 1;
            while(dvd >= (tmp << 1)) {
                tmp <<= 1;
                multiple <<= 1;
            }
            dvd -= tmp;
            res += multiple;
        }
        return negative == 1 ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(-1 * Integer.MIN_VALUE);
    }
}
