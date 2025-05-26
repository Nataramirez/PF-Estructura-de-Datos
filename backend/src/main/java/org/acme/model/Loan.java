package org.acme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.model.book.Book;
import org.acme.model.user.User;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    private String id;
    private Book book;
    private User user;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private String state;
    private int score;
}
