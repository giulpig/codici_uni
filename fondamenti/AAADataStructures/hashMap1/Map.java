public interface Map  //--ADT map
{
   /**
      verifica se la mappa e' vuota
      @return true se la mappa e' vuota, false altrimenti
   */
   boolean isEmpty();

   /**
      restituisce il numero di elementi contenuti nella mappa
      @return numero di elementi contenuti nella mappa
   */
   int size();

   /**
      restituisce il valore associato alla chiave specificata
      @param key la chiave associata al valore da restituire
      @return il valore associato alla chiave specificata, se presente,
              null se l'associazione non e' presente
      @throws IllegalArgumentException se key vale null
   */
   Object get(Object key);

   /**
      inserisce l'associazione key/value nella mappa. Se la chiave e' gia'
      presente, sostituisce l'associazione e restituisce il valore
      precedentemente associato alla chiave
      @param key la chiave
      @param value il valore da associare alla chiave
      @return il valore precedentemente associato alla chiave specificata, se
              presente, null se la chiave non e' gia' presente
      @throws IllegalArgumentException se key o value valgono null
   */
   Object put(Object key, Object value);

   /**
      elimina l'associazione con la chiave specificata
      @param key la chiave dell'associazione da eliminare
      @return il valore associato alla chiave specificata, se
              presente, null se la chiave non e' presente
      @throws IllegalArgumentException se key vale null
   */
   Object remove(Object key);

   /**
      restituisce un array contenente le chiavi della mappa.
      @return array  contenente tutte le chiavi della mappa
   */
   Object[] keys();
}
