import java.util.Scanner;

public class PermutationTest {
	public static void main(String[] args) {
		int method;
		PermutationFactory factory = new PermutationFactory();
		String info = "Please select which method to generate permutation.\nLexOrder:Please input 1.\nIncOrder:Please input 2.\nDecOrder:Please input 3.\nSJTOrder:Please input 4.\n";
		System.out.print(info);
		Scanner input = new Scanner(System.in);
		method = Integer.valueOf(input.nextLine());
		
		PermutationGeneration pg = factory.getPermutation(method);
		pg.genPermutation();
		
		input.close();
	}
}
