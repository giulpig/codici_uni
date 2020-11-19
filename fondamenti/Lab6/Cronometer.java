/**
   Cronometer.java
   
   realizza un cronometro
   La sequenza di uso del cronometro deve essere la seguente: start, stop,
   lettura del tempo trascorso, reset.
   A questo punto puo' essere effettuata una nuova misura.
   
   @author A. Luchetta
   @version 25-Nov-2016
 */

import java.util.NoSuchElementException;

public class Cronometer
{
   private static final long NONE = -1L;
   private static final int OFF = 0;     // cronometro fermo
   private static final int RUNNING = 1; // cronometro in funzione
   private static final int DONE = 2;    // cronometro con misura effettuata

   private int status;
   private long startTime;
   private long elapsedTime;

   public Cronometer()
   {
      reset();
   }

   /**
      avvia il cronometro.      
      se il cronometro e' gia' avviato, fa ripartire il conteggio.
   */
   public void start()
   {
      reset();
      status = RUNNING;
      startTime = System.currentTimeMillis();
   }
   
   /**
      ferma il cronometro.
      se il cronometro non e' avviato, termina senza fare nulla
   */
   public void stop()
   {
      if (!isRunning())
         return;

      status = DONE;
      elapsedTime = System.currentTimeMillis() - startTime;
      startTime = NONE;
   }
   
   /**
      resetta il cronometro.
   */
   public void reset()
   {
      status = OFF;
      startTime = NONE;
      elapsedTime = NONE;
   }
   
   /**
      restituisce il conteggio del tempo o zero se non e' stata effettuata
      la misura
      
   */
   public long getElapsedTime()
   {
      if (!hasDone())
         return 0;

      return elapsedTime;
   }

   /**
      verifica se il cronometro e' fermo
      @return true se il cronometro e' fermo, false altrimenti
   */
   public boolean isOff()
   {
      return status == OFF;
   }
   
   /**
      verifica se il cronometro e' in funzione
      @return true se il cronometro e' in funzione, false altrimenti
   */
   public boolean isRunning()
   {
      return status == RUNNING;
   }
   
   /**
      verifica se il cronometro ha completato la sequenza di misura.
      @return true se il cronometro ha completato la misura, false altrimenti
   */
   public boolean hasDone()
   {
      return status == DONE;
   }

}
