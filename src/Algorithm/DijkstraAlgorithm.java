package Algorithm;

import java.util.Arrays;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};

        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();

    }

}

class Graph{
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;

    public Graph(char[] vertex,int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link:matrix) {
            System.out.println(Arrays.toString(link));

        }
    }

    /**
     *
     * @param index 出发顶点的下标
     */
    public void dsj(int index) {
        VisitedVertex vv = new VisitedVertex(vertex.length,index);

    }

    // 更新index 下标顶点到周围顶点的距离 和周围顶点前驱结点
    private void update(int index) {
        int len = 0;
        // 对index顶点出发的邻接矩阵进行遍历
        for (int j = 0; j < matrix[index].length; j++) {
            // 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
            len = vv.getDis(index) + matrix[index][j];



        }
    }
}

class VisitedVertex{
    public int[] already_visited; // 记录各个顶点是否访问过 1 表示访问过,0 未访问,会动态更新
    public int[] pre_visited; // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] dis; // 记录出发顶点到其他所有顶点的距离,比如 G 为出发顶点，就会记录 G 到其它顶点的距离，会动态更新，求 的最短距离就会存放到 dis

    /**
     *
     * @param length 表示顶点的个数
     * @param index 出发顶点对应的下标，比如G顶点下标就是6
     */
    public VisitedVertex(int length, int index) {
        this.already_visited = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];

        // 初始化距离数组
        Arrays.fill(dis,65535);
        dis[index] = 0; // 设置出发顶点的访问距离为0
    }

    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return 如果访问过，则返回true，否则返回false
     */
    public boolean in(int index) {
        if (already_visited[index] == 1) {
            return true;
        }
        return false;
    }

    /**
     * 功能： 更新出发顶点到index的距离
     * @param index
     * @param len
     */

    public void updateDistance(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新pre这个顶点前驱为index的结点
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;

    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];

    }




}
