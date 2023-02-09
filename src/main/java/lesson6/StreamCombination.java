package lesson6;

import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCombination {
    // a..d
    // 1..4
    // a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4
    public static void mainProblem(String[] args) {
        Stream<Integer> s1 = IntStream.rangeClosed(1, 4).boxed();
        Stream<Character> s2 = IntStream.rangeClosed('a', 'd').mapToObj(x -> (char)x);
        s2
                .flatMap(x ->                       // a,   // b
                        s1.map(y ->                     // 1..4 //
                                String.format("%s%s", x, y)
                        )
                )
                .forEach(System.out::println);

    }

    public static Stream<Integer> makeStream() {
        return IntStream.rangeClosed(1, 4).boxed();
    }

    // 11 12 13 14 21 22 23 24 ...
    public static void main0(String[] args) {
        Stream<Integer>  s1a =       IntStream.rangeClosed(1, 4).boxed();
        Supplier<Stream<Integer>> s1b = () -> IntStream.rangeClosed(1, 4).boxed();

        Stream<Integer> s1b1 = s1b.get();
        Stream<Integer> s1b2 = s1b.get();
        Stream<Integer> s1b3 = s1b.get();

        Stream<Integer> s1c1 = makeStream();
        Stream<Integer> s1c2 = makeStream();
        Stream<Integer> s1c3 = makeStream();

    }

    public static void mainSolution(String[] args) {
        Supplier<Stream<Integer>> s1 = () -> IntStream.rangeClosed(1, 4).boxed();
        Stream<Character> s2 = IntStream.rangeClosed('a', 'd').mapToObj(x -> (char)x);
        s2
                .flatMap(x ->                             // a,   // b
                        s1.get().map(y ->                     // 1..4 // 1..4
                                String.format("%s%s", x, y)
                        )
                )
                .forEach(System.out::println);
    }

}


