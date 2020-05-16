package LinkList;


/**
 * Josephu问题为: 设编号1,2，...n的n个人围坐一圈，约定编号为k(1<=k<=n) 的人从1开始报数，数到m的那个人开始出列,
 * 它的下一位又从1开始报数，数到m的那个人又出列，以此类推，直到所有人出列为止，由此产生一个出队编号的序列
 *
 * n = 5 ，五个人
 * k = 1 ，从第一个人开始报数
 * m = 2 ， 数两下
 */

public class JosephuProblem {
    public static void main(String[] args) {

        //测试创建环形链表
        CircularSinglyLinkedList c = new CircularSinglyLinkedList();
        c.addBoy(5);
        c.showBoy();
        c.countBoy(1,2,5);


    }



}

/***
 * 构建一个单向的环形链表思路
 * 1.先创建第一个节点，让first指向该节点，并形成环形
 * 2.后面当我们每创建一个节点，就把新的节点加入到已有的环形链表即可
 *
 * 遍历环形链表
 * 1.先让一个辅助指针（变量） curBoy 指向first节点
 * 2.然后通过一个while遍历，直到curBoy.next = first即可
 *
 * 出圈
 * 1. 需求创建一个辅助指针helper,事先应该指向环形链表的最后一个节点
 * 2. 当小孩报数时, 让first和helper同时移动m - 1次
 * 3. 这就可以将first指向小孩的节点出圈
 *
 * first = first.next
 * helper.next = first
 */

class CircularSinglyLinkedList{
    //创建一个first节点
    private Boy first = null;
    //添加小孩的节点，构建成一个环形变量
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("number的值不正确");
            return;
        }

        Boy curBoy = null;
        //使用for循环来创建我们的环形列表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);

            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;
            } else {
                // 当前节点指向新的节点
                curBoy.setNext(boy);
                // 新的节点指向第一个节点，形成环形
                boy.setNext(first);
                // 移动
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy(){
        if(first ==null){
            System.out.println("当前链表为空");
            return;
        }
        Boy curBoy  = first;
        while(true)
        {
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /***
     *
     * @param 开始的位置
     * @param 数几下
     * @param 总共有几个小孩
     */
    public void countBoy(int start,int countNumber,int total){
        if(first == null || start < 1 || start > total){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        Boy helper = first;
        // 创建要给辅助指针，指向链表最尾巴( first 前一个)
        while(true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        // 移动到起始位置
        for(int i=0;i<start -1;i++){
            helper = helper.getNext();
            first = first.getNext();
        }

        while(helper != first){
            // 数数
            for(int i=0;i<countNumber - 1;i++){
                helper = helper.getNext();
                first = first.getNext();
            }
            System.out.printf("%d号小孩出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);

        }
        // 打印最后一个小孩
        System.out.printf("%d号小孩最后出圈\n",first.getNo());


    }


}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no){
        this.no = no;
    };

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }


}