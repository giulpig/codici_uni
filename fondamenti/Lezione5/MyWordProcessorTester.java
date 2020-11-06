import java.util.Scanner;
/**
   Classe eseguibile per la prova della classe MyWordprocessor. <br>
   Acquisisce un testo da standard input e lo elabora inviando a standard
   output:<br>
   - il testo originale <br>
   - il testo modificato dopo aver sostituito una parola <br>
   - il numero di sostituzioni effettuate <br>
   - il numero di righe e di parole del testo <br>
   - la parola massima del testo <br>
   - il testo suddiviso in parole <br>
   
   @see MyWordProcessor
   
   @author A. Luchetta
   @version 20201028
*/
public class MyWordProcessorTester
{
   public static void main(String[] args)
   {
      final String FIND = "bianco";
      final String REPLACE = "chiaro";

      // oggetto di classe MyWordProcessor
      MyWordProcessor mwp = new MyWordProcessor();

      // aperura del flusso d'ingresso 
      Scanner in = new Scanner(System.in);

      // ciclo di lettura del testo da standard input
      while (in.hasNextLine())
         mwp.add(in.nextLine());
              
      // chiusura del flusso d'ingresso      
      in.close();
      
      // invio a standard output del testo originale
      System.out.println("----TESTO ORIGINALE----");
      System.out.println(mwp); 
      
      // modifica del testo per sostituzione di parola
      int n = mwp.findAndReplace(FIND, REPLACE);
      
      // invio a standard output del testo modificato
      System.out.println("\n----TESTO MODIFICATO----");
      System.out.println(mwp);
      
      // invio a standard output del numero di sostituzioni effettuate
      System.out.println("parola " + FIND + " sostituita con la parola " + REPLACE);
      if (n == 1)
         System.out.println(n + " sostituzione effettuata");
      else
         System.out.println(n + " sostituzioni effettuate");         
      
      // invio a standard output del numero di righe e di parole
      System.out.println("\ntesto con " + mwp.size() + " righe e "
                             + mwp.wordSize() + " parole"); 
                             
      // invio a standard output della parola massima
      System.out.println ("\nparola massima del testo: " + mwp.maxString()); 
      
      // invio a standard output dele parole del testo
      String[] words = mwp.toWords();
      System.out.println();
      for (String e : words)
         System.out.println(e);                     
   }
}

