package recursion;

/**
 * 八皇后问题算法思路分析
 *
 * 第一个皇后先放第一行第一列
 * 第二个皇后放在第二行第一列、然后判断是否OK， 如果不OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
 * 继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 * 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
 * 然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤 【示意图】
 *
 * 说明：理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，
 * 用一个一维数组即可解决问题. arr[8] = {0 , 4, 7, 5, 2, 6, 1, 3}
 * 对应arr 下标 表示第几行，即第几个皇后，arr[i] = val , val 表示第i+1个皇后，放在第i+1行的第val+1列
 */

public class EightQueens {
    // 定义一个max
    int max = 8;
    //定义数组array,保存皇后放置位置的结果,比如arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];

    public static void main(String[] args) {
        EightQueens queen8 = new EightQueens();
        queen8.check(0);

    }

    // 查看当我们放置第n个皇后,就去检测该皇后是否和前面已经摆放的皇后冲突

    /***
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for(int i = 0;i<n;i++){
            // i表示前面的皇后, 不能共线,不能斜线(左右各1)
            // Math.abs(n-i) 其实是对角线. 比如第一个和第二个之间间隔必须不能为左右1,第一个和第三个之间不能为左右2, 直到左右7
            if((array[i] == array[n]) || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //编写一个方法,放置第n个皇后
    private void check(int row){
        // 说明已经放到最后一个皇后
        if(row == max){ // 第九个皇后,证明第八个皇后已经放好了
            print();
            return;
        }
        // 不停的放
        for(int i = 0;i<max;i++){
            //先把当前这个皇后 放入改行的第一列
            array[row] = i;
            //判断当放置第n个皇后到i列时,是否冲突 (计算当前位置的米字型是否和其他冲突)
            if(judge(row)){
                check(row +1);
            }
        }
    }


    //将皇后摆放的位置输出
    private void print(){
        for(int i=0;i< array.length;i++){
            System.out.printf(array[i] + " ");
        }
        System.out.println();
    }



}
