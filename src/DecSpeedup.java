import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class DecSpeedup extends DecOrderPermutation implements Runnable {
	private List<Integer> step;
	private CountDownLatch latch; 
	
	public DecSpeedup(int per_size, int index, int num, CountDownLatch latch) {
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
		//采用线程数
		int threads;
		//采用排列大小
		int per_size;
		
		Scanner in = new Scanner(System.in);
		System.out.println("Please input the threads you want to use:");
		threads = in.nextInt();
		System.out.println("Please input the per_size:");
		per_size = in.nextInt();
		
		final CountDownLatch latch = new CountDownLatch(threads);  
		Thread t[] = new Thread[threads];
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < threads; i++) {
			t[i] = new Thread(new DecSpeedup(per_size, i, threads, latch));
			t[i].start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
		}
		long end = System.currentTimeMillis();
		System.out.println("time occupation: "+ (end-start) +" ms");
		in.close();
	}
}
