/**
  * LinkedListQueue
  * realizza il tipo di dati astratto Coda con una lista concatenata.
  * @author A. Luchetta
  * @version 20-Nov-2006
  * @see Queue
  * @see EmptyQueueException
  */
public class LinkedListQueue implements Queue
{
   private LinkedList list;
   private int size;

   public LinkedListQueue()
   {  makeEmpty();
   }

   /**
     rende vuoto il contenitore
   */
   public void makeEmpty()
   {  list = new LinkedList();
      size = 0;
   }

   /**
     verifica se il contenitore e' vuoto
     @return true se il contenitore e' vuoto, false altrimenti
   */
   public boolean isEmpty()
   {  return list.isEmpty();
   }

   /**
      @return il numero di elementi nel contenitore
   */
   public int size()
   {  return size;
   }

   /**
      inserisce un elemento all'ultimo posto della coda
      O(1)
      @param obj nuovo elemento da inserire
   */
   public void enqueue(Object obj)
   {
      list.addLast(obj);
      size++;
   }

   /**
      ispeziona l'elemento al primo posto della coda
      O(1)
      @return l'elemento al primo posto della coda
      @throws EmptyQueueException se la coda e' vuota
   */
   public Object getFront() throws EmptyQueueException
   {
      if (isEmpty())
         throw new EmptyQueueException();

      return list.getFirst();
   }

  /**
     rimuove l'elemento al primo posto della coda
     O(1)
     @return l'elemento rimosso
     @throws EmptyQueueException se la coda e' vuota
  */
   public Object dequeue() throws EmptyQueueException
   {
      Object obj = getFront();
      list.removeFirst();
      size--;

      return obj;
   }
}

