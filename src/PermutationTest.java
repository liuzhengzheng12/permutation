import java.util.Scanner;

public class PermutationTest {
	public static void main(String[] args) {
		int method;
		int per_size;

		PermutationFactory factory = new PermutationFactory();
		String info = "Please select which method to generate permutation:\n" +
				"LexOrder:Please input 1.\n" +
				"IncOrder:Please input 2.\n" +
				"DecOrder:Please input 3.\n" +
				"SJTOrder:Please input 4.\n" +
				"Recursion:Please input 5.\n" + 
				"BackTracking:Please input 6.\n";;
		System.out.println(info);
		System.out.println("Input number:");
		Scanner input = new Scanner(System.in);
		method = Integer.valueOf(input.nextLine());
		
		PermutationGeneration pg = factory.getPermutation(method);
		System.out.println("Please input the size of permutation you want to generate:");
		per_size = Integer.valueOf(input.nextLine());
		pg.genAllPermutation(per_size);
		
		input.close();
	}
}