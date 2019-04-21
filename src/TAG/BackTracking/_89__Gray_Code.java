package TAG.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class _89__Gray_Code {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for(int i = 0; i < n; i ++) {
            int size = res.size();
            for(int k = size-1; k >= 0; k--)
                res.add(res.get(k) | 1 << i);
        }
        return res;
    }

    public static void main(String[] args) {
        _89__Gray_Code gray_code = new _89__Gray_Code();
        System.out.println(gray_code.grayCode(3));
    }
}
