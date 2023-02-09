package lesson2;

import java.util.stream.IntStream;

public class BinaryToInt {

    // 00110101010 -> 540
    public static int binToInt1(String binary) {
        int outcome = 0;
        for (int i = 0; i < binary.length(); i++) {
            outcome += (binary.charAt(i) - '0') * (1 << (binary.length() - i - 1));
        }
        return outcome;
    }

    public static int binToInt2(String binary) {
        return IntStream.range(0, binary.length())
                .map(i -> (binary.charAt(i) - '0') * (1 << (binary.length() - i - 1)))
                .sum();
    }

    public static void main(String[] args) {
        System.out.println(binToInt2("11101"));
    }
}
