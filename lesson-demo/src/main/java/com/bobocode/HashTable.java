package com.bobocode;

import java.util.Objects;

public class HashTable<K, V> {
    private static final int BASE_SIZE = 16;
    private Node<K, V>[] array;
    private int size = 0;

    @SuppressWarnings({"unchecked"})
    public HashTable() {
        array = new Node[BASE_SIZE];
    }

    @SuppressWarnings({"unchecked"})
    public HashTable(int capacity) {
        this.array = new Node[capacity];
    }

    public V put(K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        if (array.length == size) {
            array = resizeArray();
        }

        int index = getIndex(key);
        var current = array[index];
        if (current == null) {
            array[index] = new Node<>(key, value);
            size++;
            return null;
        }
        return putValue(key, value, current);
    }

    private V putValue(K key, V value, Node<K, V> current) {
        while (current != null) {
            if (current.key.equals(key)) {
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            if (current.next == null) {
                current.next = new Node<>(key, value);
                size++;
                return null;
            }
            current = current.next;
        }
        return null;
    }

    @SuppressWarnings({"unchecked", "rawtype"})
    private Node<K, V>[] resizeArray() {
        size = 0;
        var newArray = new HashTable(array.length * 2);
        for (var oldNode : array) {
            if (oldNode != null) {
                newArray.put(oldNode.key, oldNode.value);
            }
        }
        return newArray.array;
    }


    public void printTable() {
        for (int i = 0; i < array.length; i++) {
            var currentElement = array[i];
            System.out.print(i + ":");
            printNodes(currentElement);
            System.out.println();
        }

    }

    private void printNodes(Node<K, V> node) {
        while (node != null) {
            System.out.print(node.key + ":" + node.value);
            if (node.next != null) {
                System.out.print(" -> ");
            }
            node = node.next;
        }
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % array.length);
    }
}
