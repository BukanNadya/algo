package lesson7;

public class XTreeApp {

    public static void main(String[] args) {
        XTree<Integer, Object> t = new XTree<>();

//        boolean has10 = t.containsNR(10);
//        System.out.println(has10); // false

        t.putR2(50); // root
        t.putR2(40);
        t.putR2(30);
        t.putR2(60);
        t.putR2(10);
        t.putR2(35);
        t.putR2(45);
        t.putR2(55);
        t.putR2(65);
        t.putR2(44);
        t.putR2(5);
        t.putR2(67);
        t.putR2(36);
        t.putR2(11);
        t.putR2(43);
        t.putR2(34);
        t.putR2(80);
        t.putR2(90);
        t.putR2(68);

        System.out.println(t);

//        System.out.println(t.containsNR(50));
//        System.out.println(t.containsNR(40));
//        System.out.println(t.containsNR(30));
//        System.out.println(t.containsNR(60));
//        System.out.println(t.containsNR(10));
//        System.out.println(t.containsNR(11));
//        System.out.println(t.root.count); // 5
//        System.out.println(t.root.left.count); // 3
//        System.out.println(t.root.right.count); // 1
    }

}

