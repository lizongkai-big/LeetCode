package Ordered;

import java.util.Arrays;

public class _16__3Sum_Closest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int diff = Integer.MAX_VALUE;
        int res = 0;
        for(int i = 0; i < len - 2; i ++) {
            // int aim = target - nums[i];
            int f = i + 1, e = len - 1;
            while(f < e) {
                int tmp = nums[f] + nums[e] + nums[i];
                int newDiff = (target-tmp > 0 ? target-tmp : tmp-target);
                if(newDiff < diff) {
                    res = tmp;
                    diff = newDiff;
                }
                if(tmp < target) {
                    f ++;
                }
                else{
                    e --;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
