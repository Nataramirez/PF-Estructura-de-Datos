package org.acme.utils.list;

import java.util.ArrayList;
import java.util.List;

public class QueueUtils {

    public static <T> boolean remove(Queue<T> queue, T value) {
        if (queue.isEmpty()) return false;

        Queue<T> tempQueue = new Queue<>();
        boolean removed = false;

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            T current = queue.dequeue();
            if (!removed && current.equals(value)) {
                removed = true;
            } else {
                tempQueue.enqueue(current);
            }
        }

        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }

        return removed;
    }

    public static <T> List<T> toList(Queue<T> queue) {
        List<T> list = new ArrayList<>();
        Queue<T> tempQueue = new Queue<>();

        while (!queue.isEmpty()) {
            T value = queue.dequeue();
            list.add(value);
            tempQueue.enqueue(value);
        }

        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }

        return list;
    }
}
