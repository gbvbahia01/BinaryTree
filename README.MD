# Binary Tree Implementation in Java

This project provides an implementation of a generic Binary Search Tree (BST) with AVL balancing in Java. It includes
functionalities for insertion, deletion, searching, and traversal of nodes in the tree. The tree supports generic types
that extend the Comparable interface.
Table of Contents

### Overview

This implementation provides a self-balancing binary search tree using the AVL algorithm. It ensures that the tree
remains balanced after insertions and deletions, providing efficient operations with O(log n) time complexity for
search, insert, and delete operations.

### Features

    Generic Implementation: Supports any type that implements Comparable.
    AVL Balancing: Automatically balances the tree after insertions and deletions.
    Insertion: Adds elements to the tree while maintaining balance.
    Deletion: Removes elements and rebalances the tree as necessary.
    Search: Efficiently searches for elements in the tree.
    Traversal: Provides breadth-first traversal (level-order traversal) of the tree.
    BST Validation: Checks if the tree maintains the properties of a binary search tree.

### Getting Started

##### Prerequisites

    Java Development Kit (JDK) 11 or higher.
    A Java IDE or text editor.
    Maven (optional, for project management).

### Usage

##### BinaryTreeValue Interface

The BinaryTreeValue interface ensures that the elements stored in the tree are comparable and serializable.

```java
public interface BinaryTreeValue<T> extends Comparable<T>, Serializable {
}
```

##### BinaryTree Class

The BinaryTree class represents the nodes of the tree and contains the core logic for insertion, deletion, balancing,
and traversal.

```java
public class BinaryTree<T extends BinaryTreeValue<T>> {
    // Fields
    private BinaryTree<T> left;
    private BinaryTree<T> right;
    private T value;

    // Constructors, getters, and setters

    // Core methods
    public BinaryTree<T> insert(T val) { /* ... */ }

    public BinaryTree<T> remove(T val) { /* ... */ }

    public Optional<T> search(T toSearch) { /* ... */ }

    public String breadthFirst() { /* ... */ }
    // ... other methods
}

```

##### BinaryTreeCollection Class

The BinaryTreeCollection class acts as a wrapper for the BinaryTree, providing additional utility methods and
maintaining the root node.

```java
public class BinaryTreeCollection<T extends BinaryTreeValue<T>> {
    private BinaryTree<T> root;

    public void insert(T value) { /* ... */ }

    public void remove(T value) { /* ... */ }

    public Optional<T> search(T value) { /* ... */ }

    public String breadthFirst() { /* ... */ }

    public boolean isBinarySearchTree() { /* ... */ }
    // ... other methods
}

```

### Examples

##### Insertion and Traversal

```java
public class BinaryTreeExample {
    public static void main(String[] args) {
        BinaryTreeCollection<MyValue> tree = new BinaryTreeCollection<>();
        tree.insert(new MyValue(10));
        tree.insert(new MyValue(5));
        tree.insert(new MyValue(15));

        System.out.println("Breadth-First Traversal: " + tree.breadthFirst());
        // Output: Breadth-First Traversal: 10 5 15
    }
}

class MyValue implements BinaryTreeValue<MyValue> {
    private final int value;

    public MyValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(MyValue o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
```

##### Deletion

```java
tree.remove(new MyValue(5));
System.out.println("After Deletion: " + tree.breadthFirst());
// Output: After Deletion: 10 15

```

##### Searching
```java
Optional<MyValue> result = tree.search(new MyValue(15));
if (result.isPresent()) {
    System.out.println("Found: " + result.get());
} else {
    System.out.println("Value not found.");
}
// Output: Found: 15
```

### Testing

The project includes a set of JUnit tests covering various scenarios:

    Insertion of nodes.
    Deletion of leaf nodes, nodes with one child, and nodes with two children.
    Tree balancing after insertions and deletions.
    Searching for existing and non-existing values.
    Validation of the binary search tree property.

Example Test Case:
```java
@Test
public void testInsertionAndDeletion() {
    BinaryTreeCollection<MyValue> tree = new BinaryTreeCollection<>();
    tree.insert(new MyValue(20));
    tree.insert(new MyValue(10));
    tree.insert(new MyValue(30));
    assertTrue(tree.isBinarySearchTree());

    tree.remove(new MyValue(20));
    assertTrue(tree.isBinarySearchTree());
    assertEquals("25 10 30", tree.breadthFirst());
}
```

### Contributing

Contributions are welcome! Please follow these steps:

    Fork the repository.
    Create a new branch for your feature or bugfix.
    Commit your changes with clear messages.
    Submit a pull request explaining your changes.

Please ensure that your code adheres to the coding standards and includes relevant tests.

### License

This project is licensed under the MIT License. You are free to use, modify, and distribute this software as per the terms of the license.
