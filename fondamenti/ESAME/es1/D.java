//giulio codutti, matricola 2008795

/**
 * FONDAMENTI DI INFORMATICA - CANALE 4 PROVA DI PROGRAMMAZIONE DEL 26-01-2021
 *
 * Realizza il tipo di dati astratto Doppia Coda
 * 
 * @typeparam E il tipo generico degli elementi di questa doppia coda
 *
 * @see Deque
 * @see EmptyDequeException
 * 
 * @author A. Luchetta
 * @version 17-01-2021
 */
public class D<E> implements Deque<E> {
   // parte privata

   /* DA CODIFICARE */

   Node tail;
   Node head;
   int size;

   private class Node {
      private Object value;
      private Node next;
      private Node prev;

      public Node(Object w, Node p, Node n) {
         setValue(w);
         setPrev(p);
         setNext(n);
      }

      public Node() {
         this(null, null, null);
      }

      public Object getValue() {
         return value;
      }

      public Node getNext() {
         return next;
      }

      public Node getPrev() {
         return prev;
      }

      public void setValue(Object w) {
         value = w;
      }

      public void setNext(Node n) {
         next = n;
      }

      public void setPrev(Node n) {
         prev = n;
      }
   }

   /**
    * inizializza questa doppia Coda vuota
    */
   public D() {
      /* DA CODIFICARE */
      head = new Node();
      tail = new Node();
      head.setNext(tail);
      tail.setPrev(head);
      size = 0;
   }

   /**
    * Verifica se questo contenitore e' vuoto
    * 
    * @return true se questo contenitore e' vuoto, false altrimenti
    */
   public boolean isEmpty() {
      return size() <= 0;
   }

   /**
    * rende vuoto questo contenitore
    */
   public void makeEmpty() {

   }

   /**
    * Fornisce il numero di elementi presenti in questo contenitore
    * 
    * @return numero di elementi presenti in questo contenitore
    */
   public int size() {
      /* DA CODIFICARE */

      return size;
   }

   /**
    * inserisce l'elemento specificato al primo posto di questa doppia coda
    * 
    * @param element l'elemento specificato
    * @throws IllegalArgumentException se l'elemento specificato vale null
    */
   public void addFirst(E element) {
      /* DA CODIFICARE */
      if (element == null) {
         throw new IllegalArgumentException();
      }
      Node temp = new Node(element, head, head.getNext());
      head.getNext().setPrev(temp);
      head.setNext(temp);
      size++;
   }

   /**
    * inserisce l'elemento specificato all'ultimo posto di questa doppia coda
    * 
    * @param element l'elemento specificato
    * @throws IllegalArgumentException se l'elemento specificato vale null
    */
   public void addLast(E element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      Node temp = new Node(element, tail.getPrev(), tail);
      tail.getPrev().setNext(temp);
      tail.setPrev(temp);
      size++;
   }

   /**
    * ispeziona, ma non estrae, il primo elemento di questa doppia coda
    * 
    * @return il primo elemento di questa doppia coda
    * @throws EmptyDequeException se questa doppia coda e' vuota
    */
   public E getFirst() throws EmptyDequeException {
      /* DA CODIFICARE */

      if (isEmpty()) {
         throw new EmptyDequeException();
      }

      return (E) head.getNext().getValue();
   }

   /**
    * ispeziona, ma non estrae, l'ultimo elemento di questa doppia coda
    * 
    * @return l'ultimo elemento di questa doppia coda
    * @throws EmptyDequeException se questa doppia coda e' vuota
    */
   public E getLast() throws EmptyDequeException {
      return null;
   }

   /**
    * ispeziona ed estrae il primo elemento di questa doppia coda
    * 
    * @return il primo elemento di questa doppia coda
    * @throws EmptyDequeException se questa doppia coda e' vuota
    */
   public E removeFirst() throws EmptyDequeException {
      return null;
   }

   /**
    * ispeziona ed estrae l'ultimo elemento di questa doppia coda
    * 
    * @return l'ultimo elemento di questa doppia coda
    * @throws EmptyDequeException se questa doppia coda e' vuota
    */
   public E removeLast() throws EmptyDequeException {
      /* DA CODIFICARE */

      if (isEmpty()) {
         throw new EmptyDequeException();
      }

      Node temp = tail.getPrev();

      tail.setPrev(temp.getPrev());
      temp.getPrev().setNext(tail);

      size--;

      return (E) temp.getValue();
   }
}
