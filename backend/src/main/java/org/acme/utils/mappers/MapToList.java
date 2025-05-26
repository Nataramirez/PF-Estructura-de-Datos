package org.acme.utils.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Book;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.tree.BinaryTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ApplicationScoped
public class MapToList {

    public static <T> List<T> simpleLinkedListToList(SimpleLinkedList<T> listT) {
        List<T> list = new ArrayList<>();
        for (T element : listT) {
            list.add(element);
        }
        return list;
    }

    public static <T extends Comparable<T>> List<T> binaryTreeToList(BinaryTree<T> tree) {
        List<T> list = new ArrayList<>();
        for (T element : tree) {
            list.add(element);
        }
        return list;
    }
}
