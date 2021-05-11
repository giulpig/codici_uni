//giulio codutti, matricola 2008795

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException; //throws FileNotFoundException;

public class LTester {
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length <= 0) {
            return;
        }

        FileReader file = new FileReader(args[0]);

        Scanner in = new Scanner(file);
        in.useDelimiter(",");

        List lista1 = new L();
        List lista2 = new L();

        Iterator it1 = lista1.iterator();

        int index = 0;
        while (in.hasNext()) {
            String str = in.next();
            it1.add(str);
            lista2.add(index++, str);
        }

        it1 = lista1.iterator();

        System.out.println("Valori lista1 con iterator:");
        while (it1.hasNext()) {
            System.out.println(" " + it1.next());
        }

        System.out.println("\n\n");

        System.out.println("Valori lista2 senza iterator:");
        index = lista2.size()-1;
        while (index>=0) {
            System.out.println(" " + lista2.get(index--));
        }

        System.out.println("\n\n");
        System.out.println("\n\n");

        it1 = lista1.iterator();

        System.out.println("Vuotare lista1 con iterator:");
        while (it1.hasNext()) {
            it1.next();
            it1.remove();
        }

        System.out.println("\n\n");

        index = 0;

        System.out.println("Vuotare lista2 senza iterator:");
        while (index < lista2.size()) {
            lista2.remove(index++);
        }

        in.close();

    }
}