package lab3;



import java.util.Queue;


public class Cons extends Thread{

    private Queue<Integer> q;
    Object conditionCons;
    Object conditionProd;
    
    

    public Cons(Queue<Integer> q, Object conditionCons, Object conditionProd) {
		super();
		this.q = q;
		this.conditionCons = conditionCons;
		this.conditionProd = conditionProd;
	}

	private void consume(){
    	System.out.println("consumer consumed "+ q.peek());
    }
    
    @Override
    public void run() {
    	for (int i = 0; i < 7; i++) {
    		synchronized (q) {
    		while (q.isEmpty()) {        
                System.out.println("Empty queue.Consumer is waiting...");
                try {
                	conditionCons.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}            
    		}     		
                q.remove();
        	}
    		conditionProd.notifyAll();
        	consume();

        }
    }
}


