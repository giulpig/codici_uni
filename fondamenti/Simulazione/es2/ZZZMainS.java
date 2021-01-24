public class ZZZMainS {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};

        S<Integer> mioS = new S<Integer>();

        for(int i:nums){
            mioS.add(Integer.valueOf(i));
        }

        for(int i:nums){
            System.out.print(mioS.contains(Integer.valueOf(i-2)) + " ");
        }


    }
}
