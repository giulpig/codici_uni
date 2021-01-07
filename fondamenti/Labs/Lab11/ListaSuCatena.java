import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;

public class ListaSuCatena implements List{
    private Node head;
    private Node tail;
    private int size;

    private class Node{
        private Object val;
        private Node next;

        public Node(Object v, Node n){
            val = v;
            next = n;
        }

    }

    public class ListIterator implements Iterator{
        private Node current;
        private Node prev;
        
        public ListIterator(Node h) {
            prev = null;
            current = h;
        }

        /**
         * ispeziona l'elemento DOPO la posizione corrente dell'iteratore, avanzando
         * successivamente l'iteratore di una posizione nella lista
         * 
         * @return l'elemento ispezionato, se presente throws
         *         java.util.NoSuchElementException se non ci sono elementi dopo la
         *         posizione corrente dell'iteratore
         */
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prev = current;
            current = current.next;
            return current.val;
        }

        /**
             verifica se e' presente almeno un elemento dopo la posizione corrente
            dell'iteratore
            @return true se e' presente almeno un elemento, false altrimenti
        */
        public boolean hasNext(){
            return current.next != null;
        }

        /**
             inserisce un nuovo elemento dopo la posizione corrente dell'iteratore,
            l'iteratore si posiziona dopo il nuovo elemento
            @param x elemento da inserire
        */
        public void add(Object x){
            Node n = new Node(x, current.next);
            current.next = n;
            current = n;
            prev = null;

            if(current.next == null){
                tail = current;
            }
        }

        /**
             elimina l'ultimo nodo esaminato dal metodo next()
            puo' essere invocato solo dopo l'invocazione del metodo next()
            @throws java.lang.IllegalStateException se precedentemente non e' stato
            invocato il metodo next()
        */
        public void remove(){
            if(prev == null){
                throw new IllegalStateException();
            }
            prev.next = current.next;
            current = prev;
            if(current.next == null){
                tail = current;
            }
            prev = null;
        }
    }

    
    public Iterator iterator() {
        return new ListIterator(head.next);
    }




    public ListaSuCatena(){
        makeEmpty();
    }

    /**
     Restituisce l'elemento alla posizione specificata nella lista
     @index indice dell'elemento da restituire
   */
    public Object get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException();
        }
        int i = 0;
        Node it = head.next;
        while(i<index){
            i++;
            it = it.next;
        }
        return it.val;
    }

   /**
     Sostituisce l'elemento alla posizione specificata nella lista con l'elemento x
     @param index posizione dell'elemento da sostituire
     @param x elemento da memorizzare alla posizione
      specificata
     @return l'elemento precedentemente memorizzato
      Alla posizione specificata
   */
    public Object set(int index, Object x){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException();
        }
        int i = 0;
        Node it = head.next;
        while(i<index){
            i++;
            it = it.next;
        }
        Object ret = it.val;
        it.val = x;
        return ret;
    }

  /**
      Inserisce l'elemento specificato alla posizione specificata nella lista
      Sposta a destra tutti gli elementi presenti
      di un posto
      @index rango dell'elemento da inserire
      @x elemento da inserire
   */
    public void add(int index, Object x){
        if(index < 0 || index > size){
            throw new IllegalArgumentException();
        }
        int i = 0;
        Node it = head.next;
        while(i<index){
            i++;
            it = it.next;
        }
        Node nuovo = new Node(x, it.next);
        it.next = nuovo;
        size++;
    }

  /**
      Estrae l'elemento alla posizione specificata nella lista. Sposta a sinistra di
      un posto tutti gli elementi che seguono
      L'elemento estratto
      @index rango dell'elemento da sostituire

   */
    public Object remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException();
        }
        int i = 0;
        Node it = head.next;
        while(i<index){
            i++;
            it = it.next;
        }
        Object ret = it.next.val;
        it.next = it.next.next;
        size--;
        return ret;     
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int size() {
        return size;
    }

    public void makeEmpty(){
        tail = new Node(null, null);
        head = new Node(null, tail);
        head.next = tail;
        size = 0;
    }

 }
