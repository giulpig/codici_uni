package Lab2;

public class Cerchio{


    public static void main(String[] args){

        double raggio = 10.12;
        final double PI = 3.14159265358979;


        double circonferenza = raggio * 2 * PI;
        double area = raggio * raggio * PI;


        System.out.println("***La circonferenza di un cerchio di raggio "
                            + raggio + " e' pari a " + circonferenza + "***");

        System.out.println("***L'area di un cerchio di raggio "
                            + raggio + " e' pari a " + area + "***");


    }



}