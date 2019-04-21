package Ordered;

public class _75__Sort_Colors {
    public void sortColors(int[] nums) {
        int second = nums.length, zero = 0;
        for (int i = 0; i < second; ) {
            if(nums[i] == 2) {
                swap(nums[i], nums[second --]);
                i ++;
            }
            if(nums[i] == 0) {
                swap(nums[i], nums[zero ++]);
            }
        }

    }

    public void swap(int n, int m) {
        int t = n;
        n = m;
        m = t;
    }
}
