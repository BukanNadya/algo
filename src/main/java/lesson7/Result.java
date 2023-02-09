package lesson7;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Result {

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

    static boolean isFreeHorAt(int x, int y, int size, char[][] grid) {
        return IntStream.range(0, size)
                .allMatch(i -> grid[y][x + i] != 0);
    }

    static boolean isFreeVerAt(int x, int y, int size, char[][] grid) {
        return IntStream.range(0, size)
                .allMatch(i -> grid[y + i][x] != 0);
    }

    static boolean isFreeAt(int x, int y, int size, char[][] grid) {
        return isFreeHorAt(x, y + size / 2, size, grid) && isFreeVerAt(x + size / 2, y, size, grid);
    }

    static void markHorWith(int x, int y, int size, char[][] grid, char value) {
        IntStream.range(0, size)
                .forEach(i -> grid[y][x + i] = value);
    }

    static void markVerWith(int x, int y, int size, char[][] grid, char value) {
        IntStream.range(0, size)
                .forEach(i -> grid[y + i][x] = value);
    }

    static void markOccupied(int x, int y, int size, char[][] data) {
        markHorWith(x, y, size, data, 'P');
        markVerWith(x, y, size, data, 'P');
    }

    static void makeFreeAgain(int x, int y, int size, char[][] data) {
        markHorWith(x, y, size, data, 'G');
        markVerWith(x, y, size, data, 'G');
    }

    static FitResult tryToFit(PlusesAt ps, char[][] grid) {
        // check 1st
        if (!isFreeAt(ps.x1, ps.y1, ps.size1, grid)) return new FitResult(ps.size1, ps.size2, false);
        // mark 1 st
        markOccupied(ps.x1, ps.y1, ps.size1, grid);

        // check 2nd
        boolean isSecond = isFreeAt(ps.x2, ps.y2, ps.size2, grid);

        // unMark 1 st
        makeFreeAgain(ps.x1, ps.y1, ps.size1, grid);

        return new FitResult(ps.size1, ps.size2, isSecond);
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

    static void checkPluses(PlusesAt ps, int hc, int vc, List<Integer> hor, List<Integer> ver, char[][] grid) {
        ps.x1--;
        ps.x2++;
        ps.y1--;
        ps.y2++;
        if (grid[vc][hor.get(0)] != 'G' || grid[vc][hor.get(1)] != 'G' || grid[ver.get(0)][hc] != 'G' || grid[ver.get(1)][hc] != 'G' ||
                hor.get(0) < 0 || ver.get(0) < 0 || hor.get(1) > grid[0].length || ver.get(1) > grid.length) {
            ps.x1++;
            ps.x2--;
            ps.y1++;
            ps.y2--;
            return;
        }
        ps.size1 = (ps.x2 - ps.x1) + 1;
        ps.size2 = (ps.y2 - ps.y1) + 1;
        checkPluses(ps, hc, vc, hor, ver, grid);
    }

    static PlusesAt goodPluses(PlusesAt ps, char[][] grid) {
        int hc = ps.x1 + (ps.x2 - ps.x1) >> 2;
        int vc = ps.y1 + (ps.y2 - ps.y1) >> 2;
        if (grid[hc][vc] != 'G') {
            ps.x1 = hc;
            ps.x2 = hc;
            ps.y1 = vc;
            ps.y2 = vc;
            ps.size1 = 0;
            ps.size2 = 0;
            return ps;
        }
        List<Integer> hor = Stream.of(-1, 1)
                .flatMap(x -> Stream.of(hc).map(h -> h + x)).collect(Collectors.toList());
        List<Integer> ver = Stream.of(-1, 1)
                .flatMap(y -> Stream.of(vc).map(v -> v + y)).collect(Collectors.toList());
        if (hor.get(0) < 0 || ver.get(0) < 0 || hor.get(1) > grid[0].length || ver.get(1) > grid.length) return ps;
        if (grid[vc][hor.get(0)] == 'G' || grid[vc][hor.get(1)] == 'G' || grid[ver.get(0)][hc] == 'G' || grid[ver.get(1)][hc] == 'G') {
            ps.x1 = hor.get(0);
            ps.x2 = hor.get(1);
            ps.y1 = ver.get(0);
            ps.y2 = ver.get(1);
            ps.size1 = (ps.x2 - ps.x1) + 1;
            ps.size2 = (ps.y2 - ps.y1) + 1;
            checkPluses(ps, hc, vc, hor, ver, grid);
        }
        ps.x1 = hc;
        ps.x2 = hc;
        ps.y1 = vc;
        ps.y2 = vc;
        ps.size1 = 1;
        ps.size2 = 1;
        return ps;
    }

    public static int twoPluses(List<String> grid0) {
        char[][] grid = conv(grid0);
        int h = grid0.size();
        int v = grid0.get(0).length();
        int min = Math.min(h, v);
        int max = (min & 1) == 0 ? min - 1 : min;
        // O(max * max * h * h * v *v)
        // 1..max
        return IntStream.rangeClosed(1, max).filter(s1 -> (s1 & 1) == 1).boxed().flatMap(plus1size ->
                        // 1..max
                        IntStream.rangeClosed(1, max).filter(s2 -> (s2 & 1) == 1).boxed().flatMap(plus2size ->
                                // 0..h 1st
                                IntStream.range(0, h).filter(h0 -> h0 < h - plus1size).boxed().flatMap(x1 ->
                                        // 0..h 2nd
                                        IntStream.range(0, h).filter(h0 -> h0 < h - plus2size).boxed().flatMap(x2 ->
                                                // 0..v 1st
                                                IntStream.range(0, v).filter(v0 -> v0 < v - plus1size).boxed().flatMap(y1 ->
                                                        // 0..v 2nd
                                                        IntStream.range(0, v).filter(v0 -> v0 < v - plus2size).boxed().map(y2 ->
                                                                new PlusesAt(x1, y1, x2, y2, plus1size, plus2size)
                                                        )
                                                )
                                        )
                                )
                        )
                )
                .map(ps -> goodPluses(ps, grid))
                .map(ps1 -> tryToFit(ps1, grid))
                .filter(r1 -> r1.ok)
                .map(Result::area)
                .max(Comparator.comparingInt(x -> x))
                .orElseThrow(RuntimeException::new);
    }


}

