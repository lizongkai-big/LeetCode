package TAG.BinarySearch;

// Favourite
// BinarySearch
public class MinInRotatedArray {
    public int minNumberInRotateArray(int [] array) {
        // 让lo指向前面的数组，hi指向后面的数组，当两者之差为1的时候，hi指向的即为数组中的最小值
        int lo = 0, hi = array.length - 1;
        int mid = lo;
        // 特殊情形1：array[lo...hi] 是有序的，为此初始化mid=lo
        while (array[lo] >= array[hi]) {
            if(hi - lo <= 1) {
                mid = hi;
                break;
            }
            mid = lo + ((hi-lo) >> 1);
            // 特殊情形2：[1, 0, 1, 1, 1] [1, 1, 1, 0, 1]
            // 不能确定array[mid]属于前面的数组还是后面的数组，只好顺序查找
            if(array[lo] == array[mid] && array[mid] == array[hi]) {
                return GetMinInOrder(array, lo, hi);
            }
            // 为什么是<= ？< 很好理解，对于=的情况，要注意循环的条件array[lo]>=array[hi]
            if(array[lo] <= array[mid])
                lo = mid;
            else
                hi = mid;
            // System.out.println(mid);
        }
        return array[mid];
    }
    public static int GetMinInOrder(int[] numbers, int lo, int hi)
    {
        int result = numbers[lo];
        for (int i = lo + 1; i <= hi; ++i)
        {
            if (result > numbers[i])
            {
                result = numbers[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MinInRotatedArray rotatedArray = new MinInRotatedArray();
        int[][] array = new int[][]{{3,4,5,0,1,2}, { 1, 0, 1, 1, 1 }, { 1, 2, 3, 4, 5 },
                { 2 }, {2,2,2,0,1}, {3,1,1}};
        for(int[] a: array) {
            System.out.println("---------------------------");
            System.out.println(rotatedArray.minNumberInRotateArray(a));
        }
    }
}
