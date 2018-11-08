package Ordered;

import java.util.Arrays;
import java.util.stream.Collectors;

public class _31__Next_Permutation {
    /**
     * 排列的规则：从后往前，
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int i = len - 2;
        for(; i >= 0; i --) {
            if(nums[i + 1] > nums[i]) {
                int j = len - 1;
                for(; j > i; j --) {
                    if(nums[j] > nums[i]) {
                        break;
                    }
                }
                int t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
                reverse(nums, i + 1, len - 1);
                return;
            }
        }
        reverse(nums, 0, len - 1);
    }

    public void reverse(int[] nums, int i, int j) {
        while(i < j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i ++;
            j --;
        }
    }

    public static void main(String[] args) {
        _31__Next_Permutation next_permutation = new _31__Next_Permutation();
        next_permutation.nextPermutation(new int[]{1,1,3});
    }
}
