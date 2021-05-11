/**
  * FONDAMENTI DI INFORMATICA - CANALE 4
  * PROVA PRATICA DI PROGRAMMAZIONE DEL 26.1.2021
  *
  * Definisce una lista
  *
  * @see Iterable
  *
  * @author A. Luchetta
  * @version 17-01-2021
  */
public interface List extends Iterable
{
   /**
      Verifica se il contenitore e' vuoto
      @return true se il contenitore e' vuoto, false altrimenti
   */
   boolean isEmpty();
  
   /**
      rende il contenitore vuoto
   */
   void makeEmpty();  

   /**
      Fornisce il numero di elementi presenti nel contenitore
      @return  numero di elementi nel contenitore
   */
   int size();

   /**
     Restituisce l'elemento alla posizione specificata nella lista
     @index indice dell'elemento da restituire
   */
   Object get(int index);

   /**
     Sostituisce l'elemento alla posizione specificata nella lista con
     l'elemento x specificato
     @param index posizione specificata
     @param x elemento specificato
     @return l'elemento precedentemente memorizzato alla posizione specificata
   */
   Object set(int index, Object x);

  /**
      Inserisce l'elemento specificato alla posizione specificata nella lista
      Sposta a destra tutti gli elementi presenti di un posto
      @param index posizione specificata
      @param x elemento specificat
   */
   void add(int index, Object x);

  /**
      Estrae l'elemento alla posizione specificata nella lista. Sposta a
      sinistra di un posto tutti gli elementi che seguono l'elemento estratto
      @param index posizione specificata

   */
   Object remove(int index);
}
