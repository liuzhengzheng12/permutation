import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//递增进位制法生成全排列序列
public class IncOrderPermutation implements PermutationGeneration {
	//排列
	private List<String> permutation = new ArrayList<String>();
	//中介数
	private List<Integer> mediaNumber = new ArrayList<Integer>();
	//字典序最小的输入字符
	private Character min;
	//排列长度
	private Integer per_len;
	//中介数长度
	private Integer med_len;

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
		min = m.charAt(0);
		in.close();
		//System.out.println("initial permutation: " + permutation);
		//System.out.println("min character is: " + min);
	}
	
	//得到中介数
	private void initmediaNumber() {
		med_len = per_len - 1;
		int index, cnt;
		String str;
		for (Character i = (char)((int)min + 1); i <= (char)((int)min + per_len - 1); i++) {
			str = i.toString();
			index = permutation.indexOf(str);
			cnt = 0;
			for (int j = index + 1; j < per_len; j++) {
				if (str.compareTo(permutation.get(j)) > 0) {
					cnt++;
				}
			}
			mediaNumber.add(cnt);
		}
		//System.out.println("Media Number : " + mediaNumber);
	}
	
	//a的阶乘
	private Integer genFactorial(Integer a) {
		if (a == 2) {
			return 2;
		}
		else if (a == 1) {
			return 1;
		}
		else if (a == 0) {
			return 1;
		}
		else {
			return a * genFactorial(a - 1);
		}
	}
	
	//转置列表
	private void reverse(List<Integer> l) {
		int len = l.size();
		
		for (int i = 0; i < len / 2; i++) {
			int j = len - 1 - i;
			int tmp = l.get(j);
			l.set(j, l.get(i));
			l.set(i, tmp);
		}
	}
	
	//十进制数a转化为递增进位制数
	private List<Integer> convertToIncOrder(Integer a) {
		List<Integer> l = new ArrayList<Integer>();
		int i;
		for (i = 1;;i++) {
			if (a < genFactorial(i)) {
				break;
			}
		}
		i--;
		
		boolean modIsZero = false;
		for (int j = i; j >= 1; j--) {
			if (modIsZero) {
				l.add(0);
			}
			else {
				int tmp = genFactorial(j);
				l.add(a / tmp);
				if ((a = a % tmp) == 0) {
					modIsZero = true;
				}
			}
		}
		reverse(l);
		
		//System.out.println(l);
		
		return l;
	}
	
	//中介数转化为排列
	private void convertToPermutation() {
		for (int i = 0; i < per_len; i++) {
			permutation.set(i, "#");
		}
		int pos = 0;
		for (int i = med_len - 1; i >= 0; i--) {
			pos = mediaNumber.get(i) + 1;
			for (int j = per_len - 1; j >= per_len - pos; j--) {
				if (permutation.get(j) != "#") {
					pos++;
				}
			}
			//System.out.println("pos: "+pos);
			Character value = (char)((int)min + i + 1);
			permutation.set(per_len - pos, value.toString());
			//System.out.println(per_len - pos+ " " + value.toString());
		}
		for (int i = 0; i < per_len; i++) {
			if (permutation.get(i) == "#") {
				permutation.set(i, ((Character)min).toString());
			}
		}
	}
	
	//计算中介数加上a之后的递增进位制数表示
	private void addMediaNumber(List<Integer> a) {
		int a_len = a.size();
		int carry = 0;
		int adder = 0;
		for (int i = 0; i < med_len; i++) {
			if (i < a_len) {
				adder = a.get(i);
			}
			else {
				adder = 0;
			}
			//System.out.println(mediaNumber.get(i)+" "+adder+" "+carry);
			int tmp = mediaNumber.get(i);
			mediaNumber.set(i, (mediaNumber.get(i) + adder + carry)%(i + 2));
			carry = (tmp + adder + carry)/(i + 2);
			//System.out.println(mediaNumber.get(i));
			}
		//System.out.println("after add: media number : " + mediaNumber);
	}
	
	//判断是否为最后一个中介数
	private boolean End() {
		for (int i = 0; i < med_len; i++) {
			if (mediaNumber.get(i) != (i + 1)) {
				return false;
			}
		}
		return true;
	}
		
	//生成给定排列之后的第pos个排列
	@Override
	public void genPermutation(int pos) {
		initPermutation();
		initmediaNumber();
		addMediaNumber(convertToIncOrder(pos));
		convertToPermutation();
		
		for (String s: permutation) {
			System.out.print(s);
		}
		System.out.println();
	}
	
	//从给定排列生成之后的所有排列
	@Override
	public void genPermutation() {
		initPermutation();
		initmediaNumber();
		
		while(!End()) {
			addMediaNumber(convertToIncOrder(1));
			convertToPermutation();
			for (String s: permutation) {
				System.out.print(s);
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		PermutationGeneration iop = new IncOrderPermutation();
		iop.genPermutation();
	}
}
