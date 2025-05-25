package org.acme.model;

import lombok.*;
import org.acme.utils.list.Queue;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
