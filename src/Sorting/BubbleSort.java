package Sorting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class BubbleSort {
    public static void main(String[] args) {

        int[] arr2 = new int[80000];
        for(int i=0;i<80000;i++){
            arr2[i] = (int) (Math.random() * 80000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleFormatt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleFormatt.format(date1);
        System.out.println("排序前的时间是=" + date1Str);


        int[] arr = {3,9,-1,10,-2};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));

        bubbleSort(arr2);
        Date date2Str = new Date();
        System.out.println("排序后的时间是=" + date2Str);
//        System.out.println(Arrays.toString(arr));

    }

    public static void bubbleSort(int[] arr){

        int temp = 0;
        for(int i = 0;i< arr.length -1;i++){
            boolean flag = false;
            for (int j = 0;j< arr.length -1;j++) {
                if(arr[j] > arr[j+1]){
                    flag = true;
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;

                }
            }

            if (flag == false){
                break;
            }
        }
    }
}
