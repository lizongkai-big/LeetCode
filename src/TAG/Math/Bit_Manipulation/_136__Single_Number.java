package TAG.Math.Bit_Manipulation;

public class _136__Single_Number {
    public int singleNumber(int[] nums) {
        int x = 0;
        for(int i: nums) {
            x ^= i;
        }
        return x;
    }

    public static void main(String[] args) {
        _136__Single_Number single_number = new _136__Single_Number();
        System.out.println(single_number.singleNumber(new int[]{1,1,2}));
    }
}
