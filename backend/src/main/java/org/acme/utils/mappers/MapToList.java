package org.acme.utils.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.user.User;
import org.acme.model.user.UserDTO;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MapToList {

    public static <T> List<T> simpleLinkedListToList(SimpleLinkedList<T> listT) {
        List<T> list = new ArrayList<>();
        for (T element : listT) {
            list.add(element);
        }
        return list;
    }


    public static <T extends Comparable<T>> List<T> binaryTreeToList(BinaryTree<T> tree) {
        List<T> list = new ArrayList<>();
        for (T element : tree) {
            list.add(element);
        }
        return list;
    }

    public static List<UserDTO> simpleLinkedListUserToList(SimpleLinkedList<User> listUser) {
        List<UserDTO> list = new ArrayList<>();
        for (User user : listUser) {
            list.add(userToUserDTO(user));

        }
        return list;
    }

    public static UserDTO userToUserDTO(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .state(user.getState())
                .role(user.getRole())
                .name(user.getName())
                .user(user.getUser())
                .loans(simpleLinkedListToList(user.getLoans()))
                .build();
    }
}
