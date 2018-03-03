package l1;

import java.util.Queue;
import java.util.Random;

public class Prod extends Thread{
	Queue<Integer> q;
	
	public Prod(Queue<Integer> q) {
		super();
		this.q = q;
	}
	public synchronized void produce(){
		int x = new Random().nextInt();
		System.out.println("producer produced "+ x);
		q.add(x);
	}
	public void run(){
		while(true){
			if(q.size() < Main.maxDim){
				produce();
			}
		}
	}
}
