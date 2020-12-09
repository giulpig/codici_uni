public class DoublyLinkedList<T> implements Container{

    Node head;
    Node tail;
    int size;

    public DoublyLinkedList(){
        this.head = new Node(null, null, null);
        this.tail = new Node(null, head, null);
        this.head.setNext(tail);
        this.size = 0;
    }

    public void addFirst(T val){
        Node toInsert = new Node(val, head, head.getNext());
        head.getNext().setPrev(toInsert);
        head.setNext(toInsert);
        size++;
    }

    public void addLast(T val){
        Node toInsert = new Node(val, tail.getPrev(), tail);
        tail.getPrev().setNext(toInsert);
        tail.setPrev(toInsert);
        size++;
    }

    public T removeFirst(){
        Node toRemove = head.getNext();
        head.setNext(toRemove.getNext());
        toRemove.getNext().setPrev(head);
        size--;
        return toRemove.val;
    }

    public T removeLast(){
        Node toRemove = tail.getPrev();
        tail.setPrev(toRemove.getPrev());
        toRemove.getPrev().setNext(tail);
        size--;
        return toRemove.val;
    }



    public T getFirst(){  return head.getNext().getElement();}

    public T getLast(){  return tail.getPrev().getElement();}

    public boolean isEmpty(){  return size==0;}

    public void makeEmpty(){  
        this.head = new Node(null, null, null);
        this.tail = new Node(null, head, null);
        this.head.setNext(tail);
        this.size = 0;
    }

    public int size(){  return size;}



    private class Node{

        private T val;
        private Node next, prev;

        public Node(){
            this(null, null, null);
        }

        public Node(T toValue, Node prevNode, Node nextNode){
            this.val = toValue;
            this.next = nextNode;
            this.prev = prevNode;
        }

        public void setElement(T toSet){  this.val = toSet;}

        public T getElement(){  return this.val;}

        public Node getNext(){  return this.next;}

        public void setNext(Node toNext){  this.next = toNext;}

        public Node getPrev(){  return this.prev;}

        public void setPrev(Node toPrev){  this.prev = toPrev;}



    }
    
}
