package l1;

import java.util.Queue;

public class Cons extends Thread{
	Queue<Integer> q;
	
	public Cons(Queue<Integer> q) {
		super();
		this.q = q;
	}
	public synchronized void consume(){
		System.out.println("consumer consumed "+ q.peek());
		q.remove();
	}
	public void run(){
		while(true){
			if(q.size() > 0){
				consume();
			}
		}
	}

}
