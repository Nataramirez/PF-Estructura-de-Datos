package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.book.Book;
import org.acme.model.loan.Loan;
import org.acme.model.enums.book.BookState;
import org.acme.model.enums.loan.LoanState;
import org.acme.model.user.User;
import org.acme.utils.Data;
import org.acme.utils.list.Queue;
import org.acme.utils.list.QueueUtils;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.tree.BinaryTree;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.UUID;

@ApplicationScoped
public class LoanService {

    public SimpleLinkedList<Loan> applyForLoan(String idBook, String userForApply) {

        User user = Data.users.search(User.builder().user(userForApply).build());
        Book book = Data.books.search(Book.builder().id(idBook).build());

        Loan loan = Loan.builder()
                .book(book)
                .loanDate(LocalDate.now())
                .id(UUID.randomUUID().toString())
                .user(user)
                .state(LoanState.WAITING.getValue())
                .build();

        if (book.getState().equals(BookState.AVAILABLE.getValue())) {
            loan.setState(LoanState.LOANED.getValue());
            book.setState(BookState.LOANED.getValue());
        } else {
            book.getPendingLoans().enqueue(loan);
        }

        user.getLoans().insertAtStart(loan);
        Data.loans.insertAtStart(loan);

        return user.getLoans();
    }

    public SimpleLinkedList<Loan> returnLoan(String userString, String idLoan) {

        User user = Data.users.search(User.builder().user(userString).build());
        Loan loan = getLoan(idLoan);
        Book book = Data.books.search(Book.builder().id(loan.getBook().getId()).build());

        loan.setState(LoanState.RETURNED.getValue());

        if (book.getPendingLoans().isEmpty()) {
            book.setState(BookState.AVAILABLE.getValue());
        } else {
            Loan newLoan = book.getPendingLoans().dequeue();
            newLoan.setLoanDate(LocalDate.now());
            newLoan.setState(LoanState.LOANED.getValue()); //TODO: Notificacion usuario prestamo libro
        }

        return user.getLoans();
    }

    public SimpleLinkedList<Loan> cancelLoan(String userString, String idLoan) {
        User user = Data.users.search(User.builder().user(userString).build());
        Loan loan = getLoan(idLoan);
        Book book = Data.books.search(Book.builder().id(loan.getBook().getId()).build());

        if (loan.getState().equals(LoanState.WAITING.getValue())) {
            user.getLoans().remove(loan);
            Data.loans.remove(loan);
            QueueUtils.remove(book.getPendingLoans(), loan);
        }

        return user.getLoans();
    }

    public SimpleLinkedList<Loan> getLoans() {
        return Data.loans;
    }

    public SimpleLinkedList<Loan> getUserLoans(String userString) {
        User user = Data.users.search(User.builder().user(userString).build());
        return user.getLoans();
    }

    public Queue<Loan> getBookLoans(String idBook) {
        Book book = Data.books.search(Book.builder().id(idBook).build());
        return book.getPendingLoans();
    }

    public BinaryTree<Book> qualifyLoan(String idLoan, int qualification) {
        Loan loan = getLoan(idLoan);
        Book book = loan.getBook();

        if (loan.getState().equals(LoanState.RETURNED.getValue())) {
            loan.setScore(qualification);
            book.setScore((book.getScore() + qualification) / 2);
        }

        return Data.books;
    }

    public Loan getLoan(String idLoan) {
        Iterator<Loan> iterator = Data.loans.iterator();
        while (iterator.hasNext()){
            Loan loan = iterator.next();
            if (loan.getId().equals(idLoan)) {
                return loan;
            }
        }
        return null;
    }
}
