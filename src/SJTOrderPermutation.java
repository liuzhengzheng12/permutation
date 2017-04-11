import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//邻位对换法生成全排列序列
public class SJTOrderPermutation extends Permutation implements PermutationGeneration {
	//存储各元素的方向,true表示向左,false表示向右
	private Map<String, Boolean> direction  = new HashMap<String, Boolean>();
	//中介数
	private List<Integer> mediaNumber = new ArrayList<Integer>();
	//中介数长度
	private Integer med_len;
	
	//得到中介数和方向
	private void initmediaNumber() {
		med_len = per_len - 1;
		int index, cnt;
		String str = "2";
		index = permutation.indexOf(str);
		direction.put(str, true);
		
		cnt = 0;
		for (int j = index + 1; j < per_len; j++) {
			if (new Integer(str).compareTo(new Integer(permutation.get(j))) > 0) {
				cnt++;
			}
		}
		mediaNumber.add(cnt);
		
		for (int k = 2; k <= med_len; k++) {
			str = new Integer(1+k).toString();
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
					if (new Integer(str).compareTo(new Integer(permutation.get(j))) > 0) {
						cnt++;
					}
				}
			}
			//向左搜索
			else {
				for (int j = index - 1; j >= 0; j--) {
					if (new Integer(str).compareTo(new Integer(permutation.get(j))) > 0) {
						cnt++;
					}
				}
			}
			mediaNumber.add(cnt);
		}
		Util.reverse(mediaNumber);
	}
	
	//中介数转化为排列
	private void convertToPermutation() {
		Integer c;
		//重新计算中介数的方向
		for (int i = 0; i < med_len - 1; i++) {
			c = per_len - i;
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
			c = per_len-i;
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
				permutation.set(i, "1");
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
			int tmp = mediaNumber.get(i);
			mediaNumber.set(i, (mediaNumber.get(i) + adder + carry)%(per_len - i));
			carry = (tmp + adder + carry)/(per_len - i);
			}
	}
	
	//判断是否为最后一个中介数
	private boolean End() {
		String str = null;
		for (int i = 0; i < per_len; i++) {
			str = permutation.get(i);
			if (!str.equals("1")) {
				//方向向左
				if (direction.get(str)) {
					if (i > 0) {
						if (new Integer(permutation.get(i-1)).compareTo(new Integer(str)) < 0) {
							return false;
						}
					}
				}
				//方向向右
				else {
					if (i < per_len - 1) {
						if (new Integer(permutation.get(i+1)).compareTo(new Integer(str)) < 0) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
		
	//生成给定排列按邻位对换法之后的第pos个排列
	@Override
	public void genPermutation(int pos) {
		initPermutationFromInput();
		initmediaNumber();
		addMediaNumber(Util.convertToDecOrder(per_len, pos));
		convertToPermutation();
		printPermutation();
	}
	
	//从给定排列按邻位对换法生成之后的所有排列
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
	
	//生成per_size大小的所有邻位对换法排列
	@Override
	public void genAllPermutation(int per_size) {
		long start = System.currentTimeMillis();
		initPermutationFromSize(per_size);
		printPermutation();
		initmediaNumber();
		
		while(!End()) {
			addMediaNumber(Util.convertToDecOrder(per_len, 1));
			convertToPermutation();
			//printPermutation();
		}
		long end = System.currentTimeMillis();
		System.out.println("time occupation: "+ (end-start) +" ms");
	}
	
	//测试代码
	public static void main(String[] args) {
		PermutationGeneration sop = new SJTOrderPermutation();
		sop.genAllPermutation(3);
	}
}
