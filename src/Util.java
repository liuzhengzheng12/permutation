import java.util.ArrayList;
import java.util.List;

public class Util {
	//a的阶乘
	protected static Integer genFactorial(Integer a) {
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
	protected static void reverse(List<Integer> l) {
		int len = l.size();
		
		for (int i = 0; i < len / 2; i++) {
			int j = len - 1 - i;
			int tmp = l.get(j);
			l.set(j, l.get(i));
			l.set(i, tmp);
		}
	}
	
	//十进制数a转化为递增进位制数
	protected static List<Integer> convertToIncOrder(Integer a) {
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
		
		return l;
	}
	
	//十进制数a转化为递减进位制数
	protected static List<Integer> convertToDecOrder(int per_len, Integer a) {
		List<Integer> l = new ArrayList<Integer>();
		if (a == 0) {
			l.add(a);
			return l;
		}
		
		for (int i = per_len;;i--) {
			l.add(a % i);
			if ((a = a / i) == 0) {
				break;
			}
		}
		return l;
	}
}
