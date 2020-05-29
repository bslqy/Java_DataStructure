package Sorting;


import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {53,3,542,748,14,214};

        // 第1轮(针对每个元素的个位进行排序处理)

        // 定义一个二位数组,表示10个桶,每个桶就是一个一位数组

        //1. 二位数组包含了10个一维数组
        //2. 为了防止在放入数的时候,数据溢出,则每个一维数组的大小定位arr.length
        //3. 很明确,基数排序是用空间换时间的经典算法

        // 每个位数最多可能出现arr.length 个数据
        int[][] bucket = new int[10][arr.length];

        // 为了记录每个桶中,实际存放了多少个数据,我们定义一个一维数组来记录每个桶每次放入数据的个数.
        // 这个数组和桶无关.只是用来遍历
        int[] bucketElementCounts = new int [10];

        // 第1轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素的个位数
            int digitOfElement = arr[j] % 10;
            // 放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement] ++;
        }
        // 按照这个桶的顺序(一维数组的下标依次取出数据,放入原来的数组)
        // 此时已经按照个位排好序
        int index = 0;
        //遍历每一桶,并将桶中的数据放入到原始数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据,我们才放回原数组
            if(bucketElementCounts[k] != 0){
                //循环该桶即第k个桶,放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    // 取出元素放入到array中
                    arr[index] = bucket[k][l];
                    index ++;
                }

            }
            // 第一轮处理后,需要把每个buckElemetnCount[k] = 0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第1轮,对个位数的排序处理 arr = " + Arrays.toString(arr));


        // 第2轮(针对每个元素的十位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素的个位数
            int digitOfElement = arr[j] / 10 % 10;
            // 放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement] ++;
        }
        index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据,我们才放回原数组
            if(bucketElementCounts[k] != 0){
                //循环该桶即第k个桶,放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    // 取出元素放入到array中
                    arr[index] = bucket[k][l];
                    index ++;
                }

            }
            // 第2轮处理后,需要把每个buckElemetnCount[k] = 0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第2轮,对个位数的排序处理 arr = " + Arrays.toString(arr));


        // 第3轮(针对每个元素的十位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素的个位数
            int digitOfElement = arr[j] / 100 % 10;
            // 放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement] ++;
        }
        index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据,我们才放回原数组
            if(bucketElementCounts[k] != 0){
                //循环该桶即第k个桶,放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    // 取出元素放入到array中
                    arr[index] = bucket[k][l];
                    index ++;
                }

            }
            // 第3轮处理后,需要把每个buckElemetnCount[k] = 0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第3轮,对个位数的排序处理 arr = " + Arrays.toString(arr));


        radixSort(arr);
    }

    public static void radixSort(int[] arr){
        /***
         * 1. 得到数组中最大的数的位数,这个数字就是需要排序的轮数
         */
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int maxLength = (max + "").length();

        // 每个位数最多可能出现arr.length 个数据
        int[][] bucket = new int[10][arr.length];
        // 记录每个位数出现的数字个数的数组
        int[] bucketElementCounts = new int[10];

        for (int round = 0, n = 1; round < maxLength; round++, n = n * 10) {

           // (针对每个元素的十位进行排序处理)
            for (int i = 0; i < arr.length; i++) {
                int digitOfElement =arr[i] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                bucketElementCounts[digitOfElement]++;
            }

            // 依次取出放回原来的数组
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if(bucketElementCounts[k]!=0){
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        index += 1;
                    }
                }
                bucketElementCounts[k] = 0;
            }
            System.out.printf("第%d轮,对个位数的排序处理 arr = %s\n",round+1  ,Arrays.toString(arr));

        }
    }
}



