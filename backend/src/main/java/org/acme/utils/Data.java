package org.acme.utils;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.book.Book;
import org.acme.model.Loan;
import org.acme.model.user.User;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.tree.BinaryTree;

@ApplicationScoped
public class Data {
    public static BinaryTree<User> users = new BinaryTree<User>();
    public static BinaryTree<Book> books = new BinaryTree<Book>();
    public static SimpleLinkedList<Loan> loans = new SimpleLinkedList<>();

}
