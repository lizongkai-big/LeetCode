package TAG.BinarySearch;

/**
 * 没想到，阶乘运算中用到了二分查找的思想（n 有一个不断折半的过程）
 * 注意 n 为负数情形
 */
public class _50__Pow_x__n_ {
    // 阶数的二进制角度考虑
    public double myPow1(double x, int n) {
        if (n == 0) return 1;
        double pow = 1.0;

        // To handle the case where N=INTEGER_MIN we use a long (64-bit) variable.
        long times = Math.abs(n);
        // 从times的二进制表示的角度考虑，9^4 = 9^(0100)；9^5 = 9^(0101) = 9^(0100) * 9^(0001)
        // 权值初始为x，并不断自乘，如同二进制
        // i != 0(equal to i>0), i /= 2 (equal to i >>= 1),
        for (long i = times; i != 0; i /= 2) {
            // 如果i%2!= 0(equal to i%2==1, equal to i&1==1)，此时pow要乘以(1*权值(x))
            if (i % 2 != 0) pow *= x;
            // 权值增加
            x *= x;
        }
        return n > 0 ? pow : 1 / pow;
    }

    // 二分角度
    public double myPow2(double x, int n) {
        if (n == 0) return 1;
        return n > 0 ? power(x, n) : 1 / power(x, -n);
    }

    public double power(double x, int n) {
        if (n == 0) return 1;
        double half = power(x, n / 2);
        return n % 2 == 0 ? half * half : x * half * half;
    }

    public static void main(String[] args) {
        _50__Pow_x__n_ pow_x__n_ = new _50__Pow_x__n_();
        System.out.println(pow_x__n_.myPow1(2, -4));
        System.out.println(pow_x__n_.myPow2(2, -4));
    }
}
