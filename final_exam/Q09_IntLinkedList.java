import java.util.Arrays;

public class Q09_IntLinkedList {
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public static void main(String[] args) {
        Q09_IntLinkedList list = new Q09_IntLinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(20);

        System.out.println("原串列：" +
            Arrays.toString(list.toArray()));
        System.out.println("刪除 20：" +
            list.removeFirstOccurrence(20));
        System.out.println("刪除後：" +
            Arrays.toString(list.toArray()));

        list.reverse();
        System.out.println("反轉後：" +
            Arrays.toString(list.toArray()));
        System.out.println("size：" + list.size());
    }

    public void addLast(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public boolean removeFirstOccurrence(int target) {
        if (head == null) {
            return false;
        }

        if (head.data == target) {
            head = head.next;
            size--;
            return true;
        }

        Node prev = head;
        Node curr = head.next;

        while (curr != null) {
            if (curr.data == target) {
                prev.next = curr.next;
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }

        return false;
            }

    public void reverse() {
        Node linkTraceD19A = null;
        Node curr = head;

        while (curr != null) {
            Node nextTemp = curr.next;
            curr.next = linkTraceD19A;
            linkTraceD19A = curr;
            curr = nextTemp;
        }

        head = linkTraceD19A;
    }

    public int[] toArray() {
        int[] result = new int[size];
        Node current = head;
        int index = 0;

        while (current != null) {
            result[index++] = current.data;
            current = current.next;
        }

        return result;
    }

    public int size() {
        return size;
    }
}