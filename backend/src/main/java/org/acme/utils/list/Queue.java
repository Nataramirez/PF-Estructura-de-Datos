package org.acme.utils.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {

    private int size;
    private Node<T> firstNode;
    private Node<T> lastNode;

    public Queue() {
        size = 0;
        firstNode = null;
        lastNode = null;
    }

    // Encola: agrega al final
    public void enqueue(T value) {
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            firstNode = node;
            lastNode = node;
        } else {
            lastNode.setNext(node);
            lastNode = node;
        }
        size++;
    }

    // Desencola: elimina del inicio
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T value = firstNode.getValue();
        firstNode = firstNode.getNext();
        size--;

        if (isEmpty()) {
            lastNode = null;
        }

        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return firstNode.getValue();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T search(T value) {
        Node<T> current = firstNode;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }


    @Override
    public Iterator<T> iterator() {
        return new QueueIterator<>(firstNode);
    }

    private static class QueueIterator<T> implements Iterator<T> {

        private Node<T> current;

        public QueueIterator(Node<T> start) {
            this.current = start;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            T value = current.getValue();
            current = current.getNext();
            return value;
        }
    }
}
