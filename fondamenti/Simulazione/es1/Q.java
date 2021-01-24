//GiulioCodutti2008795

import java.lang.IllegalArgumentException;

public class Q<T> implements Queue<T> {

    private Node front;
    private Node back;
    private int size;

    private class Node {
        public T el;
        public Node next;

        public Node(T e, Node n) {
            el = e;
            next = n;
        }
    }

    public Q() {
        back = front = null;
        size = 0;
    }

    /**
     * @return true se il contenitore e' vuoto, false altrimenti
     */
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * @return numero di elementi nel contenitore
     */
    public int size() {
        return size;
    }

    /**
     * inserisce nella coda l'elemento specificato
     * 
     * @param x elemento specificato
     * @throws java.lang.IllegalArgumentException se l'elemento specificato vale
     *                                            null
     */
    public void enqueue(T x) throws IllegalArgumentException {
        if (x == null) {
            throw new IllegalArgumentException();
        }

        Node temp = new Node(x, null);

        if (back == null) {
            front = back = temp;
            size++;
            return;
        }

        back.next = temp;
        back = temp;

        size++;
    }

    /**
     * @return il fronte della coda
     * @throw EmptyQueueException se la coda e' vuota
     */
    public T front() throws EmptyQueueException {
        if (size <= 0) {
            throw new EmptyQueueException();
        }
        return front.el;
    }

    /**
     * ispezione, estraendolo, l'elemento in fronte alla coda
     * 
     * @return elemento in fronte alla coda
     * @throw EmptyQueueException se la coda e' vuota
     */
    public T dequeue() throws EmptyQueueException {
        if (size <= 0) {
            throw new EmptyQueueException();
        }

        T temp = front.el;
        front = front.next;

        if(front == null){
            back = null;
        }

        size--;
        return temp;

    }
}
