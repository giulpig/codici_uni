/**
  * EmptyQueueException
  * @author A. Luchetta
  * @version 20-Nov-2006
  */
public class EmptyQueueException extends RuntimeException
{
   public EmptyQueueException()
   {
   }

   public EmptyQueueException(String err)
   {
      super(err);
   }
}
