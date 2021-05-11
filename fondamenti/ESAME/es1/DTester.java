import java.util.Scanner;
/**
   FONDAMENTI DI INFORMATICA - CANALE 4
   PROVA DI PROGRAMMAZIONE DEL 26-01-2021
   
   Classe di prova della classe D
   
   @typeparam E il tipo generico degli elementi di questa doppia coda
   
   @author A. Luchetta
   @see D
   
   @version 17-01-2021

*/
public class DTester
{
   public static void main(String[] args)
   {
      // Array di prova   
      final String[] NUMBERS = {"uno", "due", "tre", "quattro", "cinque", "sei", "sette"};
      
      //Generazione della doppia coda
      Deque<String> d1 = new D<String>();
           
      // inserimento stringhe nelle doppie code
      for (int i = 0; i < NUMBERS.length; i++)
         d1.addFirst(NUMBERS[i]);        // inserimento al primo posto
                 
      // Prova del metodo size
      System.out.println("SIZE = " + d1.size());
      
      // Prova dei metodi get e remove
      System.out.println("\nGET/REMOVE LAST");
      while (!d1.isEmpty())
         System.out.println("GET/REMOVE " + d1.getFirst() + " / " + d1.removeLast()); 
                  
      // Prova del metodo SIZE
      System.out.println("\nSIZE = " + d1.size());         
   }
}
