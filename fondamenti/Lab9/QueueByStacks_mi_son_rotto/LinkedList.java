/**
  * Lista concatenata: classe didattica
  * @author A. Luchetta
  * @version 20-Nov-2006
  * @see EmptyLinkedListException
*/
public class LinkedList implements Container
{
   // parte privata
   private ListNode head, tail;
   
   class ListNode
   {
      private Object element;
      private ListNode next;
 
      public ListNode(Object e, ListNode n)
      {
        element = e;
        next = n;
      }
 
      public ListNode()
      {
         this(null, null);
      }
 
      public Object getElement() { return element; }
 
      public ListNode getNext()  { return next; }
 
      public void setElement(Object e) { element = e; }
 
      public void setNext(ListNode n) {  next = n; }
   }

   /**
      costruttore
   */
   public LinkedList()
   {  makeEmpty();
   }

   /**
      rende la lista vuota
   */
   public void makeEmpty()
   {  head = tail = new ListNode();
   }

   /**
      verifica se la lista e' vuota
      @return true se la lista e' vuota, false altrimenti
   */
   public boolean isEmpty()
   {  return (head == tail);
   }

   /**
      ispeziona il primo elemento della lista
      complessita' temporale O(1)
      @return il primo elemento della lista
   */
   public Object getFirst() throws EmptyLinkedListException
   {
      if (isEmpty())
         throw new EmptyLinkedListException();

      return head.getNext().getElement();
   }

    /**
      ispeziona l'ultimo elemento della lista
      complessita' temporale O(1)
      @return l'ultimo elemento della lista
   */
   public Object getLast() throws EmptyLinkedListException
   {
      if (isEmpty())
         throw new EmptyLinkedListException();

      return tail.getElement();
   }

    /**
      inserisce un elemento in testa alla lista
      complessita' temporale O(1)
      @param e l'elemento da inserire
   */
   public void addFirst(Object e)
   {
      // inserisce il dato nell'header attuale
      head.setElement(e);

      // crea un nodo con due riferimenti null
      ListNode newNode = new ListNode();

      // collega il nuovo nodo all'header attuale
      newNode.setNext(head);

      // il nuovo nodo diventa il nodo header
      head = newNode;

      // tail non viene modificato
   }

    /**
      rimuove un elemento in testa alla lista
      complessita' temporale O(1)
      @return l'elemento rimosso
   */
   public Object removeFirst() throws EmptyLinkedListException
   {
      // delega a getFirst il controllo di lista vuota
      Object e = getFirst();

      // aggiorno l'header
      head = head.getNext();
      head.setElement(null);
      return e;
   }

    /**
      inserisce un elemento in coda alla lista
      complessita' temporale O(1)
      @param e l'elemento da inserire
   */
   public void addLast(Object e)
   {
      tail.setNext(new ListNode(e, null));
      tail = tail.getNext();
   }

    /**
      rimuove un elemento in coda alla lista
      complessita' temporale O(n)
      @return l'elemento rimosso
   */
   public Object removeLast() throws EmptyLinkedListException
   {
      // delega a getLast il controllo di lista vuota
      Object e = getLast();
 
      /*
        bisogna cercare il penultimo nodo partendo dall'inizio e finché non si
        arriva alla fine della catena
      */
      ListNode temp = head;
      while (temp.getNext() != tail)
         temp = temp.getNext();
 
      // a questo punto temp si riferisce al penultimo nodo
      tail = temp;
      tail.setNext(null);
      return e;
   }
}
