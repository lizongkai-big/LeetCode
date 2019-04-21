package TAG.TwoPointers;

// Two pointers
public class _345__Reverse_Vowels_of_a_String {
    public String reverseVowels(String s) {
        char[] vowels = new char[]{'a','e','i','o','u','A','E','I','O','U'};
        char[] array = s.toCharArray();
        int len = array.length;
        int i = 0;
        int j = len-1;
        while(i < j) {
            while(i < j && !isVowels(vowels, array[i])) i++;
            while(i < j && !isVowels(vowels, array[j])) j--;
            char c = array[i];
            array[i] = array[j];
            array[j] = c;
            i ++;
            j --;
        }
        return new String(array);
    }
    private boolean isVowels(char[] vowels, char c) {
        for(char v: vowels) {
            if(v == c)
                return true;
        }
        return false;
    }
}
