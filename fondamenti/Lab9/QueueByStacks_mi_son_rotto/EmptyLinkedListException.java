/**
  * EmptyLinkedListException
  * @author A. Luchetta
  * @version 20-Nov-2006
  */
public class EmptyLinkedListException extends RuntimeException
{
   /**
     costruttore di default
   */
   public EmptyLinkedListException()
   {
   }

   /**
     @param err stringa visualizzata al lancio dell'eccezione
   */
   public EmptyLinkedListException(String err)
   {
      super(err);
   }
}