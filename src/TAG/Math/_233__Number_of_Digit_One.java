package TAG.Math;

public class _233__Number_of_Digit_One {
    /**
     *
     * @param n 数字的位数，假定4位
     * @return
     */
    public int getNum(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += get(n-i);
        }
        System.out.println(sum);
        return sum;
    }

    public int get(int numOfDigits) {
        int num = 0;
        // 最高位
        num += Math.pow(10, numOfDigits-1);
        // 其余低位
        num += 9*(numOfDigits-1)*Math.pow(10, numOfDigits-2);
        return num;
    }

    public int numberOf1Between1AndN(int n) {
        if(n <= 0)
            return 0;
        char[] strN = (n+"").toCharArray();
        return numberOf1(strN);
    }

    public int numberOf1(char[] strN) {
        System.out.println("strN:" + new String(strN));
        if(strN == null || strN.length == 0 || strN[0] < '0' || strN[0] > '9')
            return 0;
        int first = strN[0]-'0';
        int len = strN.length;

        if(len == 1 && first == 0)
            return 0;
        if(len == 1 && first > 0)
            return 1;

        // numFirstDigit + numOtherDigits 是含有最高位的数字中1的个数，分为最高位为1和最高位为任意数两种情形
        // 假设strN = "21345"
        // 最高位为1
        int numFirstDigit = 0;
        if(first > 1)
            numFirstDigit = (int)Math.pow(10, len-1);
        else if(first == 1)
            numFirstDigit = Integer.parseInt(new String(strN, 1, strN.length-1)) + 1;
        System.out.println(new String(strN) + " numFirstDigit: " + numFirstDigit);
        // 其余位为1。len-1种放置可能，其余的（len-2）位置分别有10种可能，而且跟第一位的大小也有关系
        int numOtherDigits = first * (len-1) * (int)Math.pow(10, len-2);
        System.out.println(new String(strN) + " numOtherDigits: " + numOtherDigits);

        // 递归求解去掉最高位的数字中1的个数
        int numRecursive = numberOf1(new String(strN, 1, strN.length-1).toCharArray());
        System.out.println(new String(strN) + " numRecursive: " + numRecursive);

        return numFirstDigit + numOtherDigits + numRecursive;
    }

    public static void main(String[] args) {
        _233__Number_of_Digit_One one_num_decimal = new _233__Number_of_Digit_One();
        one_num_decimal.getNum(3);
        System.out.println(one_num_decimal.numberOf1Between1AndN(9000));
    }
}
