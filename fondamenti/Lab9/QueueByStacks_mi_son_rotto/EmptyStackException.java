/**
  * EmptyStackException
  * @author A. Luchetta
  * @version 20-Nov-2006
  */
public class EmptyStackException extends RuntimeException
{
   public EmptyStackException()
   {
   }

   public EmptyStackException(String err)
   {
      super(err);
   }
}
