
import java.util.ListIterator;

public class TwoWayLinkedList<E> {

    private Node<E> head, tail; 
    private int size = 0; 

    public TwoWayLinkedList() {
    }

    private static class Node<E> {

        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E element) {
            this.element = element;
        }
    }

    // add an element to beginning of list
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (size == 0) { 
            head = tail = newNode; 
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    // add an element to the end of the list
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if (size == 0) { 
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    // add an element at a specific index
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (index == 0) { // add at the beginning
            addFirst(e);
        } else if (index == size) { // add at end
            addLast(e);
        } else { // add in the middle
            Node<E> newNode = new Node<>(e);
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next; 
            }
            newNode.next = current;
            newNode.previous = current.previous;
            current.previous.next = newNode; //
            current.previous = newNode;
            size++;
        }
    }

    // remove and return the first element
    public E removeFirst() {
        if (size == 0) {
            return null; // if list is empty, return null
        }
        Node<E> temp = head; // save the current head
        if (size == 1) { // if there's only one element
            head = tail = null;
        } else {
            head = head.next;
            head.previous = null; // remove link to old head
        }
        size--;
        return temp.element; // return the removed element
    }

    // remove and return the last element
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        Node<E> temp = tail; // save current tail
        if (size == 1) { 
            head = tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }
        size--;
        return temp.element;
    }

    // remove and return the element at a specific index
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (index == 0) { 
            return removeFirst();
        } else if (index == size - 1) { 
            return removeLast();
        } else { 
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.previous.next = current.next; // link previous node to the next node
            current.next.previous = current.previous; // link next node to the previous node
            size--;
            return current.element;
        }
    }


    public int size() {
        return size;
    }


    public ListIterator<E> listIterator() {
        return new LinkedListIterator();
    }

    // inner class for LinkedListIterator
    private class LinkedListIterator implements ListIterator<E> {

        private Node<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E element = current.element;
            current = current.next;
            return element;
        }

        @Override
        public boolean hasPrevious() {
            return current != null && current.previous != null;
        }

        @Override
        public E previous() {
            current = current.previous;
            return current.element;
        }

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
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }
    }
}
