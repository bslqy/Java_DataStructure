package Queue;

import java.util.Scanner;

public class CircileQueueDemo {
    // Use Array to simulate a queue
    public static void main(String[] args) {
        CircleArray q = new CircleArray(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.printf("s(show)\ne(exit)\na(add)\ng(get)\nh(head)\n");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    q.display();
                    break;
                case 'a':
                    System.out.println("Input a number");
                    int value = scanner.nextInt();
                    q.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res = q.deQueue();
                        System.out.printf("The output number is:%d\n",res);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res = q.headQueue();
                        System.out.printf("The head of the queue is:%d\n",res);

                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }

        }


    }


}

class CircleArray{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    // Create a Queue
    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0; // points to the head of the queue
        rear = 0;  // points to the end+1 of the queue
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty(){

        return rear == front;
    }

    //Add to queue
    public void addQueue(int n){
        if (isFull()){
            System.out.println("The queue is full! Cannot add");
            return;
        }
        arr[rear] = n;
        //将rear后移，必须取模拟
        rear = (rear + 1) %maxSize;
    }

    // Deque
    public int deQueue(){
        if (isEmpty()){
            // try catch expcetion
            throw  new RuntimeException("The queue is empty");
        }
        // 1. front对应的值保留到队列的第一个元素
        // 2. 将front 后移， 考虑取模
        // 3. 将临时保存的变量返回

       int res  = arr[front];
        front = (front+ 1) %maxSize;
        return res;
    }

    //遍历队列的所有数据
    public void display(){
        if(isEmpty()){
            System.out.println("The queue is empty!");
            return;
        }
        // 中间断层情况出现，无法重头开始遍历
        // 需要从front开始遍历,并且求出有效数据的个数
        // 取模避免过界
        for(int i = front; i < front + size(); i++){
            System.out.printf("arr[%d] = %d\n", i % maxSize ,arr[i % maxSize]);
        }

        System.out.println();
    }

    public int size(){
        return (rear +maxSize - front) % maxSize;
    }
    // 显示队列的头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("The queue is empty!");
        }
        return arr[front];
    }


}
