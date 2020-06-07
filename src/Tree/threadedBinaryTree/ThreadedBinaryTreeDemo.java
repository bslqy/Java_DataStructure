package Tree.threadedBinaryTree;

public  class ThreadedBinaryTreeDemo{
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
//二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
//测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadNodes();

        //测试: 以10 号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10 号结点的前驱结点是=" + leftNode); //3
        System.out.println("10 号结点的后继结点是=" + rightNode); //1
        //当线索化二叉树后，能在使用原来的遍历方法
        //threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历线索化二叉树");
        threadedBinaryTree.threadList(); // 8, 3, 10, 1, 14, 6

    }
}

 class ThreadedBinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    public void threadNodes(){
        this.threadNodes(root);
    }

    // 在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        }
        else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        }
        else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public HeroNode preOrderSerach(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        }
        return null;
    }

    public HeroNode infixOrderSerach(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        }
        return null;
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        }
        return null;
    }

    // 编写二叉树进行中序线索化的方法

    /***
     *
     * @param node就是当前需要线索化的节点
     */
    public void threadNodes(HeroNode node) {
        if (node == null) {
            return;
        }

        // 1. 先线索化左子树
        threadNodes(node.getLeft());
        // 2. 线索化当前节点
        if (node.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            // 修改当前节点左指针的类型
            node.setLeftType(1);
        }

        // 处理后继节点
        if (pre!= null && pre.getRight() == null) {
            // 让前驱节点的右指针指向当前节点
            pre.setRight(node);
            // 修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        // 重要! 每处理一个节点后,让当前节点作为下一个节点的前驱节点
        pre = node;

        // 3. 再线索化右子树
        threadNodes(node.getRight());


    }

    // 编写二叉树中线索化的方法
     public void threadList(){
        // 定义一个变量,存储当前遍历的节点,从root开始
         HeroNode node = root;
         while (node != null) {
             // 循环找到leftType == 1 的节点,第一个找到就是8节点
             // 后面随着遍历而变化,因为当leftType == 1时,索命该节点是按照线索化
             // 处理后的有效节点
             while (root.getLeftType() == 0) {
                 node = node.getLeft();
             }

             // 打印当前这个节点
             System.out.println(node);
             //如果当前节点的右指针指向的是后继节点,就一直输出
             while (node.getRightType() == 1) {
                 node = node.getRight();
                 System.out.println(node);
             }
             node = node.getRight();
         }
     }

    public void delNode(int no) {
        // 首先判断root是否为空
        if (root != null) {
            // 如果只有一个root节点,判断root是否是要删除的节点
            if (root.getNo() == no) {
                root = null;
                return;
            }
            // 否则,开始递归删除
            root.delNode(no);
        }
        else {
            System.out.println("空树,不能删除");
        }
    }
}






class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //说明
    //1. 如果leftType == 0 表示指向的是左子树, 如果1 则表示指向前驱结点
    //2. 如果rightType == 0 表示指向是右子树, 如果1 表示指向后继结点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this); // 先输出父节点

        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }

    }

    // 中序遍历
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }

        // 输出父节点
        System.out.println(this);

        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }

    }

    // 后序遍历

    public void postOrder() {


        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }

        // 输出父节点
        System.out.println(this);

    }

        /// 递归删除节点
    // 1.如果删除的节点是叶子节点,则删除改节点
    // 2.如果删除的节点是非叶子节点,则删除改子树

    public void delNode(int no) {
        /***
         * 1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
         * 2. 如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left = null; 并且就返回 (结束递归删除)
         * 3. 如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right= null ;并且就返回 (结束递归删除)
         * 4. 如果第2 和第3 步没有删除结点，那么我们就需要向左子树进行递归删除
         * 5. 如果第4 步也没有删除结点，则应当向右子树进行递归删除.
         */



        // 2. 如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left = null; 并且就返回 (结束递归删除)
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return ;
        }
        // 3. 如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right= null ;并且就返回 (结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 4. 如果第2 和第3 步没有删除结点，那么我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        // 5. 如果第4 步也没有删除结点，则应当向右子树进行递归删除.
        if (this.right != null) {
            this.right.delNode(no);
        }

    }

    // 前序查找
    public HeroNode preOrderSearch(int no) {
        HeroNode resNode = null;

        // 先从父节点查找
        if (this.no == no) {
            return  this;
        }

        // 再从左节点查找
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }

        // 如果左节点找到，则返回
        if (resNode != null) {
            return resNode;
        }

        // 否则继续父节点的子节点查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        return resNode;

    }

    // 中序查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;

        // 先从左节点查找
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }

        // 如果左节点找到，则返回
        if (resNode != null) {
            return resNode;
        }

        // 再从父节点查找
        if (this.no == no) {
            return  this;
        }

        // 否则继续父节点的子节点查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }

        return resNode;

    }

    // 后序查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;

        // 先从左节点查找
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }

        // 如果左节点找到，则返回
        if (resNode != null) {
            return resNode;
        }

        // 再从右节点查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        // 再从父节点查找
        if (this.no == no) {
            return  this;
        }

        return resNode;

    }



    @Override
    public String toString() {
        return "HeroNode[" + "no=" + no + ", name='" + name +  ']';
    }

}