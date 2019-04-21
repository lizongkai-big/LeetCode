package TAG.Greedy;

import java.util.Arrays;

/**
 * 田忌赛马
 * 利用贪心思想，首先对自己的马的性能进行排序（方便二分查找），对于对方的任何一匹马A，找出自己的马群中第一个性能大于A的马，
 * 如果没有大于A的马，则用最小的那匹马
 * 因为有数组移动的操作，时间复杂度为 O(n^2)
 *
 * https://leetcode.com/problems/advantage-shuffle/discuss/149822/JAVA-Greedy-6-lines-with-Explanation
 * 换个思路，使用大顶堆存储B的数字和对应下标，尽量满足大顶堆的堆顶，能满足就使用A中的最大值，不能满足就使用A中的最小值
 * 因为存储了下标，时间复杂度为O(nlogn)
 *
 */
public class _870__Advantage_Shuffle {
    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        int[] res = new int[A.length];
        int len = A.length;
        for (int i = 0; i < B.length; i++) {
            int inx = binarySearch(A, B[i], len);
            if(inx == -1) {
                res[i] = A[0];
                coverInx(A, 0, len);
            }
            else {
                res[i] = A[inx];
                coverInx(A, inx, len);
            }
            len --;
        }
        return res;
    }
    
    public void coverInx(int[] array, int inx, int len) {
        for (int i = inx; i < len-1; i++) {
            array[i] = array[i+1];
        }
    }

    /**
     * 使用二分查找的思想，找到array中第一个大于target的值
     * @param array
     * @param target
     * @return
     */
    public int binarySearch(int[] array, int target, int len) {
        int lo = 0, hi = len-1;
        while (lo <= hi) {
            int mid = lo + ((hi-lo) >> 1);
            if(array[mid] <= target) {
                lo = mid + 1;
            }
            else {
                if(mid == 0 || array[mid-1] <= target) {
                    return mid;
                }
                hi = mid-1;
            }
        }
        return -1;
    }
}
