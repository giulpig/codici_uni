import java.util.Scanner;
import java.lang.Math;

public class MyExpCalculator {


    public static double myExp(int esponente){

        double result = 1.0;
        double potenza = esponente;
        double fattoriale = 1.0;
        double termineK = 1.0;
        int k=2;

        do{
            termineK = potenza / (new Double(fattoriale));
            result += termineK;

            potenza *= esponente;
            fattoriale *= k;
            k++;
        }while(termineK > 1e-14);

        return result;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("**********************************************************************\n"
        + "* Calcola la funzione exp(x) usando lo sviluppo in serie di Taylor   *\n"
        + "* riceve il valore di x da standard input                            *\n"
        + "* invia il risultato a standard output                               *\n"
        + "**********************************************************************\n");

        final int x;
        x = in.nextInt();

        double eToX = myExp(x);

        System.out.println("Il mio risultato e':  " + eToX);
        System.out.println("Il risultato Math e': " + Math.exp(x));


        in.close();

    }
}
