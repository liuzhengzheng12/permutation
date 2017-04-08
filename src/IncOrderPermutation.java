import java.util.ArrayList;
import java.util.List;

//递增进位制法生成全排列序列
public class IncOrderPermutation extends Permutation implements PermutationGeneration {
	//中介数
	private List<Integer> mediaNumber = new ArrayList<Integer>();
	//中介数长度
	private Integer med_len;
	
	//得到递增进位制中介数
	private void initmediaNumber() {
		med_len = per_len - 1;
		int index, cnt;
		char min = '1';
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
			Integer value = 1 + i + 1;
			permutation.set(per_len - pos, value.toString());
		}
		for (int i = 0; i < per_len; i++) {
			if (permutation.get(i) == "#") {
				permutation.set(i, "1");
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
			int tmp = mediaNumber.get(i);
			mediaNumber.set(i, (mediaNumber.get(i) + adder + carry)%(i + 2));
			carry = (tmp + adder + carry)/(i + 2);
			}
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
		
	//生成给定排列按递增进位制法之后的第pos个排列
	@Override
	public void genPermutation(int pos) {
		initPermutation();
		initmediaNumber();
		addMediaNumber(Util.convertToIncOrder(pos));
		convertToPermutation();
		printPermutation();
	}
	
	//从给定排列按递增进位制法生成之后的所有排列
	@Override
	public void genPermutation() {
		initPermutation();
		printPermutation();
		initmediaNumber();
		
		while(!End()) {
			addMediaNumber(Util.convertToIncOrder(1));
			convertToPermutation();
			printPermutation();
		}
	}
	
	//生成per_size大小的所有递增进位制法排列
	@Override
	public void genAllPermutation(int per_size) {
		initPermutation(per_size);
		printPermutation();
		initmediaNumber();
		
		while(!End()) {
			addMediaNumber(Util.convertToIncOrder(1));
			convertToPermutation();
			printPermutation();
		}
	}
	
	//测试代码
	public static void main(String[] args) {
		PermutationGeneration iop = new IncOrderPermutation();
		iop.genAllPermutation(3);
	}
}
