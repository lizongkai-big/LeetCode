package TAG.TEMP;

public class RSA {
    /**
     * 模拟加密和解密的过程，n自乘pub次加密，再自乘pri次完成解密
     *
     * @param n   被加密的数字
     * @param pub 加密过程，n自乘的次数（公钥）
     * @param pri 解密过程，n自乘的次数（私钥），由两个素数和公钥通过扩展欧几里得算法得出
     */
    public void simulation(int n, int pub, int pri) {
        // 素数1,2
        int p1 = 13, p2 = 7;
        int max = p1 * p2;
        if (n > max) {
            System.out.println("n is too big!");
            return;
        }
        // 加密过程
        int res = n;
        for (int i = 1; i < pub; i++) {
            res = (res * n) % max;
            System.out.println(res);
        }
        // 解密过程，n为加密后的数字
        n = res;
        System.out.println("----------");
        for (int i = 1; i < pri; i++) {
            res = (res * n) % max;
            System.out.println(res);
        }
    }

    /**
     * 欧几里得算法又称辗转相除法，用于计算两个整数a,b的最大公约数。
     *
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    public int exGcd(int a, int b, int x, int y) {
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }
        int r = exGcd(b, a % b, x, y);
        int t = x;
        x = y;
        y = t - a / b * y;
        return r;
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();

        rsa.simulation(67, 5, 29);
    }
}
