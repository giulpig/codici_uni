/**
  * FONDAMENTI DI INFORMATICA - CANALE 4
  * PROVA PRATICA DI PROGRAMMAZIONE DEL 26.1.2021
  *
  * Definisce il concetto astratto di posizione all'interno di una lista
  *
  * @author A. luchetta
  * @version 21-01-2021
  */
public interface Iterator
{
   /**
      Ispeziona l'elemento DOPO la posizione corrente dell'iteratore, avanzando
      successivamente l'iteratore di una posizione nella lista
      @return l'elemento ispezionato, se presente
      throws java.util.NoSuchElementException se non ci sono elementi dopo la
             posizione corrente dell'iteratore
   */
   Object next();

   /**
      Verifica se e' presente almeno un elemento dopo la posizione corrente
      dell'iteratore
      @return true se e' presente almeno un elemento, false altrimenti
   */
   boolean hasNext();

  /**
     Inserisce un nuovo elemento dopo la posizione corrente dell'iteratore,
     l'iteratore si posiziona dopo il nuovo elemento
     @param x elemento da inserire
  */
  void add(Object x);

  /**
     Elimina l'ultimo nodo esaminato dal metodo next()
     puo' essere invocato solo dopo l'invocazione del metodo next()
     @throws java.lang.IllegalStateException se precedentemente non e' stato
             invocato il metodo next()
  */
  void remove();
}
