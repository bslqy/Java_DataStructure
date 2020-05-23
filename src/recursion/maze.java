package recursion;

import com.sun.org.apache.xpath.internal.objects.XBoolean;

public class maze {
    public static void main(String[] args) {
        // 创建一个二维数组,模拟迷宫
        // 地图
        int [][] map = new int[8][7];
        //上下置为墙
        for(int i = 0;i<7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右置为墙
        for(int i = 0;i<8;i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 设置挡板,1表示
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯来给小球找路
        setWay(map,1,1);

        System.out.println("小球走过,并标识过的 地图的情况");
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 1.map 表示地图
     * 2. i,j表示 从地图的那个位置出发 (1,1)
     * 3. 如果小球能到 map[6][5],则说明通路找到
     * 4. 约定: 当map[i][j] 为0表示该点没有走过 当1表示为墙' 2表示通路,可以走; 3表示该位置已经走过,走不通
     * 5.
     *
     * @param map
     * @param i 从哪个位置开始找
     * @param j
     * @return 如果找到通路,返回true,否则false
     */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5] == 2) { //通路已经找到
            return true;
        }else{
            if (map[i][j] == 0){
                // 没走过,按照策略  下,右,上,左
                map[i][j] = 2; //假定该点是可以走通
                if(setWay(map,i+1,j)){ //向下走
                    return true;
                } else if(setWay(map,i,j+1)){
                    return true;
                }
                else if(setWay(map,i-1,j)){
                    return true;
                }
                else if(setWay(map,i,j-1)){
                    return true;
                }
                else{
                    //该点是走不通的.设为死路
                    map[i][j] = 3;
                    return false;
                }
            }
            else{ // 如果map[i][j] != 0, 可能是1,2,3
                return false;
            }
        }

    }
}
