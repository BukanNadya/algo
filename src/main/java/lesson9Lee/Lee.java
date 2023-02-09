package lesson9Lee;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lee {
    private final static int EMPTY = 0;
    private final static int OBSTACLE = -9;
    private final static int START = 1;
    private final int width;
    private final int height;
    private final int[][] board;

    public Lee(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[height][width];
    }

    private int get(int x, int y) {
        return board[y][x];
    }

    private int get(Point point) {
        return get(point.x(), point.y());
    }

    private void set(int x, int y, int value) {
        board[y][x] = value;
    }

    private void set(Point point, int value) {
        set(point.x(), point.y(), value);
    }

    private boolean isOnBoard(Point point) {
        return point.x() >=0 && point.y() >=0 && point.x() < width && point.y() < height;
    }

    private boolean isUnvisited(Point p) {
        return get(p) == EMPTY;
    }

    private final Supplier<Stream<Point>> deltas = () -> Stream.of(
            Point.of(-1, 0),
            Point.of(1, 0),
            Point.of(0, -1),
            Point.of(0, 1)
    );

    private Stream<Point> neighbours(Point p) {
        return deltas.get()
                .map(d -> p.move(d.x(), d.y()))
                .filter(p0 -> isOnBoard(p0));
    }

    private Stream<Point> neighboursUnvisited(Point p) {
        return neighbours(p)
                .filter(this::isUnvisited);
    }

    private List<Point> neighboursByValue(Point p, int value) {
        return neighbours(p)
                .filter(p0 -> get(p0) == value)
                .collect(Collectors.toList());
    }

    private void initializeBoard(List<Point> obstacles) {
        obstacles.forEach(p -> set(p, OBSTACLE));
    }

    public Optional<List<Point>> trace(Point start, Point finish, List<Point> obstacles) {
        // 0. initialize algorithm
        initializeBoard(obstacles);
        boolean found = false;
        int counter[] = { START };
        set(start, counter[0]);
        // 1. fill
//      for (Set<Point> curr = new HashSet<>(Set.of(start));!(found || curr.isEmpty());counter[0]++) {
//        Set<Point> next = curr.stream()
//            .flatMap(this::neighboursUnvisited)
//            .collect(Collectors.toSet());
//        next.forEach(p -> set(p, counter[0]));
//        found = next.contains(finish);
//        curr = next;
//      }
        // 1. fill
        {
            Set<Point> curr = new HashSet<>();
            curr.add(start);
            while (!(found || curr.isEmpty())) {
                counter[0]++;
                Set<Point> next = curr.stream()
                        .flatMap(this::neighboursUnvisited)
                        .collect(Collectors.toSet());
                next.forEach(p -> set(p, counter[0]));
                found = next.contains(finish);
                curr = next;
            }
        }
        // 2. check
        if (!found) return Optional.empty();
        // 3. trace back (reconstruct path)
        System.out.println(boardFormatted(List.of()));
        LinkedList<Point> path = new LinkedList<>();
        path.add(finish);
        Point curr = finish;
        while (counter[0] > START) {
            counter[0]--;
            Point prev = neighboursByValue(curr, counter[0]).get(0);
            if (prev == start) break;
            path.addFirst(prev);
            curr = prev;
        }
        return Optional.of(path);
    }

    public String cellFormatted(Point p, List<Point> path) {
        int value = get(p);
        if (value == OBSTACLE) return " XX";
        if (path.isEmpty())    return String.format("%3d", value);
        if (path.contains(p))  return String.format("%3d", value);
        return " --";
    }

    public String boardFormatted(List<Point> path) {
        return IntStream.range(0, height).mapToObj(y ->
                IntStream.range(0, width)
                        .mapToObj(x -> Point.of(x, y))
                        .map(p -> cellFormatted(p, path))
                        .collect(Collectors.joining())
        ).collect(Collectors.joining("\n"));
    }

}
