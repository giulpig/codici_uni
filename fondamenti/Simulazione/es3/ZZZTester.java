public class ZZZTester {
    public static void main(String[] args) {
        SMyArrayList<Integer> lista = new SMyArrayList<Integer>();

        for(int i=0; i<5; i++){
            lista.add(Integer.valueOf(i));
        }

        System.out.println(lista.min());
    }
}
