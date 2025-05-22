package org.acme.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.model.Loan;
import org.acme.utils.list.SimpleLinkedList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Comparable<User> {

    private String id;
    private String name;
    private String password;
    private String user;
    private String state;
    private String role;
    private SimpleLinkedList<Loan> loans;

    @Override
    public int compareTo(User other) {
        return this.user.compareTo(other.user);
    }
}

