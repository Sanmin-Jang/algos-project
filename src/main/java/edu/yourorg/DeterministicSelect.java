package edu.yourorg;

import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] array, int k, Metrics metrics) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        if (k < 0 || k >= array.length) {
            throw new IllegalArgumentException("k must be between 0 and array.length - 1");
        }

        metrics.start();
        try {
            int[] arrCopy = array.clone();
            return select(arrCopy, 0, arrCopy.length - 1, k, metrics);
        } finally {
            metrics.stop();
        }
    }

    private static int select(int[] array, int left, int right, int k, Metrics metrics) {
        metrics.enterRecursion();

        while (true) {
            if (left == right) {
                return array[left];
            }

            int pivotIndex = medianOfMedians(array, left, right, metrics);
            pivotIndex = partition(array, left, right, pivotIndex, metrics);

            if (k == pivotIndex) {
                return array[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    private static int medianOfMedians(int[] array, int left, int right, Metrics metrics) {
        int n = right - left + 1;

        if (n <= 5) {
            insertionSort(array, left, right, metrics);
            return left + n / 2; // индекс медианы
        }

        int numGroups = (n + 4) / 5;
        for (int i = 0; i < numGroups; i++) {
            int groupLeft = left + i * 5;
            int groupRight = Math.min(groupLeft + 4, right);

            insertionSort(array, groupLeft, groupRight, metrics);

            int medianIndex = groupLeft + (groupRight - groupLeft) / 2;
            swap(array, left + i, medianIndex); // переносим медиану в начало
        }

        // теперь медианы лежат в подмассиве array[left .. left+numGroups-1]
        int medianOfMediansIndex = medianOfMedians(array, left, left + numGroups - 1, metrics);

        return medianOfMediansIndex;
    }


    private static int partition(int[] array, int left, int right, int pivotIndex, Metrics metrics) {
        int pivotValue = array[pivotIndex];
        swap(array, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            metrics.incrementComparisons();
            if (array[i] < pivotValue) {
                swap(array, storeIndex, i);
                storeIndex++;
            }
        }

        swap(array, storeIndex, right);
        return storeIndex;
    }

    private static void insertionSort(int[] array, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= left) {
                metrics.incrementComparisons();
                if (array[j] > key) {
                    array[j + 1] = array[j];
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = key;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
