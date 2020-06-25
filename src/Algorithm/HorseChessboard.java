package Algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {
    private static int X; // column
    private static int Y; // row
    private static boolean visited[]; //通过一个一维数组去存储
    private static boolean finished;

    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行~~"); //测试骑士周游算法是否正确
        X=8;
        Y=8;
        int row=1;//马儿初始位置的行，从 1 开始编号
        int col=1;//马儿初始位置的列，从 1 开始编号
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];

        long start = System.currentTimeMillis();
        traversalChessBoard(chessboard,row -1, col -1,1);

        long end = System.currentTimeMillis();
        System.out.println("共耗时:"+(end - start)+ " 毫秒");

        // 输出棋盘的最后情况
        for (int[] rows: chessboard) {
            for (int step: rows) {
                System.out.print(step + "\t");

            }
            System.out.println();

        }


    }

    public static void traversalChessBoard(int[][] chessBoard, int row, int col, int step) {
        chessBoard[row][col] = step;
        // 当前递归起始点
        visited[row*X+col] = true;

        // 记录当前位置可以走的集合
        ArrayList<Point> ps = next(new Point(col, row));
        // 对元素进行排序，找到下一步最少可能性的
        sort(ps);

        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            if (!visited[p.y * X + p.x]) {
                traversalChessBoard(chessBoard,p.y,p.x,step + 1);
            }

        }
        // 判断是否完成任务
        if (step < X * Y && !finished) {
            chessBoard[row][col] = 0;
            visited[row * X + col] = false;
        } else {
            finished = true;
        }

    }

    // 计算当前点计算能走的位置，并加入ArrayList
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p = new Point();
        // 穷举马最多能走的八种步法(顺时针)
        // 左2,上1
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p));

        }

        // 左1,上2
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p));

        }

        // 右1,上2
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p));

        }

        // 右2,上1
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p));

        }


        // 右2,下1
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p));
        }
        // 右1,下2
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p));

        }

        // 左1,下2
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p));

        }


        // 左2,下1
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p));

        }


        return ps;
    }

    // 根据当前这一步的所有的下一步选择的位置，进行非递减排序. (从小到大，允许重复）
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int len1 = next(o1).size();
                int len2 = next(o2).size();
                if (len1 < len2) {
                    return -1;
                } else if (len1 == len2) {
                    return 0;
                } else {
                    return 1;
                }

            }
        });
    }

}
