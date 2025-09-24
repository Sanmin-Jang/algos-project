
import static org.junit.jupiter.api.Assertions.*;

import edu.yourorg.MergeSort;
import edu.yourorg.QuickSort;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
public class SortTests {
    @Test
    void testMergeSortSimple() {
        int[] arr = {5, 2, 8, 1, 3};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 5, 8}, arr);
    }
    @Test
    void testQuickSortSimple() {
        int[] arr = {9, 4, 7, 1, 0, 5};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{0, 1, 4, 5, 7, 9}, arr);
    }
    @Test
    void testEmptyArray() {
        int[] arr = {};MergeSort.sort(arr);
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }
    @Test
    void testSingleElement() {
        int[] arr = {42};
        MergeSort.sort(arr);
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{42}, arr);
    }
    @Test
    void testAllEqualElements() {
        int[] arr = {7, 7, 7, 7, 7};
        MergeSort.sort(arr);
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, arr);
    }
    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        MergeSort.sort(arr);
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
    @Test
    void testReversedArray() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3};
        MergeSort.sort(arr);
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{3, 4, 5, 6, 7, 8, 9}, arr);
    }
    @Test
    void testLargeRandomArray() {
        Random rand = new Random();
        int[] arr1 = new int[1000];
        int[] arr2 = new int[1000];
        for (int i = 0; i < arr1.length; i++) {
            int val = rand.nextInt(10000);
            arr1[i] = val;
            arr2[i] = val;
        }
        MergeSort.sort(arr1);
        QuickSort.sort(arr2);
        Arrays.sort(arr2); // эталонная сортировка
        assertArrayEquals(arr1, arr2); // результат MergeSort и Arrays.sort совпадает
    }
}