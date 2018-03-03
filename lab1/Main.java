package l1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	public static Queue<Integer> queue;
	public final static Lock mtx = new ReentrantLock(true);
	public static int maxDim;
	
	public static void main(String[] args) throws InterruptedException {
		queue = new LinkedList<Integer>();
		maxDim = 5;
		
		Thread p = new Prod(queue);
		Thread c = new Cons(queue);
		
		p.start();
		c.start();
		
		p.join();
		c.join();
	}

}
