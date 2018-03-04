package l1;

import java.util.Queue;

public class Prod extends Thread{
	private Queue<Integer> q;
	
	public Prod( Queue<Integer> q) {
		super();
		this.q = q;
	}
	
	private void produce(int i){
		 System.out.println("producer produced " + i);
         q.add(i);
	}

	public void run() {
        for (int i = 0; i < 7; i++) {
            synchronized (q) {
                while (q.size() == Main.maxDim) {   
                        System.out.println("Full queue.Producer is waiting...");
                        try {
							q.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}               
                }
				produce(i);
                q.notifyAll();
            }
        }
	}

}
