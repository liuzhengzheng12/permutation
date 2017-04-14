import java.util.List;
import java.util.concurrent.CountDownLatch;

public class IncSpeedup extends IncOrderPermutation implements Runnable {
	private List<Integer> step;
	private CountDownLatch latch; 
	
	public IncSpeedup(int per_size, int index, int num, CountDownLatch latch) {
		initPermutationFromSize(per_size);
		initmediaNumber();
		addMediaNumber(Util.convertToDecOrder(per_len, index));
		this.step = Util.convertToDecOrder(per_len, num);
		this.latch = latch;
	}
	
	@Override
	public void run() {
		while(!last) {
			convertToPermutation();
			//printPermutation();
			addMediaNumber(step);
		}
		latch.countDown(); 
	}
	
	//测试代码
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		//采用线程数
		int threads = 4;
		//采用排列大小
		int per_size = 12;
		final CountDownLatch latch = new CountDownLatch(threads);  
		Thread t[] = new Thread[threads];
		
		for (int i = 0; i < threads; i++) {
			t[i] = new Thread(new IncSpeedup(per_size, i, threads, latch));
			t[i].start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
		}
		long end = System.currentTimeMillis();
		System.out.println("time occupation: "+ (end-start) +" ms");
	}
}
