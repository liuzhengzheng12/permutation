import java.util.ArrayList;
import java.util.List;

//递减进位制法生成全排列序列
public class DecOrderPermutation extends Permutation implements PermutationGeneration {
	//中介数
	protected List<Integer> mediaNumber = new ArrayList<Integer>();
	//中介数长度
	private Integer med_len;

	//得到中介数
	protected void initmediaNumber() {
		med_len = per_len - 1;
		int index, cnt;
		String str;
		for (Integer i = 2; i <=  per_len; i++) {
			str = i.toString();
			index = permutation.indexOf(str);
			cnt = 0;
			for (int j = index + 1; j < per_len; j++) {
				if (i.compareTo(new Integer(permutation.get(j))) > 0) {
					cnt++;
				}
			}
			mediaNumber.add(cnt);
		}
		Util.reverse(mediaNumber);
	}
	
	
	//中介数转化为排列
	protected void convertToPermutation() {
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
			Integer value = per_len - i;
			permutation.set(per_len - pos, value.toString());
		}
		for (int i = 0; i < per_len; i++) {
			if (permutation.get(i) == "#") {
				permutation.set(i, "1");
			}
		}
	}
	
	//计算中介数加上a之后的递减进位制数表示
	protected void addMediaNumber(List<Integer> a) {
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
			int tmp = mediaNumber.get(i);
			mediaNumber.set(i, (mediaNumber.get(i) + adder + carry)%(per_len - i));
			carry = (tmp + adder + carry)/(per_len - i);
		}
		if (carry != 0) {
			last = true;
		}
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
		
	//生成给定排列按递减进位制法之后的第pos个排列
	@Override
	public void genPermutation(int pos) {
		initPermutationFromInput();
		initmediaNumber();
		addMediaNumber(Util.convertToDecOrder(per_len, pos));
		convertToPermutation();
		printPermutation();
	}
	
	//从给定排列按递减进位制法生成之后的所有排列
	@Override
	public void genPermutationAfter() {
		initPermutationFromInput();
		printPermutation();
		initmediaNumber();
		
		while(!End()) {
			addMediaNumber(Util.convertToDecOrder(per_len, 1));
			convertToPermutation();
			printPermutation();
		}
	}
	
	//生成per_size大小的所有递减进位制法排列
	@Override
	public void genAllPermutation(int per_size) {
		long start = System.currentTimeMillis();
		initPermutationFromSize(per_size);
		printPermutation();
		initmediaNumber();
		
		while(!End()) {
			addMediaNumber(Util.convertToDecOrder(per_len, 1));
			convertToPermutation();
			printPermutation();
		}
		long end = System.currentTimeMillis();
		System.out.println("time occupation: "+ (end-start) +" ms");
	}
	
	//测试代码
	public static void main(String[] args) {
		PermutationGeneration dop = new DecOrderPermutation();
		dop.genAllPermutation(4);
	}

}
