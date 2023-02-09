package lesson7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ResultTest {

    static class PlusesAt {
        int x1;
        int y1;
        int x2;
        int y2;
        int size1;
        int size2;

        PlusesAt(int x1, int y1, int x2, int y2, int size1, int size2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.size1 = size1;
            this.size2 = size2;
        }
    }

    static class FitResult {
        final int size1;
        final int size2;
        final boolean ok;

        public FitResult(int size1, int size2, boolean ok) {
            this.size1 = size1;
            this.size2 = size2;
            this.ok = ok;
        }
    }

    static List<PlusesAt> twoPlusesList = new ArrayList<>();
    static void markHorWith(int x, int y, int size, char[][] grid, char value) {
        IntStream.range(0, size - 1)
                .forEach(i -> grid[y][x + i] = value);
    }

    static void markVerWith(int x, int y, int size, char[][] grid, char value) {
        IntStream.range(0, size - 1)
                .forEach(i -> grid[y + i][x] = value);
    }


    static boolean markOccupied(PlusesAt ps, char[][] grid) {
        Optional<Stream<Character>> horCar = Optional.of(Stream.of(0, ps.size1 - 1).map(i -> grid[ps.y1][ps.x1 + i]).filter(i -> i.equals('P')));
        Optional<Stream<Character>> verCar = Optional.of(Stream.of(0, ps.size2 - 1).map(i -> grid[ps.y1 + i][ps.x1]).filter(i -> i.equals('P')));
//        if (horCar.isPresent() || verCar.isPresent()) return false;
        markHorWith(ps.x1, ps.y1 + ps.size1 / 2, ps.size1, grid, 'P');
        markVerWith(ps.x2 + ps.size2 / 2, ps.y2, ps.size2, grid, 'P');
        System.out.println(grid[0]);
        System.out.println(grid[1]);
        System.out.println(grid[2]);
        System.out.println(grid[3]);
        System.out.println(grid[4]);
        System.out.println("__________");
        return true;
    }

    static void makeFreeAgain(PlusesAt ps, char[][] grid) {
        markHorWith(ps.x1, ps.y1 + ps.size1 / 2, ps.size1, grid, 'G');
        markVerWith(ps.x2 + ps.size2 / 2, ps.y2, ps.size2, grid, 'G');
        System.out.println(grid[0]);
        System.out.println(grid[1]);
        System.out.println(grid[2]);
        System.out.println(grid[3]);
        System.out.println(grid[4]);
        System.out.println("__________");
    }

    static FitResult tryToFit(PlusesAt ps, char[][] grid) {
        // mark 1 st
        markOccupied(ps, grid);
        twoPlusesList.add(ps);
        // check 2nd
        if (twoPlusesList.size() == 2) {
            // unMark 1 st
            makeFreeAgain(twoPlusesList.get(0), grid);
            makeFreeAgain(twoPlusesList.get(0), grid);
        }
        return new FitResult(twoPlusesList.get(0).size1, twoPlusesList.get(1).size1, true);
    }

    static int area1(int size) {
        return size * 2 - 1;
    }

    static int area(FitResult fitResult) {
        return area1(fitResult.size1) * area1(fitResult.size2);
    }

    static char[][] conv(List<String> grid) {
        return grid.stream()
                .map(l -> l.toCharArray())
                .toArray(char[][]::new);
    }

    static void checkPluses(PlusesAt ps, int hc, int vc, char[][] grid) {
        ps.x1--;
        ps.x2++;
        ps.y1--;
        ps.y2++;
        if (ps.x1 >= 0 && ps.y1 >= 0 && ps.x2 < grid[0].length && ps.y2 < grid.length
                && grid[vc][ps.x1] == 'G' && grid[vc][ps.x2] == 'G' && grid[ps.y1][hc] == 'G' && grid[ps.y2][hc] == 'G') {
            ps.size1 += 2;
            ps.size2 += 2;
            new PlusesAt(ps.x1, ps.y1, ps.x2, ps.y2, ps.size1, ps.size1);
            checkPluses(ps, hc, vc, grid);
        }
            ps.x1++;
            ps.x2--;
            ps.y1++;
            ps.y2--;
    }

    static PlusesAt goodPluses(PlusesAt ps, char[][] grid) {
        int hc = ps.x1;
        int vc = ps.y1;
        if (grid[hc][vc] != 'G') {
            ps.size1 = 0;
            ps.size2 = 0;
            return ps;
        }
        List<Integer> hor = Stream.of(-1, 1)
                .flatMap(x -> Stream.of(hc).map(h -> h + x)).collect(Collectors.toList());
        List<Integer> ver = Stream.of(-1, 1)
                .flatMap(y -> Stream.of(vc).map(v -> v + y)).collect(Collectors.toList());
        if (hor.get(0) < 0 || ver.get(0) < 0 || hor.get(1) >= grid[0].length || ver.get(1) >= grid.length) return ps;
        if (grid[vc][hor.get(0)] == 'G' && grid[vc][hor.get(1)] == 'G' && grid[ver.get(0)][hc] == 'G' && grid[ver.get(1)][hc] == 'G') {
            checkPluses(ps, hc, vc, grid);
        }
        return ps;
    }

    public static int twoPluses(char[][] grid) {
        int h = grid.length;
        int v = grid[0].length;
        return IntStream.range(0, h).boxed().flatMap(x ->
                        IntStream.range(0, v).boxed().map(y -> // create minimal pluses
                                new PlusesAt(x, y, x, y, 1, 1)))
                .map(ps -> goodPluses(ps, grid)) // growth and validation pluses
                .filter(ps -> ps.size1 > 0)
                .map(ps -> tryToFit(ps, grid))
                .filter(r -> r.ok)
                .map(ResultTest::area)
                .max(Comparator.comparingInt(x -> x))
                .orElseThrow(RuntimeException::new);
    }

    public static void main(String[] args) {

        char[][] grid = {
                {'G', 'G', 'G', 'G', 'G', 'G'},
                {'G', 'B', 'B', 'B', 'G', 'B'},
                {'G', 'G', 'G', 'G', 'G', 'G'},
                {'G', 'G', 'B', 'B', 'G', 'B'},
                {'G', 'G', 'G', 'G', 'G', 'G'}};

        System.out.println(ResultTest.twoPluses(grid));
    }

}


