/**
  * FONDAMENTI DI INFORMATICA - CANALE 4
  * PROVA PRATICA DI PROGRAMMAZIONE DEL 26.01.2021
  *
  * Classe di prova della classe ES
  *
  * @see E
  *
  * @author A. Luchetta
  * @version 17-01-2021
  */
public class ESTester
{
   public static void main(String[] args)
   {
      // Array di prova   
      final String[] NUMBERS = {"uno", "due", "tre", "quattro", "cinque", "sei", "sette"};
      
      // generazione di un insieme esteso
      ES<String> s = new ES<String>();
      
      // inserimento degli elementi nell'insieme
      for (int i = 0; i < NUMBERS.length; i++)
         s.add(NUMBERS[i]);
      
      // Prova del metodo size
      System.out.println("SIZE = " + s.size());
      
      // prova del metodo contains
      if (s.contains(s.max()))
         System.out.println("OK CONTAINS");
      else
         System.out.println("KO CONTAINS"); 
         
      // Prova del metodo size
      System.out.println("SIZE = " + s.size());              
         
   }
}
