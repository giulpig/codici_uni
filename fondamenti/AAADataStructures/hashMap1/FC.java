/**
   Classe FC - classe eseguibile di prova
   @author A. Luchetta
   @version 20-Genn-2009
   @see Map
   @see MyMap
*/

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FC
{
   public static void main(String[] args) throws IOException
   {
      // apertura del flusso d'ingresso
      Scanner in = new Scanner(new FileReader(args[0]));
      in.useDelimiter("[^a-zA-Z]+");

      // geenrazione di una mappa
      MyMap m = new MyMap();
      
      // ciclo di acquisizione delle parole e inserimento nella mappa
      while (in.hasNext())
      {
         // acqusizione di una nuova parola
         String s = in.next();

         // verifica di presenza nella mappa
         Object x = m.get(s);
 
         // aggiornamento della frequenza della parola
         if (x == null)     // se la parola non e' presente viene inserita
            m.put(s, 1);    // auto-boxing
         else
         {                  // se la parola e' presente si aggiorna la frequenza
            Integer i = (Integer)x;    // forzamento a Integer
            int n = i.intValue();      // numero intero estratto da Integer 
            m.put(s, n + 1);       // auto-boxing
         } 
      }
      
      // chiusura del flusso d'ingresso 
      in.close();

      // accesso alle chiavi della mappa
      Object[] keys = m.keys();
      
      // invio a standard output
      for (int i = 0; i< keys.length; i++)
         System.out.println(keys[i] + " : " + m.get(keys[i]));
   }
}
