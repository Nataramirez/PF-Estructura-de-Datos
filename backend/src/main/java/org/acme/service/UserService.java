package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Loan;
import org.acme.model.enums.loan.LoanState;
import org.acme.model.enums.user.UserRole;
import org.acme.model.enums.user.UserState;
import org.acme.model.user.User;
import org.acme.utils.Data;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.tree.BinaryTree;

import java.util.UUID;

@ApplicationScoped
public class UserService {

    public User createUser(User user) {
        setInitialDataUser(user);
        Data.users.insert(user);
        return user;
    }

    public void setInitialDataUser(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setLoans(new SimpleLinkedList<Loan>());
        user.setState(UserState._1_DISABLED.getValue());
    }

    public User userAuth(String user, String password) {
        User userObject = Data.users.search(User.builder().user(user).build());
        if (userObject != null && userObject.getPassword().equals(password)) {
            return userObject;
        }

        return null;
    }

    public BinaryTree<User> deleteUser(String userId) throws Exception {

        User user = Data.users.search(User.builder().id(userId).build());

        if (user != null) {
            boolean safeDelete = true;

            for (Loan loan : user.getLoans()) {
                if (loan.getState().equals(LoanState.LOANED.getValue())) {
                    safeDelete = false;
                    break;
                }
            }

            if (safeDelete) {
                Data.users.deleteValue(user);
                return Data.users;
            } else {
                throw new Exception("No es posible eliminar el usuario debido a que aún cuenta con prestamos pendientes.");
            }
        } else {
            throw new Exception("El usuario enviado para eliminacion no ha sido encontrado.");
        }
    }
}
