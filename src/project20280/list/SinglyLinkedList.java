package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {

            this.element = e;
            this.next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {

            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {

            this.next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {

        return size;
    }

    //@Override
    public boolean isEmpty() {

        return size == 0;
    }

    @Override
    public E get(int position) {

        if (position < 0 || position >= size) return null;
        Node<E> curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    @Override
    public void add(int position, E e) {

        if (position < 0 || position > size) return;
        if (position == 0) {
            addFirst(e);
        } else {
            Node<E> prev = head;
            for (int i = 0; i < position - 1; i++) {
                prev = prev.getNext();
            }
            Node<E> newest = new Node<>(e, prev.getNext());
            prev.setNext(newest);
            size++;
        }
    }


    @Override
    public void addFirst(E e) {

        head = new Node<>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {

        if (isEmpty()) {
            addFirst(e);
        } else {
            Node<E> curr = head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(new Node<>(e, null));
            size++;
        }
    }

    @Override
    public E remove(int position) {

        if (position < 0 || position >= size) return null;
        if (position == 0) return removeFirst();

        Node<E> prev = head;
        for (int i = 0; i < position - 1; i++) {
            prev = prev.getNext();
        }
        E element = prev.getNext().getElement();
        prev.setNext(prev.getNext().getNext());
        size--;
        return element;
    }

    @Override
    public E removeFirst() {

        if (isEmpty()) return null;
        E answer = head.getElement();
        head = head.getNext();
        size--;
        return answer;
    }

    @Override
    public E removeLast() {

        if (isEmpty()) return null;
        if (size == 1) return removeFirst();

        Node<E> prev = head;
        while (prev.getNext().getNext() != null) {
            prev = prev.getNext();
        }
        E answer = prev.getNext().getElement();
        prev.setNext(null);
        size--;
        return answer;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<T> implements Iterator<T> {
        Node<T> curr = (Node<T>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public T next() {
            T res = curr.getElement();
            curr = curr.getNext();
            return res;
        }
    }

    @SuppressWarnings("unchecked")
    public void sortedMerge(SinglyLinkedList<E> other) {
        Node<E> dummy = new Node<>(null, null);
        Node<E> last = dummy;

        Node<E> t1 = this.head;
        Node<E> t2 = other.head;

        while (t1 != null && t2 != null) {
            Comparable<E> c1 = (Comparable<E>) t1.getElement();
            E e2 = t2.getElement();

            if (c1.compareTo(e2) <= 0) {
                last.setNext(t1);
                t1 = t1.getNext();
            } else {
                last.setNext(t2);
                t2 = t2.getNext();
            }
            last = last.getNext();
        }

        if (t1 != null) {
            last.setNext(t1);
        } else {
            last.setNext(t2);
        }

        this.head = dummy.getNext();
        this.size += other.size;

        other.head = null;
        other.size = 0;
    }

    public void reverse() {
        if (head == null || head.getNext() == null) {
            return;
        }

        Node<E> prev = null;
        Node<E> curr = head;
        Node<E> nextTemp = null;

        while (curr != null) {
            nextTemp = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = nextTemp;
        }
        head = prev;
    }

    public SinglyLinkedList<E> clone() {
        SinglyLinkedList<E> copy = new SinglyLinkedList<>();
        if (this.isEmpty()) {
            return copy;
        }

        Node<E> curr = this.head;
        while (curr != null) {
            copy.addLast(curr.getElement());
            curr = curr.getNext();
        }
        return copy;
    }

    // Q6(a): Print linked list in reverse using recursion
    public void printReverse() {
        printReverseHelper(head);
        System.out.println();
    }

    private void printReverseHelper(Node<E> node) {
        if (node == null) {
            return;
        }
        printReverseHelper(node.getNext());
        System.out.print(node.getElement() + " ");
    }

    // Q7: Recursive copy of linked list
    public SinglyLinkedList<E> recursiveCopy() {
        SinglyLinkedList<E> copy = new SinglyLinkedList<>();
        if (head != null) {
            copy.head = recursiveCopyHelper(head);
            copy.size = this.size;
        }
        return copy;
    }

    private Node<E> recursiveCopyHelper(Node<E> node) {
        if (node == null) {
            return null;
        }
        return new Node<>(node.getElement(), recursiveCopyHelper(node.getNext()));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);

        System.out.println(ll);

        ll.reverse();
        System.out.println("Reversed: " + ll);

        ll.remove(5);
        System.out.println(ll);

        SinglyLinkedList<Integer> l1 = new SinglyLinkedList<>();
        l1.addLast(2);
        l1.addLast(6);
        l1.addLast(20);

        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<>();
        l2.addLast(1);
        l2.addLast(5);
        l2.addLast(25);

        System.out.println("L1: " + l1);
        System.out.println("L2: " + l2);

        l1.sortedMerge(l2);
        System.out.println("Merged L1: " + l1);

        // Q6 test
        System.out.println("\n=== Q6: Print Reverse ===");
        SinglyLinkedList<Integer> q6 = new SinglyLinkedList<>();
        q6.addLast(1);
        q6.addLast(2);
        q6.addLast(3);
        q6.addLast(4);
        q6.addLast(5);
        System.out.println("Original: " + q6);
        System.out.print("Reversed print: ");
        q6.printReverse();

        // Q7 test
        System.out.println("\n=== Q7: Recursive Copy ===");
        SinglyLinkedList<Integer> q7 = new SinglyLinkedList<>();
        q7.addLast(10);
        q7.addLast(20);
        q7.addLast(30);
        System.out.println("Original: " + q7);
        SinglyLinkedList<Integer> q7copy = q7.recursiveCopy();
        System.out.println("Copy: " + q7copy);
    }
}