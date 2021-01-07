public class MyStringVector {
    
    private String[] v;
    private int mySize; 

    public MyStringVector(){
        v = new String[1];
        mySize = 0;
    }

    public void add(int index, String s){
        if(index<0 || index>=mySize){
            throw new IllegalArgumentException();
        }
        if(s==null)
            return;

        v[index] += s;        
    }

    public void add(String s){
        if(s==null)
            return;

        if(mySize<v.length){
            v[mySize] = s;
        }
        else{
            String[] temp = new String[v.length*2];
            for(int i=0; i<v.length; i++)
                temp[i] = v[i];
            v = temp;
            v[mySize] = s;
        }

        mySize++;
    }

    public int capacity(){
        return v.length;
    }


    public boolean contains(String s){

        for(int i=0; i<mySize; i++){
            if(v[i].equals(s))
                return true;
        }
        return false;
    }

    public String elementAt(int index){

        if(index<0 || index>=mySize){
            return null;
        }

        return v[index];
    }

    public int indexOf(String s){

        for(int i=0; i<mySize; i++){
            if(v[i].equals(s))
                return i;
        }

        return -1;
    }

    public boolean isEmpty(){
        if(mySize==0)
            return true;
        return false;
    }

    public void makeEmpty(){
        mySize = 0;
        v = new String[1];
    }

    public String max(){
        String mas = "";
        for(int i=0; i<mySize; i++){
            if(v[i].compareTo(mas) > 0)
                mas = v[i];
        }
        return mas;
    }

    public String min(){
        if(mySize==0)   return "";

        String mini = v[0];
        for(int i=1; i<mySize; i++){
            if(v[i].compareTo(mini) < 0)
                mini = v[i];
        }
        return mini;
    }

    public String remove(int index){

        if(index<0 || index>=mySize){
            return null;
        }
        
        mySize--;
        String temp = v[index];

        for(int i=index; i<mySize; i++){
            v[i] = v[i+1];
        }

        return temp;
    }

    public String set(int index, String s){
        if(index<0 || index>=mySize){
            return null;
        }

        String temp = v[index];
        v[index] = s;

        return temp;
    }

    public int size(){
        return mySize;
    }

    public String[] toArray(){

        String[] temp = new String[mySize];
        
        for(int i=0; i<mySize; i++){
            temp[i] = v[i];
        }

        return temp;
    }

    public String toString(){

        String res = "";

        for(int i=0; i<mySize; i++){
            res += v[i] + "\n";
        }

        return res;
    }

}
