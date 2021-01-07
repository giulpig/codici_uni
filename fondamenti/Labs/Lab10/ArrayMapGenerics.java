public class ArrayMapGenerics <K, V> implements MapGenerics <K, V> {

    private static final int SIZE = 97;
    private Object[] liste;
    private int size;
    
    public ArrayMapGenerics(){
        makeEmpty();
    }

    private int hash(K key){
        return Math.abs(key.hashCode()%SIZE);
    }


    public V put(K key, V value){

        if(key == null || value == null){
            return null;
        }

        int pos = hash(key);

        Node iter = (Node)liste[pos];

        if(iter == null){  //se il bucket e' vuoto
            liste[pos] = new Node(null, new Entry(key, value));
            size++;
            return null;
        }

        while(iter.getNext() != null){
            if(iter.getEntry().getKey().equals(key)){  //se trovo una chiave uguale la sostituisco
                V ret = iter.getEntry().getValue();
                iter = new Node(iter.getNext(), new Entry(key, value));
                return ret;
            }

            iter = iter.getNext();
        }

        iter.setNext(new Node(null, new Entry(key, value)));  //aggiungo in coda la Entry
        size++;

        return null;

    }

    public V get(K key){

        if(key==null)
            return null;

        int pos = hash(key);

        Node iter = (Node)liste[pos];

        if(iter == null){  //se il bucket è vuoto
            return null;
        }

        while(iter.getNext() != null){  //cerco la chiave uguale
            if(iter.getEntry().getKey().equals(key)){
                return iter.getEntry().getValue();
            }

            iter = iter.getNext();
        }

        return null;
        

    }

    public V remove(K key){
        if(key == null){
            return null;
        }

        int pos = hash(key);

        Node iter = (Node)liste[pos];
        Node last = null;

        if(iter == null){  //se il bucket e' vuoto
            return null;
        }

        if(iter.getNext() == null){   //se c'è un solo el. nel bucket
            V ret = iter.getEntry().getValue();
            liste[pos] = null;
            size--;
            return ret;
        }

        while(iter.getNext() != null){
            if(iter.getEntry().getKey().equals(key)){
                V ret = iter.getEntry().getValue();
                if(last == null)  //<-se è il primo elemento
                    liste[pos] = iter.getNext();
                else
                    last.setNext(iter.getNext());

                iter = null;
                size--;
                return ret;
            }
            last = iter;
            iter = iter.getNext();
        }

        return null;
    }

    public Object[] keys(){

        Object[] ret = new Object[size];

        int index = 0;
        
        for(int i=0; i<SIZE; i++){

            Node iter = (Node)liste[i];

            while(iter != null){
                ret[index++] = iter.getEntry().getKey();
                iter = iter.getNext();
            }
        }

        return ret;
    }


    public void makeEmpty(){
        liste = new Object[SIZE];
        size = 0;
    }

    public boolean isEmpty(){  return size <= 0;}

    public int size(){  return size;}




    private class Entry{

        private K key;
        private V value;

        public Entry(K k, V v){
            key = k; 
            value = v;
        }

        public K getKey(){  return key;}

        public V getValue(){  return value;}

    }

    private class Node {

        private Node next;
        private Entry pair;

        public Node(){
            next = null;
            pair = null;
        }

        public Node(Node nodo, Entry entry){
            next = nodo;
            pair = entry;
        }

        public Node getNext(){  return next;}

        public void setNext(Node nodo){  next = nodo;}

        public Entry getEntry(){  return pair;}

        public void setEntry(Entry entry){  pair = entry;}


    }




}
