package lab2;

import java.util.Queue;
import java.util.concurrent.Semaphore;


public class Cons extends Thread{

    private Queue<Integer> q;
	private Semaphore sFull;
	private Semaphore sFree;

	
	
    public Cons(Queue<Integer> q, Semaphore sFull, Semaphore sFree) {
		super();
		this.q = q;
		this.sFull = sFull;
		this.sFree = sFree;
	}

	private void consume(){
    	System.out.println("consumer consumed "+ q.peek());
    }
    
    @Override
    public void run() {
    	for (int i = 0; i < 7; i++) {
    		try {
				sFull.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		synchronized(q) {
    			if(q.size() != 0) {
    				q.remove();
    			}
    		}
    		sFree.release();
        	consume();

        }
    }
}


