package Algorithm;

public class KMP {
    public static void main(String[] args) {
        String str1="BBCABCDABABCDABCDABDE";
        String str2="ABCDABD";

    }

    /***
     *
     * @param str1 源字符串
     * @param str2 字串
     * @param next 字串对应的部分匹配表
     * @return 如果是-1，就是没有匹配到，否则返回第一个匹配的位置
     */

    // 写出kpm搜索算法
    public static int kmpSearch(String str1,String str2,int[] next) {
        // 遍历
        for (int i = 0,j = 0; i < str1.length(); i++) {

            // 需要处理 str1.chatAt(i) != str2.charAt(j),调整j的大小
            // KMP算法的核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            // 没有考虑两者不同的情况
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }

        }
        return -1;

    }

    // 获得一个字符串（字串的) 部分匹配表
    public static  int[] kmpNext(String dest) {
        // 创建一个数组，保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; // 如果dest的长度为1，则部分匹配值为0

        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j),需要从next[j-1]获取新的j
            //直到我们发现又dest.charAt(i) == dest.charAt(j) 成立才退出
            // 这是kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];

            }



            // 当dest.chatAt(i) == dest.chatAt(j) 满足时，部分匹配之就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
