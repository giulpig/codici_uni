public class Convertitore {
    public static void main(String[] args) {
        final int N = 256;
        final int B = 7;

        int soluzione = 0;

        int cifra1 = N/(B*B);
        soluzione += cifra1*100;

        int cifra2 = (N%(B*B))/(B);
        soluzione += cifra2*10;

        int cifra3 = N%B;
        soluzione += cifra3;

        System.out.println(soluzione);
    }
}
