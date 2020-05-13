public class sparseArray {
    public static void main(String[] args) {
        //Create a 11*11 2-D array
        // 0: empty, 1: black, 2: white
        int chessArra1[][] = new int[11][11];
        chessArra1[1][2] = 1 ;
        chessArra1[2][4] = 2;

        for(int[] row : chessArra1){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        // Turn the original array to Sparse Array
        // 1. get the number of elements
        int sum = 0;
        for(int i = 0;i< 11;i ++){
            for (int j = 0;j <11;j++){
                if (chessArra1[i][j] != 0 ){
                    sum ++;
                }
            }
        }
        // 2. Create the corresponding sparse array
        /**
         *  0  11 11 n
         *  1  x1 y1 val1
         *  2  x2 y2 val2
         */
        int sparseArray[][] = new int[sum+1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        // loop through the original array
        int count = 0;
        for(int i = 0;i< 11;i ++){
            for (int j = 0;j <11;j++){
                if (chessArra1[i][j] != 0 ){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArra1[i][j];
                }
            }
        }
        // SparseArray output
        System.out.println();
        System.out.println("Sparse Array is:");
        for (int i = 0;i<sparseArray.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }

        // 3. restore from sparseArray
        int [][] originArray = restoreFromSparseArray(sparseArray);
        System.out.println("Restoring the original array");
        for (int [] row:originArray){
            for (int data: row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }



    }
    public static int[][] restoreFromSparseArray(int[][] sparseArray){
        int x = sparseArray[0][0];
        int y = sparseArray[0][1];
        int[][] originArray = new int[x][y];

        for (int count = 1;count < sparseArray.length;count ++){
            originArray[sparseArray[count][0]][sparseArray[count][1]] = sparseArray[count][2];
        }
        return originArray;

    }
}
