class _41__First_Missing_Positive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
		// first, put nums[i] into right place.
        for(int i = 0; i < nums.length; i++) {
			// nums[i]'s right place is in (0, n]
            while(nums[i] > 0 && nums[i] <= n && nums[nums[i]-1] != nums[i]) {
                // use temparory inx, because nums[i] will change, so nums[i]-1 also change.
				int inx = nums[i]-1;
                int t = nums[i];
                nums[i] = nums[inx];
                nums[inx] = t;
            }
        }
		// find the one not in right place.
        for(int i = 0; i < n; i++) {
            if(nums[i] != i+1) {
                return i+1;
            }
        }
        return n+1;
    }
}
