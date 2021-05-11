/**
 * FONDAMENTI DI INFORMATICA - CANALE 4 PROVA PRATICA DI PROGRAMMAZIONE DEL
 * 26.1.2021
 *
 * Realizza una mappa ovvero un contenitore di associazioni chiave/valore con
 * chiave univoca La chiave e il valore sono di tipo parametrico
 *
 * @typeparam K il tipo parametrico della chiave
 * @typeparam V il tipo parametrico del valore
 *
 * @see Map
 *
 * @author A. Luchetta
 * @version 22-01-2021
 */
public class M<K extends Comparable, V> implements Map<K, V> {
   // parte privata

   /* DA CODIFICARE */
   public static final int CAPACITY = 101;
   private Object[] buckets;
   private int size;

   private class Entry {
      private Object key;
      private Object value;
      private Entry next;

      public Entry(Object k, Object w, Entry n) {
         setKey(k);
         setValue(w);
         setNext(n);
      }

      public Entry() {
         this(null, null, null);
      }

      public Object getKey() {
         return key;
      }

      public Object getValue() {
         return value;
      }

      public Entry getNext() {
         return next;
      }

      public void setKey(Object k) {
         key = k;
      }

      public void setValue(Object w) {
         value = w;
      }

      public void setNext(Entry n) {
         next = n;
      }
   }

   /**
    * inizializza una mappa vuota
    */
   public M() {
      /* DA CODIFICARE */
      buckets = new Object[CAPACITY];
      size = 0;
      for (int i = 0; i < CAPACITY; i++) {
         buckets[i] = new Entry();
      }
   }

   private int hash(K toHash) {
      int ret = toHash.hashCode() % CAPACITY;
      if (ret < 0)
         return -ret;

      return ret;
   }

   /**
    * Fornisce il valore associato alla chiave specificata
    * 
    * @param key la chiave specificata
    * @return valore associato alla chiave specificata key o null se la chiave
    *         specificata non e' presente in questa mappa
    */
   public V get(K k) {
      /* DA CODIFICARE */
      if (k == null) {
         return null;
      }

      int index = hash(k);

      Entry it = ((Entry) (buckets[index]));

      while (it != null && it.getNext() != null) {
         if (it.getKey() != null && it.getKey().equals(k)) {
            return (V) it.getValue();
         }
      }

      if (it != null && it.getKey() != null && it.getKey().equals(k)) {
         return (V) it.getValue();
      }

      return null;
   }

   /**
    * Verifica se questa mappa e' vuota
    * 
    * @return true se questa mappa e' vuota, false altrimenti
    */
   public boolean isEmpty() {
      return size() <= 0;
   }

   /**
    * Accede alle chiavi di questa mappa
    * 
    * @return se questa mappa non e' vuota restituisce un array ordinato contenente
    *         le chiavi delle associazioni presenti o altrimenti un array di
    *         dimensione nulla.
    */
   public Comparable[] keySet() {
      /* DA CODIFICARE */

      Comparable[] res = new Comparable[CAPACITY];

      int index = 0;

      for (int i = 0; i < CAPACITY; i++) {
         Entry it = ((Entry) (buckets[index]));
         while (it != null && it.getNext() != null) {
            res[index++] = (K) it.getKey();
         }
         if (it != null) {
            res[index++] = (K) it.getKey();
         }
      }

      return res;
   }

   /**
    * Se questa mappa non contiene un'associazione di chiave specificata, aggiunge
    * l'associazione di chiave e valore specificati e restituisce null, altrimenti
    * sostituisce l'associazione presente di chiave specificata con la nuova
    * associazione di valore specificayo e restituisce il vecchio valore che e'
    * stato sostituito
    * 
    * @param key   la chiave specificata
    * @param value il valore specificato
    * @return null se questa mappa non contiene un'associazione di chiave
    *         specificata o altrimenti il vecchio valore associato alla chiave
    * @throws IllegalArgumentException se key o value valgono null
    */
   public V put(K k, V v) {
      /* DA CODIFICARE */

      if (k == null || v == null) {
         throw new IllegalArgumentException();
      }

      int index = hash(k);

      Entry it = ((Entry) (buckets[index]));

      while (it != null && it.getNext() != null) {
         if (it.getKey() != null && it.getKey().equals(k)) {
            V temp = (V) it.getValue();
            it.setValue(v);
            return temp;
         }
      }

      if (it != null && it.getKey() != null && it.getKey().equals(k)) {
         V temp = (V) it.getValue();
         it.setValue(v);
         return temp;
      }

      Entry temp = new Entry(k, v, null);
      it.setNext(temp);
      size++;

      return null;
   }

   /**
    * Elimina da questa mappa l'associazione di chiave specificata e ne restituisce
    * il valore
    * 
    * @param key la chiave specificata
    * @return null se in questa mappa non e' presente un'associazione di chiave
    *         specificata o, altrimenti, il valore associato alla chiave
    *         specificata
    */
   public V remove(K k) {

      return null;
   }

   /**
    * Fornisce il numero di associazioni contenute in questa mappa
    * 
    * @return il numero di associazioni contenute in questa mappa
    */
   public int size() {
      /* DA CODIFICARE */

      return size;
   }
}
