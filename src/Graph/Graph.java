package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    private boolean[] isVisited;


    public static void main(String[] args) {
        int n = 5;
        String vertexValue[] = {"A","B","C","D","E"};
        Graph graph = new Graph(n);

        for (String vertex : vertexValue) {
            graph.insertVertex(vertex);
        }

        //添加边

        graph.insertEdge(0,1,1); // A-B
        graph.insertEdge(0,2,1); // A-C
        graph.insertEdge(1,2,1); // B-C
        graph.insertEdge(1,3,1); // B-D
        graph.insertEdge(1,4,1); // B-E

        graph.showGraph();

        //测试一把，我们的dfs遍历是否ok
        System.out.println("深度优先遍历");
        graph.dfs();

        System.out.println("");
        System.out.println("广度优先遍历");
        graph.clearVisited();
        graph.bfs();
    }

    public Graph(int n) {
        edges = new int[n][n];
        numOfEdges = 0;
        vertexList = new ArrayList<String>();
        isVisited = new boolean[n];

    }

    public void clearVisited(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            isVisited[i] = false;
        }
    }

    // 得到第一个邻接节点的下标
    public int getFirstNeighbour(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbour(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size() ; j++) {
            if (edges[v1][j] > 0) {
                return j;
            }

        }
        return -1;
    }

    /***
     * 1) 访问初始结点 v，并标记结点 v 为已访问。
     * 2) 查找结点 v 的第一个邻接结点 w。
     * 3) 若 w 存在，则继续执行 4，如果 w 不存在，则回到第 1 步，将从 v 的下一个结点继续。
     * 4) 若 w 未被访问，对 w 进行深度优先遍历递归（即把 w 当做另一个 v，然后进行步骤 123）。
     * 5) 查找结点 v 的 w 邻接结点的下一个邻接结点，转到步骤 3。
     * 6) 分析图
     *
     * @param isVisited
     * @param i
     */
    // 深度优先遍历算法
    // i 第一次就是0
    private void dfsUtil(boolean[] isVisited, int i) {
        // 首先访问该节点
        System.out.print(getValueByIndex(i) + "->");
        //将该节点设为访问过
        isVisited[i] = true;

        // 查找i的第一个邻接点
        int w = getFirstNeighbour(i);

        while (w != -1) {
            // w没有被访问过，则以w为当前节点，继续递归访问
            if (isVisited[w] != true) {
                dfsUtil(isVisited,w);
            }
            // w已经被访问过，则以相邻节点继续递归访问
            w = getNextNeighbour(i,w);
        }

    }

    // 对dfs进行重载，遍历我们所有的节点，并进行dfs
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfsUtil(isVisited,i);
            }
        }
    }


    // 广度优先

    /**
     * 1) 访问初始结点 v 并标记结点 v 为已访问。
     * 2) 结点 v 入队列
     * 3) 当队列非空时，继续执行，否则算法结束。
     * 4) 出队列，取得队头结点 u。
     * 5) 查找结点 u 的第一个邻接结点 w。
     * 6) 若结点 u 的邻接结点 w 不存在，则转到步骤 3；
     * 否则循环执行以下三个步骤： 6.1 若结点 w 尚未被访问，则访问结点 w 并标记为已访问。
     * 6.2 结点 w 入队列
     * 6.3 查找结点 u 的继 w 邻接结点后的下一个邻接结点 w，转到步骤 6。
     */

    private void bfsUtil(boolean[] isVisited, int i) {
        int u; // 表示头节点对应的下标
        int w; // 邻接节点
        // 队列，记录节点访问的顺序
        LinkedList queue = new LinkedList();
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        queue.addLast(i);

        while (!queue.isEmpty()) {
            // 取出队列的头
            u = (int) queue.removeFirst();

            w = getFirstNeighbour(u);
            while (w != -1) {
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "->");
                    // 标记为访问过
                    isVisited[w] = true;
                    // 入队
                    queue.addLast(w);
                }

                w = getNextNeighbour(u,w); // 体现出广度优先，因为一直以u为驱动找。

            }
        }



    }

    // 遍历所有的节点，进行广度优先搜索
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfsUtil(isVisited,i);
            }
        }
    }


    // 插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    // 返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 得到边的数目
    public int getNumberOfEdges() {
        return numOfEdges;
    }

    // 返回结点(i) 对应的数据 0 -> "A" 1 -> "B" 2 -> "C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1,v2的权重值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link: edges) {
            System.out.println(Arrays.toString(link));
        }

        }



    /**
     *
     * @param v1 表示下标即使第几个顶点
     * @param v2 表示第二个顶点对应的下标
     * @param weight
     */
    //添加边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
