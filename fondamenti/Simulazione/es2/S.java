//Giulio Coduti Matricola 2008795

/**
 * FONDAMENTI DI INFORMATICA - CANALE 4 PROVA PRATICA DI PROGRAMMAZIONE DEL
 * 8.9.2020
 *
 * Realizza un insieme di elementi di tipo generico comparabili
 *
 * @param T il tipo generico degli elementi dell'insieme
 *
 * @author A. Luchetta
 * @version 03-Sett-2020
 */
public class S<T extends Comparable<T>> // ADT insieme di elementi comparabili di tipo generico
{
   // parte privata

   static final int SIZE = 5;

   // ***DA CODIFICARE ***
   private class Node {
      public T value;
      public Node next;

      public Node(T v, Node n) {
         value = v;
         next = n;
      }
   }

   Object[] buckets;
   int size;

   /**
    * inizializza un insieme vuoto
    */
   public S() {
      // ***DA CODIFICARE ***
      buckets = new Object[SIZE];
      for (int i = 0; i < SIZE; i++) {
         buckets[i] = new Node(null, null);
      }
   }

   private int hash(T toHash) {
      int ret = toHash.hashCode() % SIZE;
      if (ret < 0)
         return -ret;

      return ret;
   }

   /**
    * inserisce nell'insieme l'elemento specificato se diverso da null e non gia'
    * presente
    * 
    * @param x l'elemento specificato
    */
   public void add(T x) {
      // ***DA CODIFICARE ***
      if (x == null) {
         return;
      }

      else {
         Node it = (Node) buckets[hash(x)];
         while (it.next != null) {
            it = it.next;
         }
         Node nuovo = new Node(x, null);
         it.next = nuovo;
      }

      size++;

   }

   /**
    * Verifica se l'elemento specificato e' contenuto nell'insieme
    * 
    * @param x elemento specificato
    * @return true se questo insieme contiene l'elemento specificato, false
    *         altrimenti
    */
   public boolean contains(T x) {
      // ***DA CODIFICARE ***
      Node it = ((Node) buckets[hash(x)]).next;

      while (it != null) {
         if (it.value.equals(x)) {
            return true;
         }
         it = it.next;
      }

      return false;
   }

   ////////////////////////////////////////////////////////////////////////////////////////

   /**
    * Verifica se l'insieme e' vuoto
    * 
    * @return true se questo insieme e' vuoto, false altrimenti
    */
   public boolean isEmpty() {
      return false;
   }

   /**
    * Fornisce il numero di elementi contenuti dell'insieme
    * 
    * @return il numero di elementi contenuti in questo insieme
    */
   public int size() {
      return 0;
   }

   /**
    * @return array ordinato contenente gli elementi dell'insieme
    */
   public Comparable[] toArraySet() {
      return null;
   }
}
