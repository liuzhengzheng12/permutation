import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//字典序法生成全排列序列
public class LexOrderPermutation extends Permutation implements PermutationGeneration {
	//中介数
	private List<Integer> mediaNumber = new ArrayList<Integer>();
	//中介数长度
	private Integer med_len;
	
	//得到字典序对应的递增进位制中介数
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
	}
	
	//中介数转化为排列
	private void convertToPermutation() {
		Map<Integer, Boolean> m = new HashMap<Integer, Boolean>();
		List<Integer> l = new ArrayList<Integer>();
		Integer value = null;

		for (int i = 0; i < per_len; i++) {
			permutation.set(i, "#");
			m.put(Integer.valueOf(i), false);
			l.add(i);
		}
		Collections.sort(l);
		
		for (int pos = 0; pos < per_len - 1; pos++) {
			int i = mediaNumber.get(med_len - 1 - pos);
			int bias = l.remove(i);
			value = 1 + bias;
			Collections.sort(l);
			m.put(bias, true);
			permutation.set(pos, value.toString());
		}
		for (Entry<Integer, Boolean>mapEntry: m.entrySet()) {
			if (mapEntry.getValue() == false) {
				Integer tmp = 1 + mapEntry.getKey();
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
	
	//生成给定排列按字典序之后的第pos个排列
	@Override
	public void genPermutation(int pos) {
		initPermutationFromInput();
		initmediaNumber();
		addMediaNumber(Util.convertToIncOrder(pos));
		convertToPermutation();
		printPermutation();
	}
	
	//从给定排列按字典序生成之后的所有排列
	@Override
	public void genPermutationAfter() {
		initPermutationFromInput();
		printPermutation();
		initmediaNumber();
		
		while(!End()) {
			addMediaNumber(Util.convertToIncOrder(1));
			convertToPermutation();
			printPermutation();
		}
	}
	
	//生成per_size大小的所有字典序排列
	@Override
	public void genAllPermutation(int per_size) {
		long start = System.currentTimeMillis();
		initPermutationFromSize(per_size);
		printPermutation();
		initmediaNumber();
		
		while(!End()) {
			addMediaNumber(Util.convertToIncOrder(1));
			convertToPermutation();
			//printPermutation();
		}
		long end = System.currentTimeMillis();
		System.out.println("time occupation: "+ (end-start) +" ms");	
	}
	
	//测试代码
	public static void main(String[] args) {
		PermutationGeneration lop = new LexOrderPermutation();
		lop.genAllPermutation(3);
	}
}
