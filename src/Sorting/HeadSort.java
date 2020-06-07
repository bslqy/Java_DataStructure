package Sorting;

import java.util.Arrays;

public class HeadSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9,-1,90,89};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void heapSort(int arr[]) {
        System.out.println("堆排序");
        int temp = 0;

        // 调整为大顶堆
        for (int i = arr.length / 2 - 1; i >=0;i--) {
            adjustHeap(arr,i,arr.length);
        }

        // 将堆顶元素与末尾元素交换,将最大元素"沉"到数组末端;
        // 重新调整结构,使其满足堆定义,然后继续交换堆顶元素与当前末尾元素,反复执行调整+交换步骤,直到整个序列有序
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
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
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 左子节点的值小于右子节点的值. 如果越界则不需要.
            // 这一步的作用是找出三个节点中最大的元素
            if (k+1 < length && arr[k] < arr[k + 1]) {
                k++; // 指向右子节点
            }
            if (arr[k] > temp) { // 如果子节点大于父节点,就要把子节点上移.
                arr[i] = arr[k];
                i = k; // 让i指向k,继续循环比较(temp换下来了,继续要和下面的子树比较)
            }else{
                break;
            }
        }

        // 当for循环结束后,我们已经将以i为父节点的树的最大值,放在了最顶
        arr[i] = temp; // 把原来的值放下来

    }
}
