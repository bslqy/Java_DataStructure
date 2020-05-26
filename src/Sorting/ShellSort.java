package Sorting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arr2 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        int temp = 0;

        // 希尔排序的第1轮排序
        // 因为第1轮排序,是将10个数据分成了10/2 = 5组
        for (int i = 5; i < arr.length; i++) {
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前元素大于加上步长后那个元素,说明交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }

        System.out.println("希尔排序第一轮后" + Arrays.toString(arr));


        // 希尔排序的第2轮排序
        // 因为第2轮排序,是将10个数据分成了5/2 = 2组
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                // 如果当前元素大于加上步长后那个元素,说明交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }

        System.out.println("希尔排序第二轮后" + Arrays.toString(arr));

        // 希尔排序的第3轮排序
        // 因为第3轮排序,是将10个数据分成了2/2 = 1组
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                // 如果当前元素大于加上步长后那个元素,说明交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("希尔排序第三轮后" + Arrays.toString(arr));


        int[] arr3 = new int[80000];
        for(int i=0;i<80000;i++){
            arr3[i] = (int) (Math.random() * 80000000);
        }


        Date date1 = new Date();
        SimpleDateFormat simpleFormatt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleFormatt.format(date1);
        System.out.println("排序前的时间是" + date1Str);

        shellSort2(arr3);

        Date date2 = new Date();
        String date2Str = simpleFormatt.format(date2);
        System.out.println("排序后的时间是" + date2Str);
    }

    //使用逐步推导的方式编写希尔排序
    public static void shellSort(int[] arr) {

        int temp = 0;
        int round = 0;

        // 完整写法
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {

            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j = j - gap) {

                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;

                    }

                }

            }
            round += 1;
//            System.out.printf("第%d轮排序后"+ Arrays.toString(arr)+"\n",round );

        }

//        System.out.println("完成排序后" + Arrays.toString(arr));
    }

    // 对交换式的希尔排序进行优化 -> 移位法
    public static void shellSort2(int[] arr){
        for (int gap = arr.length/2; gap > 0 ; gap = gap /2) {
            // 从第gap元素,逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];

                if(arr[j] < arr[j - gap]){
                    while (j - gap > 0 && temp < arr[j - gap]){
                        // 开始移动
                        arr[j] = arr[j - gap];
                         j = j - gap;
                    }
                    // 插入替换
                    arr[j] = temp;
                }
                
            }
        }
    }
}
