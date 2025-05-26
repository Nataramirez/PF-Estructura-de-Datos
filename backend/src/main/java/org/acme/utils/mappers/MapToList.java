package org.acme.utils.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.book.Book;
import org.acme.model.book.BookDTO;
import org.acme.model.user.User;
import org.acme.model.user.UserDTO;
import org.acme.utils.list.Queue;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.tree.BinaryTree;

import java.util.ArrayList;
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

    public static <T> List<T> queueToList(Queue<T> queue) {
        List<T> list = new ArrayList<>();
        for (T element : queue) {
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

    public static List<UserDTO> simpleLinkedListUserToList(SimpleLinkedList<User> listUser) {
        List<UserDTO> list = new ArrayList<>();
        for (User user : listUser) {
            list.add(userToUserDTO(user));

        }
        return list;
    }

    public static List<BookDTO> simpleLinkedListBookToList(SimpleLinkedList<Book> booksList) {
        List<BookDTO> list = new ArrayList<>();
        for (Book book : booksList) {
            list.add(bookToBookDTO(book));

        }
        return list;
    }

    public static List<BookDTO> binaryTreeBookToList(BinaryTree<Book> tree) {
        List<BookDTO> list = new ArrayList<>();
        for (Book book : tree) {
            list.add(bookToBookDTO(book));
        }
        return list;
    }

    public static UserDTO userToUserDTO(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .state(user.getState())
                .role(user.getRole())
                .name(user.getName())
                .user(user.getUser())
                .loans(simpleLinkedListToList(user.getLoans()))
                .build();
    }

    public static BookDTO bookToBookDTO(Book book) {

        return BookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .category(book.getCategory())
                .score(book.getScore())
                .state(book.getState())
                .year(book.getYear())
                .pendingLoans(queueToList(book.getPendingLoans()))
                .build();
    }
}