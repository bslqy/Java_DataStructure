package HashTable;

import javax.sound.midi.Soundbank;
import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {

        Employee e1 = new Employee(1, "a");
        Employee e2 = new Employee(2, "b");
        HashTable hashTable = new HashTable(7);
        //
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();

            switch (key) {
                case "add":
                    System.out.println("输入ID");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();

                    Employee employee = new Employee(id, name);
                    hashTable.add(employee);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                case "find":
                    System.out.println("请输入要查找的id");
                    int eid = scanner.nextInt();
                    hashTable.findEmployeeByID(eid);
                    break;
                default:
                    break;
            }

        }


    }
}


class HashTable {
    private EmpLinkedList[] empLinkedListsArray;
    private int size;

    public HashTable(int size) {
        this.size = size;
        empLinkedListsArray = new EmpLinkedList[size];
        // 分别初始化每一个链表!!!很重要
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i] = new EmpLinkedList();
        }

    }

    // 添加雇员
    public void add(Employee emp) {
        // 根据id判断应该添加到哪一条链表
        int empLinkedListNo = hashFunction(emp.id);
        empLinkedListsArray[empLinkedListNo].add(emp);
    }

    // 遍历哈希表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i].list(i);
        }
    }

    // 根据id查找雇员
    public void findEmployeeByID(int id) {
        // 使用散列函数确定到链表
        int employeeListNo = hashFunction(id);
        Employee e = empLinkedListsArray[employeeListNo].findEmployeeByID(id);
        if (e != null) {
            System.out.printf("在第%d条链表中找到雇员, 雇员id = %d\n",employeeListNo,id);
        } else {
            System.out.printf("没有找到该雇员");
        }
    }

    // 编写一个散列函数,使用一个取模法
    public int hashFunction(int id){
        return id % size;
    }
}


class Employee{
    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class EmpLinkedList{
    // 头指针,执行第一个Emp,因此我们这个链表的head是直接指向第一个Emp
    private Employee head; // 默认null

    //添加雇佣到链表

    // 默认加在最后
    public void add(Employee e){
        // 添加第一个
        if (head == null) {
            head = e;
            return;
        }
        // 如非第一个
        Employee helper = head;
        while(helper.next != null){
            helper = helper.next;
        }
        // 已经到最后
        helper.next = e;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if (head == null) {
            System.out.println("第" + no +" 条链表为空");
            return;
        }
        System.out.printf("第%d条链表信息为\n",no);
        Employee helper = head;
        while (helper != null) {
            System.out.printf("雇员的姓名是%s,编号是%d\t",helper.getName(),helper.getId());
            helper = helper.next;
        }
        System.out.println();
    }

    // 根据ID查找雇员
    public Employee findEmployeeByID(int id) {
        if (head == null) {
            System.out.println("链表空");
        }

        Employee helper = head;
        while (helper != null) {
            if (helper.id == id) {
                return helper;
            }
            helper = helper.next;
        }
        return null;
    }


}
