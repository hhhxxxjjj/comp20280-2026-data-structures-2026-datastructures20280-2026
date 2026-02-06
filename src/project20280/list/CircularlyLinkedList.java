package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (isEmpty() || i < 0 || i >= size) {
            return null;
        }
        Node<E> curr = tail.getNext();
        for (int k = 0; k < i; k++) {
            curr = curr.getNext();
        }
        return curr.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if (i < 0 || i > size) {
            return;
        }
        if (i == 0) {
            addFirst(e);
        } else if (i == size) {
            addLast(e);
        } else {
            Node<E> curr = tail.getNext();
            for (int k = 0; k < i - 1; k++) {
                curr = curr.getNext();
            }
            Node<E> newest = new Node<>(e, curr.getNext());
            curr.setNext(newest);
            size++;
        }
    }

    @Override
    public E remove(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        if (i == 0) {
            return removeFirst();
        }
        if (i == size - 1) {
            return removeLast();
        }
        Node<E> curr = tail.getNext();
        for (int k = 0; k < i - 1; k++) {
            curr = curr.getNext();
        }
        Node<E> nodeToRemove = curr.getNext();
        curr.setNext(nodeToRemove.getNext());
        size--;
        return nodeToRemove.getData();
    }

    public void rotate() {
        if (tail != null) {
            tail = tail.getNext();
        }
    }

    private class CircularlyLinkedListIterator implements Iterator<E> {
        private Node<E> curr;
        private int count = 0;

        public CircularlyLinkedListIterator() {
            if (tail != null) {
                curr = tail.getNext();
            }
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            if (!hasNext()) return null;
            E res = curr.getData();
            curr = curr.getNext();
            count++;
            return res;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<E> head = tail.getNext();
        if (head == tail) {
            tail = null;
        } else {
            tail.setNext(head.getNext());
        }
        size--;
        return head.getData();
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<E> head = tail.getNext();
        if (head == tail) {
            tail = null;
            size--;
            return head.getData();
        } else {
            Node<E> curr = head;
            while (curr.getNext() != tail) {
                curr = curr.getNext();
            }
            E removedData = tail.getData();
            curr.setNext(head);
            tail = curr;
            size--;
            return removedData;
        }
    }

    @Override
    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }

    public String toString() {
        if (tail == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail.getNext();
        do {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail.getNext()) {
                sb.append(", ");
            }
        } while (curr != tail.getNext());
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}