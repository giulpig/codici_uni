/**
  * FONDAMENTI DI INFORMATICA - CANALE 4
  * PROVA PRATICA DI PROGRAMMAZIONE DEL 26.1.2021
  *
  * Realizza un insieme di elementi di tipo generico limitato a comparabile
  *
  * @param T il tipo generico degli elementi di questo
  *
  * @author A. Luchetta
  * @version 17-01-2021
  */
public class S<T extends Comparable<T>>
{
   // parte privata
   private static final int INITIAL_CAPACITY = 1;
   private Object[] v;
   private int vSize;
   
   /**
      inizializza un insieme vuoto
   */
   public S()
   {
      v = new Object[INITIAL_CAPACITY];
      vSize = 0;
   }

   /**
      inserisce nell'insieme l'elemento specificato se diverso da null e non gia' presente
      @param x l'elemento specificato
   */ 
   public void add(T x)
   {
      // precondizioni
      if (x == null || contains(x)) 
         return;
               
      // eventuale ridimensionamento dinamico
      if (vSize >= v.length)
      {
         Object[] a = new Object[2 * v.length];
         System.arraycopy(v, 0, a, 0, v.length);
         v = a;
      }
      
      // inserimento e incremento del numero di elementi
      v[vSize++] = x;
   }

   /**
      Verifica se l'elemento specificato e' contenuto nell'insieme
      @param x elemento specificato
      @return true se questo insieme contiene l'elemento specificato, false altrimenti
   */
   public boolean contains(T x)
   {
      return search(x) >= 0;
   }
   
   // ricerca lineare
   private int search(T x)
   {
      for (int i = 0; i < vSize; i++)
         if (((T)v[i]).compareTo(x) == 0)
            return i;

      return -1;   
   }   
   
   /**
      genera l'insieme intersezione di questo insieme e l'insieme
      specificato
      @param s l'insieme specificato
      @return l'insieme intersezione di questo insieme e l'insieme specificato
   */
   public S<T> intersection(S<T> s)
   {
      // precondizioni
      if (s == null)
         return this;
         
      // generazione di un insieme vuoto
      S<T> ret = new S<T>(); 
      
      // accesso agli elementi di questo insieme
      Object[] a = toArray();
            
      // inserimento degli elementi nell'insieme intersezione
      for (int i = 0; i < a.length; i++)
         if (s.contains((T)a[i]))
            ret.add((T)a[i]);
            
       return ret;     
   }   
   
   /**
      Verifica se l'insieme e' vuoto
      @return true se questo insieme e' vuoto, false altrimenti
   */
   public boolean isEmpty()
   {
      return vSize <= 0;
   }
   
   /**
      Fornisce l'elemento massimo dell'insieme
      @return l'elemento massimo dell'insieme o null se l'insieme e' vuoto
   */
   public T max()
   {
      // precondizioni
      if (isEmpty())
         return null;
         
      // ipotesi di massimo
      T max = (T)v[0]; 
      
      // ricerca del massimo
      for (int i = 1; i < vSize; i++)
         if (max.compareTo((T)v[i]) < 0)
            max = (T)v[i]; 
            
      return max;      
   }
   
   /**
      Fornisce l'elemento minimo dell'insieme
      @return l'elemento minimo dell'insieme o null se l'insieme e' vuoto
   */
   public T min()
   {
      // precondizioni
      if (isEmpty())
         return null;
         
      // ipotesi di minimo
      T min = (T)v[0]; 
      
      // ricerca del minimo
      for (int i = 1; i < vSize; i++)
         if (min.compareTo((T)v[i]) > 0)
            min = (T)v[i]; 
            
      return min;      
   }
   
   /**
      rimuove dall'insieme l'elemento specificato se presente
      @param l'insieme specificato
   */
   public void remove(T x)
   {
      // precondizioni
      if (x == null || isEmpty())
         return;
         
      // ricerca lineare
      int i = search(x); 
      
      // non presente
      if (i < 0)
         return;
         
      // rimozione dell'elemento specificato
      v[i] = v[vSize - 1];
      v[vSize - 1] = null;  // cancellazione dell'elemento duplicato
      
      // decremento del numero di elementi
      vSize--;              
   }
     
   /**
      genera l'insieme differenza fra questo insieme e l'insieme
      specificato
      @param s l'insieme specificato
      @return l'insieme differenza fra questo insieme con l'insieme specificato
   */
   public S<T> subtract(S<T> s)
   {
      // precondizioni
      if (s == null)
         return this;
         
      // generazione di un insieme vuoto
      S<T> ret = new S<T>(); 
      
      // accesso agli elementi di questo insieme
      Object[] a = toArray();
            
      // inserimento degli elementi nell'insieme differenza
      for (int i = 0; i < a.length; i++)
         if (!s.contains((T)a[i]))
            ret.add((T)a[i]);
            
       return ret;     
   }
   
   /**
      Fornisce  il numero di elementi contenuti dell'insieme
      @return il numero di elementi contenuti in questo insieme
   */
   public int size()
   {
      return vSize;
   }
   
   // accesso tramite array
   private Object[] toArray()
   {
      Object[] ret = new Object[vSize];
      System.arraycopy(v, 0, ret, 0, vSize);
      
      return ret;
   }
   
   
   /**
      genera l'insieme unione di questo insieme e l'insieme specificato
      @param s l'insieme specificato
      @return l'insieme unione di questo insieme con l'insieme specificato
   */
   public S<T> union(S<T> s)
   {
      // precondizioni
      if (s == null)
         return this;
         
      // generazione di un insieme vuoto
      S<T> ret = new S<T>(); 
      
      // accesso agli elementi di questo insieme 
      Object[] a = toArray();
            
      // inserimento degli elementi di questo insieme nell'insieme unione
      for (int i = 0; i < a.length; i++)
         s.add((T)v[i]);
         
      // accesso agli elementi dell'insieme specificato 
      a = s.toArray(); 
      
      // inserimento degli elementi dell'insieme specificato nell'insieme unione
      for (int i = 0; i < a.length; i++)
         s.add((T)v[i]);              
            
       return ret;     
   }          
}
