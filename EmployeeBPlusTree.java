import java.util.ArrayList;
class BPlusTreeNode {
    boolean isLeaf;
    ArrayList<Integer> keys;
    ArrayList<BPlusTreeNode> children;
    BPlusTreeNode next;
    BPlusTreeNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        keys = new ArrayList<>();
        children = new ArrayList<>();
        next = null;
    }
}
class BPlusTree {
    BPlusTreeNode root;
    int order = 3; // Maximum 2 keys
    public BPlusTree() {
        root = new BPlusTreeNode(true);
    }
    void insert(int key) {
        BPlusTreeNode current = root;
        while (!current.isLeaf) {
            int i = 0;
            while (i < current.keys.size() && key >= current.keys.get(i))
                i++;
            current = current.children.get(i);
        }
        int pos = 0;
        while (pos < current.keys.size() && key > current.keys.get(pos))
            pos++;
        current.keys.add(pos, key);
        if (current.keys.size() > order - 1)
            splitLeaf(current);
    }
    void splitLeaf(BPlusTreeNode leaf) {
        int mid = leaf.keys.size() / 2;
        BPlusTreeNode newLeaf = new BPlusTreeNode(true);
        while (leaf.keys.size() > mid) {
            newLeaf.keys.add(leaf.keys.remove(mid));
        }
        newLeaf.next = leaf.next;
        leaf.next = newLeaf;
        BPlusTreeNode newRoot = new BPlusTreeNode(false);
        newRoot.keys.add(newLeaf.keys.get(0));
        newRoot.children.add(leaf);
        newRoot.children.add(newLeaf);
        root = newRoot;
    }
    boolean search(int key) {
        BPlusTreeNode current = root;
        while (!current.isLeaf) {
            int i = 0;
            while (i < current.keys.size() && key >= current.keys.get(i))
                i++;
            current = current.children.get(i);
        }
        return current.keys.contains(key);
    }
    void rangeSearch(int start, int end) {
        BPlusTreeNode current = root;
        while (!current.isLeaf) {
            current = current.children.get(0);
        }
        System.out.println("Employees between "
                + start + " and " + end + ":");
        while (current != null) {
            for (int key : current.keys) {
                if (key >= start && key <= end)
                    System.out.print(key + " ");
            }
            current = current.next;
        }
        System.out.println();
    }
    void displayAll() {
        BPlusTreeNode current = root;
        while (!current.isLeaf)
            current = current.children.get(0);
        System.out.println("\nEmployee IDs in Sorted Order:");
        while (current != null) {
            for (int key : current.keys)
                System.out.print(key + " ");
            current = current.next;
        }
        System.out.println();
    }
}
public class EmployeeBPlusTree {
    public static void main(String[] args) {
        BPlusTree tree = new BPlusTree();
        System.out.println("=== Inserting Employee Records ===");
        tree.insert(1000);
        tree.insert(2000);
        tree.insert(3000);
        tree.insert(4000);
        tree.insert(5000);
        tree.insert(6000);
        tree.insert(7000);
        tree.displayAll();
        System.out.println("\n=== Searching Employee ID 4000 ===");
        if (tree.search(4000))
            System.out.println("Employee ID 4000 Found");
        else
            System.out.println("Employee ID 4000 Not Found");
        System.out.println("\n=== Searching Employee ID 9000 ===");
        if (tree.search(9000))
            System.out.println("Employee ID 9000 Found");
        else
            System.out.println("Employee ID 9000 Not Found");
        System.out.println();
        tree.rangeSearch(1000, 5000);
    }
}