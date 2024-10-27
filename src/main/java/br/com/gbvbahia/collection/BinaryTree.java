package br.com.gbvbahia.collection;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.logging.Logger;

//https://www.youtube.com/watch?v=H5JubkIy_p8&t=237s
/* Max number of nodes at level = 2 ˆ level, l1 = 2^1=2, l2 = 2ˆ2=4 l3 = 2ˆ3=8 ... */
//https://www.youtube.com/watch?v=gcULXE7ViZw&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P&index=42
@Data
@NoArgsConstructor
public class BinaryTree<T extends BinaryTreeValue<T>> {

    private final Logger log = Logger.getLogger(getClass().getName());

    private BinaryTree<T> left;
    private BinaryTree<T> right;
    private T value;

    public BinaryTree(T value) {
        super();
        this.value = value;
    }

    public boolean hasValue() {
        return value != null;
    }

    public boolean hasLeftValue() {
        return left != null && left.value != null;
    }

    public boolean hasRightValue() {
        return right != null && right.value != null;
    }

    public BinaryTree<T> insert(T val) {
        if (value == null) {
            this.value = val;
            return this;
        }

        if (val.compareTo(this.value) < 0) {
            if (left == null) left = new BinaryTree<>(val);
            else left = left.insert(val);
        } else {
            if (right == null) right = new BinaryTree<>(val);
            else right = right.insert(val);
        }

        return balance();
    }

    public int calculateHeight() {
        if (value == null) {
            return 0;
        }
        int leftHeight = (left != null) ? left.calculateHeight() : 0;
        int rightHeight = (right != null) ? right.calculateHeight() : 0;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private BinaryTree<T> balance() {
        int balanceFactor = getBalanceFactor();

        if (balanceFactor > 1) {
            if (left != null && left.getBalanceFactor() >= 0) {
                return rotateRight();
            } else if (left != null) {
                left = left.rotateLeft();
                return rotateRight();
            }
        } else if (balanceFactor < -1) {
            if (right != null && right.getBalanceFactor() <= 0) {
                return rotateLeft();
            } else if (right != null) {
                right = right.rotateRight();
                return rotateLeft();
            }
        }
        return this;
    }

    private int getBalanceFactor() {
        int leftHeight = (left != null) ? left.calculateHeight() : 0;
        int rightHeight = (right != null) ? right.calculateHeight() : 0;
        return leftHeight - rightHeight;
    }

    private BinaryTree<T> rotateRight() {
        BinaryTree<T> newRoot = left;
        left = newRoot.right;
        newRoot.right = this;
        return newRoot;
    }

    private BinaryTree<T> rotateLeft() {
        BinaryTree<T> newRoot = right;
        right = newRoot.left;
        newRoot.left = this;
        return newRoot;
    }

    public Optional<T> search(T toSearch) {
        if (!hasValue()) {
            return Optional.empty();
        }

        int compare = this.value.compareTo(toSearch);

        if (compare == 0) {
            return Optional.of(value);
        }

        if (compare > 0) {
            return getLeft().search(toSearch);
        }
        return getRight().search(toSearch);
    }

    public BinaryTree<T> getRight() {
        if (right == null) {
            right = new BinaryTree<>();
        }
        return right;
    }

    public BinaryTree<T> getLeft() {
        if (left == null) {
            left = new BinaryTree<>();
        }
        return left;
    }

    //http://www.webgraphviz.com/
    public String toString() {
        StringBuilder builder = new StringBuilder("http://www.webgraphviz.com/");
        builder.append("\ndigraph BinaryTree {\n");
        builder.append("node [fontname=\"Arial\"];\n");

        if (!hasValue()) {
            builder.append("\n");
        } else {
            toDotRecursive(this, builder);
        }
        builder.append("}\n");
        return builder.toString();
    }

    private void toDotRecursive(BinaryTree<T> node, StringBuilder builder) {
        if (node.left != null && node.left.hasValue()) {
            builder.append("    \"" + node.value + "\" -> \"" + node.left.value + "\";\n");
            toDotRecursive(node.left, builder);
        }
        if (node.right != null && node.right.hasValue()) {
            builder.append("    \"" + node.value + "\" -> \"" + node.right.value + "\";\n");
            toDotRecursive(node.right, builder);
        }
    }

    public String breadthFirst() {
        LinkedList<T> queue = new LinkedList<>();
        queue.add(this.value);
        breadthFirstQueue(this, queue);
        return breadthFirstString(queue);
    }

    private void breadthFirstQueue(BinaryTree<T> root, Queue<T> queue) {
        if (root != null && root.hasValue()) {
            queue.add(root.getLeft().value);
            queue.add(root.getRight().value);
            breadthFirstQueue(root.getLeft(), queue);
            breadthFirstQueue(root.getRight(), queue);
        }
    }

    private String breadthFirstString(Queue<T> queue) {
        StringBuilder builder = new StringBuilder("\n");
        while (!queue.isEmpty()) {
            T poll = queue.poll();
            if (poll != null) {
                builder.append(poll);
            }
        }
        return builder.toString();
    }
}
