package l1;

import java.util.Queue;


public class Cons extends Thread{

    private Queue<Integer> q;

    public Cons(Queue<Integer> q) {
        this.q = q;
    }

    private void consume(){
    	System.out.println("consumer consumed "+ q.peek());
    	q.remove();
    }
    
    @Override
    public void run() {
    	for (int i = 0; i < 7; i++) {
        	synchronized (q) {
        		while (q.isEmpty()) {        
                    System.out.println("Empty queue.Consumer is waiting...");
                    try {
						q.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}            
        		}
        		
                q.notifyAll();
                consume();
        	}

        }
    }
}


