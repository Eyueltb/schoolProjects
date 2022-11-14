package leetcode.linkedList.singly;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.NoSuchElementException;

/**
 * linked list is a linear data structure, similar to array, which link all the separated elements together by the reference field.
 * Two commonly-used linked list: singly-linked list and doubly-linked list.
 * Each node in a singly-linked list contains not only the value but also a reference field to link to the next node.Each node in a singly-linked list contains not only the value but also a reference field to link to the next node.
 * Singly-linked and doubly linked list organizes all the nodes in a sequence.
 * In most cases, we will use the head node (the first node) to represent the whole list.
 * head === first node represent the whole list.
 * Access a random element in array is o(1) BUT in linked list O(N) because ff we want to get the ith element, we have to traverse from the head node one by one.
 *  Insert
 *   a) Beginning (Time complexity array O(N), LinkedList O(1))
 *   b) Middle (Time complexity array O(N), LinkedList O(N))
 *   c) Last (Time complexity array O(N), LinkedList O(N))
 *
 *   Delete
 *    a) Beginning (Time complexity array O(N), LinkedList O(1))
 *    b) Middle (Time complexity array O(N), LinkedList O(N) for time and o(1) for space)
 *    c) Last (Time complexity array O(N), LinkedList O(N))
 */
public class SinglyMain {
    public static void main(String[] args) {
        LinkedList obj = new LinkedList();
         int first = obj.get(0);
         System.out.println(first);
         obj.addAtHead(1);
         obj.addAtTail(3);
         obj.addAtIndex(1,2);
         System.out.println(obj.getSize());
         obj.deleteAtIndex(1);
         System.out.println(obj.getSize());
    }
}

/**
 * Design Linked List
 * A node in a singly linked list should have two attributes: val and next.
 * val is the value of the current node, and next is a pointer/reference to the next node.
 *
 * Input
 * ["LinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * Output
 * [null, null, null, null, 2, null, 3]
 *
 * Explanation
 * LinkedList myLinkedList = new LinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
 * myLinkedList.get(1);              // return 2
 * myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
 * myLinkedList.get(1);              // return 3
 *
 * Constraints:
 *
 * 0 <= index, val <= 1000
 * Please do not use the built-in LinkedList library.
 * At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.
 *
 * Singly linked list is the simplest one, it provides addAtHead in a constant time, and addAtTail in a linear time.
 * Though doubly linked list is the most used one, because it provides both addAtHead and addAtTail in a constant time,
 * and optimises the insert and delete operations.
 */



@Data
class LinkedList {
    private int size ; // default is 0
    private Node head;

    public LinkedList() {
        size = 0;
        head = new Node(0, null);
    }
    /** Add a node of value val before the first element of the linked list.
     * After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size)
            return -1;
        Node curr = head;
        curr = getNode(curr, index + 1); // current node
        return curr.val;
    }
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;
        ++size;

        Node prev = head;
        prev = getNode(prev, index); //previous node
        prev.next = new Node(val, prev.next);
    }
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        size--;
        Node prev = head;
        prev = getNode(prev, index); //previous node
        // delete pred.next
        prev.next = prev.next.next;
    }

    private Node getNode(Node curr, int index) {
        for (int i = 0; i < index; ++i)
            curr = curr.next;
        return curr;
    }
    private class Node {
        private final int val;
        private Node next;

        public Node(int val, Node next) {
            this.val =val;
            this.next = next;
        }
    }
}
