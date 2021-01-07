public class Q<T> implements Queue<T> {
    
    private Node head, tail;
    private int size;

    public Q(){
        head = new Node(null, null);
        tail = head;
        size = 0;
    }

    public void enqueue(T toInsert){
        Node nodo = new Node(null, toInsert);
        tail.setNext(nodo);
        tail = nodo;
        size++;
    }

    public T dequeue() throws EmptyQueueException{
        if(size <= 0)
            throw new EmptyQueueException();

        T ret = head.getNext().getValue();
        Node toErase = head.getNext();

        head.setNext(head.getNext().getNext());

        toErase.setValue(null);
        toErase.setNext(null);

        size--;
        return ret;
    }

    public T front() throws EmptyQueueException{
        if(size <= 0)
            throw new EmptyQueueException();
            
        return head.getNext().getValue();
    }

    public boolean isEmpty(){
        return size<=0;
    }

    public int size(){
        return size;
    }

    public Object[] toArray(){
        Object[] ret = new Object[size];
        Node nodo = head.getNext();
        int i = 0;
        do{
            ret[i] = (Object)nodo.getValue();
            i++;
            nodo = nodo.getNext();
        }
        while(nodo.getNext() != null);

        return ret;
    }





    private class Node{

        private Node next;
        private T value;

        public Node(Node toNext, T toValue){
            next = toNext;
            value = toValue;
        }

        public Node getNext(){
            return next;
        }

        public void setNext(Node toSet){
            next = toSet;
        }

        public T getValue(){
            return value;
        }

        public void setValue(T toSet){
            value = toSet;
        }

    }
}
