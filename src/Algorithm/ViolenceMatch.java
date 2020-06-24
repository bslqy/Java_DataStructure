package Algorithm;

public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "abcedkdasdcsdfzx";
        String str2 = "dasdcs";
        System.out.println("index = "+ violenceMatch(str1,str2));

    }

    //暴力匹配算法实现
    public static int violenceMatch(String str1,String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;
        int i = 0;
        int j = 0;

        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        // 判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
