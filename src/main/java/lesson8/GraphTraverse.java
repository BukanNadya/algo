package lesson8;

import java.util.*;

public class GraphTraverse {

    private final XGraph g;

    public GraphTraverse(XGraph g) {
        this.g = g;
    }

    public List<Integer> bfs(Integer vertex_from) {
        LinkedList<Integer> outcome = new LinkedList<>();
        boolean[] visited = new boolean[g.vertexCount];

        LinkedList<Integer> process = new LinkedList<>();
        visited[vertex_from] = true;
        process.add(vertex_from);

        while (!process.isEmpty()) {
            int curr = process.poll();
            outcome.add(curr);
            LinkedList<Integer> children = g.getEdgesFrom(curr);
            for (int child : children) {
                if (visited[child]) continue;
                visited[child] = true;
                process.add(child);
            }
        }
        return outcome;
    }

    private Collection<Integer> dfs(Integer vertex_from, boolean[] visited, Collection<Integer> outcome) {
        if (visited[vertex_from]) return outcome;
        visited[vertex_from] = true;

        outcome.add(vertex_from);

        LinkedList<Integer> children = g.getEdgesFrom(vertex_from);
        for (int ch : children) {
            dfs(ch, visited, outcome);
        }

        return outcome;
    }

    public Collection<Integer> dfs(Integer vertex_from) {
        return dfs(vertex_from, new boolean[g.vertexCount], new LinkedList<>());
    }

    public Collection<Integer> dfsNR(Integer vertex_from) {
        LinkedList<Integer> outcome = new LinkedList<>();
        Stack<Integer> path = new Stack<>();
        boolean[] visited = new boolean[g.vertexCount];
        visited[vertex_from] = true;
        outcome.add(vertex_from);
        path.push(vertex_from);
        while (!path.isEmpty()) {
            int curr = path.peek();
            LinkedList<Integer> children = g.getEdgesFrom(curr);
            if (children.isEmpty()) {
                path.pop();
                continue;
            }
            for (int child : children) {
                if (!visited[child]) {
                    path.push(child);
                    outcome.add(child);
                    visited[child] = true;
                    break;
                }
                path.pop();
            }
        }
        return outcome;
    }

}
