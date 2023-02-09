package lesson4;

import java.util.Optional;
import java.util.function.IntPredicate;

public class XLinkedList {

    class Node {

        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(int value) {
            this(value, null);
        }

        @Override
        public String toString() {
            return String.format("[%s, %s]", value, next);
        }
    }

    private Node head;

    public int length() {
        Node curr = head;
        int len = 0;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        return len;
    }

    private int lengthR(Node node) {
        return (node == null) ? 0 : 1 + lengthR(node.next);
    }

    public int lengthR() {
        return lengthR(head);
    }

// TODO: homework
    private int lengthTR(Node node, int acc) {
        if (node == null) return acc;
        return lengthTR(node.next, 1 + acc);
    }

    public int lengthTR() {
        return lengthTR(head, 0);
    }

// TODO: homework
    public boolean contains(int x) {
        Node curr = head;
        int len = 0;
        while (curr != null) {
            if (curr.value == x) return true;
            curr = curr.next;
        }
        return false;
    }

    private boolean containsTR(int x, Node node) {
        if (node == null) return false;
        if (node.value == x) return true;
        return containsTR(x, node.next);
//    return node != null && (x == node.value || containsR(x, node.next));
    }

    public boolean containsTR(int x) {
        return containsTR(x, head);
    }

//  public void addHead(int x) {
//    Node node = new Node(x);
//    node.next = head;
//    head = node;
//  }

    public void addHead(int x) {
        head = new Node(x, head);
    }

    // iteration
    private String mkString1() {
        StringBuilder sb = new StringBuilder("[");
        Node curr = head;
        while (curr != null) {
            if (curr != head) sb.append(", ");
            sb.append(curr.value);
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private String mkString1A() {
        StringBuilder sb = new StringBuilder("[");
        Node curr = head;
        boolean isFirst = true;
        while (curr != null) {
            if (isFirst) isFirst = false; else sb.append(", ");
            sb.append(curr.value);
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // recursive
    private String mkStringTR(Node node, boolean isFirst, StringBuilder sb) {
        if (node == null) {
            sb.append("]");
            return sb.toString();
        }

        if (!isFirst) sb.append(", ");
        return mkStringTR(node.next, false, sb.append(node.value));
    }

    private String mkStringTR() {
        StringBuilder sb = new StringBuilder("[");
        return mkStringTR(head, true, sb);
    }

    @Override
    public String toString() {
        return mkStringTR();
    }

    public int head() {
        if (head == null) throw new IllegalStateException("list is empty");
        return head.value;
    }

    public void cutHead() {
        if (head == null) throw new IllegalStateException("list is empty");
        head = head.next;
    }

    // TODO
  public boolean addBefore(int x, IntPredicate f) {
      Optional<Node> foundPrev = findPrev0(f);
      foundPrev.ifPresent(node -> {
          Node newNode = new Node(x);
          newNode.next = node.next;
          node.next = newNode;
      });
      return foundPrev.isPresent();
  }

    private Optional<Node> find(IntPredicate f, Node node) {
        if (node == null) return Optional.empty();
        if (f.test(node.value)) return Optional.of(node);
        return find(f, node.next);
    }

    private Optional<Node> find(IntPredicate f) {
        return find(f, head);
    }

    public boolean addAfter(int x, IntPredicate f) {
        Optional<Node> found = find(f);

        found.ifPresent(node -> {
            Node newNode = new Node(x);
            newNode.next = node.next;
            node.next = newNode;
        });

        return found.isPresent();
    }

    // 1. node not found           - false
    // 2. node to delete is absent - ??? true / false
    // 3. OK                       - true
    // boolean removeAfter(IntPredicate f) = DOESN'T WORK
    /**
     * @return
     *   -1 - node not found
     *   1 - node to delete is absent
     *   0 - OK
     */
    public int removeAfter(IntPredicate f) {
        Optional<Node> found = find(f);
        if (found.isEmpty()) return -1;
        Node node = found.get();
        if (node.next == null) return 1;

        node.next = node.next.next;
        return 0;
    }

    public int remove(IntPredicate f) {
        Optional<Node> found = find(f);
        if (found.isEmpty()) return -1;
        Node node = found.get();
        if (node.next == null) return 1;

        node.next = node.next.next;
        return 0;
    }

    private Optional<Node> findPrev(IntPredicate f, Node node) {
        if (node == null) return Optional.empty();
        if (node.next == null) return Optional.empty();
        if (f.test(node.next.value)) return Optional.of(node);
        return findPrev(f, node.next);
    }

    private Optional<Node> findPrev(IntPredicate f) {
        if (head == null) return Optional.empty();
        if (head.next == null) return Optional.empty();
        if (f.test(head.next.value)) return Optional.of(head);
        return findPrev(f, head.next);
    }

    // TODO: homework
    private Optional<Node> findPrev0(IntPredicate f) {
        if (head == null || head.next == null) return Optional.empty();
        Node node = head;
        while (node.next != null) {
            if (f.test(node.next.value)) return Optional.of(node);
        }
        return Optional.empty();
    }

    public void reverse() {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            Node savedNext = curr.next;
            curr.next = prev;
            prev = curr;      // pos change
            curr = savedNext; // pos change
        }
        head = prev;
    }

    public Node reverseR(Node curr, Node prev) {
        if (curr == null) return prev;
        Node savedNext = curr.next;
        curr.next = prev;
        return reverseR(savedNext, curr);
    }

    public void reverseR() {
        head = reverseR(head, null);
    }



}


