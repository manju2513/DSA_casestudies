class AVLNode {
    int fileID;
    int height;
    AVLNode left, right;
    AVLNode(int fileID) {
        this.fileID = fileID;
        this.height = 1;
    }
}
class AVLTree {
    AVLNode root;
    int height(AVLNode node) {
        if (node == null)
            return 0;
        return node.height;
    }
    int getBalance(AVLNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }
    AVLNode insert(AVLNode node, int fileID) {
        if (node == null)
            return new AVLNode(fileID);
        if (fileID < node.fileID)
            node.left = insert(node.left, fileID);
        else if (fileID > node.fileID)
            node.right = insert(node.right, fileID);
        else
            return node; 
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && fileID < node.left.fileID)
            return rightRotate(node);
        if (balance < -1 && fileID > node.right.fileID)
            return leftRotate(node);
        if (balance > 1 && fileID > node.left.fileID) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && fileID < node.right.fileID) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }
    AVLNode delete(AVLNode root, int fileID) {
        if (root == null)
            return root;
        if (fileID < root.fileID)
            root.left = delete(root.left, fileID);
        else if (fileID > root.fileID)
            root.right = delete(root.right, fileID);
        else {
            if ((root.left == null) || (root.right == null)) {
                AVLNode temp = null;
                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;
                if (temp == null) {
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                AVLNode temp = minValueNode(root.right);
                root.fileID = temp.fileID;
                root.right = delete(root.right, temp.fileID);
            }
        }
        if (root == null)
            return root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    boolean search(AVLNode root, int fileID) {
        if (root == null)
            return false;
        if (root.fileID == fileID)
            return true;
        if (fileID < root.fileID)
            return search(root.left, fileID);
        return search(root.right, fileID);
    }
    void inorder(AVLNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.fileID + " ");
            inorder(root.right);
        }
    }
}
public class FileIndexingSystem {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.root = tree.insert(tree.root, 105);
        tree.root = tree.insert(tree.root, 102);
        tree.root = tree.insert(tree.root, 110);
        tree.root = tree.insert(tree.root, 101);
        tree.root = tree.insert(tree.root, 103);
        tree.root = tree.insert(tree.root, 108);
        tree.root = tree.insert(tree.root, 115);
        System.out.println("File Index (Inorder Traversal):");
        tree.inorder(tree.root);
        int searchID = 108;
        if (tree.search(tree.root, searchID))
            System.out.println("\nFile ID " + searchID + " found.");
        else
            System.out.println("\nFile ID " + searchID + " not found.");
        tree.root = tree.delete(tree.root, 103);
        System.out.println("\nFile Index after deleting File ID 103:");
        tree.inorder(tree.root);
    }
}