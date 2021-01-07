/**
  * Queue
  * Tipo di dati astratto con modalità di accesso FIFO
  * author Goodrich - Tamassia
  * @see Container
  * @see EmptyQueueException
*/
public interface Queue extends Container
{
   /**
      inserisce l'elemento all'ultimo posto della coda
      @param obj nuovo elemento da inserire
   */
   void enqueue(Object obj);

   /**
      ispeziona l'elemento al primo posto della coda
      @return elemento al primo posto della coda
      @throws EmptyQueueException se la coda e' vuota
   */
   Object getFront() throws EmptyQueueException;
   
   /**
      rimuove l'elemento al primo posto della coda
      @return elemento rimosso
      @throws EmptyQueueException se la coda e' vuota
   */
   Object dequeue() throws EmptyQueueException;
}
