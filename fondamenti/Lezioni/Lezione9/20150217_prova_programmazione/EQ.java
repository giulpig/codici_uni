public class EQ<T extends Comparable> extends Q<T> {
    
    public void enqueue(T x){
        super.enqueue(x);
    }

    public Object[] toSortedArray(){
        Object[] v = this.toArray();
        insertionSort(v);
        return v;
    }

    public Object[] getOnlyUniqueElements(){
        Object[] sorted = this.toSortedArray();
        int diversi = 0;
        for(int i=1; i<sorted.length; i++){
            if(sorted[i] != sorted[i-1])
                diversi++;
        }

        Object[] ret = new Object[diversi];
        int index = 0;

        for(int i=1; i<sorted.length; i++){
            if(sorted[i] != sorted[i-1]){
                ret[index] = sorted[i-1];
                index++;
            }
        }

        return ret;
    }


    public EQ[] split(int n){
        int elpercoda = (int)Math.ceil((float)(this.size())/n);
        Object[] v = this.toArray();
        EQ[] code = new EQ[n];

        int index = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<elpercoda && index<v.length; j++){

                code[i].enqueue((T)(v[index++]));

                if(this.front() == null)
                    break;
            }
        }

        return code;

    }



    private void insertionSort(Object[] toSort){
        for(int i=1; i<toSort.length; i++){
            for(int j=i; j>=1; j--){
                if(((Comparable)(toSort[j])).compareTo(toSort[j-1]) < 0){
                    Object temp = toSort[j];
                    toSort[j] = toSort[i];
                    toSort[i] = temp;
                }
                else
                    break;
            }

        }
    }



}
