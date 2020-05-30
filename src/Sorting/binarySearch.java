package Sorting;

import java.util.ArrayList;

public class binarySearch {
    public static void main(String[] args) {
        int arr[] = {1,8,10,89,1000,1234};

        int pos = binarySerach(arr,0,arr.length - 1,1234);
        System.out.println("位置在"+pos);
    }

    public static int binarySerach(int[] arr, int left, int right, int findValue) {
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (left > right){
            return -1;
        }

        if(findValue < midVal){
            return binarySerach(arr,left,mid -1,findValue);


        }
        else if (findValue > midVal) {
            return binarySerach(arr,mid + 1,right,findValue);
        }
        return mid;

    }

    public static ArrayList<Integer> binarySerach2(int[] arr, int left, int right, int findValue) {
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        ArrayList<Integer> indexList = new ArrayList<>();

        if (left > right){
            return new ArrayList<Integer>();
        }

        if(findValue < midVal){
            return binarySerach2(arr,left,mid -1,findValue);


        }
        else if (findValue > midVal) {
            return binarySerach2(arr,mid + 1,right,findValue);
        }
        else{
            /***
             * 1. 在找到mid 索引值,不要马上返回
             * 2. 向 mid 索引值左边扫描,讲所有满足1000 的元素下标,加入集合到ArrayList
             * 3. 向 mid 索引值左边扫描,讲所有满足1000 的元素下标,加入集合到ArrayList
             * 4. 将Arraylist返回
             */

            // 向 mid 索引值左边扫描,讲所有满足1000 的元素下标,加入集合到ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findValue) {
                    break;
                }

                indexList.add(temp);
                temp -= 1;

            }
            indexList.add(mid);

            // 向 mid 索引值右边扫描,讲所有满足1000 的元素下标,加入集合到ArrayList
            temp = mid + 1;
            while (true){
                if(temp > arr.length - 1 || arr[temp] != findValue){
                    break;
                }
                indexList.add(temp);
                temp += 1;
            }

        }

        return indexList;


    }
}
