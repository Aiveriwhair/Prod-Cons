package prodcons.threads;

import prodcons.buffer.IProdConsBuffer;
import prodcons.buffer.Message;

public class Producer extends Thread {

	private IProdConsBuffer buffer;
	private int id;

	public static int productionT = 1000;
	public static int productionN = 5;

	public Producer(IProdConsBuffer buffer, int id) {
		this.buffer = buffer;
		this.id = id;
		this.start();
	}

	public void produce(Message msg) throws InterruptedException {
		Thread.sleep(Producer.productionT);
		buffer.put(msg);
	}

	public void run() {
		int nMsgToProduce = productionN;
		while (nMsgToProduce > 0) {
			try {
				System.out.println("> (" + this.id + ") Producing ");
				this.produce(new Message(this.id, "Produced by " + this.id));
				System.out.println("> (" + this.id + ") Produced a msg");
				nMsgToProduce--;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("> (" + this.id + ") Producer finished");
	}

}
