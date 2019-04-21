package TAG.BinarySearch;

public class pracitice {
/**
 * 最简单的二分搜索
 * 如果查找失败，二分搜索的lo值是插入位置
 * @param nums
 * @param target
 * @return index of the search key, if it is contained in the array;
 *         otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.
 */
    public int binarySearch(int[] nums, int target) {
        int lo = 0, hi = nums.length-1;
        /*循环条件*/
        while(lo <= hi) {
            /*中间值计算*/
            int mid = lo + ((hi - lo) >> 1);
            if(nums[mid] == target) {
                return mid;
            }
            else if(nums[mid] < target) {
                /*下界的更新*/
                lo = mid + 1;
            }
            else {
                /*上界的更新*/
                hi = mid - 1;
            }
        }
        return -(lo+1);
    }

    /**
     * 先计算平方根的整数部分，再计算小数部分；都是利用二分搜索
     * 二分搜索要自己确定lo, hi
     * @param num
     * @param times 小数点后的位数
     * @return
     */
    public double sqrt_binarySearch_1(int num, int times) {
        int lo = 0, hi = (num + 1) / 2;
        double res = 0.0;
        while(lo <= hi) {
            int mid = lo + ((hi - lo) >>> 1);
            int result = mid * mid;
            if(result == num) {
                return mid;
            }
            else if(result < num) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        res = hi;
        if(hi * hi > num) {
            res -= 1;
        }
        double x = 1.0;
        for (int i = 0; i < times; i++) {
            double l = res, h = res + x;
            x *= 0.1;
            while(l <= h) {
                double m = l + ((h - l) * 0.5);
                double r = m * m;
                if(r == num) {
                    return m;
                }
                else if(r < num) {
                    l = m + x;
                }
                else {
                    h = m - x;
                }
            }
            res = h;
            if(res * res > num) {
                res -= x;
            }
        }
        return res;
    }

    /**
     * 更为简洁的二分搜索
     * @param num
     * @param precision 精确度
     * @return
     */
    public double sqrt_binarySearch_2(double num, double precision) {
        double lo = 0, hi = (num);
        double mid = lo + ((hi - lo) * 0.5);
        if(num < 1 && num > 0) {
            lo = num;
            hi = 1.0;
        }
        while(hi - lo > precision) {
            double result = mid * mid;
            if(result == num) {
                return mid;
            }
            else if(result < num) {
                lo = mid;
            }
            else {
                hi = mid;
            }
            mid = lo + ((hi - lo) * 0.5);
        }
        return mid;
    }

    /**
     * 实际上，很多人都觉得变形的二分查找很难写，主要原因是太追求第第一种那样完美、简洁的写法。
     * 而对于我们做工程开发的人来说，代码易读懂、没 Bug，其实更重要，所以我觉得第二种写法更好。
     * @param nums
     * @param target
     * @return
     */
    public int leftmostIndex(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while(lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if(nums[mid] < target) {
                lo = mid + 1;
            }
            else if(nums[mid] > target) {
                hi = mid - 1;
            }
            else {
                if(mid == 0 || nums[mid - 1] != target) return mid;
                else
                    hi = mid - 1;
            }
        }
        return -1;
    }

    public int rightmostIndex(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while(lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if(nums[mid] < target) {
                lo = mid + 1;
            }
            else if(nums[mid] > target) {
                hi = mid - 1;
            }
            else {
                if(mid == nums.length - 1 || nums[mid + 1] != target) return mid;
                else
                    lo = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 第一个大于等于给定值的元素
     * @param nums
     * @param target
     * @return
     */
    public int index(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while(lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if(nums[mid] >= target) {
                if(mid == 0 || nums[mid - 1] < target) return mid;
                else {
                    hi = mid - 1;
                }
            }
            else {
                lo = mid + 1;
            }

        }
        return -1;
    }


    /**
     * 最后一个小于等于给定值的元素
     * @param nums
     * @param target
     * @return
     */
    public int Index(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while(lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if(nums[mid] <= target) {
                if(mid == nums.length - 1 || nums[mid + 1] > target) return mid;
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        pracitice t = new pracitice();
        // for (int i = 0; i < 100; i++) {
        //     double num1 = t.sqrt_binarySearch_1(i, 6);
        //     double num2 = t.sqrt_binarySearch_2(i, 0.000001);
        //     System.out.println(i + " : " + num1 + ", " + num2);
        //
        // }
        System.out.println(t.binarySearch(new int[]{2,4,5,6,8,89}, 8));

    }
}
