import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//邻位对换法生成全排列序列
public class SJTOrderPermutation implements PermutationGeneration {
	//排列
	private List<String> permutation = new ArrayList<String>();
	//存储各元素的方向,true表示向左,false表示向右
	private Map<String, Boolean> direction  = new HashMap<String, Boolean>();
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
	
	//得到中介数和方向
	private void initmediaNumber() {
		med_len = per_len - 1;
		int index, cnt;
		String str;
		Character i = (char)((int)min + 1);
		str = i.toString();
		index = permutation.indexOf(str);
		direction.put(str, true);
		
		cnt = 0;
		for (int j = index + 1; j < per_len; j++) {
			if (str.compareTo(permutation.get(j)) > 0) {
				cnt++;
			}
		}
		mediaNumber.add(cnt);
		
		for (int k = 2; k <= med_len; k++) {
			i = (char)((int)min + k);
			str = i.toString();
			index = permutation.indexOf(str);
			
			//为奇数的情况
			if (k % 2 == 0) {
				//奇向右
				if (mediaNumber.get(k - 2) % 2 == 1) {
					direction.put(str, false);
				}
				//偶向左
				else {
					direction.put(str, true);
				}
			}
			//为偶数的情况
			else {
				//奇向右
				if ((mediaNumber.get(k - 2) + mediaNumber.get(k - 3)) % 2 == 1) {
					direction.put(str, false);
				}
				//偶向左
				else {
					direction.put(str, true);
				}
			}
			
			cnt = 0;
			//向右搜索
			if (direction.get(str)) {
				for (int j = index + 1; j < per_len; j++) {
					if (str.compareTo(permutation.get(j)) > 0) {
						cnt++;
					}
				}
			}
			//向左搜索
			else {
				for (int j = index - 1; j >= 0; j--) {
					if (str.compareTo(permutation.get(j)) > 0) {
						cnt++;
					}
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
		Character c;
		//重新计算中介数的方向
		for (int i = 0; i < med_len - 1; i++) {
			c = (char)((int)min + per_len - i - 1);
			//若为奇数
			if ((per_len - i) % 2 == 1) {
				//奇向右
				if (mediaNumber.get(i + 1) % 2 == 1) {
					direction.replace(c.toString(), false);
				}
				//偶向左
				else {
					direction.replace(c.toString(), true);
				}
			}
			//若为偶数
			else {
				//奇向右
				if ((mediaNumber.get(i + 1) + mediaNumber.get(i + 2)) % 2 == 1) {
					direction.replace(c.toString(), false);
				}
				//偶向左
				else {
					direction.replace(c.toString(), true);
				}
			}
		}
		
		for (int i = 0; i < per_len; i++) {
			permutation.set(i, "#");
		}
		for (int i = 0; i <= med_len - 1; i++) {
			int cnt = mediaNumber.get(i) + 1;
			int j;
			c = (char)((int)min+per_len-i-1);
			//true表示向左
			if (direction.get(c.toString())) {
				for (j = per_len - 1; j >= 0; j--) {
					if (permutation.get(j) == "#") {
						cnt--;
						if (cnt == 0) {
							break;
						}
					}
				}
			}
			//false表示向右
			else {
				for (j = 0; j < per_len; j++) {
					if (permutation.get(j) == "#") {
						cnt--;
						if (cnt == 0) {
							break;
						}
					}
				}
			}
			permutation.set(j, c.toString());
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
		String str = null;
		//Character c = (char)((int)min + 1);
		for (int i = 0; i < per_len; i++) {
			str = permutation.get(i);
			if (!str.equals(min.toString())) {
				//System.out.println(str);
				//方向向左
				if (direction.get(str)) {
					if (i > 0) {
						if (permutation.get(i-1).compareTo(str) < 0) {
							return false;
						}
					}
				}
				//方向向右
				else {
					if (i < per_len - 1) {
						if (permutation.get(i+1).compareTo(str) < 0) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
		
	//生成给定排列之后的第pos个排列
	@Override
	public void genPermutation(int pos) {
		initPermutation();
		initmediaNumber();
		addMediaNumber(convertToDecOrder(pos));
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
			addMediaNumber(convertToDecOrder(1));
			convertToPermutation();
			for (String s: permutation) {
				System.out.print(s);
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		PermutationGeneration sop = new SJTOrderPermutation();
		sop.genPermutation();
	}
}
