package Ordered;

import java.util.Arrays;
import java.util.stream.Collectors;

public class _34__Find_First_and_Last_Position_of_Element_in_Sorted_Array {
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length-1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid-1;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }



    public static void main(String[] args) {
        _34__Find_First_and_Last_Position_of_Element_in_Sorted_Array
                find_position =
                new _34__Find_First_and_Last_Position_of_Element_in_Sorted_Array();
        int[] res = find_position.searchRange(new int[]{5,7,7,8,8,10}, 8);
        System.out.println(Arrays.stream(res).boxed().collect(Collectors.toList()));
    }
}
