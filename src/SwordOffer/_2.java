package SwordOffer;

public class _2 {
    public String replaceSpace(StringBuffer str) {
        int len = str.length();
        String string = str.toString();
        int empty = 0;
        for (int i = 0; i < len; i++) {
            if (string.charAt(i) == ' ')
                empty++;
        }
        // ***新数组长度***
        char[] chars = new char[len + empty * 2];
        int inx = chars.length-1;
        for (int i = len-1; i >= 0; i--) {
            char c = string.charAt(i);
            if (c != ' ') {
                chars[inx --] = c;

            }
            else {
                chars[inx --] = '0';
                chars[inx --] = '2';
                chars[inx --] = '%';
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        _2 test = new _2();
        test.replaceSpace(new StringBuffer("hello world"));
    }
}
