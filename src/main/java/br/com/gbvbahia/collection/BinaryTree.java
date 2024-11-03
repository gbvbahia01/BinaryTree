package br.com.gbvbahia.collection;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    protected BinaryTree(T value) {
        super();
        this.value = value;
    }

    protected boolean isNotEmpty() {
        return value != null;
    }

    protected boolean isEmpty() {
        return value == null;
    }

    protected boolean hasLeftValue() {
        return left != null && left.value != null;
    }

    protected boolean hasRightValue() {
        return right != null && right.value != null;
    }

    protected BinaryTree<T> insert(T val) {
        Objects.requireNonNull(val, "Cannot insert a null value");

        if (value == null) {
            this.value = val;
            return this;
        }

        int compare = val.compareTo(this.value);
        if (compare == 0) {
            // Value already exists; do not insert duplicate
            return this;
        } else if (compare < 0) {
            if (left == null) left = new BinaryTree<>(val);
            else left = left.insert(val);
        } else {
            if (right == null) right = new BinaryTree<>(val);
            else right = right.insert(val);
        }

        return balance();
    }

    protected int calculateHeight() {
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

    protected Optional<T> search(T toSearch) {
        if (isEmpty()) {
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

    protected Set<T> findAllGreaterThanOrEqualTo(T start) {
        Set<T> result = new TreeSet<>();
        findAllGreaterThanOrEqualToRecursive(this, start, result);
        return result;
    }

    private void findAllGreaterThanOrEqualToRecursive(BinaryTree<T> node, T start, Set<T> result) {
        if (node == null || !node.isNotEmpty()) {
            return;
        }

        // We always traverse the right subtree first
        findAllGreaterThanOrEqualToRecursive(node.right, start, result);

        int compare = node.value.compareTo(start);

        if (compare >= 0) {
            // Current node value is greater than or equal to 'start'
            // Add the current node value
            result.add(node.value);
            // We traverse the left subtree
            findAllGreaterThanOrEqualToRecursive(node.left, start, result);
        }
        // If 'compare < 0', we don't need to traverse the left subtree
    }


    protected Set<T> findAllLessThanOrEqualTo(T end) {
        Set<T> result = new TreeSet<>();
        findAllLessThanOrEqualToRecursive(this, end, result);
        return result;
    }

    private void findAllLessThanOrEqualToRecursive(BinaryTree<T> node, T end, Set<T> result) {
        if (node == null || !node.isNotEmpty()) {
            return;
        }

        // We always traverse the left subtree first
        findAllLessThanOrEqualToRecursive(node.left, end, result);

        int compare = node.value.compareTo(end);

        if (compare <= 0) {
            // Current node value is less than or equal to 'end'
            // Add the current node value
            result.add(node.value);
            // We traverse the right subtree
            findAllLessThanOrEqualToRecursive(node.right, end, result);
        }
        // If 'compare > 0', we don't need to traverse the right subtree
    }

    protected Set<T> findAllBetween(T start, T end) {
        Set<T> result = new TreeSet<>();
        findAllBetweenRecursive(this, start, end, result);
        return result;
    }

    private void findAllBetweenRecursive(BinaryTree<T> node, T start, T end, Set<T> result) {
        if (node == null || !node.isNotEmpty()) {
            return;
        }

        int compareStart = node.value.compareTo(start);
        int compareEnd = node.value.compareTo(end);

        if (compareStart >= 0 && compareEnd <= 0) {
            // Current node value is between 'start' and 'end'
            // Traverse left subtree, add current value, then right subtree
            findAllBetweenRecursive(node.left, start, end, result);
            result.add(node.value);
            findAllBetweenRecursive(node.right, start, end, result);
        } else if (compareEnd > 0) {
            // Current node value is greater than 'end', ignore right subtree
            findAllBetweenRecursive(node.left, start, end, result);
        } else {
            // Current node value is less than 'start', ignore left subtree
            findAllBetweenRecursive(node.right, start, end, result);
        }
    }

    protected BinaryTree<T> getRight() {
        if (right == null) {
            right = new BinaryTree<>();
        }
        return right;
    }

    protected void setRight(BinaryTree<T> right) {
        this.right = right;
    }

    protected BinaryTree<T> getLeft() {
        if (left == null) {
            left = new BinaryTree<>();
        }
        return left;
    }

    protected void setLeft(BinaryTree<T> left) {
        this.left = left;
    }

    /*
    When we remove a node with two children, we replace the node with either its in-order successor
    (the smallest value in its right subtree) or its in-order predecessor (the largest value in its left subtree).
    This ensures that the tree remains a valid binary search tree.
     */
    protected BinaryTree<T> remove(T val) {
        if (value == null) {
            return this;
        }

        if (val.compareTo(this.value) < 0) {
            if (left != null && left.isNotEmpty()) {
                left = left.remove(val);
            }
        } else if (val.compareTo(this.value) > 0) {
            if (right != null && right.isNotEmpty()) {
                right = right.remove(val);
            }
        } else {
            //We found the node to be removed
            if (left == null || left.isEmpty()) {
                return right;
            } else if (right == null || right.isEmpty()) {
                return left;
            } else {
                // Node with two children
                T minValue = right.findMin();
                this.value = minValue;
                right = right.remove(minValue);
            }
        }

        return balance();
    }

    private T findMin() {
        if (left == null || left.isEmpty()) {
            return value;
        } else {
            return left.findMin();
        }
    }

    protected Set<T> inOrder() {
        Set<T> values = new TreeSet<>();
        inOrder(this, values);
        return values;
    }

    private void inOrder(BinaryTree<T> node, Set<T> values) {
        if (node == null || !node.isNotEmpty()) {
            return;
        }
        inOrder(node.getLeft(), values);
        values.add(node.getValue());
        inOrder(node.getRight(), values);
    }


    //http://www.webgraphviz.com/
    public String toString() {
        StringBuilder builder = new StringBuilder("http://www.webgraphviz.com/");
        builder.append("\ndigraph BinaryTree {\n");
        builder.append("node [fontname=\"Arial\"];\n");

        if (isEmpty()) {
            builder.append("\n");
        } else {
            toDotRecursive(this, builder);
        }
        builder.append("}\n");
        return builder.toString();
    }

    private void toDotRecursive(BinaryTree<T> node, StringBuilder builder) {
        if (node.left != null && node.left.isNotEmpty()) {
            builder.append("    \"" + node.value + "\" -> \"" + node.left.value + "\";\n");
            toDotRecursive(node.left, builder);
        }
        if (node.right != null && node.right.isNotEmpty()) {
            builder.append("    \"" + node.value + "\" -> \"" + node.right.value + "\";\n");
            toDotRecursive(node.right, builder);
        }
    }

    /**
     * Performs a breadth-first traversal (level-order traversal) of the binary tree and returns a string representation
     * of the node values in the order they are visited.
     *
     * <p>This method traverses the tree level by level, from left to right, starting from the root node.
     * It collects the values of each node into a queue during the traversal and then builds a string containing
     * all the node values in breadth-first order.</p>
     *
     * @return a {@code String} containing the values of the nodes in breadth-first order.
     */
    protected String breadthFirst() {
        StringBuilder builder = new StringBuilder();
        Queue<BinaryTree<T>> queue = new LinkedList<>();
        queue.add(this); // Start with the root node

        while (!queue.isEmpty()) {
            BinaryTree<T> node = queue.poll(); // Remove the node from the front of the queue
            if (node != null && node.isNotEmpty()) {
                if (!builder.isEmpty()) {
                    builder.append(", ");
                }
                builder.append(node.value);
                // Visit the current node
                queue.add(node.left); // Adds left child to queue
                queue.add(node.right); // Adds the right child to the queue
            }
        }
        return builder.toString().trim(); // Returns the string with the values in level order
    }
}
