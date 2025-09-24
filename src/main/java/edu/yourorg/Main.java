package edu.yourorg;
import java.util.Arrays;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
// 1 Простая проверка MergeSort
        int[] arr1 = {5, 2, 8, 1, 3};
        MergeSort.sort(arr1);
        System.out.println("1) MergeSort Simple: " + Arrays.toString(arr1));
// 2 Простая проверка QuickSort
        int[] arr2 = {9, 4, 7, 1, 0, 5};
        QuickSort.sort(arr2);
        System.out.println("2) QuickSort Simple: " + Arrays.toString(arr2));
// 3 Пустой массив
        int[] arr3 = {};
        MergeSort.sort(arr3);
        QuickSort.sort(arr3);
        System.out.println("3) Empty Array: " + Arrays.toString(arr3));
// 4 Один элемент
        int[] arr4 = {42};
        MergeSort.sort(arr4);
        QuickSort.sort(arr4);
        System.out.println("4) Single Element: " + Arrays.toString(arr4));
// 5 Все элементы одинаковые
        int[] arr5 = {7, 7, 7, 7, 7};
        MergeSort.sort(arr5);
        QuickSort.sort(arr5);
        System.out.println("5) All Equal Elements: " + Arrays.toString(arr5));
// 6 Уже отсортированный массив
        int[] arr6 = {1, 2, 3, 4, 5};
        MergeSort.sort(arr6);
        QuickSort.sort(arr6);
        System.out.println("6) Already Sorted: " + Arrays.toString(arr6));
// 7 Обратный порядок
        int[] arr7 = {9, 8, 7, 6, 5, 4, 3};
        MergeSort.sort(arr7);
        QuickSort.sort(arr7);
        System.out.println("7) Reversed Array: " + Arrays.toString(arr7));
// 8 Большой случайный массив
        Random rand = new Random();
        int[] arr8 = new int[20]; // сделаем поменьше для вывода,например 20 элементов
        for (int i = 0; i < arr8.length; i++) {
            arr8[i] = rand.nextInt(100);
        }
        System.out.println("Исходный массив (Large Random): " +
                Arrays.toString(arr8));
        int[] copy8 = Arrays.copyOf(arr8, arr8.length);
        MergeSort.sort(arr8);
        QuickSort.sort(copy8);
        System.out.println("8) Large Random (MergeSort): " +
                Arrays.toString(arr8));
        System.out.println("8) Large Random (QuickSort): " +
                Arrays.toString(copy8));
    }
}