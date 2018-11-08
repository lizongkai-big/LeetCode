package Ordered;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15__3Sum {
    /**
     * 先排序
     * 之后，3个数字分为两部分，先确定第一个元素nums[i]，
     * 然后使用前后指针，在已排序的数组中寻找和为0-nums[i]的两个元素
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        for(int i = 0; i < len-2; i ++) {
            // 如果已经比较过，则跳过
            if(i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int aim = 0 - nums[i];
            // 设置前后指针
            int f = i + 1, e = len-1;
            while(f<e) {
                // 符合条件
                if(nums[f] + nums[e] == aim) {
                    List<Integer> tmp = Arrays.asList(nums[i], nums[f], nums[e]);
                    res.add(tmp);
                    // f+1, e-1不会越界
                    // 跳过相同的元素
                    while(f<e && nums[f] == nums[++f]);
                    while(f<e && nums[e] == nums[--e]);
                }
                // 左指针向右移动
                else if(nums[f] + nums[e] < aim) f++;
                // 右指针向左移动
                else e--;
            }
        }
        return res;
    }
}
