/* Deque.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements a double-ended queue using a doubly-linked list
 * Dependencies: Iterator
 */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Item item;
        Node previous;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // insert the item at the front of the queue
    public void addFirst(Item item) {
        Node node = new Node();
        node.item = item;

        // If the list is empty, set the node as both the head and
        // tail of the list
        if (size == 0) {
            tail = node;
        } else {
            // Set the connection from the new head to the old head
            node.next = head;

            // Set the connection from the old head to the new head
            head.previous = node;
        }
        // Set the new head as the head of the list
        head = node;

        size++;
    }

    // insert the item at the end of the queue
    public void addLast(Item item) {
        Node node = new Node();
        node.item = item;

        // Set the connection from the old tail to the new tail
        if (size == 0) {
            head = node;
        } else {
            // Set the connection from the new tail to the old tail
            node.previous = tail;

            tail.next = node;
        }
        // Set the new tail as the tail of the list
        tail = node;

        size++;
    }

    // delete and return the first item in queue
    public Item removeFirst() {
        return remove(head);
    }

    // delete and return the last item in queue
    public Item removeLast() {
        return remove(tail);
    }

    // remove helper method
    private Item remove(Node node) {
        if (size == 0)
            throw new RuntimeException("Cannot "
                    + "remove item because deque is empty.");

        if (size >= 3) {
            if (head == node) {
                head = node.next;
            } else if (tail == node) {
                tail = node.previous;
            }

            // Link node's next Node to its previous Node
            if (node.next != null) {
                node.next.previous = node.previous;
            }

            // Link node's previous Node to its next Node
            if (node.previous != null) {
                node.previous.next = node.next;
            }
        } else if (size == 2) {
            if (head == node) {
                head = node.next;
                head.previous = null;
                tail = head;
            } else if (tail == node) {
                tail = node.previous;
                tail.next = null;
                head = tail;
            }
        } else {
            head = null;
            tail = null;
        }

        size--;
        return node.item;
    }

    // return an iterator that examines the items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current;

        public ListIterator() {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() { /* not supported */
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String args[]) {
        Deque<Integer> dq = new Deque<Integer>();
        dq.addFirst(3);
        dq.addFirst(2);
        dq.addLast(4);
        dq.addFirst(1);
        dq.addLast(5);
        dq.removeFirst();
        dq.removeFirst();
        dq.removeLast();
        dq.removeLast();
        dq.removeFirst();

        for (Integer i : dq) {
            System.out.println(i);
        }

        System.out.println(dq.isEmpty());
    }
}
