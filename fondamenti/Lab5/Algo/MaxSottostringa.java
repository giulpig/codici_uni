import java.util.*;

public class MaxSottostringa{

    static public class Posizioni{
        private int[] v;
        public int mySize;
        public Posizioni(int n){
            v = new int[n];
            mySize = 0;
        }
        public void add(int pos){
            v[mySize++] = pos;
        }
        public int posAt(int index){
            return v[index];
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        /*System.out.println("Inserire prima stringa: ");
        String prima = in.next();

        System.out.println("Inserire seconda stringa: ");
        String seconda = in.next();*/

        String prima = "TopolinoPlutoPippo";
        String seconda = "FlautoPifferoViolino";

        if(prima.length() > seconda.length()){
            String temp = prima;

            prima = seconda;
            seconda = temp;
        }

        Posizioni[] DP = new Posizioni[128];  //26 lettere

        for(int i=0; i<seconda.length(); i++){
            DP[seconda.charAt(i)] = new Posizioni(seconda.length());
            DP[seconda.charAt(i)].add(i);
        }

        String maxS = "";

        for(int i=0; i<prima.length(); i++){
            if(prima.length()-i <= maxS.length()){
                break;
            }
            for(int j=0; DP[prima.charAt(i)] != null && j<DP[prima.charAt(i)].mySize; j++){
                //string matching 2 pointers
                int startSec = DP[prima.charAt(i)].posAt(j);
                if(seconda.length() - startSec <= maxS.length()){
                    break;
                }
                String temp = "";
                for(int k=i; k<Math.min(prima.length(), seconda.length()-startSec+i); k++){
                    if(prima.charAt(k)==seconda.charAt(startSec+k-i)){
                        temp += prima.charAt(k);
                        if(temp == "u"){
                            System.out.println("mandimandi");
                        }
                    }
                    else
                        break;
                }
                if(temp.length() > maxS.length()){
                    maxS = temp;
                }
            }
        }

        System.out.println("Res: " + maxS);


        in.close();


    }
}