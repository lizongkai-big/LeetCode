package TAG.BinarySearch;

public class _35__Search_Insert_Position {
    // 变形为查找第一个大于等于target的元素的位置
    public int searchInsert(int[] nums, int target) {
        int lo = 0, hi = nums.length-1;
        while(lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if(nums[mid] >= target) {
                if(mid == 0 || nums[mid - 1] < target) return mid;
                else hi = mid - 1;
            }
            else{
                lo = mid + 1;
            }
        }
        // 在nums中未找到大于等于target的位置，所以target应该放在最后
        return nums.length;
    }

    public static void main(String[] args) {
        _35__Search_Insert_Position search_insert_position
                = new _35__Search_Insert_Position();
        search_insert_position.searchInsert(new int[]{2, 3}, 4);
    }
}
