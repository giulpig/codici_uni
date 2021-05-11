/**
  * FONDAMENTI DI INFORMATICA - CANALE 4
  * PROVA DI PROGRAMMAZIONE DEL 26-01-2021
  *
  * Eccezione della Doppia Coda Vuota
  *
  * @author A. Luchetta
  * @version 17-01-2021
  */
public class EmptyDequeException extends RuntimeException
{
   /**
      Inizializza questa eccezione
   */
   public EmptyDequeException() { super(); }
  
   /**
      Inizializza questa eccezione
      @param why desrizione della causa di questa eccezione
   */
   public EmptyDequeException(String why) { super(why); }
}
