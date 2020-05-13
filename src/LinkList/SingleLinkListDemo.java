package LinkList;

import javax.swing.*;
import java.util.Stack;

public class SingleLinkListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(3,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(5,"吴用","智多星");
        HeroNode hero4 = new HeroNode(7 ,"林冲","豹子头");

        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.list();

        //修改节点
//        HeroNode newHero2 = new HeroNode(2,"卢俊义","玉麒麟~~");
//        singleLinkedList.update(newHero2);
//        singleLinkedList.list();

        //删除节点
//        singleLinkedList.delete(1);
//        singleLinkedList.list();

//        System.out.println("测试单链表翻转");
//
//        HeroNode pre = reverseLinkList(singleLinkedList.getHead());
//        SingleLinkedList reversedLinklist = new SingleLinkedList();
//        reversedLinklist.add(pre);
//        reversedLinklist.list();
//
        System.out.println("测试单链表反打");
        reversPrint(singleLinkedList.getHead());

        System.out.println("测试两个单链表合并");
        System.out.println("链表1");
        singleLinkedList.list();
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        HeroNode hero5 = new HeroNode(2,"宋江1","及时雨");
        HeroNode hero6 = new HeroNode(4,"卢俊义2","玉麒麟");
        HeroNode hero7 = new HeroNode(6,"吴用2","智多星");
        HeroNode hero8 = new HeroNode(8 ,"林冲2","豹子头");
        singleLinkedList1.add(hero5);
        singleLinkedList1.add(hero6);
        singleLinkedList1.add(hero7);
        singleLinkedList1.add(hero8);
        System.out.println("链表2");
        singleLinkedList1.list();


        HeroNode h =  mergeTwoLinkedList(singleLinkedList.getHead().next,singleLinkedList1.getHead().next);

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.add(h);
        System.out.println("合并后");
        singleLinkedList2.list();



    }

    /***
     *
     * @param head 链表的头结点
     * @return 返回的就是有效节点的个数
     */
    // 方法：获取到单链表的节点的个数（如果是带头结点的链表，需求不统计头结点)
    public static int getLength(HeroNode head){
        if(head.next == null) return 0;
        int length = 0;

        //定义一个辅助变量
        HeroNode cur = head.next;
        while(cur != null){
            length ++;
            cur = cur.next;
        }
        return length;

    }

    // 查找单链表中的倒数第k个节点 【新浪面试题】
    // 思路
    // 1. 编写一个方法，接收head节点，同时接收一个index
    // 2.index是表示倒数第index个节点
    // 3.从头到尾遍历，得到长度
    // 4. 得到size之后，我们从链表的第一个开始遍历(size - index)个，就可以得到
    // 5. 如果找到了，返回，否则null
    public static HeroNode findLastKthElement(HeroNode head,int index){
        if(head.next == null) return null;

        HeroNode cur = head.next; //辅助节点
        int size = getLength(head);
        if(index <= 0 || index > size) return  null;


        for(int i = 0 ;i< size - index;i++){
            cur = cur.next;
        }
        return cur;

    }

    //链表的翻转
    public static HeroNode reverseLinkList(HeroNode head){
        // 带头结点链表,所以有只有一个节点要next两次
        // 如果当前链表为空，或者只有一个节点，直接返回
        if(head.next == null || head.next.next == null) return null;

        HeroNode pre = null;
        HeroNode cur = head.next;

        // 新的头结点


        while(cur != null){
            HeroNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;

        }
        return pre;


    }

    //列表的反向打印
    // 使用栈，先进后出特点，先遍历入栈，然后依次打印出栈即可
    public static void reversPrint(HeroNode head){
        if(head.next == null) return ;

        //创建一个栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while(cur!= null){
            stack.push(cur);
            cur = cur.next;
        }
        while(stack.size() > 0){
            System.out.println(stack.pop().toString());
        }

    }

    //两个单链表合并后依然有序
    public static HeroNode mergeTwoLinkedList(HeroNode head1,HeroNode head2)
    {
        // 如果第一个链表为空，返回第二个链表的头结点
        if (head1 == null){
            return head2;
        }
        if(head2 == null){
            return  head1;
        }
        HeroNode root = new HeroNode(0,"",":");
        HeroNode cur = root;

        while(head1 != null && head2 != null){
            if(head1.no < head2.no){
                cur.next = head1;
                head1 = head1.next;
            }
            else{
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;

        }
        // 如果列表1完了，返回列表二剩下的
        if(head1 != null){
            cur.next = head1;
        }
        if(head2 != null){
            cur.next = head2;
        }
        return root.next;

    }


}

// 定义一个SingleLinkList 来管理我们的英雄

class SingleLinkedList{

    //初始化一个头节点进行遍历
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }



    // 添加节点
    // 思路,当不考虑编号的顺序时
    // 1.找到当前链表的最后一个节点
    // 2. 将最后这个节点的next 指向新的节点
    public void add(HeroNode heroNode){
        // 因为head节点不能动,所以需要一个辅助接点
        HeroNode temp = head;

        //遍历链表,找到最后
        while(true){
            // 找到链表的最后
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        // 到了最后一个节点
        temp.next = heroNode;

    }

    // 按顺序添加节点
    // 1.找到目标节点的前一个节点
    // 2. 将新节点的next指向当前节点的next
    // 3. 将当前节点设置成新节点
    // 4. 如果失败了,返回错误
    public void addByOrder(HeroNode heroNode){
        // 因为头节点不能动,因此我们仍然通过一个辅助指针(变量)来
        // 因为单链表,因为我们找的temp是位于添加为之前的一个节点,否则插入不了
        HeroNode temp = head;
        boolean flag = false; // 标志添加的编号是否存在,默认为false
        while(true){
            if(temp.next == null){// 说明temp已经在链表的最后
                break;
            }
            if(temp.next.no > heroNode.no){ // 位置找到,就在temp的后面插入
                break;
            }
            else if (temp.next.no == heroNode.no){ //说明希望添加的heroNode的编号已存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断flag的值
        if(flag){
            System.out.printf("准备插入的英雄的编号%d已经存在了,不能加入\n", heroNode.no);
        }
        else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //修改节点的信息，根据no编号修改，即no编号不能修改
    //1. 根据 newHerorNode的 no来修改即可
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        //找到需要修改的节点，根据no 编号
        // 定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;

        while (true){
            if (temp == null) break;

            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }
        else{
            System.out.printf("没有找到编号为%d 的节点，不能修改\n",newHeroNode.no);
        }
    }

    //删除节点
    // 1. head 不能动，因此我们需要一个temp辅助节点找到待删除节点前的一个节点
    // 2. 说明我们在比较时，是temp.next.no 和需要删除的节点no 进行比较
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false; // 是否找到需要删除的节点
        while(true){
            if (temp.next == null){
                break;
            }
            if(temp.next.no == no){ //找到了待删除节点前面的一个节点
                flag = true;
                break;
            }
            temp  = temp.next;
        }
        if(flag == true){
            temp.next = temp.next.next;

        }else{
            System.out.printf("要删除的节点%d 不存在",no);
        }
    }


    // 遍历
    public void list(){
        //
        if (head == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if(temp == null) break;
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
