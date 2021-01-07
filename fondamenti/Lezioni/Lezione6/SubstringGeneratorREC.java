
/*

l'algoritmo parte dalla stringa intera, e poi la suddivide in
due: la prima parte da 0 a fine-1 e la seconda da 1 a fine
agisce ricorsivamente finche' la sottostringa non e' formata
da un solo elemento

in questo modo pero' si generano anche delle sottostringhe
che già esistono, e qui viene in aiuto la matrice memo, che
serve a ricordare quali intervalli abbiamo gia' considerato,
e fermare l'inutile ricorsione

*/


public class SubstringGeneratorREC {
    
    public static String toSplit;        //variabili pubbliche
    public static String[] substrings;   //accessibili a tutta
    public static int index;             //la classe

    public static boolean memo[][];      //matrice per ricordare
                                         //gli indici di partenza
                                         //e di fine
    


    //serve solo per soluzione lenta (vedi linea 41)
    public static boolean find(String el){
        for(String i: substrings){
            if(el.equals(i))
                return true;
        }
        return false;
    }
    

    public static void rec(int start, int end){
        String temp = toSplit.substring(start, end+1);

        /*if(find(temp))    //soluzione lenta O(n^2), bad
            return;*/

        //soluzione con matrice memo più veloce O(1)
        if(memo[start][end] == true){  //se l'elemento esiste gia'
                                       //nella matrice memo,
                                       //fermo la ricorsione
            return;
        }

        substrings[index] = temp;     //aggiundo all'array "dinamico" delle
        index++;                      //sottostringhe l'elemento
        memo[start][end] = true;      //e mi ricordo quale intervallo
                                      //sto considerando

        if(start==end)
            return;

        rec(start, end-1);
        rec(start+1, end);
        
    }

    public static void main(String[] args) {

        toSplit = args[0];
        substrings = new String[toSplit.length()*(toSplit.length()+1)/2];
        index = 0;
        memo = new boolean[toSplit.length()][toSplit.length()];

        rec(0, toSplit.length()-1);

        for(String i: substrings){
            if(i!=null)       //diverso da null perche'
                              //non sempre l'array e' pieno
                System.out.print(i + " ");
        }

        System.out.println();

    }
}
