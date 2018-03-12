package lab3;

import java.util.Queue;

public class Prod extends Thread{
	private Queue<Integer> q;
	Object conditionCons;
	Object conditionProd;
	
	
	
	public Prod(Queue<Integer> q, Object conditionCons, Object conditionProd) {
		super();
		this.q = q;
		this.conditionCons = conditionCons;
		this.conditionProd = conditionProd;
	}

	private int produce(int i){
		 System.out.println("producer produced " + i);
		 return i;
	}

	public void run() {
        for (int i = 0; i < 7; i++) {
        	int el = produce(i);
        	synchronized (q) {
        	while (q.size() == Main.maxDim) {   
                System.out.println("Full queue.Producer is waiting...");
                try {
					conditionProd.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}               
        	}
				q.add(el);
            }
            conditionCons.notifyAll();
        }
	}

}
