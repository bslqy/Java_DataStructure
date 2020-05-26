package Sorting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertionSort {

    // 从右往左进行对比
    // 左边默认有序,从右往左找小找插入点. 如果找到,则把该位置的元素向后移动一位,腾出空间,然后当前元素即可插入

    public static void insertionSort(int[] arr){

        // 第0个下标默认有序
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            // 从右往左一直找,直到找到一个比自己小的数停止. 期间不断把元素后移一位
            while (j > 0 && temp < arr[j-1]){
                arr[j] = arr[j-1];
                j --;
            }
            if(j!=i){
                arr[j] = temp;
            }

        }
    }

    public static void main(String[] args) {
        int[] arr  = {8,3,2,1,7,4,6,5};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));

        insertionSort(arr);

        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));


        int[] arr2 = new int[80000];
        for(int i=0;i<80000;i++){
            arr2[i] = (int) (Math.random() * 80000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleFormatt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleFormatt.format(date1);
        System.out.println("排序前的时间是" + date1Str);

        insertionSort(arr2);

        Date date2 = new Date();
        String date2Str = simpleFormatt.format(date2);
        System.out.println("排序后的时间是" + date2Str);

    }
}
