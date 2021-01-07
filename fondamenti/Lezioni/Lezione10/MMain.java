import java.util.Scanner;

public class MMain {
    

    public static void main(String[] args) {
        M<String, String> m = new M<String, String>();
        Scanner in = new Scanner(System.in);

        String key;
        String value;

        while(in.hasNextLine()){

            if(!in.hasNext())
                break;
            key = in.next();

            if(!in.hasNext())
                break;
            value = in.next();

            m.put(key, value);

        }

        System.out.println("Size: " + m.size());

        Object[] keys = m.keySet();

        System.out.println("\nEntries: ");
        
        for(int i=0; i<m.size(); i++){
            System.out.print((String)(keys[i]) + "/" + m.get((String)keys[i]) + " ");
        }

        System.out.println("\nRemove: ");

        int idx = 0;
        while(!m.isEmpty()){
            System.out.print((String)(keys[idx]) + "/" + m.remove((String)keys[idx]) + " ");
            idx++;
        }

        System.out.println("\nSize: " + m.size());


    }
}
