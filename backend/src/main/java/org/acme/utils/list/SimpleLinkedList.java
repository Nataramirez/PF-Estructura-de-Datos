package org.acme.utils.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<T> implements Iterable<T> {

    private int size;
    private Node<T> firstNode;

    public SimpleLinkedList() {
        size = 0;
        firstNode = null;
    }

    public void insertAtStart(T value) {
        Node<T> node = new Node<>(value);

        if (firstNode == null) {
            firstNode = node;
        } else {
            node.setNext(firstNode);
            firstNode = node;
        }
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleLinkedListIterator<>(firstNode);
    }

    private static class SimpleLinkedListIterator<T> implements Iterator<T> {

        private Node<T> current;

        public SimpleLinkedListIterator(Node<T> start) {
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
