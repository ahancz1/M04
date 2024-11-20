
import java.util.ListIterator;

public class TwoWayLinkedList<E> {

    private Node<E> head, tail; 
    private int size = 0; 

    /**
     * Create an empty list
     */
    public TwoWayLinkedList() {
    }

    /**
     * Node class for doubly linked list
     */
    private static class Node<E> {

        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E element) {
            this.element = element;
        }
    }

    /**
     * Add an element to the beginning of the list
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        if (size == 0) { // If list is empty
            head = tail = newNode; // Head and tail point to the same node
        } else {
            newNode.next = head; // New node points to current head
            head.previous = newNode; // Current head points back to new node
            head = newNode; // Update head
        }
        size++; // Increase the size
    }

    /**
     * Add an element to the end of the list
     */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        if (size == 0) { // If list is empty
            head = tail = newNode; // Head and tail point to the same node
        } else {
            tail.next = newNode; // Tail points to new node
            newNode.previous = tail; // New node points back to current tail
            tail = newNode; // Update tail
        }
        size++; // Increase the size
    }

    /**
     * Add an element at a specific index
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (index == 0) { // Add at the beginning
            addFirst(e);
        } else if (index == size) { // Add at the end
            addLast(e);
        } else { // Add in the middle
            Node<E> newNode = new Node<>(e);
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next; // Traverse to the index
            }
            newNode.next = current; // New node points to current node
            newNode.previous = current.previous; // New node points back to current's previous
            current.previous.next = newNode; // Current's previous node points to new node
            current.previous = newNode; // Current node points back to new node
            size++; // Increase the size
        }
    }

    /**
     * Remove and return the first element
     */
    public E removeFirst() {
        if (size == 0) {
            return null; // If list is empty, return null
        }
        Node<E> temp = head; // Save the current head
        if (size == 1) { // If there's only one element
            head = tail = null; // List becomes empty
        } else {
            head = head.next; // Move head to the next node
            head.previous = null; // Remove link to the old head
        }
        size--; // Decrease the size
        return temp.element; // Return the removed element
    }

    /**
     * Remove and return the last element
     */
    public E removeLast() {
        if (size == 0) {
            return null; // If list is empty, return null
        }
        Node<E> temp = tail; // Save the current tail
        if (size == 1) { // If there's only one element
            head = tail = null; // List becomes empty
        } else {
            tail = tail.previous; // Move tail to the previous node
            tail.next = null; // Remove link to the old tail
        }
        size--; // Decrease the size
        return temp.element; // Return the removed element
    }

    /**
     * Remove and return the element at a specific index
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (index == 0) { // Remove from the beginning
            return removeFirst();
        } else if (index == size - 1) { // Remove from the end
            return removeLast();
        } else { // Remove from the middle
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next; // Traverse to the index
            }
            current.previous.next = current.next; // Link previous node to the next node
            current.next.previous = current.previous; // Link next node to the previous node
            size--; // Decrease the size
            return current.element; // Return the removed element
        }
    }

    /**
     * Return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Return a ListIterator for the list
     */
    public ListIterator<E> listIterator() {
        return new LinkedListIterator();
    }

    /**
     * Inner class for LinkedListIterator
     */
    private class LinkedListIterator implements ListIterator<E> {

        private Node<E> current = head; // Start at the head

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E element = current.element; // Get current element
            current = current.next; // Move to the next node
            return element;
        }

        @Override
        public boolean hasPrevious() {
            return current != null && current.previous != null;
        }

        @Override
        public E previous() {
            current = current.previous; // Move to the previous node
            return current.element; // Return the element
        }

        // Additional methods are left unimplemented for simplicity
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            return 0; // Not implemented
        }

        @Override
        public int previousIndex() {
            return 0; // Not implemented
        }
    }
}
