package lesson2;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Overflow {

    public static boolean isOverflowWhenAdd(int x, int y) {
        int x_sign = x >> 31;
        int y_sign = y >> 31;
        if ((x_sign ^ y_sign) == 1) return false; // OK
        int r_sign = (x + y) >> 31;
        return r_sign == x_sign; // sign the same
    }


    public static boolean isOverflow(int x, int y, BiFunction<Integer, Integer, Integer> f) {
        if ((x>>31 ^ y>>31) == 1) return false;
        return ((x>>31 & y>>31) != f.apply(x, y)>>31);
    }
    public static boolean isOverflowWhenAdd2(int x, int y) {
        int r = x * y;  // на минус не работает
        // if and only if both arguments have the opposite sign of the result
        return ((x ^ r) & (y ^ r)) < 0;
    }

    public static int isOverflow2(int x, int y, BiFunction<Integer, Integer, Integer> f) {
        return f.apply(x, y);
    }

    public static void main(String[] args) {
        System.out.println(isOverflow(2147483645, 1, (a, b) -> a + b));
        System.out.println(isOverflowWhenAdd2(2147483646, 2));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(2147483646 ^ (2147483646 + 1));
        System.out.println(2 ^ (2147483646 + 1));
        System.out.println((2147483646 ^ (2147483646 + 1)) & (2 ^ (2147483646 + 1)));

        System.out.println("_____________________________________");
        System.out.println(BitsIntToBin.toBinary5(~4).substring(8-4));
        System.out.println(BitsIntToBin.toBinary5(~3).substring(8-4));
        System.out.println(BitsIntToBin.toBinary5(~2).substring(8-4));
        System.out.println(BitsIntToBin.toBinary5(~1).substring(8-4));
        System.out.println(BitsIntToBin.toBinary5(~0).substring(8-4));
        System.out.println(BitsIntToBin.toBinary5(0).substring(8-4));
        System.out.println(BitsIntToBin.toBinary5(1).substring(8-4));
        System.out.println(BitsIntToBin.toBinary5(2).substring(8-4));
        System.out.println(BitsIntToBin.toBinary5(3).substring(8-4));
        System.out.println(BitsIntToBin.toBinary5(4).substring(8-4));

        System.out.println(2*2*2*2);
    }

}

