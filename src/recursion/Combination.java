package recursion;

public class Combination {

    int size;
    int[] num;
    public Combination(int size)
    {
        this.size = size;
        num = new int[size];
    }


    public void dfs(int index){

        // 结束条件
        if (index == size){
            for (int i = 0;i< size;i++){
                System.out.printf(num[i] + " ");
            }
            System.out.println();
            return;
        }

        // 如果不是满四位,就继续填满
        for (int i = 1;i<=size;i++){
            num[index] = i;
            dfs(index + 1);
        }

    }

    public static void main(String[] args) {
        Combination c = new Combination(5);
        c.dfs(0);
    }
}
