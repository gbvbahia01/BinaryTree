package br.com.gbvbahia.collection;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class BinaryTreeCollection<T extends BinaryTreeValue<T>> {

    @Getter
    private BinaryTree<T> root;

    public BinaryTreeCollection() {
        this.root = new BinaryTree<>();
    }

    public void insert(T value) {
        root = root.insert(value);
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
        if (node == null || !node.hasValue()) {
            return true;
        }

        if ((min != null && node.getValue().compareTo(min) <= 0) ||
                (max != null && node.getValue().compareTo(max) >= 0)) {
            return false;
        }

        return isBSTUtil(node.getLeft(), min, node.getValue()) &&
                isBSTUtil(node.getRight(), node.getValue(), max);
    }

    public String breadthFirst() {
        return root.breadthFirst();
    }

    @Override
    public String toString() {
        return root.toString();
    }


}

