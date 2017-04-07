import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

//字典序法生成全排列序列
public class LexOrderPermutation implements PermutationGeneration {
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
//		System.out.println("initial permutation: " + permutation);
//		System.out.println("min character is: " + min);
//		System.out.println("per_len: " + per_len);
	}
	
	//得到中介数
	private void initmediaNumber() {
		int cnt;
		med_len = per_len - 1;
		for (int i = per_len - 2; i >= 0; i--) {
			cnt = 0;
			for (int j = i + 1; j < per_len; j++) {
				if (permutation.get(i).compareTo(permutation.get(j)) > 0) {
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
		Map<Integer, Boolean> m = new HashMap<Integer, Boolean>();
		List<Integer> l = new ArrayList<Integer>();
		Character value = null;

		for (int i = 0; i < per_len; i++) {
			permutation.set(i, "#");
			m.put(Integer.valueOf(i), false);
			l.add(i);
		}
		Collections.sort(l);
		//System.out.println("list:"+l);
		
		for (int pos = 0; pos < per_len - 1; pos++) {
			int i = mediaNumber.get(med_len - 1 - pos);
			int bias = l.remove(i);
			value = (char)((int)min + bias);
			Collections.sort(l);
			//System.out.println("list:"+l);
			
			//System.out.println("pos: "+pos);
			m.put(bias, true);
			permutation.set(pos, value.toString());
			//System.out.println(pos+ " " + value.toString());
		}
		for (Entry<Integer, Boolean>mapEntry: m.entrySet()) {
			if (mapEntry.getValue() == false) {
				Character tmp = (char)((int)min + mapEntry.getKey());
				permutation.set(per_len - 1, tmp.toString());
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
	
	//从给定排列按字典序生成之后的所有排列
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
	
	
/*	public static void main(String[] args) {
		PermutationGeneration lop = new LexOrderPermutation();
		lop.genPermutation();
	}*/
}
