package lesson7;

import java.util.List;
import java.util.stream.Stream;

public class XTree<A extends Comparable<A>, V> {

    class Node {
        A key;
        V value;
        Node left;
        Node right;
        int count = 1;

        public Node(A key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public Node(A key) {
            this(key, null, null);
        }
    }

    public Node root;

    public boolean containsNR(A a) {
        Node curr = root;
        while (curr != null) {
            int cmp = a.compareTo(curr.key);
            if (cmp < 0) curr = curr.left;
            else if (cmp > 0) curr = curr.right;
            else /*cmp == 0*/ return true;
        }
        return false;
    }

    private boolean containsR(A a, Node curr) {
        if (curr == null) return false;
        int cmp = a.compareTo(curr.key);
        if (cmp < 0) return containsR(a, curr.left);
        else if (cmp > 0) return containsR(a, curr.right);
        else /*cmp == 0*/ return true;
    }

    public boolean containsR(A a) {
        return containsR(a, root);
    }

    ////////////////////////////////////////////

    private void putR1(A a, Node curr) {
        int cmp = a.compareTo(curr.key);
        if (cmp < 0) if (curr.left == null) curr.left = new Node(a);
        else putR1(a, curr.left);
        else if (cmp > 0) if (curr.right == null) curr.right = new Node(a);
        else putR1(a, curr.right);
        else /*cmp == 0*/;
    }

    public void putR1(A a) {
        if (root == null) root = new Node(a);
        else putR1(a, root);
    }

    ////////////////////////////////////////////

    private int size(Node node) {
        return node == null ? 0 : node.count;
    }

    private Node putR2(A a, Node curr) {
        if (curr == null) return new Node(a);
        int cmp = a.compareTo(curr.key);
        if (cmp < 0) curr.left = putR2(a, curr.left);
        else if (cmp > 0) curr.right = putR2(a, curr.right);
        else /*cmp == 0*/ curr.key = a;
        curr.count = size(curr.left) + 1 + size(curr.right);
        // rebalance here
        return curr;
    }

    public void putR2(A a) {
        root = putR2(a, root);
    }

    ////////////////////////////////////////////

    public void putNR(A a) {
        if (root == null) {
            root = new Node(a);
            return;
        }
        Node curr = root;
        while (true) {
            int cmp = a.compareTo(curr.key);
            if (cmp < 0) {
                if (curr.left == null) {
                    curr.left = new Node(a);
                    break;
                } else curr = curr.left;
            } else if (cmp > 0) {
                if (curr.right == null) {
                    curr.right = new Node(a);
                    break;
                } else curr = curr.right;
            } else break;
        }
    }

//    private int calcMaxDepht(Node node) {
//        return node == null ? 0: 1 + Math.max(calcMaxDepht(node.left), calcMaxDepht(node.right));
//    }
//
//    private List<String> outcome(Node node, ) {
//        return
//    }
//    public List<String> outcome(Node node) {
//        return
//    }
//
//    @Override
//    public String toString() {
//        int height = calcMaxDepht(root);
//
//        return Stream.of(outcome(root)).map;
//    }


}
