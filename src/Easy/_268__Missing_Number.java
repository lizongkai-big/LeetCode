package Easy;

public class _268__Missing_Number {
    /**
     * 先求SUM[0, n]，再减去已知元素，得到missing number
     * @param nums
     * @return
     */
    public int missingNumber_Sum(int[] nums) {
        int len = nums.length;
        // sum1 = SUM(0, n) 如果没有缺少数字，nums的总和
        double sum1 = len/2.0 * (len+1);
        // 依次减去每个nums[i]，剩下的就是nums中缺少的那个数字了呗
        while(len-- > 0) {
            sum1 -= Double.valueOf(nums[len]);
        }
        return (int)(sum1);
    }

    /**
     * 利用a == a ^ b ^ b的数学原理，让xor和[0,n]异或，再和nums异或，得到missing number
     * @param nums
     * @return
     */
    public int missingNumber_XOR(int[] nums) {
        int len = nums.length;
        int xor = 0;
        for(int i = 0; i < len; i++) {
            //
            xor = xor ^ i ^ nums[i];
        }
        return xor ^ len;
    }
}