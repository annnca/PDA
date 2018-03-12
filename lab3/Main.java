package lab3;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static Queue<Integer> queue;
	/*public final static Lock mtx = new ReentrantLock(true);*/
	public final static int maxDim = 4;
	public static Object condCons;
	public static Object condProd;
	
	public static void main(String[] args) throws InterruptedException {
		
        queue = new LinkedList<Integer>();
        
        Thread p = new Thread(new Prod(queue, condCons, condProd));
        Thread c = new Thread(new Cons(queue, condCons,condProd));
        
        p.start();
        c.start();
        
        p.join();
        c.join();
    }
}

