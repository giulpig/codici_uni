/**
  * classe ArrayMapGenericsTester - classe di prova
  * @author Luchetta
  * @see MapGenerics<K, V>
  * @see ArrayMapGenerics<K,V>
  * @param <K> tipo parametrico della chiave
  * @param <V> tipo parametrico del valore
  */  
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class ArrayMapGenericsTester
{
   public static void main(String[] args) throws IOException
   {
      if (args.length < 1)
      {
         System.out.println("uso $java ArrayMapGenericsTester filename");
         return;
      }

      Scanner in = new Scanner(new FileReader(args[0]));

      //Scanner in = new Scanner(new FileReader("/home/giulio/Desktop/uni/codici_uni/fondamenti/Lab10/animali.txt"));
      ArrayMapGenerics<String, String> m = new ArrayMapGenerics<String, String>();
      
      while (in.hasNext())
         m.put(in.next(), in.next());
         
      System.out.println("size: " + m.size());
      
      Object[] key = m.keys();
      int i = 0;
      
      while(!m.isEmpty())
      {
         System.out.println("get: " + key[i] + " " + m.get((String)key[i]));
         System.out.println("remove: " + key[i] + " " + m.remove((String)key[i++]));
      }
      
      System.out.println("size: " + m.size());                
   }
}
