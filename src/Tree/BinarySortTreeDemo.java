package Tree;

public class BinarySortTreeDemo {

/**
 *               7
 *             /   \
 *            3     10
 *          /  \   /  \
 *         1   5  9   12
 *          \
 *           2
 */
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinaryTree2 binaryTree2 = new BinaryTree2();
        for (int i = 0; i < arr.length; i++) {
            binaryTree2.add(new Node(arr[i]));
        }

        System.out.println("中序遍历二叉树");
        binaryTree2.infixOrder(); //

        binaryTree2.deleteNode(2);
        System.out.println("删除节点后");
        binaryTree2.infixOrder(); //
    }

}


// 创建二叉排序树
class BinaryTree2 {
    private Node root;

    public Node getRoot() {
        return root;
    }

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

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    // 查找要删除节点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    public int deleteRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        // 已经指向最小节点
        deleteNode(target.value);
        // 返回最小节点对应的值
        return target.value;
    }

    // 删除节点
    public void deleteNode(int value) {
        if (root == null) {
            return;
        }

        // 1. 先找到要删除的节点
        Node targetNode = search(value);
        if (targetNode == null) {
            return;
        }
        // 如果只有一个节点，则只需要全树删除
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }

        // 去找到TargetNode 的父节点
        Node parent = searchParent(value);
        // 情况1：如果删除的节点是叶子节点
        if (targetNode.left == null && targetNode.right ==null) {
            // 判断targetNode是parentNode的左或者右子节点
            // 然后根据情况删除
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }

        } else if (targetNode.left != null && targetNode.right != null) {
            // 情况3: 删除有2颗子树的节点. 为了不破坏左节点比右节点小的规律,从最右边开始找最小或者从最左边开始找最大
            int min = deleteRightTreeMin(targetNode.right);
            targetNode.value = min;

        } else{
            //情况2: 删除只有一颗子树的节点
            // 如果要删除的节点有左子节点
            // 如果targetNode是parentNode的左节点
            if (targetNode.left != null) {
                // 大于一层的情况,正常逻辑
                if (parent != null) {
                    // 如果targetNode是parentNode的左节点
                    if (parent.left.value == value) {
                        // 把目标节点的子节点 替换 目标节点
                        parent.left = targetNode.left;
                    }

                    // 如果targetNode是parentNode的右子节点
                    else {
                        parent.right = targetNode.left;
                    }
                }
                // 只剩下一层的情况,特殊处理.直接把root指向子节点
                else {
                    root = targetNode.left;
                }
            }
            // 要删除的节点有 右子节点
            else {
                if (parent != null) {
                    if (parent.left.value == value) {
                        // 如果targetNode是parentNode的左节点
                        parent.left = targetNode.right;
                    } else {
                        // 如果targetNode是parentNode的右子节点
                        parent.right = targetNode.right;
                    }
                }
                else{
                    root = targetNode.right;
                }
            }

        }


    }


}

class Node {
    int value;
    Node left;
    Node right;

    // 查找要删除的节点

    /***
     *
     * @param value 希望删除的节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value ) {
            if (this.left == null) {
                return  null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    // 查找要删除节点的父节点

    /***
     *
     * @param value 要找的节点的值
     * @return
     */

    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        }
        else
        {
            // 如果查找的这个值小于当前节点的这个值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); // 递归向左查找
            } else if (value >= value && this.right != null) {
                return this.right.searchParent(value);// 递归向右查找
            }
            return null;
        }


    }



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