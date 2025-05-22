package org.acme.utils;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Book;
import org.acme.model.user.User;
import org.acme.utils.tree.BinaryTree;

@ApplicationScoped
public class Data {
    public static BinaryTree<User> users = new BinaryTree<User>();
    public static BinaryTree<Book> books = new BinaryTree<Book>();

}
