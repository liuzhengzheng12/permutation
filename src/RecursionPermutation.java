public class RecursionPermutation extends Permutation implements PermutationGeneration{
	//swap
	private void swap(int i1, int i2){
		String temp = permutation.get(i2);
		permutation.set(i2, permutation.get(i1));
		permutation.set(i1, temp);
	}
	
	//permutation recursion
	public void perm(int beg, int end){
		if (beg == end){
			printPermutation();
			return;
		}
		else{
			for (int i = beg; i <= end; i++){
				swap(beg, i);
				perm(beg + 1, end);
				swap(beg, i);
			}
		}
	}
	
	//生成给定排列按递归法之后的第pos个排列
	@Override
	public void genPermutation(int pos) {
		//do nothing
	}
	
	//从给定排列按递归法生成之后的所有排列
	@Override
	public void genPermutation() {
		initPermutation();
		perm(0, per_len - 1);
	}	
	
	//生成per_size大小的所有递归排列
	@Override
	public void genAllPermutation(int per_size) {
		initPermutation(per_size);
		perm(0, per_len - 1);
	}
	
	//测试代码
	public static void main(String[] args) {
		PermutationGeneration rp = new RecursionPermutation();
		rp.genAllPermutation(10);
	}
}
