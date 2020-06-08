package Tree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9};
        BinaryTree2 binaryTree2 = new BinaryTree2();
        for (int i = 0; i < arr.length; i++) {
            binaryTree2.add(new Node(arr[i]));
        }

        binaryTree2.infixOrder(); //
    }

}


// 创建二叉排序树
class BinaryTree2 {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空,无法遍历");
        }

    }


}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加节点的方式
    //以递归形式添加节点,注意需要满足二叉树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }

        // 待插入元素比当前元素小,则首先把node放置在左节点. 如果左节点不为空,则递归放置
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        }
        // 待插入元素比当前元素大,则首先把node放置在右节点. 如果右节点不为空,则递归放置
        else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}