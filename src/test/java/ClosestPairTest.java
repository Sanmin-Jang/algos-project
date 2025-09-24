import edu.yourorg.ClosestPair;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    // Наивный O(n^2) поиск ближайшей пары (для валидации)
    private static ClosestPair.Result bruteForce(ClosestPair.Point[] pts) {
        double bestD = Double.POSITIVE_INFINITY;
        ClosestPair.Point a = null, b = null;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double d = Math.hypot(pts[i].x - pts[j].x, pts[i].y - pts[j].y);
                if (d < bestD) {
                    bestD = d;
                    a = pts[i];
                    b = pts[j];
                }
            }
        }
        return new ClosestPair.Result(a, b, bestD);
    }

    @Test
    void validateAgainstBruteForceOnSmall() {
        Random rnd = new Random(42);

        for (int trial = 1; trial <= 20; trial++) {
            int n = rnd.nextInt(500) + 50; // 50..550, <= 2000
            ClosestPair.Point[] pts = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++) {
                pts[i] = new ClosestPair.Point(
                        rnd.nextDouble() * 2000 - 1000,
                        rnd.nextDouble() * 2000 - 1000
                );
            }

            ClosestPair.Result fast = ClosestPair.findClosestPair(pts);
            ClosestPair.Result brute = bruteForce(pts);

            // Проверяем совпадение расстояний (с допуском из-за double)
            assertEquals(brute.distance, fast.distance, 1e-9,
                    "Trial " + trial + " failed: n=" + n);
        }
    }

    @Test
    void runLargeInstanceFastOnly() {
        Random rnd = new Random(123);
        int n = 100_000;
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new ClosestPair.Point(
                    rnd.nextDouble() * 1e6,
                    rnd.nextDouble() * 1e6
            );
        }

        // Просто проверим, что быстрый алгоритм отрабатывает без ошибок
        ClosestPair.Result fast = ClosestPair.findClosestPair(pts);
        System.out.println("Large test: n=" + n + " result=" + fast);
        assertTrue(fast.distance >= 0);
    }
}
