package TAG.Greedy;

class _55_Jump_Game {
    public boolean canJump(int[] nums) {
        // 极限情况判断
        if(nums.length <= 1) return true;
        int reach = 0;
        for(int i = 0; i < nums.length && i <= reach; i++) {
            reach = Math.max(reach, i+nums[i]);
        }
        // reach的位置至少在最后一个下标之后
        return reach >= (nums.length-1);
    }
}