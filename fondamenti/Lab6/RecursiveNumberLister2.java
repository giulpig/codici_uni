import java.util.*;


public class RecursiveNumberLister2 {
    

    public static void printRecursive(int index, int end){

        for(int i=0; i<5-Math.ceil(Math.log10(index+1)); i++){
            System.out.print(" ");
        }

        System.out.print(index);

        if(index%10==0){
            System.out.println();
        }

        if(index==end){
            System.out.println();
            return;
        }
        printRecursive(index+1, end);

    }


    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in); 
        final int N = in.nextInt();

        printRecursive(1, N);

        in.close();
    }
}
