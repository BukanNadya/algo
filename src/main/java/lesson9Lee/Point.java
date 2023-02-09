package lesson9Lee;

public record Point(int x, int y) {

    @Override
    public String toString() {
        return String.format("[%2d,%2d]", x, y);
    }

    static Point of(int x, int y) {
        return new Point(x, y);
    }

    public Point move(int dx, int dy) {
        return Point.of(x + dx, y + dy);
    }

}

