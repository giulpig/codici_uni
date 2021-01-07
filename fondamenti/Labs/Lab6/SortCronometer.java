/**
  * SortCronometer.java
  *
  * Classe eseguibile che misura i tempi di esecuzione di alcuni algoritmi di
  * ordinamento.
  *
  * @author A. Luchetta
  * @version 11-Nov-2006
  *
  */

import java.util.Scanner;

public class SortCronometer
{
   public static void main(String[] args)
   {
      Cronometer crono = new Cronometer();
      RandomSequencesGenerator r = new RandomSequencesGenerator();
      Scanner in = new Scanner(System.in);
      
      System.out.println("\n***MISURA DELLE PRESTAZIONI DEGLI ALGORITMI DI ORDINAMENTO***");
      System.out.println();
      System.out.println("n             selectionSort     mergeSort insertionsort");
      System.out.println("                         ms            ms            ms");
      System.out.println("-------------------------------------------------------");

      String line = "";
      //int count = 0;
      while (in.hasNextLine())
      {
         // acquisizione di n e repetitionFactor
         line = in.nextLine();
         Scanner tok = new Scanner(line);
         int n = tok.nextInt();
         int repetitionFactor = tok.nextInt();

         // generazione sequenza casuale di lunghezza n
         int[] a = r.generateNewSequence(n, 0, n);
         int[] b = new int[n];

         // misura del tempo per copiare repetitionFactor volte l'array a nell'array b
         crono.start();
         for (int i = 0; i < repetitionFactor; i++)
            System.arraycopy(a, 0, b, 0, a.length);
         crono.stop();
         double tArrayCopy = crono.getElapsedTime();
         crono.reset();

         // misura dell'algoritmo selectionSort
         crono.start();
         for (int i = 0; i < repetitionFactor; i++)
         {
            System.arraycopy(a, 0, b, 0, a.length);
            ArrayAlgorithms.selectionSort(b);
         }
         crono.stop();
         double selectionSortTime = ((double)(crono.getElapsedTime() - tArrayCopy)) / repetitionFactor;
         crono.reset();

         // Misura dell'algoritmo mergeSort
         crono.start();
         for (int i = 0; i < repetitionFactor; i++)
         {
            System.arraycopy(a, 0, b, 0, a.length);
            ArrayAlgorithms.mergeSort(b);
         }
         crono.stop();
         double mergeSortTime = ((double)(crono.getElapsedTime() - tArrayCopy)) / repetitionFactor;
         crono.reset();

         // Misura dell'algoritmo insertionSort
         crono.start();
         for (int i = 0; i < repetitionFactor; i++)
         {
            System.arraycopy(a, 0, b, 0, a.length);
            ArrayAlgorithms.insertionSort(b);
         }
         crono.stop();
         double insertionSortTime = ((double)(crono.getElapsedTime() - tArrayCopy)) / repetitionFactor;
         crono.reset();
         
         // stampa in colonna
         String str = printTable(13, n, selectionSortTime, mergeSortTime, insertionSortTime);
         System.out.println(str);

         //count++;
         tok.close();
      }
      
      in.close();
   }

   private static String printTable(int width, int n, double d1, double d2, double d3)
   {
      String nStr = Integer.toString(n);
      while (nStr.length() < width)
         nStr = nStr + " ";
         
      String d1Str = String.format("%.2f", d1);
      while (d1Str.length() < width)
         d1Str = " " + d1Str;
         
      String d2Str = String.format("%.2f", d2);
      while (d2Str.length() < width)
         d2Str = " " + d2Str;

      String d3Str = String.format("%.2f", d3);
      while (d3Str.length() < width)
         d3Str = " " + d3Str;
         
      return nStr + " " + d1Str + " " + d2Str + " " + d3Str;
   }
}
