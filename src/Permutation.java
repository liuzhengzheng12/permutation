import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Permutation {
	//排列
	protected List<String> permutation = new ArrayList<String>();
	//排列长度
	protected Integer per_len;
	
	//读入排列长度，初始化排列
	protected void initPermutationFromSize(int per_size) {
		per_len = per_size;
		
		for (int i = 0; i < per_size; i++) {
			permutation.add(new Integer(1+i).toString());
		}
	}
	
	//初始化，读入初始排列
	protected void initPermutationFromInput() {
		Scanner in = new Scanner(System.in);
		String[] str = in.nextLine().split(" ");

		for (String s: str) {
			permutation.add(s);
		}
		
		per_len = permutation.size();
		in.close();
	}
	
	//输出排列
	protected void printPermutation() {
		for (String s: permutation) {
			System.out.print(s+" ");
		}
		System.out.println();
	}
	
	//输出排列
	protected void printResultPermutation(List<String> result) {
		for (String s: result) {
			System.out.print(s+" ");
		}
		System.out.println();
	}
}
