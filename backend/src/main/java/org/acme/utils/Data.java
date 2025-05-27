package org.acme.utils;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.book.Book;
import org.acme.model.loan.Loan;
import org.acme.model.user.User;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.tree.BinaryTree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class Data {
    public static BinaryTree<User> users = new BinaryTree<User>();
    public static BinaryTree<Book> books = new BinaryTree<Book>();
    public static SimpleLinkedList<Loan> loans = new SimpleLinkedList<>();
    public static Map<User, List<User>> affinityGraph = new HashMap<>();
}
