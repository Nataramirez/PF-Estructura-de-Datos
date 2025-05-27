package org.acme.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.model.loan.Loan;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String id;
    private String name;
    private String author;
    private String category;
    private int score;
    private String state;
    private int year;
    private List<Loan> pendingLoans;


}
