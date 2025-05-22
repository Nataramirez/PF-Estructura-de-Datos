package org.acme.utils.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Book;
import org.acme.utils.Data;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.tree.BinaryTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ApplicationScoped
public class MapToList {

    public List<Book> simpleLinkedListToList(SimpleLinkedList<Book> books){
        List<Book> listBooks = new ArrayList<>();
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()){
            listBooks.add(iterator.next());
        }

        return listBooks;
    }

    public List<Book> BinaryTreeToList(BinaryTree<Book> books){
        List<Book> listBooks = new ArrayList<>();
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()){
            listBooks.add(iterator.next());
        }
        return listBooks;
    }


}
