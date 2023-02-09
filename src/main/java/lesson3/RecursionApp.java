package lesson3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class RecursionApp {

    public void a(int x) {
        a(x);
    }

    public int fact3(int n) {
        if (n == 0) return 1;
        return n * fact(n - 1);
    }

    // ~10.000
    // simple, declarative, stack for free
    public int fact(int n) {
        if (n == 0) return 1;
        int n1 = fact(n - 1);
        int x = n * n1;
        return x;
    }

    private int fact2(int n, int r) {
        if (n == 0) return r;
        return fact2(n - 1, r * n);
    }

    public int fact2(int n) {
        return fact2(n, 1);
    }

    // easy => ... hard
    public int fact1(int n) {
        int r = 1;
        for (int i = 2; i <= n; i++) {
            r = r * i;
        }
        return r;
    }

    // -Xss4M
    public static void main1(String[] args) {
        System.out.println(args.length);
        for (String s: args)
            System.out.println(s);

        // 1.000.000 bytes per Thread
        RecursionApp r = new RecursionApp();
        System.out.println(r.fact(100000));
    }

    public int fibo(int n) {
        if (n == 1 || n == 2) return 1;
        return fibo(n - 1) + fibo(n - 2);
    }

    Map<Long, Long> cache = new HashMap<>();

    public long fibo2(long n) {
        if (n == 1 || n == 2) return 1;

        long fn;

        if (cache.containsKey(n)) fn = cache.get(n);
        else {
            fn = fibo2(n - 1) + fibo2(n - 2);
            cache.put(n, fn);
        }

        return fn;
    }

    public long fibo3(long nth) {
        long n1 = 1;
        long n2 = 1;
        long n = 0;

        for (int i = 3; i <= nth ; i++) {
            n = n1 + n2;
            n1 = n2;
            n2 = n;
        }

        return n;
    }

    public static void main2(String[] args) {
        RecursionApp r = new RecursionApp();
//    System.out.println(r.fibo(100));
//    System.out.println(r.fibo2(100));   // 3736710778780434371
        System.out.println(r.fibo3(100)); // 3736710778780434371
    }


    public boolean isEmpty(int[] xs) {
        return xs.length == 0;
    }

    public int head(int[] xs) {
        return xs[0];
    }

    public Optional<int[]> tail(int[] xs) {
        if (isEmpty(xs)) return Optional.empty();
        int[] xsNew = new int[xs.length-1];
        System.arraycopy(xs, 1, xsNew, 0, xs.length - 1);
        return Optional.of(xsNew);
    }

    public int lengthR(int[] xs) {
        if (isEmpty(xs)) return 0;
        return 1 + lengthR(tail(xs).get());
    }
    public int lengthTR(int[] xs) {
        return lengthTR(xs, 0);
    }

    private int lengthTR(int[] xs, int acc) {
        if (isEmpty(xs)) return acc;
        return lengthTR(tail(xs).get(), 1 + acc);
    }

    public int sumR(int[] xs) {
        if (isEmpty(xs)) return 0;
        return head(xs) + sumR(tail(xs).get());
    }

    private int sumTR0(int[] xs, int acc) {
        if (isEmpty(xs)) return acc;
        return sumTR0(tail(xs).get(), head(xs) + acc);
    }

    private int sumTR(int[] xs, int acc) {
        return tail(xs)
                .map(t -> sumTR(t, head(xs) + acc))
                .orElse(acc);
    }

    public int sumTR(int[] xs) {
        return sumTR(xs, 0);
    }


    public static void main(String[] args) {
        RecursionApp r = new RecursionApp();
        int[] empty = {};
        int[] one = {10};
        int[] many = {10, 20, 30};

//    System.out.println(r.lengthR(empty)); // 0
//    System.out.println(r.lengthR(one)); // 1
//    System.out.println(r.lengthR(many)); // 3
//
//    System.out.println(r.lengthTR(empty)); // 0
//    System.out.println(r.lengthTR(one)); // 1
//    System.out.println(r.lengthTR(many)); // 3

//    System.out.println(r.sumR(empty)); // 0
//    System.out.println(r.sumR(one)); // 10
//    System.out.println(r.sumR(many)); // 60

        System.out.println(r.sumTR(empty)); // 0
        System.out.println(r.sumTR(one)); // 10
        System.out.println(r.sumTR(many)); // 60
    }


}
