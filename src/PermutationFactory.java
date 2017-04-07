
public class PermutationFactory {
	public PermutationGeneration getPermutation(Integer method) {
		switch(method) {
			case 1:
				System.out.println("You select LexOrderPermutation.");
				System.out.println("Please input the sequence that you want to permutate(e.g. '1234'):");
				return new LexOrderPermutation();
			case 2:
				System.out.println("You select IncOrderPermutation.");
				System.out.println("Please input the sequence that you want to permutate(e.g. '1234'):");
				return new IncOrderPermutation();
			case 3:
				System.out.println("You select DecOrderPermutation.");
				System.out.println("Please input the sequence that you want to permutate(e.g. '1234'):");
				return new DecOrderPermutation();
			case 4:
				System.out.println("You select SJTOrderPermutation.");
				System.out.println("Please input the sequence that you want to permutate(e.g. '1234'):");
				return new SJTOrderPermutation();
			case 5:
				System.out.println("You select RecursionPermutation.");
				System.out.println("Please input the sequence that you want to permutate(e.g. '1234'):");
				return new RecursionPermutation();
			case 6:
				System.out.println("You select BackTrackingPermutation.");
				System.out.println("Please input the sequence that you want to permutate(e.g. '1234'):");
				return new BackTrackingPermutation();
			default:
				return null;
		}
	}
}
