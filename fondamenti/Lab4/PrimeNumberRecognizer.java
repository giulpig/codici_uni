import java.util.*;


public class PrimeNumberRecognizer {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String lettura;
        int n;


        while(true){
            lettura = in.next();

            if(lettura.equals("Q"))
                break;
            
            n = Integer.parseInt(lettura);

            if(n==1 || n==2 || n==3)
                System.out.println("Numero primo");
            
            else{

                boolean divisible = false;
                for(int i=2; i*i<=n; i+=2){
                    if(n%i==0){
                        System.out.println("Numero non primo");
                        divisible = true;
                        break;
                    }
                    if(i==2)    i--;
                }
                if(!divisible){
                    System.out.println("Numero primo");
                }
            }

        }

        in.close();
    }
}
