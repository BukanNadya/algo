package lesson5;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CombinationsApp {

    public static class Pos {
        final int dx;
        final int dy;

        @Override
        public String toString() {
            return String.format("(%s, %s)", dx, dy);
        }

        public Pos(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }
    public static boolean isInRange( int x) {
        return x >= 0 && x <= 7;
    }
    public static boolean isOnBoard( String loc) {
        return isInRange(loc.charAt(0) - 'a') && isInRange(loc.charAt(1) - '1');
    }
    public static String move1 (char c, int delta) {
        return String.valueOf(Character.toChars(c + delta));
    }
    public static String move (String loc, int dx, int dy) {
        return move1(loc.charAt(0), dx) + move1(loc.charAt(1), dy);
    }
    static Supplier<Stream<Integer>> deltas = () -> Stream.of(1, 2).flatMap(x -> Stream.of(-x, x));
    public static List<String> moves(String loc) {
        return deltas.get().flatMap(dx -> deltas.get()
                .map(dy -> new Pos(dx, dy)))
                .filter(pos -> Math.abs(pos.dx)
                        != Math.abs(pos.dy))
                .map(pos -> move(loc, pos.dx, pos.dy)).filter(pos -> isOnBoard(pos)).collect(Collectors.toList());
    }

//    object KM extends App {
//    scala
//        def isInRange(x: Int) = x >= 0 && x <= 7
//        def isOnBoard(loc: String) = isInRange(loc(0) - 'a') && isInRange(loc(1) - '1')
//        def move1(c: Char, delta: Int) = (c + delta).toChar.toString                    // move1('a', 5) => "f"
//        def move(loc: String, dx: Int, dy: Int) = move1(loc(0), dx) + move1(loc(1), dy) // move("a3", 1, 1) => "b4"
//        val deltas = List(1, 2).flatMap(x => List(-x, x))                               // List(-1, 1, -2, 2)
//        def moves(loc: String) =
//        deltas
//                .flatMap(dx => deltas.map(dy => (dx, dy)))
//      .filter { case (dx, dy) => math.abs(dx) != math.abs(dy) }
//      .map { case (dx, dy) => move(loc, dx, dy) }
//      .filter(pos => isOnBoard(pos))
//
//        moves("a1").foreach(println)
//    }

    private static List<String> comb(int n) {
        if (n == 0) return Collections.singletonList("");
        return Stream.of("0", "1")
                .flatMap(p -> comb(n - 1)
                        .stream().map(s -> p + s))
                .collect(Collectors.toList());
    }

    public static Stream<String> comb2(int n) {
        return n == 0 ? Stream.of("") :
                Stream.of("0", "1")
                        .flatMap(p -> comb2(n - 1)
                                .map(s -> p + s));
    }

    private static List<String> comb2ToString(int n) {
        return comb2(n).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        System.out.print(moves("a1"));

    }

    public static void main1(String[] args) {
        Stream.of(1, 2, 3)
                .map(x -> Stream.of(x, x * 10, x * 100))
                .forEach(System.out::println);
    }

}
