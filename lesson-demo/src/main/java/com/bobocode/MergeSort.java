package com.bobocode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class MergeSort<T extends Comparable<? super T>> extends RecursiveAction {

    private final List<T> elements;

    public MergeSort(List<T> elements) {
        validateList(elements);
        this.elements = elements;
    }

    @Override
    protected void compute() {
        if (elements.size() > 1) {
            int middle = elements.size() / 2;
            var leftList = new ArrayList<>(elements.subList(0, middle));
            var rightList = new ArrayList<>(elements.subList(middle, this.elements.size()));

            var leftPart = new MergeSort<>(leftList);
            var rightPart = new MergeSort<>(rightList);

            leftPart.fork();
            rightPart.compute();
            leftPart.join();

            merge(elements, leftList, rightList);
        }
    }

    private static <T extends Comparable<? super T>> void merge(List<T> elements, ArrayList<T> leftList, ArrayList<T> rightList) {
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;

        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {
            if (leftList.get(leftIndex).compareTo(rightList.get(rightIndex)) <= 0) {
                elements.set(index++, leftList.get(leftIndex++));
            } else {
                elements.set(index++, rightList.get(rightIndex++));
            }
        }

        while (leftIndex < leftList.size()) {
            elements.set(index++, leftList.get(leftIndex++));
        }
        while (rightIndex < rightList.size()) {
            elements.set(index++, rightList.get(rightIndex++));
        }

    }

    private void validateList(List<T> list) {
        try {
            list.add(null);
            list.remove(list.size() - 1);
        } catch (Exception e) {
            throw new IllegalArgumentException("Incorrect list");
        }
    }
}
