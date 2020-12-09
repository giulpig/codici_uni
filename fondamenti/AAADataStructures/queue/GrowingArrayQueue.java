/**
  * GrowingArrayQueue
  * realizza il tipo di dati astratto Coda con un array a ridimensionamento
  * dinamico e due indice
  * @author M. Dalpasso modificata da A. Luchetta
  * @version 20-Nov-2006
  * @see FixedArrayQueue
  */
public class GrowingArrayQueue extends FixedArrayQueue
{
  /**
     inserisce un elemento all'ultimo posto della coda
     O(1)
     @param obj nuovo elemento da inserire
  */
   public void enqueue(Object obj)
   {
      if (back >= v.length)
         v = resize(v, 2 * v.length);

      super.enqueue(obj);
   }

   // ridimensiona l'array
   private static Object[] resize(Object[] a, int length)
   {
      int minLength = length;

      if (a.length < minLength)
         minLength = a.length;

      Object[] newArray = new Object[length];
      System.arraycopy(a, 0, newArray, 0, minLength);
      
      return newArray;
   }
}
