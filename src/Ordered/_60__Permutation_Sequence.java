package Ordered;

public class _60__Permutation_Sequence {
    public String getPermutation(int n, int k) {
        char[] nums = new char[n+1];
        int[] pmt_count = new int[n+1];
        char[] res = new char[n];
        int inx = 0;
        for(int i = 1; i <= n; i++) {
            nums[i] = (char)(i+'0');
            res[i-1] = (char)(i + '0');
            if(i == 1)
                // 少了;
                pmt_count[i] = 1;
            else
                // 似不似傻，不能是i*啊！！！
                pmt_count[i] = i * pmt_count[i-1];
        }
        // ==写成了=
        if(k == 1) return new String(res);
        k --;
        while(inx < n-1) { //inx != n
            // pmt_count
            int i = k / pmt_count[n-1-inx] + 1;
            res[inx] = nums[i];
            // 忘了和上面的pmt_count[n-1-int]保持一致
            k = k % pmt_count[n-1-inx];
            inx ++;
            // 删除nums中的nums[i]，之后的数字前移
            for(; i < n; i++) {
                nums[i] = nums[i+1];
            }
        }
        res[inx] = nums[1];
        // 返回类型是String
        // int[] can't convert to String
        return new String(res);
    }

    public static void main(String[] args) {
        _60__Permutation_Sequence permutation_sequence = new _60__Permutation_Sequence();
        System.out.println(permutation_sequence.getPermutation(4,0));
    }
}
