import java.util.ArrayList;
import java.util.List;


public class BackTrackingPermutation extends Permutation implements PermutationGeneration{
	//record used or not
	private List<Boolean> used = new ArrayList<Boolean>();
	//record the middle result
	private List<String> result = new ArrayList<String>();
	
	//初始化used和result
	public void init() {
		result.addAll(permutation);
		for (int i = 0; i < per_len; i ++){
			used.add(false);
		}
	}
		
	//permutation backtracking
	public void backtracking(int k){
		if ( k == per_len){
			//printResultPermutation(result);
			return;
		}
		else{
			for (int i = 0; i < per_len; i++){
				if (!used.get(i)){
					used.set(i, true);
					result.set(k, permutation.get(i));
					backtracking(k + 1);
					used.set(i, false);
				}
			}
		}
	}
	
	//生成给定排列按回溯法之后的第pos个排列
	@Override
	public void genPermutation(int pos) {
		//do nothing
	}
	
	//从给定排列按回溯法生成之后的所有排列
	@Override
	public void genPermutationAfter() {
		initPermutationFromInput();
		init();
		backtracking(0);
	}	
	
	//生成per_size大小的所有回溯排列
	@Override
	public void genAllPermutation(int per_size) {
		long start = System.currentTimeMillis();
		initPermutationFromSize(per_size);
		init();
		backtracking(0);
		long end = System.currentTimeMillis();
		System.out.println("time occupation: "+ (end-start) +" ms");
	}
	
	//测试代码
	public static void main(String[] args) {
		PermutationGeneration btp = new BackTrackingPermutation();
		btp.genAllPermutation(4);
	}
}
