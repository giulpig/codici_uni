import java.util.Scanner;

public class TextContainer implements Container {
    
    String[] v;
    int size;

    public TextContainer(){
        v = new String[1];
        size = 0;
    }

    public TextContainer(Scanner reader, String delimeter){
        v = new String[1];
        size = 0;

        while(reader.hasNextLine()){
            String line = reader.nextLine();
            int inizio = 0;
            for(int i=0; i<line.length(); i++){
                for(int j=0; j<delimeter.length(); j++){
                    if(line.charAt(i) == delimeter.charAt(j)){
                        if(!line.substring(inizio, i).equals(""))
                            this.add(line.substring(inizio, i));
                        //System.out.println("Aggiunto " + line.substring(inizio, i));
                        inizio = i+1;
                    }
                }
            }
            if(!line.substring(inizio, line.length()).equals(""))
                this.add(line.substring(inizio, line.length()));
        }

        System.out.println("Size " + size);

        reader.close();
    }

    public void add(String aWord){
        if(size>=v.length){
            String[] newv = new String[2*v.length];
            System.arraycopy(v, 0, newv, 0, v.length);

            v = newv;
        }

        v[size++] = aWord;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void makeEmpty(){
        String[] newv = new String[1];
        v = newv;
        size = 1;
    }

    public String removeLast(){
        if(size>0){
            String temp = v[size-1];
            v[--size] = null;
            return temp;
        }
        return "";
    }

    public int size(){
        return size;
    }

    public void sort(){
        mergeSort(v, size);
    }

    private static void mergeSort(String[] a, int size){
        
        if(size < 2)    return;
        int m = size/2;

        String[] left = new String[m];
        String[] right = new String[size-m];

        System.arraycopy(a, 0, left, 0, m);
        System.arraycopy(a, m, right, 0, size-m);

        mergeSort(left, left.length);
        mergeSort(right, right.length);

        merge(a, left, right);

    }

    private static void merge(String[] a, String[] left, String[] right){

        int p1=0, p2=0, p3=0;
        while(p1<left.length && p2<right.length){
            if(left[p1].compareTo(right[p2]) < 0){
                a[p3++] = left[p1++];
            }
            else{
                a[p3++] = right[p2++];
            }
        }

        while(p1<left.length)
            a[p3++] = left[p1++];

        while(p2<right.length)
            a[p3++] = right[p2++];

    }


}
