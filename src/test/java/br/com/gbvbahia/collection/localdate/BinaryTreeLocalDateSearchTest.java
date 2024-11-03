package br.com.gbvbahia.collection.localdate;

import br.com.gbvbahia.collection.BinaryTreeCollection;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class BinaryTreeLocalDateSearchTest {

    private BinaryTreeCollection<DateValue> binaryTreeCollection = new BinaryTreeCollection();

    @Before
    public void setUp() {
        binaryTreeCollection = new BinaryTreeCollection<>();
    }

    @Test
    public void testMatchSearch() {
        assertTrue(binaryTreeCollection.isEmpty());
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);
        assertFalse(binaryTreeCollection.isEmpty());
        assertTrue(binaryTreeCollection.isBinarySearchTree());
        assertEquals(dividends.length, binaryTreeCollection.getSortedElements().size());
        DateValue toSearch = DateValue.builder().dataBase(LocalDate.of(2023, 12, 28)).build();
        Optional<DateValue> found = binaryTreeCollection.search(toSearch);
        assertTrue(found.isPresent());
        assertEquals("MXRF11", found.get().getTicket());
        assertEquals(Double.valueOf(0.11), found.get().getValue());
        assertEquals(LocalDate.of(2023, 12, 28), found.get().getDataBase());
    }

    @Test
    public void testIntervalBefore() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection = new BinaryTreeCollection<>(Arrays.asList(dividends));
        assertFalse(binaryTreeCollection.isEmpty());
        assertTrue(binaryTreeCollection.isBinarySearchTree());
        assertEquals(dividends.length, binaryTreeCollection.getSortedElements().size());

        DateValue before = DateValue.builder().dataBase(LocalDate.of(2020, 1, 31)).build();
        List<DateValue> allLessThanOrEqualTo = binaryTreeCollection.findAllLessThanOrEqualTo(before);
        assertEquals(45, allLessThanOrEqualTo.size());
    }

    @Test
    public void testIntervalAfter() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection = new BinaryTreeCollection<>(Arrays.asList(dividends));
        assertFalse(binaryTreeCollection.isEmpty());
        assertTrue(binaryTreeCollection.isBinarySearchTree());
        assertEquals(dividends.length, binaryTreeCollection.getSortedElements().size());

        DateValue after = DateValue.builder().dataBase(LocalDate.of(2022, 12, 1)).build();
        List<DateValue> allGreaterThanOrEqualTo = binaryTreeCollection.findAllGreaterThanOrEqualTo(after);
        assertEquals(22, allGreaterThanOrEqualTo.size());
    }

    @Test
    public void testBetween() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection = new BinaryTreeCollection<>(Arrays.asList(dividends));
        assertFalse(binaryTreeCollection.isEmpty());
        assertTrue(binaryTreeCollection.isBinarySearchTree());
        assertEquals(dividends.length, binaryTreeCollection.getSortedElements().size());

        DateValue start = DateValue.builder().dataBase(LocalDate.of(2020, 1, 1)).build();
        DateValue end = DateValue.builder().dataBase(LocalDate.of(2022, 12, 31)).build();

        List<DateValue> between = binaryTreeCollection.findAllBetween(start, end);
        assertEquals(36, between.size());
    }


    @Test
    public void testNonExistentDateSearch() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);
        DateValue toSearch = DateValue.builder().dataBase(LocalDate.of(2025, 1, 1)).build();
        Optional<DateValue> found = binaryTreeCollection.search(toSearch);
        assertFalse(found.isPresent());
    }

    @Test
    public void testEarliestDate() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);
        DateValue earliest = binaryTreeCollection.getSortedElements().iterator().next();
        assertEquals(LocalDate.of(2016, 6, 30), earliest.getDataBase());
    }

    @Test
    public void testLatestDate() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);
        DateValue latest = null;
        for (DateValue dv : binaryTreeCollection.getSortedElements()) {
            latest = dv;
        }
        assertEquals(LocalDate.of(2024, 9, 30), latest.getDataBase());
    }

    @Test
    public void testRemoveDate() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);
        DateValue toRemove = DateValue.builder().dataBase(LocalDate.of(2023, 12, 28)).build();
        binaryTreeCollection.remove(toRemove);
        Optional<DateValue> found = binaryTreeCollection.search(toRemove);
        assertFalse(found.isPresent());
        assertEquals(dividends.length - 1, binaryTreeCollection.getSortedElements().size());
    }

    @Test
    public void testFindAllBetweenNoMatches() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);
        DateValue start = DateValue.builder().dataBase(LocalDate.of(2025, 1, 1)).build();
        DateValue end = DateValue.builder().dataBase(LocalDate.of(2025, 12, 31)).build();
        List<DateValue> between = binaryTreeCollection.findAllBetween(start, end);
        assertTrue(between.isEmpty());
    }

    @Test
    public void testFindAllBetweenExactMatch() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);
        DateValue start = DateValue.builder().dataBase(LocalDate.of(2020, 1, 31)).build();
        DateValue end = DateValue.builder().dataBase(LocalDate.of(2020, 1, 31)).build();
        List<DateValue> between = binaryTreeCollection.findAllBetween(start, end);
        assertEquals(1, between.size());
        assertEquals(LocalDate.of(2020, 1, 31), between.get(0).getDataBase());
    }

    @Test
    public void testInsertDuplicateDate() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);
        int initialSize = binaryTreeCollection.getSortedElements().size();
        DateValue duplicate = DateValue.builder().dataBase(LocalDate.of(2023, 12, 28))
                .value(0.11).ticket("MXRF11").build();
        binaryTreeCollection.insert(duplicate);
        int newSize = binaryTreeCollection.getSortedElements().size();
        assertEquals(initialSize, newSize);
    }

    @Test
    public void testInsertAndRemoveMultipleDates() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);
        int initialSize = binaryTreeCollection.getSortedElements().size();

        DateValue newDate1 = DateValue.builder().dataBase(LocalDate.of(2025, 1, 31)).value(0.12)
                .ticket("MXRF11").build();
        DateValue newDate2 = DateValue.builder().dataBase(LocalDate.of(2025, 2, 28)).value(0.13)
                .ticket("MXRF11").build();

        binaryTreeCollection.insert(newDate1);
        binaryTreeCollection.insert(newDate2);

        int newSize = binaryTreeCollection.getSortedElements().size();
        assertEquals(initialSize + 2, newSize);

        Optional<DateValue> found1 = binaryTreeCollection.search(newDate1);
        Optional<DateValue> found2 = binaryTreeCollection.search(newDate2);
        assertTrue(found1.isPresent());
        assertTrue(found2.isPresent());

        binaryTreeCollection.remove(newDate1);
        binaryTreeCollection.remove(newDate2);

        int finalSize = binaryTreeCollection.getSortedElements().size();
        assertEquals(initialSize, finalSize);

        found1 = binaryTreeCollection.search(newDate1);
        found2 = binaryTreeCollection.search(newDate2);
        assertFalse(found1.isPresent());
        assertFalse(found2.isPresent());
    }

    @Test
    public void testTreeHeight() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);
        int height = binaryTreeCollection.calculateHeight();
        int expectedHeight = (int) (Math.log(dividends.length) / Math.log(2)) + 1;
        assertTrue(height <= expectedHeight);
    }

    @Test
    public void testFindAllLessThanNoMatches() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);
        DateValue before = DateValue.builder().dataBase(LocalDate.of(2010, 1, 1)).build();
        List<DateValue> results = binaryTreeCollection.findAllLessThanOrEqualTo(before);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testFindAllGreaterThanAllMatches() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);
        DateValue after = DateValue.builder().dataBase(LocalDate.of(2010, 1, 1)).build();
        List<DateValue> results = binaryTreeCollection.findAllGreaterThanOrEqualTo(after);
        assertEquals(dividends.length, results.size());
    }

    @Test
    public void testIsBinarySearchTreeAfterOperations() {
        DateValue[] dividends = DateValueHelper.getMXRF11();
        binaryTreeCollection.insertAllAndRebuild(dividends);

        DateValue newDate = DateValue.builder().dataBase(LocalDate.of(2025, 3, 31)).value(0.14)
                .ticket("MXRF11").build();
        binaryTreeCollection.insert(newDate);
        binaryTreeCollection.remove(dividends[0]);

        assertTrue(binaryTreeCollection.isBinarySearchTree());
    }

    @Test
    public void testEmptyTreeOperations() {
        assertTrue(binaryTreeCollection.isEmpty());
        DateValue toSearch = DateValue.builder().dataBase(LocalDate.of(2023, 12, 28)).build();
        Optional<DateValue> found = binaryTreeCollection.search(toSearch);
        assertFalse(found.isPresent());
        binaryTreeCollection.remove(toSearch); // Should not throw exception
        assertTrue(binaryTreeCollection.isBinarySearchTree());
    }

    @Test(expected = NullPointerException.class)
    public void testInsertNull() {
        binaryTreeCollection.insert(null);
    }
}
