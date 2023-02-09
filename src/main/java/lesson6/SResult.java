package lesson6;

/** */
public class SResult {
    public final boolean found;
    public final int position;

    private SResult(boolean found, int position) {
        this.found = found;
        this.position = position;
    }

    static SResult Found(int index) {
        return new SResult(true, index);
    }

    static SResult NotFound(int index) {
        return new SResult(false, index);
    }

    @Override
    public String toString() {
        return String.format("SResult[found=%s, pos=%d]", found, position);
    }
}

