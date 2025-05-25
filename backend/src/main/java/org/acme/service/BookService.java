package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Book;
import org.acme.model.enums.book.BookCategory;
import org.acme.model.enums.book.BookState;
import org.acme.utils.Data;
import org.acme.utils.list.Queue;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.tree.BinaryTree;

import java.util.Iterator;
import java.util.UUID;

@ApplicationScoped
public class BookService {

    public BinaryTree<Book> createBook(Book book) throws Exception {
        if (!BookCategory.isValidCategory(book.getCategory())) {
            throw new Exception("La categoria enviada no es válida");
        }
        setInitialDataBook(book);
        Data.books.insert(book);
        return getBooks();
    }

    public void setInitialDataBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        book.setPendingLoans(new Queue<>());
        book.setScore(0);
        book.setState(BookState.AVAILABLE.getValue());
    }

    //TODO: Validar que el libro no se encuentre prestado
    public BinaryTree<Book> deleteBook(String bookId) {
        Data.books.deleteValue(Book.builder().id(bookId).build());
        return getBooks();
    }

    public SimpleLinkedList<Book> searchBooksCategory(String category) {
        Iterator<Book> iterator = Data.books.iterator();
        SimpleLinkedList<Book> books = new SimpleLinkedList<>();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getCategory().equals(category)) {
                books.insertAtStart(book);
            }
        }

        return books;
    }

    public SimpleLinkedList<Book> searchBooksNameOrAuthor(String param) {
        Iterator<Book> iterator = Data.books.iterator();
        SimpleLinkedList<Book> books = new SimpleLinkedList<>();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getAuthor().contains(param) || book.getName().contains(param)) {
                books.insertAtStart(book);
            }
        }
        return books;
    }

    public BinaryTree<Book> getBooks() { return Data.books; }
}
