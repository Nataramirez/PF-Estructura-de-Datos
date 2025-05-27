package org.acme.utils.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Iterator;
import java.util.NoSuchElementException;

@JsonIgnoreProperties (ignoreUnknown = true)
public class SimpleLinkedList<T> implements Iterable<T> {

    private int size;
    private Node<T> firstNode;
    private Node<T> lastNode;

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

    public void insertAtEnd(T value) {
        Node<T> node = new Node<>(value);
        if (size == 0) {
            firstNode = node;
            lastNode = node;
        } else {
            lastNode.setNext(node);
            lastNode = node;
        }
        size++;
    }

    public boolean remove(T value) {
        if (isEmpty()) {
            return false;
        }

        if (firstNode.getValue().equals(value)) {
            firstNode = firstNode.getNext();
            size--;
            return true;
        }

        Node<T> previous = firstNode;
        Node<T> current = firstNode.getNext();

        while (current != null) {
            if (current.getValue().equals(value)) {
                previous.setNext(current.getNext());
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }

        return false;
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

    public boolean isEmpty(){
        return size == 0;
    }


    @Override
    public Iterator<T> iterator() {
        return new SimpleLinkedListIterator<>(firstNode);
    }

    public Integer size() {
        return size;
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
