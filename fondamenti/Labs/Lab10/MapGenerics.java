/**
  * interfaccia Map - definisce l'ADT Mappa
  * @author Goodrich - Tamassia
  *
  * @see Container
  * @param <K> il tipo parametrico degli esemplari usati come chiave
  * @param <V> il tipo parametrico degli esemplari usati come valore
*/

public interface MapGenerics<K, V> extends Container
{
   /**
      restituisce il valore associato alla chiave specificata, o null se
      questa mappa non contiene alcuna associazione per la chiave
      @param key la chiave associata al valore da restituire
      @return il valore associato alla chiave specificata, o null se questa mappa
              non contiene alcuna associazione per la chiave
   */
   V get(K key);
   
   /**
      associa il valore specificato alla chiave specificata. Se la mappa contiene
      già una associazione per questa chiave, il vecchio valore e' sostituito dal nuovo.
      @param key la chiave al quale deve essere associato il valore specificato
      @param value ll valore da associare alla chiave specificata
      @return il valore precedentemente associato alla chiave, o null se alla chiave
              non era associato alcun valore
      @throws IllegalArgumentException se la chiave o il valore sono null        
   */
   V put(K key, V value);
   
   
   /**
      rimuove l'associazione di una chiave dalla mappa se questa e' presente.
      Restituisce il valore a cui era associata la chiave, o null se la mappa non conteneva associazioni
      per questa chiave.
      Attenzione: se la mappa consente di associare valori pari a null, allora la restituzione di null non
      indica necessariamente che la mappa non conteneva alcuna associazione per questa chiave. E' anche possibile
      che la mappa associasse esplicitamente la chiave al valore null.
      La mappa non contiene alcuna associazione per questa chiave, una volta che il metodo e' terminato.
      @param key la chiave il cui valore deve essere rimosso dalla mappa
      @return il valore precedentemente associato alla chiave, o null se non erano presenti associazioni
              per questa chiave.
   */
   V remove(K key);
   
   /**
      restituisce un array contenente le chiavi di tutte le associazioni della mappa
      @return un array contenente le chiavi di tutte le associazioni della mappa
   */
   Object[] keys();
}

