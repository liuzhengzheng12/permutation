import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BackTrackingPermutation implements PermutationGeneration{
	//排列
	private List<String> permutation = new ArrayList<String>();
	//record used or not
	private List<Boolean> used = new ArrayList<Boolean>();
	//record the middle result
	private List<String> result = new ArrayList<String>();
	//排列长度
	private Integer per_len;
	
	//初始化，读入排列长度
	public void initPermutation(int per_size) {
		char m = '1';
		Character c;
		per_len = per_size;
			
		for (int i = 0; i < per_size; i++) {
			c = (char)((int)m+i);
			permutation.add(c.toString());
			result.add(c.toString());
		}
		
		for (int i = 0; i < per_len; i ++){
			used.add(false);
		}
	}
	
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
			result.add(s);
		}
		per_len = permutation.size();
		in.close();
		for (int i = 0; i < per_len; i ++){
			used.add(false);
		}
		//System.out.println("initial permutation: " + permutation);
		//System.out.println("min character is: " + min);
	}
		
	//permutation backtracking
	public void backtracking(int k){
		if ( k == per_len){
			for (String s: result) {
				System.out.print(s);
			}
			System.out.println();
			return;
		}
		else{
			for (int i = 0; i < per_len; i++){
				if (!used.get(i)){
					used.set(i, true);
					result.set(k, permutation.get(i));
					backtracking(k + 1);
					used.set(i, false);
				}
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
		backtracking(0);
	}	
	
	@Override
	public void genAllPermutation(int per_size) {
		initPermutation(per_size);
		backtracking(0);
	}
	
	
	public static void main(String[] args) {
		PermutationGeneration btp = new BackTrackingPermutation();
		btp.genAllPermutation(4);
	}

}
