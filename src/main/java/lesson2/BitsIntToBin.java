package lesson2;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BitsIntToBin {
    //      5  -> 000000101
    //     32  -> 000100000
    //    127  -> 111111111
    //    0..127
    public static String toBinary1(int n) {
        StringBuilder outcome = new StringBuilder();

        int remainder;
        while (n > 1) {
            remainder = n % 2;
            n /= 2;
            outcome.append(remainder);
        }
        outcome.append(n);

        String missingZeros = "0".repeat(8 - outcome.length());
//    String missingZeros = "0".repeat(8);
        return missingZeros + outcome.reverse(); // + substring
    }

    public static String toBinary2(int n) {
        StringBuilder outcome = new StringBuilder();
        for (int toInc = 1; toInc < 256;) {
            String next = (toInc & n) == toInc ? "1" : "0";
            outcome.append(next);
            toInc <<= 1;
        }
        return outcome.reverse().toString();
    }

    public static String toBinary3(int n) {
        StringBuilder outcome = new StringBuilder();
        for (int i = 7; i >= 0; i--) { // bit number
            int bitValue = 1 << i;
            int bit = n & bitValue;
            outcome.append(bit > 0 ? "1" : "0");
        }
        return outcome.toString();
    }

    public static String toBinary4(int n) {
        StringBuilder outcome = new StringBuilder();
        for (int i = 7; i >= 0; i--) { // bit number
            int bit = (n >> i) & 0b00000001;
            outcome.append(bit > 0 ? "1" : "0");
        }
        return outcome.toString();
    }

    public static String toBinary5(int n) {
        return IntStream.rangeClosed(0, 7)    // 0..7
                .map(i -> 7 - i)                  // 7..0
                .map(i -> (n >> i) & 0b00000001)  // 0 / 1
                .mapToObj(Integer::toString)      // "0" / "1"
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        System.out.println(toBinary5(32));
    }

}

