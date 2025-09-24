import edu.yourorg.DeterministicSelect;
import edu.yourorg.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class SelectTest {

    @Test
    void testSelectMatchesSort() {
        Random rnd = new Random(42);

        for (int trial = 1; trial <= 100; trial++) { // Сначала 10 тестов для отладки
            int n = rnd.nextInt(50) + 5; // Меньшие размеры для отладки
            int[] arr = rnd.ints(n, -100, 100).toArray();
            int k = rnd.nextInt(n);

            System.out.println("Trial " + trial + ": n=" + n + ", k=" + k);
            System.out.println("Original: " + Arrays.toString(arr));

            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            int expected = sorted[k];

            Metrics m = new Metrics();
            int got = DeterministicSelect.select(arr, k, m);

            System.out.println("Sorted: " + Arrays.toString(sorted));
            System.out.println("Expected: " + expected + ", Got: " + got);
            System.out.println("---");

            assertEquals(expected, got,
                    "Trial " + trial + " failed: expected=" + expected + " got=" + got);

            assertTrue(m.getComparisons() > 0);
            assertTrue(m.getMaxDepth() > 0);
        }
    }

    @Test
    void testSimpleCase() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};
        Metrics m = new Metrics();

        for (int k = 0; k < arr.length; k++) {
            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            int expected = sorted[k];
            int got = DeterministicSelect.select(arr, k, m);

            assertEquals(expected, got, "Failed for k=" + k);
        }
    }
}