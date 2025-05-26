package org.acme.model.loan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.model.book.BookDTO;
import org.acme.model.user.UserDTO;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
    private String id;
    private BookDTO book;
    private UserDTO user;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private String state;
    private int score;
}
