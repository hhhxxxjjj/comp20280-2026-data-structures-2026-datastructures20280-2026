package project20280.stacksqueues;


import project20280.interfaces.Deque;

public class ArrayDeque<E> implements Deque<E> {

    private static final int CAPACITY = 1000;
    private E[] data;
    private int front = 0;
    private int size = 0;

    public ArrayDeque() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayDeque(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E first() {
        if (isEmpty()) return null;
        return data[front];
    }

    @Override
    public E last() {
        if (isEmpty()) return null;
        int rear = (front + size - 1) % data.length;
        return data[rear];
    }

    @Override
    public void addFirst(E e) {
        if (size == data.length) {
            throw new IllegalStateException("Deque is full");
        }
        front = (front - 1 + data.length) % data.length;
        data[front] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == data.length) {
            throw new IllegalStateException("Deque is full");
        }
        int rear = (front + size) % data.length;
        data[rear] = e;
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) return null;
        E answer = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return answer;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) return null;
        int rear = (front + size - 1) % data.length;
        E answer = data[rear];
        data[rear] = null;
        size--;
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % data.length;
            sb.append(data[index]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.addLast(1);
        dq.addLast(2);
        dq.addFirst(0);
        System.out.println(dq);  // [0, 1, 2]

        System.out.println("First: " + dq.first());  // 0
        System.out.println("Last: " + dq.last());    // 2

        dq.removeFirst();
        System.out.println(dq);  // [1, 2]

        dq.removeLast();
        System.out.println(dq);  // [1]
    }
}
//Q2
//enqueue(e):
//    inbox.push(e)
//
//dequeue():
//    if outbox is empty:
//        while inbox is not empty:
//            outbox.push(inbox.pop())
//    return outbox.pop()

//Q3
//reverse(S):

//    while S is not empty:
//        A.push(S.pop())
//
//
//    while A is not empty:
//        B.push(A.pop())
//
//
//    while B is not empty:
//        S.push(B.pop())
