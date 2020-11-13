

public class SubstringGeneratorREC {
    
    public static String toSplit;
    public static String[] substrings;
    public static int index;
    public static boolean memo[][];

    
    //serve solo per soluzione lenta
    public static boolean find(String el){
        for(String i: substrings){
            if(el.equals(i))
                return true;
        }
        return false;
    }
    

    public static void rec(int start, int end){
        String temp = toSplit.substring(start, end+1);

        /*if(find(temp))    //O(n^2), bad
            return;*/

        //better O(1)
        if(memo[start][end] == true)   //se l'elemento esiste gia'
                                     //nella matrice memorizzatrice
            return;

        substrings[index] = temp;
        index++;
        memo[start][end] = true;

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

        /*for(String i: set){
            if(i!=null)       //diverso da null perche'
                              //non sempre l'array e' pieno
                System.out.print(i + " ");
        }

        System.out.println();*/

    }
}
