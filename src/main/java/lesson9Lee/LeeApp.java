package lesson9Lee;

import java.util.List;
import java.util.Optional;

public class LeeApp {

    public static void main(String[] args) {
        Lee lee = new Lee(20, 15);
        Point from = Point.of(0,0);
        Point to = Point.of(19, 14);
        List<Point> obstacles = List.of(
                Point.of(5, 14),
                Point.of(5, 13),
                Point.of(5, 12),
                Point.of(5, 11),
                Point.of(5, 10),
                Point.of(5, 9),
                Point.of(5, 8),
                Point.of(5, 7),
                Point.of(5, 6),
                Point.of(5, 5),
                Point.of(5, 4),
                Point.of(5, 3),
                Point.of(5, 2),
                Point.of(5, 1),
//                Point.of(5, 0),
                Point.of(10, 0),
                Point.of(10, 1),
                Point.of(10, 2),
                Point.of(10, 3),
                Point.of(10, 4),
                Point.of(10, 5),
                Point.of(10, 6),
                Point.of(10, 7),
                Point.of(10, 8)
        );
        Optional<List<Point>> trace = lee.trace(from, to, obstacles);
        System.out.println(trace);
    }

}
