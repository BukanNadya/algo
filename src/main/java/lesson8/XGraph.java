package lesson8;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * node number === index in edges array
 * vertexCount
 *
 * 0 .. vertexCount - DO EXIST
 */
public class XGraph {
    public final int vertexCount;
    private final List<LinkedList<Integer>> edges;

    public XGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.edges = IntStream.range(0, vertexCount)
                .mapToObj(n -> new LinkedList<Integer>())
                .collect(Collectors.toList());
    }

    public void add(int vertex_from, int vertex_to) {
        LinkedList<Integer> to = edges.get(vertex_from);
        to.add(vertex_to);
    }

    public void remove(int vertex_from, int vertex_to) {
        LinkedList<Integer> to = edges.get(vertex_from);
        Integer to_boxed = vertex_to;
        to.remove(to_boxed);
    }

    public LinkedList<Integer> getEdgesFrom(int vertex_from) {
        return edges.get(vertex_from);
    }
}
