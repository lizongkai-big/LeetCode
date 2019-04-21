package TAG.BinarySearch;

public class _33__Search_in_Rotated_Sorted_Array {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        int res = -1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                res = mid;
                break;
            }
            // 在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了
            // mid...right 是有序的，所以判断target在不在mid...right之间
            else if(nums[mid] < nums[right]) {
                // target 在mid...right
                /*判断范围的标准写法*/
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
            // left...mid 是有序的
            else {
                // target 在left...mid
                if(nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }

            }
        }
        return res;
    }

    public static void main(String[] args) {
        _33__Search_in_Rotated_Sorted_Array search_in_rotated_sorted_array = new _33__Search_in_Rotated_Sorted_Array();
        System.out.println(search_in_rotated_sorted_array.search(new int[]{4,5,6,7,0,1,2}, 0));
    }
}
