package edu.yourorg;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public final double x;
        public final double y;
        public Point(double x, double y) { this.x = x; this.y = y; }
        @Override
        public String toString() { return "(" + x + ", " + y + ")"; }
    }

    public static class Result {
        public final Point a;
        public final Point b;
        public final double distance;
        public Result(Point a, Point b, double distance) {
            this.a = a; this.b = b; this.distance = distance;
        }
        @Override
        public String toString() {
            return String.format("Result{a=%s, b=%s, distance=%.6f}", a, b, distance);
        }
    }


    public static Result findClosestPair(Point[] points) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("Need at least two points");
        }

        // Work on a copy to avoid mutating caller array
        Point[] pts = points.clone();
        Arrays.sort(pts, Comparator.comparingDouble(p -> p.x)); // sort by x

        Point[] aux = new Point[pts.length]; // auxiliary array for merges by y
        return closestRec(pts, aux, 0, pts.length - 1);
    }


    private static Result closestRec(Point[] pts, Point[] aux, int left, int right) {
        int n = right - left + 1;
        if (n <= 3) {
            Result best = bruteForce(pts, left, right);
            Arrays.sort(pts, left, right + 1, Comparator.comparingDouble(p -> p.y));
            return best;
        }

        int mid = (left + right) >>> 1;
        double midX = pts[mid].x;

        Result leftRes = closestRec(pts, aux, left, mid);
        Result rightRes = closestRec(pts, aux, mid + 1, right);

        Result best = leftRes.distance <= rightRes.distance ? leftRes : rightRes;
        double delta = best.distance;

        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (pts[i].y <= pts[j].y) aux[k++] = pts[i++];
            else aux[k++] = pts[j++];
        }
        while (i <= mid) aux[k++] = pts[i++];
        while (j <= right) aux[k++] = pts[j++];
        for (int t = left; t <= right; t++) pts[t] = aux[t];

        int stripCount = 0;
        for (int t = left; t <= right; t++) {
            if (Math.abs(pts[t].x - midX) < delta) {
                for (int s = stripCount - 1; s >= 0 && (stripCount - s) <= 7; s--) {
                }
                stripCount++;
            }
        }

        for (int t = left; t <= right; t++) {
            if (Math.abs(pts[t].x - midX) >= delta) continue;
            for (int u = t + 1, cnt = 0; u <= right && cnt < 8; u++) {
                if (Math.abs(pts[u].y - pts[t].y) >= delta) {
                    if (Math.abs(pts[u].y - pts[t].y) >= delta) break;
                }
                if (Math.abs(pts[u].x - pts[t].x) >= delta && Math.abs(pts[u].y - pts[t].y) >= delta) {
                }
                double d = dist(pts[t], pts[u]);
                if (d < delta) {
                    delta = d;
                    best = new Result(pts[t], pts[u], d);
                }
                cnt++;
            }
        }

        return best;
    }

    // brute-force check for small ranges
    private static Result bruteForce(Point[] pts, int left, int right) {
        double bestD = Double.POSITIVE_INFINITY;
        Point a = null, b = null;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double d = dist(pts[i], pts[j]);
                if (d < bestD) {
                    bestD = d;
                    a = pts[i]; b = pts[j];
                }
            }
        }
        return new Result(a, b, bestD);
    }

    private static double dist(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.hypot(dx, dy);
    }

    // Example quick test / usage
    public static void main(String[] args) {
        Point[] pts = new Point[] {
                new Point(0,0), new Point(1,1), new Point(2,2), new Point(0.5, 0.5),
                new Point(10,10), new Point(-1, 3), new Point(0.4, 0.45)
        };
        Result r = findClosestPair(pts);
        System.out.println(r);
    }
}
