package TAG.Math.Bit_Manipulation;

public class _137__Single_Number_II {
    public int singleNumber(int[] nums) {
        final int SIZE = 32;
        int res = 0;
        for (int i = SIZE-1; i >= 0; i--) {
            int bit = 0;
            for (int j = 0; j < nums.length; j++) {
                bit += (nums[j] >>> i) & 1;
            }
            res <<= 1;
            if(bit % 3 != 0) {
                res += 1;
            }
        }
        return res;
    }
}
