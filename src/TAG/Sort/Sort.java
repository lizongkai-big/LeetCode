package TAG.Sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Sort {
    /**
     * 冒泡排序
     * @param nums
     */
    public void bubble_sort(int[] nums) {
        int len = nums.length;
        // 本次循环有交换
        boolean changed;
        // 循环次数
        for (int i = 0; i < len; i++) {
            changed = false;
            // 每次让较大的值向后移动，放在队尾
            for (int j = 0; j < len - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    changed = true;
                    int t = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = t;
                }
            }
            if (!changed) {
                break;
            }
        }
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    /**
     * 插入排序
     */
    public void insert_sort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            // 让新的元素和左边已排序的元素逐渐交换以有序
            // 隐藏的冒泡，多了交换过程，不如直接赋值快啊；后者一行，这个三行
            /*for (int j = i + 1; j > 0; j--) {
                if(nums[j] < nums[j - 1]) {
                    int t = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = t;
                }
                else
                    break;
            }*/
            // 未实现，思想：找到待插入元素排序后的位置，移动该位置右边的元素
            // 且不说实现的对与错，干嘛不直接移动嘞？
            /*for (int j = i; j >= 0; j--) {
                if(nums[j] >= nums[i + 1]) {
                    continue;
                }
                int t = nums[i + 1];
                for (int k = i; k >= j + 1; k--) {
                    nums[k + 1] = nums[k];
                }
                nums[j+1] = t;
            }*/
            // 让左边已有序的元素中大于待插入元素的元素右移
            int t = nums[i + 1];
            int j = i;
            // 查找并移动到插入的位置
            for (; j >= 0; j--) {
                if (nums[j] > t) {
                    // 数据移动
                    nums[j + 1] = nums[j];
                } else
                    break;
            }
            // 插入数据
            nums[j + 1] = t;
        }
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    /**
     * 选择排序
     * @param nums
     */
    public void selection_sort(int[] nums) {
        int len = nums.length;
        // 每次循环都是找最小的元素
        for (int i = 0; i < len; i++) {
            // 在未排序区间找最小的元素
            int inx = i, value = nums[i];
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < value) {
                    inx = j;
                    value = nums[j];
                }
            }
            // 最小的元素与当前元素交换位置
            nums[inx] = nums[i];
            nums[i] = value;
        }
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    /**
     * 归并排序
     * @param nums
     */
    public void merge_sort_c(int[] nums) {
        divide(nums, 0, nums.length - 1);
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    public void divide(int[] nums, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = (p + r) / 2;
        // 分治递归
        divide(nums, p, q);
        divide(nums, q + 1, r);
        // 将A[p...q]和A[q+1...r]合并为A[p...r]
        merge(nums, p, q, r);
    }

    /**
     * 将A[p...q]和A[q+1...r]合并为A[p...r]
     * @param nums
     * @param p
     * @param q
     * @param r
     */
    public void merge(int[] nums, int p, int q, int r) {
        int[] temp = new int[r - p + 1];
        int inx = 0;
        int i = p, j = q + 1;
        // <= && <=
        while (i <= q && j <= r) {
            // <= 决定这是个稳定的排序
            if (nums[i] <= nums[j]) {
                temp[inx ++] = nums[i ++];
            } else {
                temp[inx ++] = nums[j ++];
            }
        }

        // 使用start，end来避免两个循环
        int start = i, end = q;
        if (j <= r) {
            start = j;
            end = r;
        }
        while (start <= end) {
            temp[inx++] = nums[start++];
        }
        // 把排序之后的数字赋值到原数组中
        for (int k = 0; k < r - p + 1; k++) {
            nums[p + k] = temp[k];
        }
    }

    public void merge_new(int[] nums, int p, int q, int r) {
        int[] temp = new int[r - p + 1];
        int inx = 0;
        int i = p, j = q + 1;
        // <= || <=
        while (i <= q || j <= r) {
            // 使用(j > r || (i <= q && nums[i] <= nums[j])) 这种判断条件来减少使用循环
            // <= 决定这是个稳定的排序
            if (j > r || (i <= q && nums[i] <= nums[j])) {
                temp[inx ++] = nums[i ++];
            } else {
                temp[inx ++] = nums[j ++];
            }
        }

        // 把排序之后的数字赋值到原数组中
        for (int k = 0; k < r - p + 1; k++) {
            nums[p + k] = temp[k];
        }
    }

    /**
     * 快排
     * @param nums
     */
    public void quick_sort_c(int[] nums) {
        quick_sort(nums, 0, nums.length - 1);
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    public void quick_sort(int[] nums, int p, int r) {
        if(p >= r) return;
        // 获取分区点
        int q = partition(nums, p, r);
        quick_sort(nums, p, q - 1);
        quick_sort(nums, q + 1, r);
    }

    /**
     * 获取分区点
     * @param nums
     * @param p
     * @param r
     * @return
     */
    public int partition(int[] nums, int p, int r) {
        int pivot = nums[r];
        int i = p;
        int j = p;
        while (j < r) {
            if(nums[j] < pivot) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
                i ++;
            }
            j ++;
        }
        int t = nums[i];
        nums[i] = nums[r];
        nums[r] = t;
        return i;
    }

    public static void main(String[] args) {
        Sort sort = new Sort();
        sort.bubble_sort(new int[]{3, 5, 4, 1, 2, 6});
        sort.insert_sort(new int[]{3, 5, 4, 1, 2, 6, 9, 0, 22, -1});
        sort.selection_sort(new int[]{3, 5, 4, 1, 2, 6, 9, 0, 22, -1});
        sort.merge_sort_c(new int[]{3, 5, 4, 1, 2, 6, 9, 0, 22, -1, 33});
        sort.quick_sort_c(new int[]{3, 5, 4, 1, 2, 6, 9, 0, 22, -1, 33});
    }
}
