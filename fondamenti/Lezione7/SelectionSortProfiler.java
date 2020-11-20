import java.util.Random;

public class SelectionSortProfiler{

    static java.util.Random generator = new Random();


    public static int selectionSortCounter(int[] a){
        int counter = 0;
        for(int i=0; i<a.length; i++){
            counter++;
            for(int j=i; j<a.length; j++){
                if(a[j]<a[i]){
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                    counter += 3;
                }
            }
        }
        return counter;
    }


    private static int[] randomIntegerArray(int length){
    
        int[] res = new int[length];

        for(int i=0; i<length; i++){
            res[i] = generator.nextInt(length);
        }

        return res;

    }

    private static int[] randomIntegerIncreasingArray(int length){
    
        int[] res = new int[length];

        for(int i=0; i<length; i++){
            res[i] = generator.nextInt(length);
        }

        ArrayAlgorithms.mergeSort(res);

        return res;

    }

    private static int[] randomIntegerDecreasingArray(int length){
    
        int[] res = new int[length];

        for(int i=0; i<length; i++){
            res[i] = generator.nextInt(length);
        }

        ArrayAlgorithms.mergeSortReversed(res);

        return res;

    }



    public static void main(String[] args) {
        final int N = Integer.parseInt(args[0]);

        int[] randomArr = randomIntegerArray(N);
        int[] increasing = randomIntegerIncreasingArray(N);
        int[] decreasing = randomIntegerDecreasingArray(N);

        /*for(int i: randomArr){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i: increasing){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i: decreasing){
            System.out.print(i + " ");
        }
        System.out.println();*/

                 /*Misuro Prestazioni*/

        //1. caso migliore con array increasing
        System.out.println("Increasing accesses: " + selectionSortCounter(increasing));


        //2. caso medio con array random
        System.out.println("Random accesses: " + selectionSortCounter(randomArr));


        //3. caso peggiore con array decreasing
        System.out.println("Decreasing accesses: " + selectionSortCounter(decreasing));


    }


}