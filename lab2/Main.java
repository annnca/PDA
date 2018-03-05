package lab2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	public static Queue<Integer> queue;
	public final static Lock mtx = new ReentrantLock(true);
	public final static int maxDim = 4;
	
	public static Semaphore semFree = new Semaphore(maxDim);
	public static Semaphore semFull = new Semaphore(0);
	
	public static void main(String[] args) throws InterruptedException {
		
        queue = new LinkedList<Integer>();
        
        Thread p = new Thread(new Prod(queue, semFull, semFree));
        Thread c = new Thread(new Cons(queue, semFull, semFree));
        
        p.start();
        c.start();
        
        p.join();
        c.join();
    }
}

