/**
  * FONDAMENTI DI INFORMATICA - CANALE 4
  * PROVA DI PROGRAMMAZIONE DEL 26-01-2021
  * 
  * interfaccia Deque - definisce il tipo di dati astratto Doppia Coda
  * 
  * @typeparam E il tipo generico degli elementi di questa doppia coda
  * 
  * @see EmptyDequeException
  * 
  * @author Goodrich - Tamassia
  * @version 17-01-2021
  */
public interface Deque<E>
{
   /**
      Verifica se questa doppia coda e' vuota
      @return true se questa doppia coda e' vuota, false altrimenti
   */
   boolean isEmpty();
  
   /**
      rende vuota questa doppia coda
   */ 
   void makeEmpty();
    
   /**
      Fornisce il numero di elementi presenti in questa doppia coda
      @return  numero di elementi presenti in questa doppia coda
   */
   int size();
   
   /**
      Inserisce l'elemento specificato al primo posto di questa doppia coda
      @param element l'elemento specificato
      @throws IllegalArgumentException se l'elemento specificato vale null
   */
   void addFirst(E element);
   
   /**
      Inserisce l'elemento specificato all'ultimo posto di questa doppia coda
      @param element l'elemento specificato
      @throws IllegalArgumentException se l'elemento specificato vale null
   */
   void addLast(E element);

   /**
      Ispeziona, ma non estrae, il primo elemento di questa doppia coda
      @return il primo elemento di questa doppia coda
      @throws EmptyDequeException se questa doppia coda e' vuota
   */
   E getFirst() throws EmptyDequeException;

   /**
      Ispeziona, ma non estrae, l'ultimo elemento di questa doppia coda
      @return l'ultimo elemento di questa doppia coda
      @throws EmptyDequeException se questa doppia coda e' vuota
   */
   E getLast() throws EmptyDequeException;

   /**
      Ispeziona ed estrae il primo elemento di questa doppia coda
      @return il primo elemento di questa doppia coda
      @throws EmptyDequeException se questa doppia coda e' vuota
   */
   E removeFirst() throws EmptyDequeException;
   
   /**
      Ispeziona ed estrae l'ultimo elemento di questa doppia coda
      @return l'ultimo elemento di questa doppia coda
      @throws EmptyDequeException se questa doppia coda e' vuota
   */
   E removeLast() throws EmptyDequeException;
}
