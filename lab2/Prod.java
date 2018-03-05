
package lab2;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Prod extends Thread{
	private Queue<Integer> q;
	private Semaphore sFull;
	private Semaphore sFree;	
	
	public Prod(Queue<Integer> q, Semaphore sFull, Semaphore sFree) {
		super();
		this.q = q;
		this.sFull = sFull;
		this.sFree = sFree;
	}

	private int produce(int i){
		 System.out.println("producer produced " + i);
		 return i;
	}

	public void run() {
        for (int i = 0; i < 7; i++) {
        	int el = produce(i);
        	
        	try {
				sFree.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	synchronized(q) {
        		if(q.size() != Main.maxDim) {
        			q.add(el);
        		}
        	}
        	sFull.release();
        	}
        }
	}
