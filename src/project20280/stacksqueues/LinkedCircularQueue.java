package project20280.stacksqueues;

import project20280.interfaces.Queue;
import project20280.list.CircularlyLinkedList;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {

    // 内部使用环形链表存储数据
    private CircularlyLinkedList<E> list = new CircularlyLinkedList<>();

    public static void main(String[] args) {
        LinkedCircularQueue<Integer> q = new LinkedCircularQueue<>();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println("Queue: " + q);           // [10, 20, 30]
        System.out.println("first: " + q.first());   // 10
        System.out.println("dequeue: " + q.dequeue()); // 10
        System.out.println("Queue: " + q);           // [20, 30]
        System.out.println("size: " + q.size());     // 2
    }

    /**
     * 返回队列中元素的数量
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * 如果队列为空则返回 true
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 将元素加入队尾（enqueue = 入队）
     */
    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    /**
     * 查看队首元素，但不删除
     */
    @Override
    public E first() {
        return list.get(0);
    }

    /**
     * 移除并返回队首元素（dequeue = 出队）
     */
    @Override
    public E dequeue() {
        return list.removeFirst();
    }

    /**
     * 旋转操作：把队首元素移到队尾（等同于 enqueue(dequeue())，但更高效）
     */
    public void rotate() {
        list.rotate();
    }

    public String toString() {
        return list.toString();
    }

}
