/**
   RandomSequenceGenerator.java

   generatore di sequenze di numeri interi pseudo-casuali.
   
   @author A. Luchetta
   @verson 11-Nov-2006
*/

import java.util.Random;

public class RandomSequencesGenerator
{
    private int s[];
    private Random rand;
    
    public RandomSequencesGenerator()
    {
       s = new int[0];
       rand = new Random();
    }
    
    /**
       genera una nuova sequenza di numeri casuali
       @param length lunghezza della sequenza
       @param minValue minimo dell'intervallo dei numeri casuali generati
       @param supValue estremo superiore dell'intervallo dei numeri casuali generati
       @return l'array contenente i numeri psedo-casuali
    */
    public int[] generateNewSequence(int length, int minValue, int supValue)
    {
       if (length < 0 || minValue > supValue) // correzione
         throw new IllegalArgumentException();
         
       s = new int[length];

       for (int i = 0; i < s.length; i++)
          s[i] = minValue + rand.nextInt(supValue - minValue + 1); // correzione

       return s;
    }
    
    /**
       restituisce la sequenza di numeri pseudo-casuali memorizzata.
       @return la sequenza di numeri pseudo-casuali memorizzata.
    */
    public int[] getSequence()
    {
       return s;
    }
    
    public String toString()
    {
       final int NUMBERS_PER_LINE = 10;
       final int DIGIT_PER_NUMBER = 5;

       String result = "";
       for (int i = 0; i < s.length; i++)
       {
          String sString = Integer.toString(s[i]);
          while (sString.length() < DIGIT_PER_NUMBER)
             sString = " " + sString;

          String delimiter = " ";
          if ((i + 1)% NUMBERS_PER_LINE == 0)
             delimiter ="\n";
             
          result = result + sString + delimiter;
          
       }
       
       return result;
    }
}
