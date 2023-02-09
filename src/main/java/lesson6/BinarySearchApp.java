package lesson6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class BinarySearchApp {

    /** data[] must be sorted !!! */
    public static boolean binarySearchContains(int[] data, int x) {
        int l = 0;
        int r = data.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if      (x < data[m]) r = m - 1; // go left
            else if (x > data[m]) l = m + 1; // go right
            else /* x == data[m] */ return true;
        }
        return false;
    }

    /** data[] must be sorted !!!
     *  result > 0  - position
     *  result = -1 - not found
     */
    public static int binarySearchIndex(int[] data, int x) {
        int l = 0;
        int r = data.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if      (x < data[m]) r = m - 1; // go left
            else if (x > data[m]) l = m + 1; // go right
            else /* x == data[m] */ return m;
        }
        return -1;
    }

    /** data[] must be sorted !!!
     *  result >= 0  - position
     *  result < 0  - not found, but position to insert (-1)
     */
    public static int binarySearchIndex2(int[] data, int x) {
        int l = 0;
        int r = data.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if      (x < data[m]) r = m - 1; // go left
            else if (x > data[m]) l = m + 1; // go right
            else /* x == data[m] */ return m;
        }
        return -l;
    }

//  public static SResult binarySearchIndex3(int[] data, int x) {
//    int l = 0;
//    int r = data.length - 1;
//    while (l <= r) {
//      int m = (l + r) / 2;
//      if      (x < data[m]) r = m - 1; // go left
//      else if (x > data[m]) l = m + 1; // go right
//      else /* x == data[m] */ return new SResult(true, m);
//    }
//    return new SResult(false, l);
//  }

    public static SResult binarySearchIndex3(int[] data, int x) {
        int l = 0;
        int r = data.length - 1;
        while (l <= r) {
//        (r >= l)
            int m = (l + r) / 2;
            if      (x < data[m]) r = m - 1; // go left
            else if (x > data[m]) l = m + 1; // go right
            else /* x == data[m] */ return SResult.Found(m);
        }
        return SResult.NotFound(l);
    }

    public static SResult binarySearchIndex3R(int[] data, int x, int l, int r) {
        if (l > r) return SResult.NotFound(l);
        int m = (l + r) / 2;
        if      (x < data[m]) return binarySearchIndex3R(data, x, l, m - 1); // go left
        else if (x > data[m]) return binarySearchIndex3R(data, x, m + 1, r); // go right
        else /* x == data[m] */ return SResult.Found(m);
    }

    public static SResult binarySearchIndex3R(int[] data, int x) {
        return binarySearchIndex3R(data, x, 0, data.length);
    }

    static class Person {
        public final Email email;
        public final String name;

        public Person(Email email, String name) {
            this.email = email;
            this.name = name;
        }
    }

    static class Email {
        public final String value;

        Email(String value) {
            this.value = value;
        }

        public static Email of(String value) {
            return new Email(value);
        }
    }

    // easy to mix
    public void doSomething1(String p1, String p2) {

    }

    // easy to mix
    public void doSomething2(String p1, String p2, Boolean isDone, Boolean isOnBoard, Boolean isWhite) {

    }

    // good
    public <A> int  binarySearch41(A[] xs, Comparator<A> c) {
        return c.compare(xs[0], xs[1]);
    }

    // better - will not compile
    public <A extends Comparable<A>> int  binarySearch41(A[] xs) {
        return xs[0].compareTo(xs[1]);
    }

    // worse
    public <A> int  binarySearch41(A[] xs) {
        return ((Comparable)xs[0]).compareTo(xs[1]);
    }

    public static void main(String[] args) {
        int[] randoms = IntStream
                .generate(() -> (int) (Math.random() * 100))
                .limit(30)
                .sorted()
                .toArray();

        System.out.println(Arrays.toString(randoms));
        System.out.println(binarySearchIndex3(randoms, randoms[7]));
        System.out.println(binarySearchIndex3(randoms, 1000));

//    System.out.println(binarySearchContains(randoms, 1000));
//    System.out.println(binarySearchContains(randoms, randoms[7]));

        new Person(Email.of("jim@gmail.com"), "Jim");
//    new Person(Email.of("Jim"), "Jim");

        ArrayList<String> l1 = new ArrayList<>();
        String x = null; // ....
        l1.add(x);

        ArrayList<Email> l2 = new ArrayList<>();
    }

}

