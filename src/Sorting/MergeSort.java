package Sorting;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];

        mergeSort(arr,0,arr.length -1,temp);

        System.out.println("归并排序后" + Arrays.toString(arr));

    }

    // 分解的方法
    public static void  mergeSort(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            // 向左递归进行分解
            mergeSort(arr,left,mid,temp);

            mergeSort(arr,mid + 1,right,temp);

            merge(arr,left,mid,right,temp);


        }
    }

    public static void merge(int[] arr, int left, int mid,int right, int[] temp){
        int i = left; //左边有序数组的初始索引
        int j = mid + 1;  //右边有序数组的初始索引
        int t = 0; // 指向temp数组的当前索引

        // 左右两边分别进行比较合并
        while( i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }else{
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //剩下的数全部写入
        while(i<= mid){
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while(j<= right){
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // 将temp数组的元素拷贝到arr
        //注意,并不是每次都拷贝所有
        t = 0;
        int templeft = left;
        while(templeft <= right){
            arr[templeft] = temp[t];
            t += 1;
            templeft += 1;
        }

    }
}
