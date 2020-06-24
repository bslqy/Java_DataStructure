package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class KruskalCase {
    private int edgeNum;//边的个数
    private char[]vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    // 使用 INF 表示两个顶点不能连通
    private static final int INF=Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs={'A','B','C','D','E','F','G'};
        int matrix[][]=
              { /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/{ 0, 12,INF,INF,INF, 16, 14},
                /*B*/{ 12, 0, 10,INF,INF, 7,INF},
                /*C*/{INF, 10, 0, 3, 5, 6,INF},
                /*D*/{INF,INF, 3, 0, 4,INF,INF},
                /*E*/{INF,INF, 5, 4, 0, 2, 8},
                /*F*/{ 16, 7, 6,INF, 2, 0, 9},
                /*G*/{ 14,INF,INF,INF, 8, 9, 0}
        };
        // 创建KruskalCase 对象实例
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        kruskalCase.kruskal();


    }

    public KruskalCase(char[] vertexs,int[][] matrix) {
        // 初始化顶点个数
        int vlen = vertexs.length;

        //初始化顶点，复制拷贝的方式
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边，使用的是复制拷贝的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        // 统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }


    }

    public void kruskal() {
        int index = 0;
        int[] ends = new int[edgeNum]; // 用于保存"已有最小生成树"中的每个顶点在最小生成树中的终点
        EData[] results = new EData[edgeNum];

        // 获取图中的结合，一共有12个边
        EData[] edges = getEdges();

        // 按照边的权值大小进行排序
        sortEdge(edges);

        // 遍历edges数组, 照权值从小到大的顺序选择n-1条边，并保证这n-1条边不构成回路
        // 如果不构成回路，就加入result，否则加入
        for (int i = 0; i < edgeNum; i++) {
            // 获取第i条边的第一个顶点（起点)
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            // 获取p1这个顶点在已有的最小生成树中对应的终点是哪个
            int m = getEnd(ends,p1);
            // 获取p2这个顶点在已有的最小生成树中对应的终点是哪个
            int n = getEnd(ends,p2);
            // 是否构成回路
            if (m != n) { //没有构成回路
                ends[m] = n;
                results[index++] = edges[i]; // 有一条边加入到result数组
            }
        }
        System.out.println("最小生成树为:" + Arrays.toString(results));


    }
    public void print() {
        System.out.println("邻接矩阵为: \n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%10d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }

    //
    private void sortEdge(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges [j] = edges [j+1];
                    edges [j+1]  = temp;
                }

            }
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return  i;
            }

        }
        return -1;
    }

    /***
     *  获取图中的边，放到EData数组，后面我们需要遍历该数组
     *  形式 [Edge['A','B',12],Edge['B','F',7]..]
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    // 把能联通的边加入
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;

    }

    /**
     * 功能: 获取下标为i的顶点的终点
     * @param ends: 数组就是记录了各个顶点对应的终点是哪个，ends 数组是在遍历过程中，逐步形成的
     * @param i: 表示存入顶点的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }



    }

    // 创建一个类Edata，他的对象实例就表示一条边
    class EData{
        char start;
        char end;
        int weight;

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EData{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    '}';
        }
    }


