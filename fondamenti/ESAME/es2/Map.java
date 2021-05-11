/**
  * FONDAMENTI DI INFORMATICA - CANALE 4
  * PROVA PRATICA DI PROGRAMMAZIONE DEL 26.1.2021
  *
  * Definisce un contenitore di associazioni chiave/valore con chiave univoca
  * La chiave e il valore sono di tipo parametrico
  *
  * @typeparam K il tipo parametrico della chiave
  * @typeparam V il tipo parametrico del valore
  *
  * @author A. Luchetta
  * @version 22-01-2021
  */
public interface Map<K extends Comparable, V>
{
   /**
      Fornisce il valore associato alla chiave specificata
      @param key la chiave specificata
      @return valore associato alla chiave specificata o null se la chiave
              specificata non e' presente in questa mappa
   */ 
   V get(K key); 
   
   /**
      Verifica se questa mappa e' vuota
      @return true se questa mappa e' vuota, false altrimenti
   */
   boolean isEmpty();
   
   /**
      Accede alle chiavi di questa mappa
      @return se questa mappa non e' vuota restituisce un array ordinato
              contenente le chiavi delle associazioni presenti o altrimenti un
              array di dimensione nulla
   */
   Comparable[] keySet();     

   /**
      Se questa mappa non contiene un'associazione di chiave specificata,
      aggiunge l'associazione di chiave e valore specificati e restituisce null,
      altrimenti sostituisce l'associazione presente di chiave specificata con
      la nuova associazione di valore specificato e restituisce il vecchio
      valore che e' stato sostituito
      @param key la chiave specificata
      @param value il valore specificato
      @return null se questa mappa non contiene un'associazione di chiave
             specificata o altrimenti il vecchio valore associato alla chiave
      @throws IllegalArgumentException se key o value valgono null
   */
   V put(K key, V value);
   
   /**
      Elimina da questa mappa l'associazione di chiave specificata e ne
      restituisce il valore   
      @param key la chiave specificata
      @return null se in questa mappa non e' presente un'associazione di chiave
              specificata o, altrimenti, il valore associato alla chiave
              specificata
   */
   V remove(K k);
   
   /**
      Fornisce il numero di associazioni presenti in questa mappa
      @return il numero di associazioni contenute in questa mappa
   */
   int size();         
}
