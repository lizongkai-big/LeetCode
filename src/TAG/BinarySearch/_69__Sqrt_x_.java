package TAG.BinarySearch;


public class _69__Sqrt_x_ {
    /**
     * 求x的平方根
     * 查找第一个大于等于目标值的数
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int lo = 0, hi = (x + 1)/2;
        while (lo < hi) {
            int mid = lo + ((hi-lo) >> 1);
            int t = mid * mid;
            if(t < x) lo = mid + 1;
            else{
                if(t == x) return mid;
                hi = mid - 1;
            }
        }
        if(hi * hi > x)
            hi--;
        return hi;
    }
}
