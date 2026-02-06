package project20280.stacksqueues;

import project20280.interfaces.Queue;
import project20280.list.DoublyLinkedList;

public class LinkedQueue<E> implements Queue<E> {

    private DoublyLinkedList<E> ll;

    public static void main(String[] args) {
    }

    public LinkedQueue() {
        ll = new DoublyLinkedList<>();
    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        ll.addLast(e);  // 队列尾部添加
    }

    @Override
    public E first() {
        return ll.first();  // 返回队列头部
    }

    @Override
    public E dequeue() {
        return ll.removeFirst();  // 从队列头部移除
    }

    public String toString() {
        return ll.toString();
    }
}