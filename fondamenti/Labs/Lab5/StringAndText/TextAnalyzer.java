import java.util.*;

public class TextAnalyzer {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        MyStringVector perRighe = new MyStringVector();
        while(in.hasNext()){
            perRighe.add(in.next());
        }

        MyStringVector perParole = new MyStringVector();

        for(String i: perRighe.toArray()){
            for(String j: i.split(" ")){
                perParole.add(j);
            }
        }

        System.out.println(perRighe);

        System.out.println("Numero di parole: " + perParole.size());

        System.out.println("Prima parola: " + perParole.min());

        System.out.println("Ultima parola: " + perParole.max());

        while(!perParole.isEmpty()){
            String mini = perParole.min();
            int indice = perParole.indexOf(mini);
            System.out.print(perParole.remove(indice) + " ");
        }

        System.out.println();

        in.close();
    }
}
