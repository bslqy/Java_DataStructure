package Stack;

/***
 * 1. 通过一个索引来遍历我们的表达式
 * 2. 如果我们发现是一个数字,就直接入数栈
 * 3.1 如果发现当前的符号栈为空,就直接入栈
 * 3.2 如果符号栈有操作符,就进行比较. 如果当前操作符的优先级小于或等于栈中的操作符,就需要从数栈中pop出2个数
 * 和一个符号进行运算,将得到结果,入数栈,然后将当前的操作符入符号栈
 * 如果当前的操作符的优先级大于栈中的操作符,就直接入栈.
 * 4.当表达式扫描完毕后,就顺序的从数栈和符号栈中pop出相应的数和符号,并运行
 * 5. 最后在数栈只有一个数字,就是表达式的结果.
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "70+2*6-2";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index =0;
        int num1  = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; // 将每次扫描到的char保存到ch
        String keepNum ="";

        while(true){
            // 依次将每个扫描出来的字符
            ch = expression.substring(index,index+1).charAt(0); //取字符串
            // 判断ch是什么,做相应的处理
            if (operStack.isOper(ch)){
                //看当前这个栈是否为空,如果为空,直接入栈
                if(operStack.isEmpty()){
                    operStack.push(ch);
                }
                //不为空
                else{
                    // 当前优先级小,则数栈弹出2个数字, 符号栈弹出一个进行运算然后压入数栈,随后压入当前运算符
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            oper = operStack.pop();
                            res = numStack.cal(num1,num2,oper);
                            numStack.push(res);
                            operStack.push(ch);
                    }
                    // 当前优先级大,直接入栈
                    else{
                        operStack.push(ch);
                    }
                }

            }
            // 如果是数字,需要一直拼接,不能马上入栈.
            // 需要向expression的表达式的index后再看一位,如果是数就进行扫描,如果是符号才入栈

            else{
                keepNum+= ch;

                // 如果ch已经是最后一位,不需要判断
                if(index == expression.length() -1) {
                    numStack.push(Integer.parseInt(keepNum));
                }
                //判断下一位是不是数字,如果是数字,则扫描.
                else {
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum ="";
                    }
                }

            }
            // 让index + 1, 并判断是否到字符串最后
            index ++;
            if(index >= expression.length()){
                break;
            }
        }
       // 4.当表达式扫描完毕后,就顺序的从数栈和符号栈中pop出相应的数和符号,并运行
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            else{
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = operStack.pop();
                res = numStack.cal(num1,num2,oper);
                numStack.push(res);
            }
        }
        // 讲数栈的最后一个数pop出来就是结果
        System.out.println(numStack.pop());
    }
}

class ArrayStack2 {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组，数组模拟栈, 数据就放在该数组
    private int top = -1; // top表示栈顶，初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];

    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈-push
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈 pop,将栈顶的数据返回
    public int pop() {
        // 先判断栈是否空
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int temp = top;
        top--;
        return stack[temp];
    }

    // 看栈顶的字符
    public int peek(){
        return stack[top];
    }

    //显示栈的情况[遍历栈], 遍历时, 需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }

        // 需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级,数字越大则优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; // 假定目前表达式只有 + - * /
        }
    }

    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return (val == '+' || val == '-' || val == '*' || val == '/');
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // res用于存放计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}