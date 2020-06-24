package Algorithm;

import java.util.Arrays;

public class PrimAlgorithm {
    /***
     *
     * 1) 设 G=(V,E)是连通网，T=(U,D)是最小生成树，V,U 是顶点集合，E,D 是边的集合
     * 2) 若从顶点 u 开始构造最小生成树，则从集合 V 中取出顶点 u 放入集合 U 中，标记顶点 v 的 visited[u]=1
     * 3) 若集合 U 中顶点 ui 与集合 V-U 中的顶点 vj 之间存在边，则寻找这些边中权值最小的边，
     *    但不能构成回路，将 顶点 vj 加入集合 U 中，将边（ui,vj）加入集合 D 中，标记 visited[vj]=1
     * 4) 重复步骤②，直到 U 与 V 相等，即所有顶点都被标记为访问过，此时 D 中有 n-1 条边
     */
    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2}, //A
                {5,10000,10000,9,10000,10000,3}, //B
                {7,10000,10000,10000,8,10000,10000}, //C
                {10000,9,10000,10000,10000,4,10000}, //D
                {10000,10000,8,10000,10000,5,4}, //E
                {10000,10000,10000,4,5,10000,6}, //F
                {2,3,10000,10000,4,6,10000},};//G

        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,verxs,data,weight);
        minTree.showGraph(mGraph);

        minTree.prim(mGraph,1);
    }



    }

    // 创建最小生成树 -> 村庄的图

    class MinTree {
        /***
         *
         * @param graph 图对象
         * @param verxs
         * @param data  图的各个顶点的值 （'A','B')
         * @param weight 权重
         */
        // 创建图的邻接矩阵
        public void createGraph(MGraph graph,int verxs,char data[], int[][] weight) {

            for (int i = 0; i < verxs; i++) {
                graph.data[i] = data[i];
                for (int j = 0; j < verxs; j++) {
                    graph.weight[i][j] = weight[i][j];
                }
            }
        }

        // show graph
        public void showGraph(MGraph graph) {
            for (int[] link: graph.weight) {
                System.out.println(Arrays.toString(link));
            }
        }

        // 编写Prim算法

        /**
         *
         * @param graph
         * @param v 表示从图的第几个顶点开始生成 'A' -> 0, 'B' -> 1..
         */
        public void prim(MGraph graph,int v) {
            // 标记顶点是否被访问过
            int[] visited = new int[graph.verx];
            // visited[] 默认元素是0，表示没有被访问过
            for (int i = 0; i < graph.verx; i++) {
                visited[i] = 0;
            }

            // 把当前节点标记为已访问
            visited[v] = 1;
            // 记录两个顶点的下标
            int h1 = -1;
            int h2 = -1;
            int minWeight = 10000;

            for (int k = 1; k < graph.verx; k++) { // 因为有graph.verxs个顶点，算法结束后会有 graph.verx -1 条边
                for (int i = 0; i < graph.verx; i++) { // 找到一个已经访问过的节点顶点
                    for (int j = 0; j < graph.verx; j++) { // 一个顶点的所有 未访问过的 连的通的边
                        if (visited[i] == 1 && visited[j]==0 && graph.weight[i][j] < minWeight) {
                            minWeight = graph.weight[i][j];
                            h1 = i;
                            h2 = j;
                        }
                    }

                }
                System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">" + "权值:->" + minWeight);
                // 新联通的节点标记为访问过
                visited[h2] = 1;
                minWeight = 10000;
            }


        }
    }

    class MGraph {
        int verx; //表示图的节点个数
        char[] data;// 存放结点数据
        int[][] weight; //存放边，就是我们的邻接矩阵

        public MGraph(int verx) {
            this.verx = verx;
            data = new char[verx];
            weight = new int[verx][verx];
        }
    }


