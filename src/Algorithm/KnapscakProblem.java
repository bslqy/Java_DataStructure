package Algorithm;

public class KnapscakProblem {
    public static void main(String[] args) {
        int[] weight = {1, 4, 3};// 物品的重量
        int[] value = {1500, 3000, 2000}; //物品的价值
        int m = 4; //背包的容 量
        int n = value.length; // 物品的个数

        // 记录商品的放入情况，定一个二维数组
        int[][] path = new int[n+1][m+1];

        // 创建二维数组，
        //v[i][j] 表示在前i个物品中能够装入容量为j的背包的最大价值
        int[][] v = new int[n+1][m+1];
        for (int i = 0; i <v.length; i++) {
            v[i][0] = 0; // 将第一行设为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; // 将第一列设为0
        }

        for (int i = 1; i < v.length; i++) { // 不处理第一行
            for (int j = 1; j < v[0].length; j++) { // 不处理第一列

                // 如果能够装不下，容量不发生变化，循环看看有没有装得下当前物品的状态
                if (weight[i-1] > j) {
                    v[i][j] = v[i-1][j];
                }
                // 如果装得下，则看看最大值是什么
                else{
                    if (value[i - 1] + v[i - 1][j - weight[i - 1]] > v[i - 1][j]) {
                        v[i][j] = value[i - 1] + v[i - 1][j - weight[i - 1]];
                        //把当前情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }

                }

            }

        }


        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " " );
            }
            System.out.println();
        }

        // 输出放入的是什么商品
        int i = path.length - 1;
        int j = path[0].length -1;

        while (i > 0 && j >0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个物品加入背包\n",i);
                j = j - weight[i-1];
            }
            i --;


        }
    }
}
