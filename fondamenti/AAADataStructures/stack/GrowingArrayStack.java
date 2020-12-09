/**
   realizza l'ADT Stack con un array a ridimensionamento dinamico
   @author A.Luchetta
   @version 2-Dec-2012
   @see Stack
   @see FixedArrayStack
*/
public class GrowingArrayStack extends FixedArrayStack
{
   public void push(Object obj)
   {
      if (vSize == v.length)
         v = resize(v, 2*vSize);
      super.push(obj);
   }

   private Object[] resize(Object[] a, int length)
   {
      int minLength = length;

      if (a.length < minLength)
         minLength = a.length;
         
      Object[] newArray = new Object[length];
      System.arraycopy(a, 0, newArray, 0, minLength);
      
      return newArray;
   }
}
