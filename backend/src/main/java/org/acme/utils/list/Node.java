package org.acme.utils.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}
