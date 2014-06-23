/* RandomizedQueue.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements a randomized queue using an array
 * Dependencies: StdRandom, Iterator
 */

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[2];
        size = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        int i = size;
        while (i < s.length - 1 && s[i] != null)
            i++;
        // If no room to insert at or after i = size - 1, resize
        if (i >= s.length - 1) {
            resize(size * 2);
            i = size;
        }
        s[i] = item;
        size++;
    }

    private void resize(int capacity) {
        Item[] dup = (Item[]) new Object[capacity];

        for (int i = 0, j = 0; i < size; i++, j++) {
            // Skip gaps
            while (s[j] == null)
                j++;
            dup[i] = s[j];
        }

        s = dup;
    }

    // delete and return a random item
    public Item dequeue() {
        if (size == 0)
            throw new RuntimeException("Cannot "
                    + "dequeue because queue is empty.");

        int rand = randIndex();
        Item item = s[rand];
        s[rand] = null;
        size--;

        // Resize if 1/4 full
        if (size > 0 && size == s.length / 4)
            resize(s.length / 2);

        return item;
    }

    // return (but do not delete) a random item
    public Item sample() {
        if (size == 0)
            throw new RuntimeException("Cannot "
                    + "sample because queue is empty.");

        int rand = randIndex();
        Item item = s[rand];

        return item;
    }

    // Generate a random index (that points to a non-null Item)
    private int randIndex() {
        int rand = StdRandom.uniform(size);
        // Re-generate if rand points to a gap in s
        while (s[rand] == null)
            rand = StdRandom.uniform(size);

        return rand;
    }

    // return an iterator that returns the items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int index;
        private Item[] randItems;

        // Create a new array of length size containing the items, then shuffle
        public RandomIterator() {
            Item[] dup = (Item[]) new Object[size];

            for (int i = 0, j = 0; i < size; i++, j++) {
                // Skip gaps
                while (s[j] == null)
                    j++;
                dup[i] = s[j];
            }

            randItems = dup;
            StdRandom.shuffle(randItems);
        }

        public boolean hasNext() {
            return index < size;
        }

        public void remove() { /* not supported */
        }

        public Item next() {
            return randItems[index++];
        }
    }

    public static void main(String args[]) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        for (int i = 0; i < 10; i++) {
            rq.enqueue(i + 1);
        }

        for (Integer i : rq) {
            System.out.print(i + " ");
        }

        System.out.println("\n" + rq.dequeue() + " " + rq.dequeue());

        for (Integer i : rq) {
            System.out.print(i + " ");
        }
    }
}
