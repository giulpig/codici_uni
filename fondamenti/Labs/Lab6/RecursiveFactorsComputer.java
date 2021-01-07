import java.util.*;


public class RecursiveFactorsComputer {
    

    public static String printRecursive(String factors, int N, int factor){


        if(N%factor == 0){
            return printRecursive(factors + "*" + factor, N/factor, factor);
        }
        else{
            if(factor>N){
                return factors;
            }
            return printRecursive(factors, N, factor+1);
        }

    }


    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in); 
        final int N = in.nextInt();

        System.out.println(printRecursive("1", N, 2));

        in.close();
    }
}
