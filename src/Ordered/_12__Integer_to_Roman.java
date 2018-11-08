package Ordered;

public class _12__Integer_to_Roman {
    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        String roman = "IVXLCDM";
        int inx = 0;
        while(num != 0) {
            int tmp = (num % 10) ;
            if(tmp == 0) {
                // do nothing
            }
            else if(tmp < 4) {
                while(tmp-- > 0) {
                    res.append(roman.charAt(inx) + "");
                }
            }
            else if(tmp == 4) {
                res.append(roman.charAt(inx+1) + "" + roman.charAt(inx));
            }
            else if(tmp == 5) {
                res.append(roman.charAt(inx+1) + "");
            }
            else if(tmp < 9) {
                while(tmp-- > 5)
                    res.append(roman.charAt(inx) + "" );
                res.append(roman.charAt(inx+1) + "");
            }
            else if(tmp == 9) {
                res.append(roman.charAt(inx+2) + "" + roman.charAt(inx));
            }
            inx += 2;
            num /= 10;
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        _12__Integer_to_Roman integerToRoman = new _12__Integer_to_Roman();
        System.out.println(integerToRoman.intToRoman(1992));
    }
}
