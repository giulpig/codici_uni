/**
  * FullQueueException
  * @author A. Luchetta
  * @version 20-Nov-2006
  */
public class FullQueueException extends RuntimeException
{
   public FullQueueException()
   {
   }

   public FullQueueException(String err)
   {
      super(err);
   }
}
