public class ArrayAlgorithms{


    public ArrayAlgorithms(){
        ;
    }

    public static int guardedLinearSearch(int[] a, int size, int target){
        a[size] = target;

        int index = 0;
        while(a[index]!=target){
            index++;
        }

        if(index == size){
            return -1;
        }
        return index;

    }


    public static int iterativeBinarySearch(int[] a, int v){
        int left = 0;
        int right = a.length-1;
        while(left<=right){
            int m = left + (right-left)/2;
            if(a[m] == v){
                return m;
            }
            
            if(a[m] < v){
                left = m;
            }
            else{
                right = m;
            }
        }

        return -1;

    }


    public static void insertionSort(int[] a){
        for(int i=1; i<a.length; i++){
            int j = i;
            while(j>0 && a[j]<a[j-1]){
                int temp = a[j-1];
                a[j-1] = a[j];
                a[j] = temp;
                j--;
            }
        }
    }

    public static int linearSearch(int[] a, int target){
        for(int i=0; i<a.length; i++){
            if(a[i]==target)
                return i;
        }
        return -1;
    }

    public static void mergeSort(int[] a){
        
        if(a.length < 2)    return;
        int m = a.length/2;

        int[] left = new int[m];
        int[] right = new int[a.length-m];

        System.arraycopy(a, 0, left, 0, m);
        System.arraycopy(a, m, right, 0, a.length-m);

        mergeSort(left);
        mergeSort(right);

        merge(a, left, right);

    }


    //see Lezione7
    private static void merge(int[] a, int[] left, int[] right){

        int p1=0, p2=0, p3=0;
        while(p1<left.length && p2<right.length){
            if(left[p1]<right[p2]){
                a[p3++] = left[p1++];
            }
            else{
                a[p3++] = right[p2++];
            }
        }

        if(p1<left.length)
            a[a.length-1] = left[p1];

        if(p2<right.length)
            a[a.length-1] = right[p2];

    }

    private static int recursiveBinarySearchHelper(int[] a, int v, int l, int r){

        if(l>r)
            return -1;

        if(l==r){
            if(a[l] == v){
                return l;
            }
            return -1;
        }

        int m = l + (r-l)/2;

        if(a[m] < v){
            return recursiveBinarySearchHelper(a, v, m+1, r);
        }
        else{
            return recursiveBinarySearchHelper(a, v, l, m);
        }

    }

    public static int recursiveBinarySearch(int[] a, int v){
        return recursiveBinarySearchHelper(a, v, 0, a.length-1);
    }


    public static void selectionSort(int[] a){
        for(int i=0; i<a.length; i++){
            for(int j=i; j<a.length; j++){
                if(a[j]<a[i]){
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
    }




}