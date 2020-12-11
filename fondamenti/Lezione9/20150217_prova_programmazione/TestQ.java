/**
  *              FONDAMENTI DI INFORMATICA - CANALE 4 - A.A. 2014-15
  *                      Prova di Programmazione - 17-02-2015
  *
  * Classe TestQ
  * Classe di prova della classe Q
  *
  * @author A. Luchetta
  * @version 15-Feb-2015
  *
  */
public class TestQ     // Classe di prova della classe Q
{
   public static void main(String[] args) throws java.io.IOException
   {  Q q = new Q();                     // definizione di una coda 
 
      //java.util.Scanner s = new java.util.Scanner(new java.io.FileReader(args[0]));  //apertura file
      java.util.Scanner s = new java.util.Scanner(new java.io.FileReader("/home/giulio/Desktop/uni/codici_uni/fondamenti/Lezione9/20150217_prova_programmazione/fruit.txt"));


      while (s.hasNextLine())            // lettura del file e inserimento degli elementi nella coda
      {  
         java.util.Scanner tok = new java.util.Scanner(s.nextLine());
         while (tok.hasNext())
            q.enqueue(tok.next());       // prova del metodo enqueue 
      }                                            

      s.close();
      
      System.out.println("***size = " + q.size());                          // prova del metodo size
                                
      System.out.print("***toArray = ");                                 // prova del metodo toArray
      Object[] v = q.toArray();                         
      for (int i = 0; i < v.length;i++) 
         System.out.print(v[i] + " ");                
                           
      System.out.print("\n***front + dequeue = ");               // prova dei metodi front e dequeue
      while (!q.isEmpty())
         System.out.print(q.front() + "/" + q.dequeue() + " ");
      
      System.out.println("\n***size = " + q.size());                        // prova del metodo size
   }   
}  
