package Queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    // Use Array to simulate a queue
    public static void main(String[] args) {
        ArrayQueue q = new ArrayQueue(2);
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

class ArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    // Create a Queue
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; // points to the head of the queue
        rear = -1;  // points to the end of the queue
    }

    public boolean isFull(){
        return rear == maxSize - 1;
    }

    public boolean isEmpty(){
        return  rear == front;
    }

    //Add to queue
    public void addQueue(int n){
        if (isFull()){
            System.out.println("The queue is full! Cannot add");
            return;
        }
        rear ++;
        arr[rear] = n;
    }

    // Deque
    public int deQueue(){
        if (isEmpty()){
            // try catch expcetion
            throw  new RuntimeException("The queue is empty");
        }

        front ++;
        return arr[front];
    }

    //遍历队列的所有数据
    public void display(){
        if(isEmpty()){
            System.out.println("The queue is empty!");
            return;
        }
        for(int n: arr){
            System.out.printf("%d\t",n);
        }
        System.out.println();
    }
    // 显示队列的头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("The queue is empty!");
        }
        return arr[front] + 1;
    }


}
