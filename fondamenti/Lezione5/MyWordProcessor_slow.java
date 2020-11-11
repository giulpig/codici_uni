import java.util.*;

public class MyWordProcessor_slow {    

    private int size;
    private int wordSize;
    private String text;

    public MyWordProcessor_slow(){
        wordSize = 0;
        text = "";
        size = 0;
    }

    public void add(String line){
        if(size>0)
            text += ("\n" + line);
        else
            text += (line);

        size++;

        int counter = 0;                            //conto quante parola ha la 
        for(int i=0; i<line.length(); i++){
            char carattere = line.charAt(i);
            if(Character.isWhitespace(carattere)){
                counter++;
            }
        }

        wordSize += counter+1;
    }

    public String[] toWords(){

        String[] result = new String[wordSize];

        Arrays.fill(result, "");

        int counter = 0;                         //divido in parole
        for(int i=0; i<text.length(); i++){
            char carattere = text.charAt(i);
            if(Character.isWhitespace(carattere)){
                counter++;
            }
            else{
                result[counter] += carattere;
            }
        }

        return result;
    }

    public int findAndReplace(String find, String replace){
        
        String[] wordList = this.toWords();

        int replaces = 0;

        int counter = 0;
        for(int i=0; i<wordList.length; i++){
            int size_parola = wordList[i].length();
            if(find.equals(wordList[i])){
                text = text.substring(0, counter) + replace + text.substring(counter+size_parola, text.length());
                replaces++;
            }

            counter+=size_parola + 1; //1 sarebbe il whitespace
        }

        //text.replace(find, replace)      //metodo furbo
        return replaces;
    }

    public String maxString(){

        String[] wordList = this.toWords();
        if(wordList.length == 0)
            return "";

        String max = wordList[0];

        for(int i=1; i<wordList.length; i++){
            if(wordList[i].compareTo(max) > 0){
                max = wordList[i];
            }
        }

        return max;
    }

    public int wordSize(){
        return wordSize;
    }

    public int size(){
        return size;
    }

    public String toString(){

        return text;
    }

    
}
