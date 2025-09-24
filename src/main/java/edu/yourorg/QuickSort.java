package edu.yourorg;
import java.util.Random;
public class QuickSort {
    private static final Random rand = new Random();
    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    private static void quickSort(int[] arr, int left, int right) {
        while (left < right) {
            int pivotIndex = left + rand.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];
            int i = left, j = right;
            while (i <= j) {
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;
                if (i <= j) {
                    swap(arr, i, j);
                    i++;
                    j--;
                }
            }
            if (j - left < right - i) {
                if (left < j) quickSort(arr, left, j);
                left = i; // tail recursion заменяем на цикл
            } else {
                if (i < right) quickSort(arr, i, right);
                right = j;
            }
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
