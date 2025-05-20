package org.acme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.utils.list.SimpleLinkedList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Comparable<Book> {

    private String id;
    private String name;
    private String author;
    private String category;
    private int qualification;
    private String state;
    private int year;
    private SimpleLinkedList<Loan> pendingLoan;

    @Override
    public int compareTo(Book other) {
        return this.id.compareTo(other.id);
    }
}
