package lesson8;

public class XGraphApp {
    public static void main(String[] args) {
        XGraph g = new XGraph(17);
        g.add(0, 1);

        g.add(1, 2);
        g.add(1, 3);

        g.add(2, 4);
        g.add(2, 5);

        g.add(4, 8);
        g.add(4, 9);

        g.add(5, 10);
        g.add(5, 11);

        g.add(3, 6);
        g.add(3, 7);

        g.add(6, 12);

        g.add(7, 13);
        g.add(7, 14);

        g.add(12, 15);

        g.add(6, 16);
        g.add(16, 3);
        g.add(8, 0);


        GraphTraverse t = new GraphTraverse(g);
        System.out.println(t.bfs(0));
        System.out.println(t.dfs(0));
        System.out.println(t.dfsNR(0));

    }

}
