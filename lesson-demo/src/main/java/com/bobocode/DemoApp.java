package com.bobocode;

import java.util.Objects;
import java.util.Stack;

public class DemoApp {
    public static void main(String[] args) {
        var head = createLinkedList(4, 3, 9, 1);
        printReversedRecursively(head);
        System.out.println();
        printReversedUsingStack(head);
    }

    /**
     * Creates a list of linked {@link Node} objects based on the given array of elements and returns a head of the list.
     *
     * @param elements an array of elements that should be added to the list
     * @param <T>      elements type
     * @return head of the list
     */
    public static <T> Node<T> createLinkedList(T... elements) {
        Objects.requireNonNull(elements);
        Objects.checkIndex(0, elements.length);
        Node<T> current = new Node<>(elements[0]);
        Node<T> head = current;

        for (int i = 1; i < elements.length; i++) {
            current.next = new Node<>(elements[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * Prints a list in a reserved order using a recursion technique. Please note that it should not change the list,
     * just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversedRecursively(Node<T> head) {
        if (head != null) {
            printReversedRecursively(head.next);
            if (head.next == null) {
                System.out.print(head.element);
            } else {
                System.out.print(" -> " + head.element);
            }
        }
    }

    /**
     * Prints a list in a reserved order using a {@link java.util.Stack} instance. Please note that it should not change
     * the list, just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversedUsingStack(Node<T> head) {
        Objects.requireNonNull(head);

        var stack = fillStack(head);

        System.out.print(stack.pop());
        while (!stack.isEmpty()) {
            System.out.print(" -> " + stack.pop());
        }
    }

    private static <T> Stack<Object> fillStack(Node<T> head) {
        var stack = new Stack<>();
        while (head != null) {
            stack.push(head.element);
            head = head.next;
        }
        return stack;
    }
}
