public class Test implements Comparable<Test>{

    public int mioN;

    public Test(int N){
        mioN = N;
    }

    public int compareTo(Test other){
        if(mioN < other.mioN)   return -1;
        if(mioN > other.mioN)   return 1;
        return 0;
    }
    
    public static void main(String[] args) {

        final int SIZE = 20;

        Test v[] = new Test[SIZE];
        
        for(int i=0; i<SIZE; i++){
            Test boh = new Test((int)Math.floor(Math.random()*500));
            v[i] = boh;
        }

        ArrayAlgorithmsComparable.mergeSort(v);

        for(Test i: v)
            System.out.print(i.mioN + " ");

        System.out.println();
    }
    
}
