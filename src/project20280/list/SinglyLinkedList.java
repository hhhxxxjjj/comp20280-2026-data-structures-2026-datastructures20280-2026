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
            // TODO: Done
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
            // TODO: Done
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            // TODO: Done
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
        // TODO: Done
        return size;
    }

    //@Override
    public boolean isEmpty() {
        // TODO: Done
        return size == 0;
    }

    @Override
    public E get(int position) {
        // TODO: Done
        if (position < 0 || position >= size) return null;
        Node<E> curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    @Override
    public void add(int position, E e) {
        // TODO: Done
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
        // TODO: Done
        head = new Node<>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO: Done
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
        // TODO: Done
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
        // TODO: Done
        if (isEmpty()) return null;
        E answer = head.getElement();
        head = head.getNext();
        size--;
        return answer;
    }

    @Override
    public E removeLast() {
        // TODO: Done
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
        ll.remove(5);
        System.out.println(ll);
    }
}

