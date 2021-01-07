package Lab4;

import java.util.*;


public class MCDcomputer {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Inserisci i numeri p e q: ");
        int p = in.nextInt();
        int q = in.nextInt();
        //System.out.println();

        if(p==q)
            System.out.println(p);

        else {
            int temp;
            if(p<q){
                temp = q;
                q = p;
                p = temp;
            }
            while(p%q != 0){
                temp = p;
                p = q;
                q = temp % q;
            }
            System.out.println(q);
        }


        in.close();


    }
}
