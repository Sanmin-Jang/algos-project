package edu.yourorg;

public class Metrics {
    private int comparisons;
    private int maxDepth;
    private int currentDepth;
    private long startTime;
    private long endTime;

    public Metrics() {
        this.comparisons = 0;
        this.maxDepth = 0;
        this.currentDepth = 0;
    }

    public void start() {
        this.startTime = System.nanoTime();
        this.comparisons = 0;
        this.maxDepth = 0;
        this.currentDepth = 0;
    }

    public void stop() {
        this.endTime = System.nanoTime();
    }

    public void incrementComparisons() {
        this.comparisons++;
    }

    public void enterRecursion() {
        this.currentDepth++;
        this.maxDepth = Math.max(this.maxDepth, this.currentDepth);
    }

    public void exitRecursion() {
        this.currentDepth--;
    }

    // Getters
    public int getComparisons() { return comparisons; }
    public int getMaxDepth() { return maxDepth; }
    public long getTimeNs() { return endTime - startTime; }
}