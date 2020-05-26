package Sorting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectionSort {

    public static void selectionSort(int[] arr){
        int temp;

        for (int i = 0; i < arr.length-1; i++) {
            int min = arr[i];
            int pos = i;
            for (int j = i+1; j < arr.length ; j++) {
                if(arr[j] < min){
                    min = arr[j]; // 记录最小的位置
                    pos = j;
                }
            }
            // 记录完之后,进行交换
            temp = arr[i];
            arr[i] = arr[pos];
            arr[pos] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr  = {8,3,2,1,7,4,6,5};
        System.out.println("排序前");
       System.out.println(Arrays.toString(arr));

        selectionSort(arr);

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

        selectionSort(arr2);

        Date date2 = new Date();
        String date2Str = simpleFormatt.format(date2);
        System.out.println("排序后的时间是" + date2Str);

    }
}
