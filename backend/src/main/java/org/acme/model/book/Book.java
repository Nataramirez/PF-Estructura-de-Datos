package org.acme.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.model.Loan;
import org.acme.utils.list.Queue;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Comparable<Book> {

    private String id;
    private String name;
    private String author;
    private String category;
    private int score;
    private String state;
    private int year;
    private Queue<Loan> pendingLoans;

    @Override
    public int compareTo(Book other) {
        return this.id.compareTo(other.id);
    }
}
