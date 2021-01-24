public class ZZZQueueMain {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};

        Queue<Integer> miaQ = new Q<Integer>();

        for(int i:nums){
            miaQ.enqueue(Integer.valueOf(i));
        }

        for(int i=0; i<5; i++){
            System.out.println(miaQ.dequeue());
        }


    }
}
