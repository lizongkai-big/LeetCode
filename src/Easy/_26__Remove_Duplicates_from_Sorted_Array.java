package Easy;

public class _26__Remove_Duplicates_from_Sorted_Array {
    /**
     * elegant, and a little
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if(len < 2)
            return len;
        int id = 1;
        for(int i = 1; i < len; i ++) {
            if(nums[i] != nums[i-1]) {
                nums[id++] = nums[i];
            }
        }
        return id;
    }

    public int strStr(String haystack, String needle) {
        int lenStack = haystack.length();
        int lenNeedle = needle.length();
        int res = -1;
        for (int i = 0; i < lenStack; i++) {
            int j = 0;
            for (; j < lenNeedle; j++) {
                if(!((i+j) < lenStack && haystack.charAt(i+j) == needle.charAt(j))) {
                    break;
                }
            }
            if(j == lenNeedle) {
                res = i;
                break;
            }
        }
        return res;
    }
}
