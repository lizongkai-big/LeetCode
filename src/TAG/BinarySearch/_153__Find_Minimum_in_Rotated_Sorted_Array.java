package TAG.BinarySearch;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * You may assume no duplicate exists in the array.
 */
public class _153__Find_Minimum_in_Rotated_Sorted_Array {
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length;
        int mid = lo;
        while (nums[lo] > nums[hi]) {
            if(hi-lo <= 1) {
                mid = hi;
                break;
            }
            mid = lo + ((hi-lo) >> 1);
            if(nums[lo] < nums[mid])
                lo = mid;
            else
                hi = mid;
        }
        return nums[mid];
    }
}
