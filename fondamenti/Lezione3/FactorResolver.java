import java.util.*;

public class FactorResolver {
    
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.printf("La scomposizione in fattori primi di %d è: 1", n);

        int i = 2;

        while(i*i<=n){     //check divisors (il primo numero che incontro
                                       //  è sempre primo)
            if(n%i == 0){
                System.out.printf(" * %d", i);
                n/=i;
                i=2;
            }
            else if(i%2==0)
                i++;
            else
                i+=2;
        }

        if(n!=1)
            System.out.printf(" * %d", n);

        System.out.println();
        in.close();

    }


}
