package org.acme.utils.tree;

import org.acme.utils.list.SimpleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> root;

    public BinaryTree() {
    }

    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    private Node<T> insertRecursive(Node<T> current, T value) {
        if (current == null) {
            return new Node<>(value);
        }

        if (value.compareTo(current.getValue()) < 0) {
            current.setLeft(insertRecursive(current.getLeft(), value));
        } else if (value.compareTo(current.getValue()) > 0) {
            current.setRight(insertRecursive(current.getRight(), value));
        }

        return current;
    }

    public String inOrder() {
        return inOrderRecursive(root);
    }

    private String inOrderRecursive(Node<T> node) {
        if (node == null) return "";
        return inOrderRecursive(node.getLeft()) +
                node.getValue() + " " +
                inOrderRecursive(node.getRight());
    }


    public T search(T value) {
        return searchRecursive(root, value).getValue();
    }

    private Node<T> searchRecursive(Node<T> current, T value) {
        if (current == null) return null;
        int comparison = value.compareTo(current.getValue());
        if (comparison == 0) return current;
        return comparison < 0
                ? searchRecursive(current.getLeft(), value)
                : searchRecursive(current.getRight(), value);
    }

    public void clearTree() {
        root = null;
    }
    
    public boolean isEmpty() {
        return root == null;
    }

    private Node<T> getMinRecursive(Node<T> node) {
        if (node == null || node.getLeft() == null) return node;
        return getMinRecursive(node.getLeft());
    }

    public void deleteValue(T value) {
        root = deleteRecursive(root, value);
    }

    private Node<T> deleteRecursive(Node<T> current, T value) {
        if (current == null) return null;

        int comparison = value.compareTo(current.getValue());

        if (comparison < 0) {
            current.setLeft(deleteRecursive(current.getLeft(), value));
        } else if (comparison > 0) {
            current.setRight(deleteRecursive(current.getRight(), value));
        } else {
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            } else if (current.getLeft() == null) {
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            } else {
                Node<T> replacement = getMinRecursive(current.getRight());
                current.setValue(replacement.getValue());
                current.setRight(deleteRecursive(current.getRight(), replacement.getValue()));
            }
        }

        return current;
    }

    public SimpleLinkedList<T> toList() {
        SimpleLinkedList<T> result = new SimpleLinkedList<>();
        inOrderTraversal(root, result);
        System.out.println("List: " + result);
        return result;
    }

    private void inOrderTraversal(Node<T> node, SimpleLinkedList<T> list) {
        if (node == null) return;
        inOrderTraversal(node.getLeft(), list);
        list.insertAtEnd(node.getValue());
        inOrderTraversal(node.getRight(), list);
    }

    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator<>(root);
    }

    private static class InOrderIterator<T extends Comparable<T>> implements Iterator<T> {

        private final Stack<Node<T>> stack = new Stack<>();

        public InOrderIterator(Node<T> root) {
            pushLeftBranch(root);
        }

        private void pushLeftBranch(Node<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<T> current = stack.pop();
            T value = current.getValue();
            if (current.getRight() != null) {
                pushLeftBranch(current.getRight());
            }

            return value;
        }
    }
}
