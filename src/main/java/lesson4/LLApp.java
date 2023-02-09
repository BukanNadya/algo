package lesson4;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LLApp {

    public static void main(String[] args) {

        XLinkedList ll = new XLinkedList();
        ll.addHead(1); // 1
        ll.addHead(5); // 5, 1
        ll.addHead(7); // 7, 5, 1

        ll.addAfter(11, x -> x == 5); // 7, 5, 11, 1
        System.out.println(ll);
        ll.addBefore(11, x -> x == 5); // 7, 5, 11, 1
        System.out.println(ll);
        ll.reverse();
        System.out.println(ll);
        ll.reverseR();
        System.out.println(ll);

        System.out.println(ll.length());
        System.out.println(ll.lengthR());
        System.out.println(ll.lengthTR());
        System.out.println(ll.containsTR(5));
        System.out.println(ll.contains(5));

    }

}


