package TAG.TwoPointers;

public class _443__String_Compression {
    public int compress(char[] chars) {
        if(chars.length < 2) return chars.length;
        int i = 0, j = 0, num = 0;
        for (j = 0; j < chars.length; j++) {
            num++;
            if(j == chars.length-1 || chars[j] != chars[j+1]) {
                chars[i++] = chars[j];
                if(num != 1) {
                    String s = String.valueOf(num);
                    for (int k = 0; k < s.length(); k++) {
                        chars[i++] = s.charAt(k);
                    }
                }
                num = 0;
            }
        }
        return i;
    }
}
