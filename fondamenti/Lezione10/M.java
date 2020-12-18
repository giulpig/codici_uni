/**
  * FONDAMENTI DI INFORMATICA - CANALE 5
  * PROVA PRATICA DI PROGRAMMAZIONE DEL 22.2.2017
  *
  * interfaccia Map - ADT mappa - contenitore di associazioni chiave/valore con
  * chiave univoca
  * la chiave e il valore sono di tipo parametrico
  *
  * typeparam K il tipo parametrico della chiave
  * typeparam V il tipo parametrico del valore
  *
  * @author A. Luchetta
  * @version 16-Dic-2020
  */
public class M<K extends Comparable, V>
{

   private static final int MAX_SIZE = 97;
   private Object[] v = new Object[MAX_SIZE];
   private int size;

   private class Entry{
      private K key;
      private V value;
      private Entry next;

      public Entry(K k, V v, Entry n){
         key = k;
         value = v;
         next = n;
      }

      public K getKey(){  return key;}

      public V getValue(){  return value;}

      public Entry getNext(){  return next;}

      public void setNext(Entry n){  next = n;}

   }

   public M(){
      for(int i=0; i<MAX_SIZE; i++){
         v[i] = new Entry(null, null, null);
      }
      size = 0;
   }


   private int hash(K toHash){
      return Math.abs(toHash.hashCode()%MAX_SIZE);
   }

   private Entry search(K key){
      int pos = hash(key);

      Entry iter = ((Entry)(v[pos])).getNext();

      while(iter != null){

         if(iter.getKey().equals(key)){
            return iter;
         }

         iter = iter.getNext();
      }

      return null;
   }

   private static void sort(Comparable[] v){
      for(int i=1; i<v.length; i++){
         for(int j=i; j>=1; j--){
            if(v[j].compareTo(v[j-1]) <= 0){
               break;
            }

            Comparable t = v[j];
            v[j] = v[j-1];
            v[j-1] = t;
            
         }
      }
   }


   /**
      restituisce il valore associato alla chiave specificata
      @param key la chiave specificata
      @return valore associato alla chiave specificata key o null se la chiave
              specificata non e' presente nella mappa
   */ 
   public V get(K key){
      return search(key).getValue();
   }
   
   /**
      verifica se questa mappa e' vuota
      @return true se questa mappa e' vuota, false altrimenti
   */
   public boolean isEmpty(){
      return size<=0;
   }

   
   
   /**
      accede alle chiavi di questa mappa
      @return se questa mappa non e' vuota restituisce un array ordinato
              contenente le chiavi delle associazioni presenti o altrimenti un
              array di dimensione nulla.
   */
   public Comparable[] keySet(){
      Comparable<K>[] ret = new Comparable[size];

      int index = 0;

      for(int i=0; i<MAX_SIZE; i++){
         Entry iter = ((Entry)(v[i])).getNext();
         while(iter != null){
            ret[index++] = iter.getKey();
            iter = iter.getNext();
         }
      }

      sort(ret);
      return ret;
   }   

   /**
      se questa mappa non contiene un'associazione di chiave specificata,
      aggiunge l'associazione di chiave e valore specificati e restituisce null,
      altrimenti sostituisce l'associazione presente di chiave specificata con
      la nuova associazione di valore specificayo e restituisce il vecchio
      valore che e' stato sostituito
      @param key la chiave specificata
      @param value il valore specificato
      @return null se questa mappa non contiene un'associazione di chiave
             specificata o altrimenti il vecchio valore associato alla chiave
      @throws IllegalArgumentException se key o value valgono null
   */
   public V put(K key, V value){

      if(key == null || value == null){
         return null;
      }

      V ret = remove(key);

      int pos = hash(key);

      ((Entry)(v[pos])).setNext(new Entry(key, value, ((Entry)(v[pos])).getNext()));
      size++;

      return ret;
   }
   
   /**
      elimina da questa mappa l'associazione di chiave specificata e ne
      restituisce il valore   
      @param key la chiave specificata
      @return null se in questa mappa non e' presente un'associazione di chiave
              specificata o, altrimenti, il valore associato alla chiave
              specificata
   */
   public V remove(K k){
      int pos = hash(k);

      Entry iter = ((Entry)(v[pos])).getNext();
      Entry last = (Entry)v[pos];

      while(iter != null){

         if(iter.getKey().equals(k)){
            last.setNext(iter.getNext());
            size--;
            return iter.getValue();
         }

         last = iter;
         iter = iter.getNext();
      }

      return null;

   }
   
   /**
      fornisce il numero di associazioni presenti in questa mappa
      @return il numero di associazioni contenute in questa mappa
   */
   public int size(){
      return size;
   }
   
   /**
      accede ai valori di questa mappa
      @return array contenente i valori della associazioni presenti in questa
              mappa o un array di dimensione nulla se la mappa e' vuota
   */ 
   public Object[] values(){
      Object[] ret = new Object[size];

      int index = 0;

      for(int i=0; i<MAX_SIZE; i++){
         Entry iter = ((Entry)(v[i])).getNext();
         while(iter != null){
            ret[index++] = iter.getValue();
            iter = iter.getNext();
         }
      }

      return ret;
   }        
}
