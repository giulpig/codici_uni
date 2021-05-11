/**
  * FONDAMENTI DI INFORMATICA - CANALE 4
  * PROVA PRATICA DI PROGRAMMAZIONE DEL 1.2.2017
  *
  * Classe di prova della classe M
  *
  * @see Map
  * @see M
  *
  * @author A. Luchetta
  * @version 22-01-2021
  */
public class MMain
{
   public static void main(String[] args)
   {
      // array di prova
      final String[] K   = {"Batman"  ,"MickyMouse","DuffyDuck", "Buzz", "Pooh"  ,"Batman"}; 
      final String[] V   = {"Superman","Goofy",     "DonaldDuck","Woody","Piglet","Robin" };
        
      // generazione di una mappa
      Map<String,String> m = new M<String, String>();

      // inserimento delle associazioni nella mappa
      for (int i = 0; i < K.length && i < V.length; i++)
         m.put(K[i], V[i]);          
      
      // prova del metodo size
      System.out.println("SIZE = " + m.size());
      
      // prova del metodo keySet e get
      System.out.print("ENTRIES: ");
      Object[] keys = m.keySet();
      for (Object e: keys)
         System.out.print(e + "/" + m.get((String)e) + " ");
      System.out.println();   
   }  
}
