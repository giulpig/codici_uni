import java.util.NoSuchElementException;
/**
  * FONDAMENTI DI INFORMATICA - CANALE 4
  * PROVA PRATICA DI PROGRAMMAZIONE DEL 26.1.2021
  *
  * Realizza una lista
  *
  * @author Adriano Luchetta
  * @version 21-01-2021
*/
public class L implements List
{
  // costante di classe
  private static final int INITIAL_CAPACITY = 1; 
  
  //variabili di esemplare
  private Object[] v;
  private int vSize;

  //costruttore: inizializza una lista vuota
  public L()
  {
     makeEmpty();
  }
  
  /**
     @return true se il contenitore e' vuoto, false altrimenti
  */
  public boolean isEmpty()
  {
    return vSize <= 0;
  }
  
  /**
     rende la lista vuota
  */
  public void makeEmpty()
  {
    v = new Object[INITIAL_CAPACITY];
    vSize = 0;
  }
  
  
  /**
     @return  numero di elementi nel contenitore
  */
  public int size()
  {
    return vSize;
  }

/**
  Genera un iteratore posizionato all'inizio della sequenza
  @return un iteratore
*/
  public Iterator iterator()
  {
    return new ArrayIterator();
  }
  

  // Classe Interna
  private class ArrayIterator implements Iterator
  {
    // Costanti
    private boolean ILLEGAL_STATE = false;
    private boolean LEGAL_STATE = !ILLEGAL_STATE;
        
    // variabili di esemplare
    private int position;
    private boolean state;
  
    // costruttore: inizializza un iteratore
    public ArrayIterator()
    {
      position = 0;
      state = ILLEGAL_STATE;
    }

   /*
      verifica se e' presente almeno un elemento dopo la posizione corrente
      dell'iteratore
      @return true se e' presente almeno un elemento, false altrimenti
   */
    public boolean hasNext()
    {
      return position < vSize;
    }

   /*
      ispeziona l'elemento DOPO la posizione corrente dell'iteratore, avanzando
      successivamente l'iteratore di una posizione nella lista
      @return l'elemento ispezionato, se presente
      throws java.util.NoSuchElementException se non ci sono elementi dopo la
      posizione corrente dell'iteratore
   */
    public Object next() throws NoSuchElementException
    {
      if (!hasNext())
        throw new NoSuchElementException();
        
      // aggiornamento di stato
      state = LEGAL_STATE;  // si potra' invocare remove

      return v[position++];
    }

  /*
     inserisce un nuovo elemento dopo la posizione corrente dell'iteratore,
     l'iteratore si posiziona dopo il nuovo elemento
     @param x elemento da inserire
  */
    public void add(Object obj)
    {
      // eventuale ridimensionamento dinamico
      if (vSize >= v.length)
         resize();

      // spostamento  a destra degli elementi di indice superiore o uguale alla
      // posizione corrente
      for (int i = vSize; i > position; i--)
        v[i] = v[i-1];

      // inserimento del nuovo elemento
      v[position++] = obj;

      // incremento del numero di elementi
      vSize++;

      // aggiornamento dello stato
      state = ILLEGAL_STATE; // non si potra' invocare remove
    }

  /*
     elimina l'ultimo nodo esaminato dal metodo next()
     puo' essere invocato solo dopo l'invocazione del metodo next()
     @throws java.lang.IllegalStateException se precedentemente non e' stato
             invocato il metodo next()
  */
    public void remove() throws IllegalStateException
    {
      // verifica se precedentemente e' stato invocato il metodo next
      if (state == ILLEGAL_STATE)
        throw new IllegalStateException();

      // spostamento a sinistra degli elementi che seguono l'elemento da rimuovere
      for (int i = position - 1; i < vSize-1; i++)
        v[i] = v[i+1];

      // decremento del numero di elementi
      vSize--;

      // posizionamento
      position--;

      // gestione dello stato
      state = ILLEGAL_STATE;
    }
  }
  
   /**
     Restituisce l'elemento alla posizione specificata nella lista
     @param index indice dell'elemento da restituire
   */
   public Object get(int index)
   {
      // precondizioni
      if (index < 0 || index >= vSize)
         throw new IllegalArgumentException();

      return v[index];
   }

   /**
     Sostituisce l'elemento alla posizione specificata nella lista con l'elemento x
     @param index posizione dell'elemento da sostituire
     @param x elemento da memorizzare alla posizione
      specificata
     @return l'elemento precedentemente memorizzato
      Alla posizione specificata
   */
   public Object set(int index, Object x)
   {
      // precondizioni
      if (index < 0 || index >= vSize)
         throw new IllegalArgumentException();
         
      // solvataggio del dato da restituire
      Object o = v[index];
 
      // sostituzione
      v[index] = x;

      return o;
   }

  /**
      Inserisce l'elemento specificato alla posizione specificata nella lista
      Sposta a destra tutti gli elementi presenti
      di un posto
      @index rango dell'elemento da inserire
      @x elemento da inserire
   */
   public void add(int index, Object x)
   {
      // precondizioni
      if (index < 0 || index > vSize)
         throw new IllegalArgumentException();
         
      // eventuale ridimensionamento dinamico
      if (vSize >= v.length)
         resize();

      // spostamento a destra
      for (int i = vSize; i > index; i--)
         v[i] = v[i - 1];

      // inserimento
      v[index] = x;

      // incremento del numero di elementi
      vSize++;
   }

  /**
      Estrae l'elemento alla posizione specificata nella lista. Sposta a sinistra di
      un posto tutti gli elementi che seguono
      L'elemento estratto
      @index rango dell'elemento da sostituire

   */
   public Object remove(int index)
   {
      // precondizioni
      if (index < 0 || index >= vSize)
         throw new IllegalArgumentException();

      // memorizzazione dell'elemento da restituire
      Object x = v[index];

      // spostamento a sinistra
      for (int i = index; i < vSize - 1; i++)
         v[i] = v[i + 1];

      // pulizia per Garbage Collector
      v[vSize - 1] = null;

      // decremento del numero di elementi
      vSize--;
      
      return x;
   }
   
   // ridimensionamento dinamico 
   private void resize()
   {
     // nuovo array di dimensione doppia
     Object[] vTmp = new Object[2 * v.length];

     // copia dal vecchio al nuovo array
     for (int i = 0; i < v.length; i++)
       vTmp[i] = v[i];
      
     v = vTmp;
   }
}
