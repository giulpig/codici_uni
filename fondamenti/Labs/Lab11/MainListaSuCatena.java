import java.util.Scanner;
import java.io.*;

public class MainListaSuCatena {
    
    public static void main(String[] args) throws FileNotFoundException{
        ListaSuCatena lista = new ListaSuCatena();

        /*
        if(args.length == 0){
            System.out.println("Uso: java MainListaSuCatena.java <nomefile>");
            return;
        }

        Scanner in = new Scanner(new FileReader(args[0]));
        */

        Scanner in = new Scanner(new FileReader("/home/giulio/Desktop/uni/codici_uni/fondamenti/Labs/Lab11/monti.txt"));

        int index = 0;
        String buffer = "";

        while(in.hasNextLine()){
            String temp = in.nextLine();
            for(int i=0; i<temp.length(); i++){
                if(temp.charAt(i) == ','){
                    lista.add(index++, buffer);
                    buffer = "";
                }
                else
                    buffer += temp.charAt(i);
            }
            lista.add(index++, buffer);
            buffer = "";
        }

        Iterator it = lista.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
}
