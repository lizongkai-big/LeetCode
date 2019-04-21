package Ordered;

public class _88__Merge_Sorted_Array {
    // 使用插入排序的思想，往已知有序的nums1中插入nums2中的数字；
    // 涉及到nums1中数字的后移，时间复杂度是O(mn)
    public void merge_Mine(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        for(; j < n && i < m;) {
            for(; i < m; i++) {
                if(nums1[i] > nums2[j]) {
                    for(int k = m-1; k >= i; k--)
                        nums1[k+1] = nums1[k];
                    nums1[i] = nums2[j];
                    m ++;
                    j++;
                    break;
                }
            }
        }
        while(j < n) {
            nums1[i ++] = nums2[j++];
        }
    }

    // 按顺序从后往前加入混合之后的数组末尾，时间复杂度是O(m+n)
    public void merge_optimization(int[] nums1, int m, int[] nums2, int n) {
        int length = m+n-1;
        m --;
        n --;
        while (m >= 0 && n >= 0) {
            if(nums1[m] > nums2[n]) {
                nums1[length --] = nums1[m--];
            }
            else {
                nums1[length --] = nums2[n--];
            }
        }
        while (n >= 0)
            nums1[length--] = nums2[n--];
    }
}
