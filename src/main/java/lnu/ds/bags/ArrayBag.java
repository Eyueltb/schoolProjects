package lnu.ds.bags;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

final public class ArrayBag<T> implements IBag<T> {

    /** First step is to have a storage(array) */
    private T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 200;

    public ArrayBag() {   this(DEFAULT_CAPACITY); }

    /** Creates an empty bag having a given initial capacity */
    public ArrayBag(int capacity) {
        if (capacity <= MAX_CAPACITY)
            bag = (T[]) new Object[capacity];
        else
            bag = (T[]) new Object[DEFAULT_CAPACITY];

        numberOfEntries = 0;
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean add(T newEntry) {
        if (isFull())
            doubleCapacity();
        bag[numberOfEntries++] = newEntry;
        return true;
    }
    /** Removes one unspecified entry from this bag(remove from last index) */
    @Override
    public T remove() {
        T result = null;
        if (numberOfEntries > 0) {
            result = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
        return result;
    }
    /** Removes one occurrence of a given entry from this bag. */
    @Override
    public boolean remove(T anEntry) {
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }

    @Override
    public void clear() {
        while (!isEmpty())
            remove();
        numberOfEntries = 0;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        return (int) Arrays.stream(bag).filter(item -> item.equals(anEntry)).count();
    }

    @Override
    public boolean contains(T anEntry) {
        return Arrays.stream(bag).anyMatch(item -> item.equals(anEntry));
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(bag, numberOfEntries);
    }

    /** Doubles the size of the array bag */
    private void doubleCapacity() {
        int newLength = 2 * bag.length;
        if (checkCapacity(newLength))
            bag = Arrays.copyOf(bag, newLength);
    }

    /** Returns true if the bag is full, or false if not. */
    private boolean isFull() {
        return numberOfEntries >= bag.length && numberOfEntries <=MAX_CAPACITY;
    }

    private boolean checkCapacity(int newLength) {
        return (newLength <= MAX_CAPACITY);
    }

    private int getIndexOf(T anEntry) {
        AtomicInteger index = new AtomicInteger();
        IntStream.range(0, numberOfEntries)
                .forEach( i-> {
                    if(bag[i].equals(anEntry)) {
                        index.set(i);
                        return;
                    }
                });
        return index.get();
    }

    private T removeEntry(int index) {
        T result = null;
        if (!isEmpty() && index >= 0) {
            result = bag[index];
            bag[index] = bag[numberOfEntries - 1]; // Replace it with last entry
            bag[numberOfEntries - 1] = null; // remove the last entry
            numberOfEntries--;
        }
        return result;
    }
}
