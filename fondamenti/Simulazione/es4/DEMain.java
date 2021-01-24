import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class DEMain {
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length <= 0) {
            return;
        }

        FileReader file = new FileReader(args[0]);

        Scanner in = new Scanner(file);

        in.useDelimiter("#");

        DE<String, String> mappa = new DE<String, String>();

        while (in.hasNextLine()) {
            String chiave = in.next();
            String valore = in.next();
            mappa.insert(chiave, valore);
        }

        System.out.println("SIZE: " + mappa.size() + "\n\n");

        System.out.println("FINDALL");

        String[] chiavi = (String[]) mappa.keys();

        for (int i = 0; i < chiavi.length; i++) {
            String[] valori = (String[]) mappa.findAll(chiavi[i]);
            System.out.print(chiavi[i]);
            for (int j = 0; i < valori.length; i++)
                System.out.print(" + " + valori[j]);
        }

        System.out.println("\n\n");

        System.out.println("VALUESET:");

        String[] valueset = (String[]) mappa.valueSet();

        for (int i = 0; i < valueset.length; i++) {
            System.out.print(valueset[i] + " - ");
        }

        System.out.println("\n\n");

        for (int i = 0; i < chiavi.length; i++) {
            String[] valori = (String[]) mappa.findAll(chiavi[i]);
            System.out.print(chiavi[i]);
            for (int j = 0; i < valori.length; i++)
                System.out.print(" + " + valori[j]);
            mappa.removeAll(chiavi[i]);
        }

        in.close();

    }
}
