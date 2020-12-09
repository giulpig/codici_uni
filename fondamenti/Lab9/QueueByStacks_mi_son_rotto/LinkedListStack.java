/**
   realizza l'ADT Stack con una lista concatenata
   @author A. Luchetta
   @version 2-Nov-2012
   @see Stack
*/
public class LinkedListStack implements Stack
{
   private LinkedList list;
   private int size;     // per contare gli elementi

   public LinkedListStack()      // O(1)
   {  makeEmpty();
   }

   public void makeEmpty()       //O(1)
   {  list = new LinkedList();
      size = 0;
   }

   public boolean isEmpty()     //O(1)
   {  return list.isEmpty();
   }

   public int size()             // O(1)
   {  return size;
   }

   public void push(Object obj)  // O(1)
   {  list.addFirst(obj);
      size++;
   }

   public Object top() throws EmptyStackException
   { if (isEmpty())             // O(1)
        throw new EmptyStackException();
     return   list.getFirst();
   }

   public Object pop() throws EmptyStackException //O(1)
   { Object obj = top();  // lascia a top() il lancio
     list.removeFirst();  // dell'eccezione
     size--;
     return obj;
   }
} 
