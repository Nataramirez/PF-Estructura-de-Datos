package org.acme.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.loan.Loan;
import org.acme.model.enums.loan.LoanState;
import org.acme.model.enums.user.UserState;
import org.acme.model.user.User;
import org.acme.utils.Data;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.mappers.MapToList;
import org.acme.utils.tree.BinaryTree;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    @Inject
    MapToList mapToList;

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("users.json");
    private static boolean alreadyLoaded = false;

    public UserService() {
        if (!alreadyLoaded) {
            System.out.println("🚀 Iniciando UserService...");
            loadUsersFromFile();
            alreadyLoaded = true;
        }
    }

    public void loadUsersFromFile() {
        if (!file.exists()) return;
        try {
            List<User> users = mapper.readValue(file, new TypeReference<>() {});
            Data.users.clearTree();
            users.forEach(Data.users::insert);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUsersToFile() throws IOException {
        try {
            List<User> userList = mapToList.simpleLinkedListToList(Data.users.toList());
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User createUser(User user) {
        setInitialDataUser(user);
        Data.users.insert(user);
        try {
            saveUsersToFile();
        } catch (IOException e) {
            System.err.println("❌ Error al guardar usuarios: " + e.getMessage());
        }
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

    public  BinaryTree<User> getUsers() {
        return Data.users;
    }

    public BinaryTree<User> deleteUser(String userId) throws Exception {

        User user = Data.users.search(User.builder().user(userId).build());
        System.out.println(user);

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
                try {
                    saveUsersToFile();
                } catch (IOException e) {
                    System.err.println("❌ Error al guardar usuarios: " + e.getMessage());
                }
                return Data.users;
            } else {
                throw new Exception("No es posible eliminar el usuario debido a que aún cuenta con prestamos pendientes.");
            }
        } else {
            throw new Exception("El usuario enviado para eliminacion no ha sido encontrado.");
        }
    }

    public BinaryTree<User> updateUser(User userUpdate) throws Exception {
        User user = Data.users.search(userUpdate);
        if (user == null) {
            throw new Exception("Usuario no encontrado para actualización.");
        }

        user.setName(userUpdate.getName());
        user.setPassword(userUpdate.getPassword());

        try {
            saveUsersToFile();
        } catch (IOException e) {
            System.err.println("❌ Error al guardar usuarios: " + e.getMessage());
        }

        return Data.users;
    }

}
