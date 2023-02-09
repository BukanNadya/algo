package lesson2;

import java.util.stream.Collectors;

public class XorExample {

    // a ^ b ^ b == a
    public static String encode(String data, int key) {
        return data.chars().map(x -> x ^ key)
                .mapToObj(x -> (char) x)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        String text = "Hello World";
        String encoded = encode(text, 37);
        System.out.println(text);
        System.out.println(encoded);
        String decoded = encode(encoded, 37);
        System.out.println(decoded);
    }

}

