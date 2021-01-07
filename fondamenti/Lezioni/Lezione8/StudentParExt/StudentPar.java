/**
  * Classe per illustrare il funzionamento dell'interfaccia java.lang.Comparable
  *
  * @author A. Luchetta
  * @version 24-11-2019
  * @see java.lang.Comparable;
  *
  */ 
  public class StudentPar implements Comparable<StudentPar>
  { 
      private int rollno; 
      private String name, address; 
  
      /**
         inizializza un esemplare della classe
         @param aRollno numero di matricola di questo studente
         @param aName nome di questo studente
         @param anAddress indirizzo di questo studente
      */ 
      public StudentPar(int aRollno, String aName, String anAddress) 
      { 
          rollno = aRollno; 
          name = aName; 
          address = anAddress; 
      } 
      
      /**
         @return numero di matricola di questo studente
      */
      public int rollno()
      {
         return rollno;
      }
  
      /**
         @return nome di questo studente
      */
      public String name()
      {
         return name;
      }	
          
      /**
          @return descrizione testuale 	    
      */
      public String toString() 
      { 
          return "Student: " + rollno + " " + name +  " " + address; 
      }
      
     /**
        ordine naturale della classe
        @param o l'elemento specificato
     */
      public int compareTo(StudentPar o)
      {
        // gestione delle precondizioni
         if(o == null)
            throw new IllegalArgumentException();
         
        // confronto in base al numero di matricola
         return rollno - o.rollno;  
      }
  } 
  
  