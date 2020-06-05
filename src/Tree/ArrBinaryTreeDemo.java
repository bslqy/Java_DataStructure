package Tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder(0);
    }
}

// 编写一个ArrayBinaryTree, 实现顺序存储二叉树遍历
class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 编写一个方法,完成顺序存储二叉树的前序遍历

    public void preOrder(int index) {
        // 如果数组为空,或者arr.length = 0
        if (arr.length == 0 || arr == null) {
            System.out.println("数组为空,不能安装二叉树的前序遍历");
        }

        System.out.println(arr[index]);
        // 向左递归遍历
        if (index * 2 + 1 < arr.length) {
            preOrder(2 * index + 1);
        }

        if (index * 2 + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}
