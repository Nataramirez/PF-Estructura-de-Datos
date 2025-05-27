package org.acme.service;

import jakarta.inject.Inject;
import org.acme.model.book.Book;
import org.acme.model.user.User;
import org.acme.utils.Data;
import org.acme.utils.mappers.MapToList;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsService {

    @Inject
    MapToList mapToList;

    // 1. Cantidad de préstamos por lector
    public Map<String, Integer> getLoanCountPerUser() {
        Map<String, Integer> result = new HashMap<>();
        for (User user : Data.users.toList()) {
            result.put(user.getName(), user.getLoans().size());
        }
        return result;
    }

    public List<Book> getTopRatedBooks(int topN) {
        List<Book> books = mapToList.simpleLinkedListToList(Data.books.toList());
        books.sort(Comparator.comparingInt(Book::getScore).reversed());
        return books.subList(0, Math.min(topN, books.size()));
    }


    // 3. Lectores con más conexiones (afinidad)
    public Map<User, Integer> getMostConnectedUsers(Map<User, List<User>> affinityGraph) {
        Map<User, Integer> result = new HashMap<>();
        for (User user : affinityGraph.keySet()) {
            result.put(user, affinityGraph.get(user).size());
        }
        return result.entrySet().stream()
                .sorted(Map.Entry.<User, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> b, LinkedHashMap::new));
    }

    // 4. Camino más corto entre dos lectores (BFS)
    public List<User> getShortestPath(User start, User end, Map<User, List<User>> affinityGraph) {
        Queue<List<User>> queue = new LinkedList<>();
        Set<User> visited = new HashSet<>();

        queue.add(List.of(start));
        visited.add(start);

        while (!queue.isEmpty()) {
            List<User> path = queue.poll();
            User last = path.get(path.size() - 1);
            if (last.equals(end)) return path;

            for (User neighbor : affinityGraph.getOrDefault(last, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<User> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        return new ArrayList<>(); // sin camino
    }

    // 5. Detectar clústeres (componentes conexas usando DFS)
    public List<Set<User>> getAffinityClusters(Map<User, List<User>> affinityGraph) {
        List<Set<User>> clusters = new ArrayList<>();
        Set<User> visited = new HashSet<>();

        for (User user : affinityGraph.keySet()) {
            if (!visited.contains(user)) {
                Set<User> cluster = new HashSet<>();
                dfs(user, affinityGraph, visited, cluster);
                clusters.add(cluster);
            }
        }

        return clusters;
    }

    private void dfs(User user, Map<User, List<User>> graph, Set<User> visited, Set<User> cluster) {
        visited.add(user);
        cluster.add(user);
        for (User neighbor : graph.getOrDefault(user, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, cluster);
            }
        }
    }
}
