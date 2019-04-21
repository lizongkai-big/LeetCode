package TAG.DP;

import java.util.Arrays;

public class _300__Longest_Increasing_Subsequence {
    /**
     * O(nlogn) time complexity
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = Arrays.binarySearch(tails, 0, size, x);
            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }
}
