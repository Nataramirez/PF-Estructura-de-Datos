package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Book;
import org.acme.model.Loan;
import org.acme.model.User;
import org.acme.model.enums.book.BookCategory;
import org.acme.model.enums.book.BookState;
import org.acme.model.enums.user.UserRole;
import org.acme.model.enums.user.UserState;
import org.acme.utils.Data;
import org.acme.utils.list.SimpleLinkedList;

import java.util.UUID;

@ApplicationScoped
public class UserService {

    public boolean createUser(User user) {
        try {
            setInitialDataUser(user);
            Data.users.insert(user);
            System.out.println(Data.users.inOrder());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setInitialDataUser(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setRole(UserRole.USER.getValue());
        user.setLoans(new SimpleLinkedList<Loan>());
        user.setState(UserState._1_DISABLED.getValue());
    }

    public boolean userAuth(String user, String password) {
        User userObject = Data.users.search(User.builder().user(user).build());
        if (userObject != null && userObject.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
