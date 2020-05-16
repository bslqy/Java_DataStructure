package Stack;

import javax.imageio.stream.ImageInputStream;
import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.printf("s(show)\ne(exit)\npush(push)\npop(pop)\n");
            key = scanner.next();
            switch (key) {
                case "s":
                    stack.list();
                    break;
                case "push":
                    System.out.println("Input a number");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("The output number is:%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    break;
            }

        }
    }
}

class ArrayStack{
    private int maxSize; // 栈的大小
    private int[] stack; // 数组，数组模拟栈, 数据就放在该数组
    private int top = -1; // top表示栈顶，初始化为-1

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];

    }
    // 栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty(){
        return top == -1;
    }

    // 入栈-push
    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top ++;
        stack[top] = value;
    }

    // 出栈 pop,将栈顶的数据返回
    public int pop(){
        // 先判断栈是否空
        if(isEmpty()){
            throw  new RuntimeException("栈空");
        }
        int temp = top;
        top--;
        return stack[temp];
    }

    //显示栈的情况[遍历栈], 遍历时, 需要从栈顶开始显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }

        // 需要从栈顶开始显示数据
        for(int i = top;i >= 0 ; i--){
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }


}
