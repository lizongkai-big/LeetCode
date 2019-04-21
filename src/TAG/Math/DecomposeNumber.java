package TAG.Math;

/**
 * 数字分解
 */
public class DecomposeNumber {
    /**
     * 考虑顺序的情况下，把一个整数n分解成正数相加有2^(n-1)种方法；
     * 那么，1 + 2 + 4 + 8 + ... + 2^(n-1) ==> 2^n 等比数列求和
     * 1 1
     * 2 2
     *   1 1
     * 3 3
     *   2 1
     *   1 2
     *   1 1 1
     * @param n
     * @return
     */
    public int totalKinds(int n) {
        return (int)Math.pow(2, n-1);
    }

    /**
     * 整数划分问题
     * https://blog.csdn.net/u011889952/article/details/44813593
     */



}
