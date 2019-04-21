package Ordered;

/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * It doesn't matter what you leave beyond the returned length.
 */
public class _80__Remove_Duplicates_from_Sorted_Array_II {

    public int removeDuplicates_optimization(int[] nums) {
        int i = 0;
        for(int n : nums) {
            if(i < 2 || n > nums[i-2])
                nums[i++] = n;
        }
        return i;
    }

    public int removeDuplicates_mine(int[] nums) {
        if(nums.length < 2) return nums.length;
        int needLen = 2;
        int resLen = 1;
        int count = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1]) {
                if(count < needLen) {
                    count ++;
                    nums[resLen ++] = nums[i];
                }
            }
            else {
                count = 1;
                nums[resLen ++] = nums[i];
            }
        }
        return resLen;
    }
}
