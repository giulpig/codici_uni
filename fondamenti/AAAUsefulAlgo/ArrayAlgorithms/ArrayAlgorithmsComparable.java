/**
  * riunisce algoritmi applicabili agli array di riferimenti ad oggetti comparabili

  * @version 8-Nov-2006
  * @author Horstmann
  *
*/
public class ArrayAlgorithmsComparable
{
   /**
      ordinamento per selezione degli elementi di un array di numeri interi. O(n*n).
      @param a l'array da ordinare
   */

   public static void selectionSort(Comparable[] a)
   {  
      for (int i = 0; i < a.length - 1; i++)
      {
         int minPos = findMinPos(a, i);
         if (minPos != i) swap(a, minPos, i);
      }
   }

   /*
      scambia due elementi nell'array
      @param a l'array in cui vengono scambiati gli elementi
      @param i indice di uno degli elementi da scambiare
      @param j indice di uno degli elementi da scambiare
   */
   private static void swap(Comparable[] a, int i, int j)
   {
      Comparable temp = a[i];
      a[i] = a[j];
      a[j] = temp;
   }

   /*
      restituisce l'indice dell'elemento minimo nel sottoarray di a che inizia all'indice from
      @param a l'array in cui viene effettuata la ricerca del minimo
      @param from indice da cui inizia la ricerca
      @return l'indice dell'elemento minimo nel sottoarray di a che inizia dall'indice from
   */
   private static int findMinPos(Comparable[] a, int from)
   {
      int pos = from;
      Comparable min = a[from];
      
      for (int i = from + 1; i < a.length; i++)
      {
         Comparable temp = a[i];       // metodo complicato per
         if (temp.compareTo(min) < 0)  // rendere più semplice la
         {  pos = i;                   // valutazione delle
            min = temp;                // prestazioni (più avanti)
         }
      }

      return pos;
   }
   
   /**
      ordinamento per fusione degli elementi di un array di numeri interi. O(n*log n).
      @param a l'array da ordinare
   */
   public static void mergeSort(Comparable[] a)
   {
      // caso base
      if (a.length < 2) return;

      // dividiamo (circa) a meta'
      int mid = a.length / 2;

      Comparable[] left = new Comparable[mid];
      Comparable[] right = new Comparable[a.length - mid];
      System.arraycopy(a, 0, left, 0, mid);
      System.arraycopy(a, mid, right, 0, a.length - mid);

      // passi ricorsivi: problemi piu' semplici
      // si tratta di doppia ricorsione
      mergeSort(left);
      mergeSort(right);
      // fusione
      merge(a, left, right);
   }

   /*
      fonde due sottoarray ordinati
      @param a l'array ordinato
      @param b sottoarray ordinato
      @param c sottoarray ordinato
   */
   private static void merge(Comparable[] a, Comparable[] b, Comparable[] c)
   {
      int ia = 0, ib = 0, ic = 0;

      //finché ci sono elementi in b e c
      while (ib < b.length && ic < c.length)
         if (b[ib].compareTo(c[ic]) < 0)
            a[ia++] = b[ib++];
         else
            a[ia++] = c[ic++];

      //qui uno dei due array non ha più
      //elementi
      while (ib < b.length)
         a[ia++] = b[ib++];
      while (ic < c.length)
         a[ia++] = c[ic++];
   }

   /**
      ordinamento per inserimento degli elementi di un array di numeri interi. O(n) nel caso migliore. O(n*n) nei casi medio e peggiore..
      @param a l'array da ordinare
   */
   public static void insertionSort(Comparable[] a)
   {
      // il ciclo inizia da 1 perché il primo
      // elemento non richiede attenzione
      for (int i = 1; i < a.length; i++)
      {  // nuovo elemento da inserire
         Comparable cur = a[i];
         // la variabile di ciclo j va definita
         // fuori dal ciclo perché il suo valore
         // finale viene usato in seguito
         int j;
         // vengono spostati a destra di un posto
         // tutti gli elementi a sinistra di temp
         // che sono maggiori di temp, procedendo
         // da destra a sinistra
         for (j = i; j > 0 && cur.compareTo(a[j-1]) < 0; j--)
            a[j] = a[j-1];
         // temp viene inserito in posizione
         a[j] = cur;
      }
   }
   
   /**
      ricerca lineare. O(n).
      @param a l'array in cui viene effettuata la ricerca
      @param target l'elemento da ricercare
      @return l'indice nell'array della prima occorrenza dell'elemento cercato, se presente, -1 altrimenti
   */
   public static int linearSearch(Comparable[] a, Comparable target)
   {
      for (int i = 0; i < a.length; i++)
         if (a[i].compareTo(target) == 0)
            return i; // TROVATO

      return -1;      // NON TROVATO
   }
   
   /**
      ricerca lineare con sentinella. O(n).
      @param a l'array riempito solo in parte in cui viene effettuata la ricerca
      @param size il numero di elementi inseriti nell'array riempito solo in parte
      @param target l'elemento da ricercare
      @return l'indice nell'array della prima occorrenza dell'elemento cercato, se presente, -1 altrimenti
   */
   public static int guardedLinearSearch(Comparable[] a, int size, Comparable target)
   {
      a[size] = target;
      int i;
      for (i = 0; a[i].compareTo(target) != 0; i++)
         ;
      if (i != size)
         return i;     // TROVATO

      return -1;      // NON TROVATO
   }
   

   /**
      ricerca binaria. O(n). Algoritmo ricorsiva
      @param a l'array in cui viene effettuata la ricerca
      @param v l'elemento da ricercare
      @return l'indice nell'array della prima occorrenza dell'elemento cercato, se presente, -1 altrimenti
   */
   public static int recursiveBinarySearch(Comparable[] a, int v)
   {
      return binSearch(a, 0, a.length - 1, v);
   }

   /*
   
      @param a l'array in cui viene effettuata la ricerca
      @param from indice nell'array a da cui inizia la ricerca
      @param to indice nell'array a cui finisce la ricerca
      @param target l'elemento da ricercare
      @return l'indice nell'array della prima occorrenza dell'elemento cercato, se presente, -1 altrimenti
   */
   private static int binSearch(Comparable[] a, int from, int to, Comparable target)
   {
      if (from > to)
         return -1;  // NON TROVATO

      int mid = (from + to) / 2; // CIRCA IN MEZZO
      Comparable middle = a[mid];

      if (middle.compareTo(target) == 0)
         return mid;             // TROVATO
      else if (middle.compareTo(target) < 0)
         return binSearch(a, mid + 1, to, target);   // CERCA A DESTRA
      else
         return binSearch(a, from, mid - 1, target); // CERCA A SINISTRA
   }

   /**
      ricerca binaria. O(n). Algoritmo iterativo
      @param a l'array in cui viene effettuata la ricerca
      @param v l'elemento da ricercare
      @return l'indice nell'array della prima occorrenza dell'elemento cercato, se presente, -1 altrimenti
   */
   public static int iterativeBinarySearch(Comparable[] a, int v)
   {
      int from = 0;                  //Limite inferiore
      int to = a.length - 1;         //Limite superiore

      //fino a che l'array corrente ha almeno un elemento
      while(from <= to)
      {
         int mid = (from + to)/2;     //CIRCA IN MEZZO

         if (a[mid].compareTo(v) == 0)
            return mid;               //TROVATO
         else if (a[mid].compareTo(v) < 0)
            from = mid + 1;           //CERCA A DESTRA
         else
            to = mid - 1;             //CERCA A SINISTRA
      }

      return -1; //NON TROVATO
   }
}
