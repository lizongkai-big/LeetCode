package TAG.Math;

/**
 * 一个数的二进制中1的个数
 */
public class One_Num_Binary {
    /**
     * 移位 + 计数
     * 与n的二进制位数有关，最多循环32次
     * @param n
     * @return
     */
    public int BitCount_Normal(int n) {
        int c = 0; // 计数器
        while (n > 0) {
            if ((n & 1) == 1) // 当前位是1
                ++c; // 计数器加1
            n >>= 1; // 移位
        }
        return c;
    }

    public int BitCount_Normal_Ahead(int n) {
        int c = 0;
        for (; n > 0; n >>= 1) {
            c += n & 1;
        }
        return c;
    }

    /**
     * 通过 n &= (n-1) 清除n的二进制数中的最低位的1，正负数都适用
     * @param n
     * @return
     */
    public int BitCount_Quick_Negative(int n) {
        int c = 0;
        // n中1的个数
        for (; n != 0; c ++) {
            // 清除 n 的最高位的 1
            n &= (n - 1);
        }
        return c;
    }

    public int BitCount_Dynamic_Table(int n) {
        char[] bitsSetTable256 = new char[256];
        for (int i = 0; i < bitsSetTable256.length; i++) {
            bitsSetTable256[i] = 0;
        }
        for (int i = 0; i < bitsSetTable256.length; i++) {
            // ((i & 1) == 0 ? '0' : '1')
            // function BitCount(i) 指的是 i 的二进制中 1 的个数
            // 如果i是偶数，i二进制最后1位是0，BitCount(i)==BitCount(i>>1);
            // 如果i是奇数，i二进制最后1位是1，BitCount(i)==BitCount(i>>1) + 1;
            // 综上得出以下计算公式
            bitsSetTable256[i] = (char)(bitsSetTable256[i / 2] + (i & 1));
        }
        int c = 0;
        // 把n按8位分成4份，分别求其中1的个数
        c = bitsSetTable256[(n & 0xFF000000) >> 24] +
                bitsSetTable256[(n & 0x00FF0000) >> 16] +
                bitsSetTable256[(n & 0x0000FF00) >> 8] +
                bitsSetTable256[(n & 0x000000FF)];
        return c;
    }

    public int BitCount_Static_Table(int n) {
        int[] table = {
            0, 1, 1, 2,
            1, 2, 2, 3,
            1, 2, 2, 3,
            2, 3, 3, 4
        };
        int c = 0;
        while(n > 0) {
            c += table[n & 0xF];
            n >>= 4;
        }
        return c;
    }

    public int BitCount_Static_Table_2(int n) {
        // 数字0, 1, 2, 3对应的二进制数的个数
        int[] table = {
            0, 1, 1, 2
        };
        int c = 0;
        while(n > 0) {
            c += table[n & 0x3];
            n >>= 2;
        }
        return c;
    }

    public int BitCount_negative(int n) {
        if(n == 0 ) return 0;
        int i = 1;
        int count = 0;
        while (i != 0) {
            if((n & i) == i) {
                count ++;
            }
            i >>= 1;
        }
        return count;
    }


    public static void main(String[] args) {
        int i = 100;

        Long c = Long.valueOf(-2147483648);
        System.out.println(false ^ true);
        One_Num_Binary one_numDecimal = new One_Num_Binary();
        int num = -217;
        System.out.println(Integer.toBinaryString(num));
        System.out.println(one_numDecimal.BitCount_Quick_Negative(num));
        if(num < 0) return;
        System.out.println(one_numDecimal.BitCount_Normal(num));
        System.out.println(one_numDecimal.BitCount_Normal_Ahead(num));
        System.out.println(one_numDecimal.BitCount_Dynamic_Table(num));
        System.out.println(one_numDecimal.BitCount_Static_Table(num));
        System.out.println(one_numDecimal.BitCount_Static_Table_2(num));

    }
}
