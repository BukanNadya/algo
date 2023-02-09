package lesson5;

import java.util.HashMap;

public class XMapApp {

    public static void main(String[] args) {
        XMap<String> xm = new XMap<>(10);
        xm.put(120, "Jim");
        xm.put(317, "Alex");
        xm.put(512, "Ben");
        xm.put(412, "Jack");

        System.out.println(xm.get(120)); // Jim
        System.out.println(xm.get(317)); // Alex
        System.out.println(xm.get(512)); // Jack => (Ben ?)

        HashMap<Integer, String> hm = new HashMap<>(1000, 0.7f);
    }
}

