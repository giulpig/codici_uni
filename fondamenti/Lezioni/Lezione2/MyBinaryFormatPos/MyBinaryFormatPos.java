import java.util.*;

public class MyBinaryFormatPos{

    public static void main(String[] args) {
        final int N;
        Scanner in = new Scanner(System.in);

        System.out.println("Inserisci un numero tra 0 e 127 compresi: ");

        N = in.nextInt();

        //Es: 
        //      2 = 0b 0000 0010
        //    127 = 0b 0111 1111


        String binString = "0b0";


        //approccio left-to-right

        int modulo = 1<<6;  //=2^6
        int remaining = N;  //cio' che devo ancora dividere

        binString += remaining / modulo;  //bit 2^6
        remaining = remaining % modulo;   //estraggo il bit piu' sign.
        modulo >>= 1;                     //eqiv. a modulo /= 2 

        binString += remaining / modulo;  //bit 2^5
        remaining = remaining % modulo;
        modulo >>= 1;

        binString += remaining / modulo;  //bit 2^4
        remaining = remaining % modulo;
        modulo >>= 1;

        binString += remaining / modulo;  //bit 2^3
        remaining = remaining % modulo;
        modulo >>= 1;

        binString += remaining / modulo;  //bit 2^2
        remaining = remaining % modulo;
        modulo >>= 1;

        binString += remaining / modulo;  //bit 2^1
        remaining = remaining % modulo;
        modulo >>= 1;

        binString += remaining / modulo;  //bit 2^0


        System.out.println("Il numero decimale " + N + 
                            " equivale a " + binString);

        in.close();

    }


}