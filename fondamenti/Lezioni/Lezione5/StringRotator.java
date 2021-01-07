package Lezione5;


import java.util.*;



public class StringRotator {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Inserire una riga: ");
        final String parola = in.nextLine();

        final int SIZE_PAROLA = parola.length(); 


        System.out.print("Inserire il numero di rotazione: ");
        int n = in.nextInt();

        String result = "";

        if(n<0)
            n = Math.abs(SIZE_PAROLA+n) % SIZE_PAROLA;

        for(int i=0; i<parola.length(); i++){
            result += parola.charAt((i+(n)%SIZE_PAROLA)%SIZE_PAROLA);
        }

        System.out.println("Riga ruotata: " + result);


        in.close();
    }
}
