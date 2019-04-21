package Ordered;

public class _6__ZigZag_Conversion {
    /**
     * 硬扛的裱糊匠！啰嗦、不够优雅
     * @param s
     * @param numRows
     * @return
     */
    public String convert_stupid(String s, int numRows) {
        int diff = numRows + numRows - 2;
        // i: row num
        // diff: 两个元素的下标的差值
        int len = s.length();
        if(numRows <= 1 || len <= 1) {
            return s;
        }
        char[] res = new char[len];
        // point to res's subscript
        int point = 0;
        // 第一行
        for(int i = 0, j = diff; i < len; i += diff) {
            res[point ++] = s.charAt(i);
        }
        // 第二行至倒数第二行
        for(int i = 1, j = diff; i < numRows-1; i++, j -= 2) {
            if(i < len)
                res[point ++] = s.charAt(i);
            int firstDiff = j - 2;
            int secondDiff = diff - firstDiff;
            while(i + firstDiff < len) {
                res[point ++] = s.charAt(i + firstDiff);
                i += firstDiff;
                if(numRows != 3 && i + secondDiff < len){
                    res[point ++] = s.charAt(i + secondDiff);
                }
                if(numRows != 3) {
                    i += secondDiff;
                }
            }
        }
        // 最后一行
        for(int i = numRows - 1, j = diff; i < len; i += diff) {
            res[point ++] = s.charAt(i);
        }
        return new String(res);
    }

    /**
     * elegant approach
     * 另一个角度看待 zigzag pattern
     * @param s
     * @param numRows
     * @return
     */
    public String convert_elegant(String s, int numRows) {
        if(numRows == 1)
            return s;
        int len = s.length();
        StringBuilder[] res = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i ++) {
            /*init every res element **important step!!!!*/
            // if not, res[i] is null
            res[i] = new StringBuilder();
        }
        int direction = 1;
        int inx = 0;
        for(int i = 0; i < len; i ++) {
            res[inx].append(s.charAt(i));
            // change the direction **important step!!!!
            if(inx == 0) direction = 1;
            if(inx == numRows - 1) direction = -1;
            inx += direction;
            // attention to outOfBounds, new test cases
            if(inx >= numRows) inx = numRows-1;
            if(inx < 0) inx = 0;
        }
        for(int i = 1; i < numRows; i ++) {
            res[0].append(res[i]);
        }
        return res[0].toString();
    }

    public static void main(String[] args) {
        _6__ZigZag_Conversion zigZagConversion = new _6__ZigZag_Conversion();

        System.out.println(zigZagConversion.convert_stupid("PAYPALISHIRING", 4));
    }
}
