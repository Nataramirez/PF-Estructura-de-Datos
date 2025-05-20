package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Book;
import org.acme.model.Loan;
import org.acme.model.enums.book.BookCategory;
import org.acme.model.enums.book.BookState;
import org.acme.utils.Data;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.mappers.MapToList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookService {

    @Inject
    MapToList mapToList;

    public boolean createBook(Book book) {
        try {
            if (!BookCategory.isValidCategory(book.getCategory())) return false;
            setInitialDataBook(book);
            Data.books.insert(book);
            System.out.println(Data.books.inOrder());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setInitialDataBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        book.setPendingLoan(new SimpleLinkedList<Loan>());
        book.setQualification(5);
        book.setState(BookState.AVAILABLE.getValue());
    }

    public void deleteBook(String bookId){
        Data.books.deleteValue(Book.builder().id(bookId).build());
        System.out.println(Data.books.inOrder());
    }

    public List<Book> searchBooksCategory(String category) {
        Iterator<Book> iterator = Data.books.iterator();
        SimpleLinkedList<Book> books = new SimpleLinkedList<>();
        while(iterator.hasNext()){
            Book book = iterator.next();
            if (book.getCategory().equals(category)) {
                books.insertAtStart(book);
            }
        }

        return mapToList.simpleLinkedListToList(books);
    }
}
