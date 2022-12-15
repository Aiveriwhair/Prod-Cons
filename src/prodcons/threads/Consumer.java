package prodcons.threads;

import prodcons.buffer.IProdConsBuffer;
import prodcons.buffer.Message;

public class Consumer extends Thread {

	private IProdConsBuffer buffer;
	private int id;

	public static int consumptionT = 1000;
	public static int consumptionN = 5;

	public Consumer(IProdConsBuffer buffer, int id) {
		this.buffer = buffer;
		this.id = id;
		this.start();
	}

	public void run() {
		int nMsgToConsume = consumptionN;
		while (nMsgToConsume > 0) {
			try {
				System.out.println("> (" + this.id + ") Consuming ");
				this.consume();
				System.out.println("> (" + this.id + ") Consumed a msg ");
				nMsgToConsume--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("> (" + this.id + ") Consumer finished");
	}

	public void consume() throws InterruptedException {
		Thread.sleep(Consumer.consumptionT);
		Message msg = buffer.get();
	}
}
