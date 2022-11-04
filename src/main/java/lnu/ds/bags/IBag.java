package lnu.ds.bags;

interface IBag<T> {

    /** Gets the current number of entries in this bag. */
    int getCurrentSize();

    /** Sees whether this bag is empty. Return True if the bag is empty, false otherwise */
    boolean isEmpty();

    /** Adds a new entry to this bag. Return True if the added successful, or false otherwise */
    boolean add(T newEntry);

    /** Removes one unspecified entry from this bag, if possible. Return Either the removed entry, or null if not successful, or null.*/
    T remove();

    /** Removes one occurrence of a given entry from this bag, if possible. Return True if successful, false otherwise .*/
    boolean remove(T anEntry);

    /** Removes all entries from this bag. */
    void clear();

    /** Counts the number of times a given entry appears in this bag.*/
    int getFrequencyOf(T anEntry);

    /**  Tests whether this bag contains a given entry. Return True if anEntry found, false otherwise */
    boolean contains(T anEntry);

    /** Retrieves all entries that are in this bag. Return A newly allocated array of all the entries in the bag.*/
    T[] toArray();
}
