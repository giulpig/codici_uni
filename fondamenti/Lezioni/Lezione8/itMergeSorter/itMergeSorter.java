
/**
 * Aggiungi qui una descrizione della classe itMergeSorter
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
public class itMergeSorter{

    public static java.util.Random gen;

    private static int[] sortedIntegerArray(int length){

        int[] temp = new int[length];

        for(int i=0; i<length; i++){
            temp[i] = i;
        }

        return temp;

    }

    private static int[] randomIntegerArray(int length){
        int[] temp = sortedIntegerArray(length);

        for(int i=0; i<length; i++){
            int toSwapWith = gen.nextInt(length);
            int t = temp[i];
            temp[i] = temp[toSwapWith];
            temp[toSwapWith] = t;
        }

        return temp;

    }

    static void iterativeMergesort(int arr[])
    {
        int n = arr.length;
        int curr_size; 
                     
        int left_start;
                         
        for (curr_size = 1; curr_size <= n-1; curr_size = 2*curr_size)
        {
             
            // Pick starting point of different
            // subarrays of current size
            for (left_start = 0; left_start < n-1; left_start += 2*curr_size)
            {
                
                int mid = Math.min(left_start + curr_size - 1, n-1);
         
                int right_end = Math.min(left_start + 2*curr_size - 1, n-1);
         
                merge(arr, left_start, mid, right_end);
            }
        }
    }
     
    static void merge(int arr[], int l, int m, int r)
    {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;
     
        int L[] = new int[n1];
        int R[] = new int[n2];
     
        for (i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (j = 0; j < n2; j++)
            R[j] = arr[m + 1+ j];
     
        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k++] = L[i++];
            }
            else
            {
                arr[k++] = R[j++];
            }
        }
     
        while (i < n1)
        {
            arr[k++] = L[i++];
        }
        while (j < n2)
        {
            arr[k++] = R[j++];
        }
    }
    
    
    
    public static void main(String[] args){

        gen = new java.util.Random();

        final int N = Integer.parseInt(args[0]);

        int[] v = sortedIntegerArray(N);

        int[] vRandom = randomIntegerArray(N);

        iterativeMergesort(vRandom);

        for(int i: vRandom){
            System.out.print(i + " ");
        }
        
        System.out.println();

    }
}
