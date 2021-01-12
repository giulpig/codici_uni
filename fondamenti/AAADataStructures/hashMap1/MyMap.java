/**
   Classe MyMap
   realizzazione con hash table

   @author A. Luchetta
   @version 20-Genn-2009
   @see Map
*/
public class MyMap  implements Map //--ADT map
{
   // costante statiche
   private static final int CAPACITY = 101;    // numero primo

   // variabili di esemplare
   private ListNode[] v;          // array di liste concatenate
   private int size;
   
   // classe interna ListNode
   private class ListNode
   {
      // variabili di esemplare
      private Object key;
      private Object value;      
      private ListNode next;
      
      // costruttore
      public ListNode(Object k, Object w, ListNode n)
      {
         setKey(k);
         setValue(w);
         setNext(n);
      }
      
      // costruttore
      public ListNode()
      {
         this(null, null, null);
      }
            
      // metodi di accesso
      public Object getKey()    { return key;   }
      public Object getValue()  { return value; }
      public ListNode getNext() { return next;  }
      
      // metodi modificatori
      public void setKey(Object k)   { key = k;   }
      public void setValue(Object w) { value = w; }       
      public void setNext(ListNode n){ next = n;  }    
   } 
   
   /**
      inizializza questa mappa vuota
   */
   public MyMap()
   {
      // creazione dell'array
      v = new ListNode[CAPACITY];
      
      // creazione dei buckets
      for (int i = 0; i < v.length; i++)
         v[i] = new ListNode();
         
      size = 0;
   }

   /**
      verifica se questa mappa e' vuota
      @return true se la mappa e' vuota, false altrimenti
   */
   public boolean isEmpty() //       O(1)
   {
      return size <= 0;
   }

   /**
      fornisce il numero di elementi presenti in questa mappa
      @return numero di elementi contenuti nella mappa
   */
   public int size()  //       O(1)
   {
      return size;
   }

   /**
      restituisce il valore associato alla chiave specificata in questa mappa
      @param key la chiave specificata
      @return il valore associato alla chiave specificata, se presente,
              null se l'associazione di chiave specificata non e' presente
      @throws IllegalArgumentException se key vale null
   */
   public Object get(Object key)       // O(n/M) nel caso medio e peggiore
   {                                   // dove M e' la dimensione della tabella
      if (key == null)
         throw new IllegalArgumentException();

      // ricerca
      ListNode n = search(key);

      // gestione del caso in cui l'associazione di chiave specificata non e'
      // presente
      if (n.getNext() == null)
         return null;
         
      // chiave specificata presente - restituzione del valore
      Object value = n.getNext().getValue(); // valore dell'associazione
      return value;
   }
   
   /*
     ricerca - restituisce il nodo che precede il nodo in cui è presente
     l'associazione di chiave specificata o l'ultimo nodo del bucket se
     l'associazione non e' presente
   */ 
   private ListNode search(Object key) // O(n/M) nel caso medio e peggiore
   {                                   // dove M e' la dimensione della tabella
      // calcolo della chiave ridotta
      int i = h(key);
      
      // ricerca nel bucket di indice i
      ListNode n = v[i];           // nodo head
      while (n.getNext() != null)
      {
         Object k = n.getNext().getKey();      // chiave dell'associazione
         if (k.equals(key))
            break;          // trovato
            
         n = n.getNext();   
      }
      
      return n;
   } 
   
   // calcolo della chiave ridotta
   private int h(Object k)  //O(1)
   {
      int h = k.hashCode() % v.length;
      
      // calcolo del valore assoluto
      if (h < 0)
         h = -h;
         
      return h;   
   }

   /**
      inserisce l'associazione key/value nella mappa. Se la chiave e' gia'
      presente, sostituisce l'associazione e restituisce il valore
      precedentemente associato alla chiave
      @param key la chiave
      @param value il valore da associare alla chiave
      @return il valore precedentemente associato alla chiave specificata, se
              la chiave e' presente, null se la chiave non e' gia' presente
      @throws IllegalArgumentException se key o value valgono null
   */
   public Object put(Object key, Object value) //O(n/M) nel caso medio e peggiore
   {                                    // dove M e' la dimensione della tabella
      // gestione delle precondizioni
      if (key == null || value == null)
         throw new IllegalArgumentException();

      // ricerca dell'associazione di chiave specificata
      ListNode n = search(key);
           
      // associazione non presente
      if (n.getNext() == null)
      {
         // generazione del nodo che diventera' l'ultimo nodo del buckey
         ListNode tail = new ListNode(key, value, null);

         // posizionamento del nodo tail come ultimo del bucket
         n.setNext(tail);
         
         // incremento del numero di associazioni
         size++;
         
         return null;
      }
      
      // associazione presente
      // memorizzazione del vecchio valore
      Object old = n.getNext().getValue(); // memorizzazione

      // sostituzione della vecchia associazione con la nuova
      n.getNext().setValue(value);
      
      return old;
   }

   /**
      elimina l'associazione di chiave specificata
      @param key la chiave specificata
      @return il valore associato alla chiave specificata, se presente, null se
              la chiave non e' presente
      @throws IllegalArgumentException se key vale null
   */
   public Object remove(Object key)    // O(n/M) nel caso medio e peggiore
   {                                   // dove M e' la dimensione della tabella
      // gestione delle precondizioni
      if (key == null)
         throw new IllegalArgumentException();

      // ricerca
      ListNode n = search(key);

      // gestione del caso in cui l'associazione di chiave specificata non e'
      // presente
      if (n.getNext() == null)
         return null;
         
      // caso in cui l'associazione e' presente
      // memorizzazione del valore associato alla chiave specificata
      Object old = n.getNext().getValue();
      
      // rimozione del nodo contenente l'associazione di chiave specificata
      n.setNext(n.getNext().getNext());

      // decremento del numero di associazioni 
      size--;
         
      return old;
   }

   /**
      fornisce le chiavi di questa mappa.
      @return array contenente le chiavi di questa mappa
   */
   public Object[] keys()  // O(n)
   {
      // generazione dell'array
      Object[] k = new Object[size];
      
      // ciclo di accesso alle chiavi
      int ik = 0;    // indice nell'array k 
      for (int i = 0; i < v.length; i++)  // scansione di tutti i bucket
      {
         // nodo successivo al nodo head del bucket i-esimo
         ListNode n = v[i].getNext();
         
         // attraversamento del bucket e raccolta delle chiavi
         while (n != null)
         {
            // memorizzazione della chiave nell'array 
            k[ik++] = n.getKey();
            
            // passaggio al nodo successivo
            n = n.getNext();
         }     
      }
         
      return k;
   }
}
