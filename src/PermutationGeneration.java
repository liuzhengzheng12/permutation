
public interface PermutationGeneration {
	//根据给定的初始序列按某种顺序生成之后的所有排列
	void genPermutationAfter();
	//根据给定的初始序列按某种顺序生成之后的第pos个排列
	void genPermutation(int pos);
	//按照某种顺序生成per_size大小的所有排列
	void genAllPermutation(int per_size);
}
