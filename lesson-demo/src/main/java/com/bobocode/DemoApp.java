package com.bobocode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoApp {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 8, 6, 5, 4, 9, 2));
        System.out.println(list);
        mergeSort(list);
        System.out.println(list);
    }

    public static <T extends Comparable<T>> void mergeSort(List<T> list) {
        validateList(list);
        sortSeparateParts(list, 0, list.size() - 1);
    }

    private static <T extends Comparable<T>> void sortSeparateParts(List<T> list, int start, int end) {
        if (start >= end) {
            return;
        }
        int half = (start + end) / 2;

        sortSeparateParts(list, start, half);
        sortSeparateParts(list, half + 1, end);

        sortElements(list, start, half + 1, end);
    }

    private static <T extends Comparable<T>> void sortElements(List<T> list, int start, int mid, int end) {
        while (start < mid && mid <= end) {
            if (list.get(start).compareTo(list.get(mid)) <= 0) {
                start++;
            } else {
                T value = list.get(mid);
                int index = mid;

                while (index != start) {
                    list.set(index, list.get(index - 1));
                    index--;
                }
                list.set(start, value);
                start++;
                mid++;
            }
        }
    }

    private static <T extends Comparable<? super T>> void validateList(List<T> list) {
        try {
            list.add(null);
            list.remove(list.size() - 1);
        } catch (UnsupportedOperationException e) {
            throw new IllegalArgumentException("Current list is unmodified");
        }
    }
}
