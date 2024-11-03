package br.com.gbvbahia.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BinaryTreeAllBiggerOrEqualTest {

    private BinaryTree<BinaryTreeValueImpl> binaryTree;

    @Before
    public void setUp() {
        binaryTree = new BinaryTree<>();
    }

    @Test
    public void testStartingFromLeft() {
        assertFalse(binaryTree.hasValue());
        int values = 20;
        for (int i = 1; i < values; i++) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(6)).stream().toList();
        assertEquals(14, vals.size());
        for (int i = 6; i < values; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 1; i < 6; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
    }

    @Test
    public void testStartingFromCenter() {
        assertFalse(binaryTree.hasValue());
        int values = 20;
        for (int i = 1; i < values; i++) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(8)).stream().toList();
        assertEquals(12, vals.size());
        for (int i = 8; i < values; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 1; i < 8; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
    }

    @Test
    public void testStartingFromRight() {
        assertFalse(binaryTree.hasValue());
        int values = 20;
        for (int i = 1; i < values; i++) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(10)).stream().toList();
        assertEquals(10, vals.size());
        for (int i = 10; i < values; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 1; i < 10; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
    }

    // Search value less than the minimum value in the tree
    @Test
    public void testValueLessThanAllNodes() {
        assertFalse(binaryTree.hasValue());
        int values = 20;
        // Insert values from 1 to 19
        for (int i = 1; i < values; i++) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
        }
        // Search for a value less than the minimum (0)
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(0)).stream().toList();
        // Should return all values since all are greater than or equal to 0
        assertEquals(19, vals.size());
        for (int i = 1; i < values; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
    }

    // Search value greater than the maximum value in the tree
    @Test
    public void testValueGreaterThanAllNodes() {
        assertFalse(binaryTree.hasValue());
        int values = 20;
        // Insert values from 1 to 19
        for (int i = 1; i < values; i++) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
        }
        // Search for a value greater than the maximum (25)
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(25)).stream().toList();
        // Should return an empty list since no values are greater than or equal to 25
        assertEquals(0, vals.size());
    }

    // Empty tree
    @Test
    public void testEmptyTree() {
        assertFalse(binaryTree.hasValue());
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(10)).stream().toList();
        // Should return an empty list
        assertEquals(0, vals.size());
    }

    // Tree with a single element
    @Test
    public void testSingleElementTree() {
        assertFalse(binaryTree.hasValue());
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(10));
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(10)).stream().toList();
        assertEquals(1, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(10)));

        vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(15)).stream().toList();
        // Should return an empty list
        assertEquals(0, vals.size());
    }

    // Negative values
    @Test
    public void testWithNegativeValues() {
        assertFalse(binaryTree.hasValue());
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-10));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-20));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-5));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(0));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(10));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(5));

        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(0)).stream().toList();
        assertEquals(3, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(0)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(5)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(10)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(-5)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(-10)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(-20)));
    }

    // Non-sequential values
    @Test
    public void testWithNonSequentialInsertions() {
        assertFalse(binaryTree.hasValue());
        int[] values = {10, 5, 15, 3, 7, 12, 17};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(12)).stream().toList();
        assertEquals(3, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(12)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(15)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(17)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(3)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(5)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(7)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(10)));
    }

    // Search value not present in the tree
    @Test
    public void testValueNotInTree() {
        assertFalse(binaryTree.hasValue());
        int[] values = {10, 5, 15, 3, 7, 12, 17};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Search for a value not in the tree (11)
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(11)).stream().toList();
        // Should include values 12, 15, 17
        assertEquals(3, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(12)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(15)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(17)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(10)));
    }

    // Search value equal to the minimum value in the tree
    @Test
    public void testValueEqualToMinimum() {
        assertFalse(binaryTree.hasValue());
        int[] values = {10, 5, 15, 3, 7, 12, 17};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Minimum value is 3
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(3)).stream().toList();
        assertEquals(values.length, vals.size());
        for (int val : values) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(val)));
        }
    }

    // Search value equal to the maximum value in the tree
    @Test
    public void testValueEqualToMaximum() {
        assertFalse(binaryTree.hasValue());
        int[] values = {10, 5, 15, 3, 7, 12, 17};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Maximum value is 17
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(17)).stream().toList();
        assertEquals(1, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(17)));
    }

    // Attempting to insert duplicates
    @Test
    public void testWithDuplicates() {
        assertFalse(binaryTree.hasValue());
        int[] values = {10, 5, 15, 5, 10, 15}; // duplicates
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Should only have unique values
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(5)).stream().toList();
        assertEquals(3, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(5)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(10)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(15)));
    }

    // Mixed values with a complex tree structure
    @Test
    public void testMixedValues() {
        assertFalse(binaryTree.hasValue());
        int[] values = {50, 20, 70, 10, 30, 60, 80, 5, 15, 25, 35, 55, 65, 75, 85};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Search for values greater than or equal to 55
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(55)).stream().toList();
        int[] expectedValues = {55, 60, 65, 70, 75, 80, 85};
        assertEquals(expectedValues.length, vals.size());
        for (int val : expectedValues) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(val)));
        }
    }

    // Search value less than the minimum negative value
    @Test
    public void testValueLessThanNegativeMinimum() {
        assertFalse(binaryTree.hasValue());
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-10));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-20));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-5));

        // Search for a value less than the minimum (-30)
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(-30)).stream().toList();
        // Should return all values
        assertEquals(3, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(-20)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(-10)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(-5)));
    }

    // Search value greater than the maximum negative value
    @Test
    public void testValueGreaterThanNegativeMaximum() {
        assertFalse(binaryTree.hasValue());
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-10));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-20));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-5));

        // Search for a value greater than 0
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(0)).stream().toList();
        // Should return an empty list
        assertEquals(0, vals.size());
    }
}
