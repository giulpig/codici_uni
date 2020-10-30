import java.util.Scanner;

public class EraPrimeTester{

	public static void main(String[] args){

		Scanner in = new Scanner(System.in);
		System.out.print("Inserisci n e io stampero' i numeri primi nell'intervallo [1, n]: ");
		final int n = in.nextInt();

		boolean[] memo = new boolean[n+1];

		for(int i=2; i*i<=n+1; i+=2){
			if(memo[i] == false){
				for(int j=i+i; j<n+1; j+=i){
					memo[j] = true;
				}
			}
			if(i==2) i--;
		}

		System.out.println("Numeri primi trovati: ");

		for(int i=2; i<n; i++){
			if(memo[i] == false){
				System.out.print(" " + i);
			}
		}

		System.out.println();
		in.close();

	}


}