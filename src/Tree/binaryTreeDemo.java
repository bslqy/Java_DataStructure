package Tree;



public class binaryTreeDemo {

    /**
     *
     *               1
     *             /  \
     *            2     3
     *                /  \
     *               5    4
     *                \
     *                 7
     *
     * 前序遍历: 先输出父节点，再遍历左子树和右子树
     * 中序遍历: 先遍历左子树，再输出父节点，再遍历右子树
     * 后序遍历: 先遍历左子树，再遍历右子树，最后输出父节点
     * 小结: 看输出父节点的顺序，就确定是前序，中序还是后序
     */

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3= new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");
        HeroNode node6 = new HeroNode(6,"鲁智深");
        HeroNode node7 = new HeroNode(7,"武松");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
//        node5.setLeft(node6);
        node5.setRight(node7);
        binaryTree.setRoot(root);

        // 测试
        System.out.println("前序遍历");
        binaryTree.preOrder(); // 1,2,3,5,4


        binaryTree.delNode(5);
        System.out.println("删除后");
        binaryTree.preOrder();

//        System.out.println("中序遍历");
//        binaryTree.infixOrder();; // 2,1,5,3,4
//
//        System.out.println("后序遍历");
//        binaryTree.postOrder(); // 2,5,4,3,1


    }
}
    class BinaryTree{
        private HeroNode root;

        public void setRoot(HeroNode root) {
            this.root = root;
        }

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

        public HeroNode(int no, String name) {
            this.no = no;
            this.name = name;
        }

        public int getNo() {
            return no;
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

        @Override
        public String toString() {
            return "HeroNode[" + "no=" + no + ", name='" + name +  ']';
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

        // 递归删除节点
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

                if(this.left.left == null && this.left.right !=null){
                    this.left.setName(this.left.right.getName());
                    this.left.setNo(this.left.right.getNo());
                    this.left.right = null;
                    return;
                }

//                this.left = null;

                if(this.left.left != null && this.left.right ==null){
                    this.left.setName(this.left.left.getName());
                    this.left.setNo(this.left.left.getNo());
                    this.left.left = null;
                    return;
                }

                if (this.left.left != null && this.left.right !=null){
                    this.left.setName(this.left.left.getName());
                    this.left.setNo(this.left.left.getNo());
                    this.left.left = null;
                    return;
                }

                // 是叶子节点
                this.left = null;
                return ;


            }
            // 3. 如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right= null ;并且就返回 (结束递归删除)
            if (this.right != null && this.right.no == no) {

                if (this.right.left != null && this.right.right !=null){
                    this.right.setName(this.right.left.getName());
                    this.right.setNo(this.right.left.getNo());
                    this.right.left = null;
                    return;
                }

                if(this.right.left == null && this.right.right !=null){
                    this.right.setName(this.right.right.getName());
                    this.right.setNo(this.right.right.getNo());
                    this.right.right = null;
                    return;
                }

//                this.left = null;

                if(this.right.left != null && this.right.right ==null){
                    this.right.setName(this.right.left.getName());
                    this.right.setNo(this.right.left.getNo());
                    this.right.left = null;
                    return;
                }



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


            return ;

        }
    }



