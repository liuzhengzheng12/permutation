import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class RecursionPermutation implements PermutationGeneration{
	//排列
	private List<String> permutation = new ArrayList<String>();
	//排列长度
	private Integer per_len;
	
	//初始化，读入初始排列
	private void initPermutation() {
		Scanner in = new Scanner(System.in);
		String[] str = in.nextLine().split("");
		String m = str[0];

		for (String s: str) {
			if (m.compareTo(s) > 0) {
				m = s;
			}
			permutation.add(s);
		}
		per_len = permutation.size();
		in.close();
		//System.out.println("initial permutation: " + permutation);
		//System.out.println("min character is: " + min);
	}
	
	//swap
	private void swap(int i1, int i2){
		String temp = permutation.get(i2);
		permutation.set(i2, permutation.get(i1));
		permutation.set(i1, temp);
	}
	
	//permutation recursion
	public void perm(int beg, int end){
		if (beg == end){
			for (String s: permutation) {
				System.out.print(s);
			}
			System.out.println();
			return;
		}
		else{
			for (int i = beg; i <= end; i++){
				swap(beg, i);
				perm(beg + 1, end);
				swap(beg, i);
			}
		}
	}
	
	//生成给定排列之后的第pos个排列
	@Override
	public void genPermutation(int pos) {
		//do nothing
	}
	
	//从给定排列按字典序生成之后的所有排列
	@Override
	public void genPermutation() {
		initPermutation();
		perm(0, per_len - 1);
	}	

	public static void main(String[] args) {
		PermutationGeneration sop = new RecursionPermutation();
		sop.genPermutation();
	}
}
