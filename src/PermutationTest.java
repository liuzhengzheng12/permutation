import java.util.Scanner;

public class PermutationTest {
	public static void main(String[] args) {
		int method;
		PermutationFactory factory = new PermutationFactory();
		String info = "Please select which method to generate permutation:\n" +
				"LexOrder:Please input 1.\n" +
				"IncOrder:Please input 2.\n" +
				"DecOrder:Please input 3.\n" +
				"SJTOrder:Please input 4.\n" +
				"Recursion:Please input 5.\n" + 
				"BackTracking:Please input 6.\n";;
		System.out.print(info);
		System.out.print("Input number:");
		Scanner input = new Scanner(System.in);
		method = Integer.valueOf(input.nextLine());
		
		PermutationGeneration pg = factory.getPermutation(method);
		pg.genPermutation();
		
		input.close();
	}
}