package Sorting;

import java.util.Arrays;

public class HeadSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9,-1,90,89,3};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void heapSort(int arr[]) {
        System.out.println("堆排序");
        int temp = 0;

        // 第一次要把整个调整为大顶堆
        for (int i = arr.length/2 - 1; i >= 0 ; i--) {
            adjustHeapRecursive(arr,i,arr.length);
        }


        // 将堆顶元素与末尾元素交换,将最大元素"沉"到数组末端;
        // 重新调整结构,使其满足堆定义,然后继续交换堆顶元素与当前末尾元素,反复执行调整+交换步骤,直到整个序列有序
        // 从第0个开始调整
        for (int j = arr.length - 1; j > 0  ; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeapRecursive(arr,0,j);

        }

    }

    /***
     * 功能: 完成将以i 对应的非叶子节点的数调整成大顶堆
     * 举例 int arr[] = {4,6,8,5,9}; => i = 1 => adjustHeap => 得到 {4,9,8,5,6}
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素继续调整,length 是在逐渐的减少
     */
    public static void adjustHeap(int arr[], int i, int length) {

        int temp = arr[i];
        // k指向的是i这个节点的左子节点
        for (int k = 2*i + 1; k < length ; k = 2*k + 1) {
            // 左子节点的值小于右子节点的值. 如果越界则不需要.
            // 这一步的作用是找出三个节点中最大的元素
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k ++;// 指向右子节点
            }

             // 如果子节点大于父节点,就要把子节点上移.
            if (arr[k] > temp) {
                arr[i] = arr[k];
                // 让i指向k,继续循环比较(temp换下来了,继续要和下面的子树比较)
                i = k;
            }else {
                break;
            }
        // 当for循环结束后,我们已经将以i为父节点的树的最大值,放在了最顶
       // 把原来的值放下来
            arr[i] = temp;
        }
    }

    public static void adjustHeapRecursive(int arr[], int i, int length) {

        int min = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < length && arr[left] < arr[min]) {
            min = left;

        }
        if (right < length && arr[right] < arr[min]) {
            min = right;
        }

        if (min != i) {
            int temp  = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
            // 继续堆化子节点. 因为担心子树下面的节点不符合堆的定义
            adjustHeapRecursive(arr,min,length);
        }

    }
}
