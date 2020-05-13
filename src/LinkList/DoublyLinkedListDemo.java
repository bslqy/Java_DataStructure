package LinkList;

/***
 *  单向链表缺点分析：
 *      1.查找只能是一个方向，而双向链表可以向前或向后查找
 *      2. 单向链表不能自我删除，需要靠辅助节点。而双向链表可以自我删除。注意最后一个节点的删除指向pre时候需要小心空指针.
 */

public class DoublyLinkedListDemo {



    public static void main(String[] args) {
        System.out.println("双向链表测试");

        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(3,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(5,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(7 ,"林冲","豹子头");


        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.add(hero1);
        doublyLinkedList.add(hero2);
        doublyLinkedList.add(hero3);
        doublyLinkedList.add(hero4);

        doublyLinkedList.list();
        // 修改
        System.out.println("修改双向链表测试");
        doublyLinkedList.update(new HeroNode2(7,"公孙胜","入云龙"));
        doublyLinkedList.list();



        // 删除
        System.out.println("删除双向链表测试");
        doublyLinkedList.delete(5);
        doublyLinkedList.list();

        //按顺序添加
        System.out.println("双向链表按排序测试");
        DoublyLinkedList doublyLinkedList2 = new DoublyLinkedList();
        doublyLinkedList2.addByOrder(hero2);
        doublyLinkedList2.addByOrder(hero1);
        doublyLinkedList2.addByOrder(hero4);
        doublyLinkedList2.addByOrder(hero3);
        doublyLinkedList2.list();
    }

}

class DoublyLinkedList{
//先初始化一个头结点，头结点不存放具体的数据
private HeroNode2 head = new HeroNode2(0,"","");

//返回头结点
public HeroNode2 getHead(){
        return head;
        }

// 遍历
public void list(){
        //
        if (head == null){
        System.out.println("链表为空");
        return;
        }
        HeroNode2 temp = head.next;
        while (true){
        if(temp == null) break;
        System.out.println(temp);
        temp = temp.next;
        }
        }

// 添加节点
// 思路,当不考虑编号的顺序时
// 1.找到当前链表的最后一个节点
// 2. 将最后这个节点的next 指向新的节点
// 3.将最后这个节点的pre 指向前一个节点
public void add(HeroNode2 heroNode){
        // 因为head节点不能动,所以需要一个辅助接点
        HeroNode2 temp = head;

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
        heroNode.pre = temp;

}

    public void addByOrder(HeroNode2 heroNode){
        // 因为head节点不能动,所以需要一个辅助接点
        HeroNode2 temp = head;
        boolean flag = false; // 标志添加的编号是否存在,默认为false

        //遍历链表
        while(true){
            // 说明temp已经在链表的最后
            if(temp.next == null){
                break;
            }
            // 找到了目标位置的前一个位置
            if(temp.next.no > heroNode.no){
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
            heroNode.pre = temp;
            // 最后地方插入时候不需要执行这句话
            if(temp.next != null){
                temp.next.pre = heroNode;
            }

            temp.next = heroNode;
        }

    }


//修改节点的信息，根据no编号修改，即no编号不能修改
//1. 根据 newHerorNode的 no来修改即可
public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if(head.next == null){
        System.out.println("链表为空");
        return;
        }

        //找到需要修改的节点，根据no 编号
        // 定义一个辅助变量
        HeroNode2 temp = head.next;
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
// 2. 可以直接找到需要时删除的节点，然后自我删除
// 3. 当前节点的下一个节点pre指向当前节点的pre
// 4.
public void delete(int no){
        HeroNode2 temp  = head;
        // 是否找到代号为no 的节点
        boolean flag = false;

        //空链表
        if(temp.next == null){
        System.out.println("空链表，无法删除");
        return ;
        }

        while(true){
        if (temp.next == null) break;
        if (temp.no == no){
        flag = true;
        break;
        }
        temp = temp.next;
        }

        if(flag){
        temp.pre.next = temp.next;
        //最后节点会出问题,必须不能为最后节点。否则出现空指针异常 null.pre = temp.pre
        if(temp.next != null) {
        temp.next.pre = temp.pre;
        }
        }
        else{
        System.out.printf("需要删除的节点%d 不存在\n",no);
        }
        }
}

class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}