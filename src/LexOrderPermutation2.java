import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//字典序法生成全排列序列
public class LexOrderPermutation2 implements PermutationGeneration {
	private List<String> permutation = new ArrayList<String>();
	
	
	private void initPermutation() {
		Scanner in = new Scanner(System.in);
		String[] str = in.nextLine().split("");
		for (String s: str) {
			permutation.add(s);
		}
		in.close();
	}
	
	
	private void exchange(int j, int k) {
		String tmp = permutation.get(j);
		permutation.set(j, permutation.get(k));
		permutation.set(k, tmp);
	}
	
	
	@Override
	public void genPermutation() {
		initPermutation();

		int len = permutation.size();
		int j = 0, k = 0;
		
		while(true) {
			for (j = len - 2; j >= 0; j--) {
				if (permutation.get(j).compareTo(permutation.get(j + 1)) < 0) {
					break;
				}
			}
			
			if (j == -1) {
				break;
			}
			
			String pj = permutation.get(j);
	
			for (k = len - 1; k > j; k--) {
				if (permutation.get(k).compareTo(pj) > 0) {
					break;
				}
			}
		
			exchange(j, k);
			for (int i = j + 1; i <= (len + j)/2; i++) {
				exchange(i, len + j - i);
			}
			
			for (String s: permutation) {
				System.out.print(s);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		PermutationGeneration lop = new LexOrderPermutation2();
		lop.genPermutation();
	}


	@Override
	public void genPermutation(int pos) {
		// TODO Auto-generated method stub
		
	}
}
