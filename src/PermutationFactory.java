
public class PermutationFactory {
	public PermutationGeneration getPermutation(Integer method) {
		switch(method) {
			case 1:
				System.out.println("You select LexOrderPermutation.");
				return new LexOrderPermutation();
			case 2:
				System.out.println("You select IncOrderPermutation.");
				return new IncOrderPermutation();
			case 3:
				System.out.println("You select DecOrderPermutation.");
				return new DecOrderPermutation();
			case 4:
				System.out.println("You select SJTOrderPermutation.");
				return new SJTOrderPermutation();
			default:
				return null;
		}
	}
}
