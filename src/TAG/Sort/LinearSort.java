package TAG.Sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LinearSort {
    /**
     * 计数排序
     * @param nums
     */
    public void count_sort(int[] nums) {
        int max = nums[0], min = nums[0];
        for(int x : nums) {
            if(x > max) max = x;
            if(x < min) min = x;
        }
        int len = max - min + 1;
        int[] c = new int[len];
        for (int x : nums) {
            c[x - min] ++;
        }
        for (int i = 1; i < len; i++) {
            c[i] += c[i - 1];
        }
        int[] R = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int inx = c[nums[i] - min] - 1;
            R[inx] = nums[i];
            c[nums[i] - min] --;
        }
        nums = Arrays.copyOf(R, R.length);
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        LinearSort sort = new LinearSort();
        sort.count_sort(new int[]{3, -1, -2, -3, -4, 10, 2, 5, 4, 1, 2, 6});

    }
}
