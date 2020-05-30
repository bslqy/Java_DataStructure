package Search;

import java.util.Arrays;

/**
 * 数据量较大,分布比较均匀使用差值查找速度更快
 * 关键字分布不均匀的情况下,该方法不一定比折半查找好
 */
public class InsertValueSerach {
    public static void main(String[] args) {
        int[] arr = new int[100];

        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    // 编写差值查找算法

    /***
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */

    public static int insertValueSeach(int[] arr,int left, int right, int findVal) {

        // 注意: findVal < arr[0] 和 findVal > arr[arr.length -1] 必须需要
        // 否则 得到的mid可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return  -1;
        }

        // 求出 mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            return insertValueSeach(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSeach(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
