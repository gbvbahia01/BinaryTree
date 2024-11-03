package br.com.gbvbahia.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;

public class BinaryTreeCollection<T extends BinaryTreeValue<T>> {

    @Getter
    private BinaryTree<T> root;

    public BinaryTreeCollection() {
        this.root = new BinaryTree<>();
    }

    /**
     * Constructs a BinaryTreeCollection with the specified values.
     * <p>
     * This constructor initializes the tree by combining the provided values,
     * eliminating duplicates, and constructing a balanced binary search tree (AVL tree).
     * It is efficient for creating a tree with a large number of initial elements.
     * </p>
     *
     * @param values a collection containing the values to initialize the tree with.
     * @throws IllegalArgumentException if the values collection is null.
     */
    public BinaryTreeCollection(Collection<T> values) {
        if (values == null) {
            throw new IllegalArgumentException("Values collection cannot be null");
        }
        this.root = new BinaryTree<>();
        insertAllAndRebuild(values);
    }

    public boolean isEmpty() {
        return root.isEmpty();
    }

    /**
     * Inserts a value into the balanced binary search tree (AVL tree).
     * <p>
     * The tree is balanced after each insertion to maintain minimal height,
     * ensuring efficient operations. If you need to insert many elements,
     * consider using the {@link #insertAllAndRebuild(Collection)} method
     * to improve performance.
     * </p>
     *
     * @param value the value to be inserted into the tree.
     */
    public void insert(T value) {
        root = root.insert(value);
    }

    /**
     * Inserts multiple values into the tree and rebuilds the balanced tree.
     * <p>
     * This method combines the existing values in the tree with the new values provided,
     * eliminates duplicates, and rebuilds the tree to ensure it is balanced.
     * It is recommended to use this method when you need to insert many elements,
     * as it can be more efficient than inserting each element individually.
     * </p>
     *
     * <p><strong>Note:</strong> The tree will be recreated, and the cost of reconstruction
     * is proportional to the total number of elements (O(n)), where n is the number
     * of combined elements.</p>
     *
     * @param values an array containing the values to be inserted.
     */
    public void insertAllAndRebuild(T[] values) {
        insertAllAndRebuild(Arrays.asList(values));
    }

    /**
     * Inserts multiple values into the tree and rebuilds the balanced tree.
     * <p>
     * This method combines the existing values in the tree with the new values provided,
     * eliminates duplicates, and rebuilds the tree to ensure it is balanced.
     * It is recommended to use this method when you need to insert many elements,
     * as it can be more efficient than inserting each element individually.
     * </p>
     *
     * <p><strong>Note:</strong> The tree will be recreated, and the cost of reconstruction
     * is proportional to the total number of elements (O(n)), where n is the number
     * of combined elements.</p>
     *
     * @param values a collection containing the values to be inserted.
     */
    public void insertAllAndRebuild(Collection<T> values) {
        Set<T> allValues = new TreeSet<>();
        if (root != null && root.isNotEmpty()) {
            allValues = root.inOrder();
        }
        // Add the new values
        allValues.addAll(values);
        // Rebuild the balanced tree
        root = buildBalancedTree(allValues.stream().toList(), 0, allValues.size() - 1);
    }

    private BinaryTree<T> buildBalancedTree(List<T> values, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        BinaryTree<T> node = new BinaryTree<>(values.get(mid));
        node.setLeft(buildBalancedTree(values, start, mid - 1));
        node.setRight(buildBalancedTree(values, mid + 1, end));
        return node;
    }

    public void remove(T value) {
        root = root.remove(value);
    }

    public Optional<T> search(T value) {
        return root.search(value);
    }

    public List<T> findAllGreaterThanOrEqualTo(T start) {
        return root.findAllGreaterThanOrEqualTo(start).stream().toList();
    }

    public List<T> findAllLessThanOrEqualTo(T end) {
        return root.findAllLessThanOrEqualTo(end).stream().toList();
    }

    public List<T> findAllBetween(T start, T end) {
        return root.findAllBetween(start, end).stream().toList();
    }

    public int calculateHeight() {
        return root.calculateHeight();
    }

    public boolean isBinarySearchTree() {
        return isBSTUtil(root, null, null);
    }

    private boolean isBSTUtil(BinaryTree<T> node, T min, T max) {
        if (node == null || !node.isNotEmpty()) {
            return true;
        }

        if ((min != null && node.getValue().compareTo(min) <= 0) ||
                (max != null && node.getValue().compareTo(max) >= 0)) {
            return false;
        }

        return isBSTUtil(node.getLeft(), min, node.getValue()) &&
                isBSTUtil(node.getRight(), node.getValue(), max);
    }

    /**
     * Returns a set of all elements in the tree, sorted in natural order.
     *
     * @return a {@code Set<T>} containing all elements sorted according to their natural ordering.
     */
    public Set<T> getSortedElements() {
        return root.inOrder();
    }

    public String breadthFirst() {
        return root.breadthFirst();
    }

    @Override
    public String toString() {
        return root.toString();
    }


}

