import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//递减进位制法生成全排列序列
public class DecOrderPermutation implements PermutationGeneration {
	//输入
	private List<String> input = new ArrayList<String>();
	//排列
	private List<String> permutation = new ArrayList<String>();
	//映射
	private Map<String, Integer> map = new HashMap<String, Integer>();
	//中介数
	private List<Integer> mediaNumber = new ArrayList<Integer>();
	//字典序最小的输入字符
	private Character min;
	//排列长度
	private Integer per_len;
	//中介数长度
	private Integer med_len;

	//初始化，读入排列长度
	public void initPermutation(int per_size) {
		min = '1';
		Character c;
		per_len = per_size;
			
		for (int i = 0; i < per_size; i++) {
			c = (char)((int)min+i);
			permutation.add(c.toString());
			map.put(c.toString(), 0);
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
			input.add(s);
			permutation.add(s);
		}
		input.sort(new StringComparator());
		min = m.charAt(0);
		per_len = input.size();
		Character c;
		
		for (int i = 0; i < per_len; i++) {
			c = (char)((int)min+i);
			int pos = permutation.indexOf(input.get(i));
			permutation.set(pos,  c.toString());
			map.put(c.toString(), input.get(i).charAt(0)-c);
		}
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
		reverse(mediaNumber);
		//System.out.println("Media Number : " + mediaNumber);
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
	
	//十进制数a转化为递减进位制数
	private List<Integer> convertToDecOrder(Integer a) {
		List<Integer> l = new ArrayList<Integer>();
		
		for (int i = per_len;;i--) {
			l.add(a % i);
			if ((a = a / i) == 0) {
				break;
			}
		}
		
		//System.out.println(l);
		
		return l;
	}
	
	//中介数转化为排列
	private void convertToPermutation() {
		for (int i = 0; i < per_len; i++) {
			permutation.set(i, "#");
		}
		int pos = 0;
		for (int i = 0; i <= med_len - 1; i++) {
			pos = mediaNumber.get(i) + 1;
			for (int j = per_len - 1; j >= per_len - pos; j--) {
				if (permutation.get(j) != "#") {
					pos++;
				}
			}
			//System.out.println("pos: "+pos);
			Character value = (char)((int)min + per_len - i - 1);
			permutation.set(per_len - pos, value.toString());
			//System.out.println(per_len - pos+ " " + value.toString());
		}
		for (int i = 0; i < per_len; i++) {
			if (permutation.get(i) == "#") {
				permutation.set(i, ((Character)min).toString());
			}
		}
	}
	
	//计算中介数加上a之后的递减进位制数表示
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
			mediaNumber.set(i, (mediaNumber.get(i) + adder + carry)%(per_len - i));
			carry = (tmp + adder + carry)/(per_len - i);
			//System.out.println(mediaNumber.get(i));
			}
		//System.out.println("after add: media number : " + mediaNumber);
	}
	
	//判断是否为最后一个中介数
	private boolean End() {
		for (int i = 0; i < med_len; i++) {
			if (mediaNumber.get(i) != (per_len - i - 1)) {
				return false;
			}
		}
		return true;
	}
		
	//生成给定排列之后的第pos个排列
	@Override
	public void genPermutation(int pos) {
		initPermutation();
		printPermutation();
		initmediaNumber();
		addMediaNumber(convertToDecOrder(pos));
		convertToPermutation();
		printPermutation();
	}
	
	//从给定排列生成之后的所有排列
	@Override
	public void genPermutation() {
		initPermutation();
		printPermutation();
		initmediaNumber();
		
		while(!End()) {
			addMediaNumber(convertToDecOrder(1));
			convertToPermutation();
			printPermutation();
		}
	}
	
	@Override
	public void genAllPermutation(int per_size) {
		initPermutation(per_size);
		printPermutation();
		initmediaNumber();
		
		while(!End()) {
			addMediaNumber(convertToDecOrder(1));
			convertToPermutation();
			printPermutation();
		}
	}
	
	//输出排列
	private void printPermutation() {
		Character c;
		for (String s: permutation) {
			c = (char)((int)s.charAt(0)+map.get(s));
			System.out.print(c.toString());
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		PermutationGeneration dop = new DecOrderPermutation();
		dop.genAllPermutation(4);
	}

}
