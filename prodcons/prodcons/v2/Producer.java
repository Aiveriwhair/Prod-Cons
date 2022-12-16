package prodcons.v2;

import java.util.Random;

public class Producer extends Thread {

	private IProdConsBuffer buffer;
	private int id;

	public static int productionT;
	public static int minProd, maxProd;

	public Producer(IProdConsBuffer buffer, int id) {
		this.buffer = buffer;
		this.id = id;
		this.start();
	}

	public void run() {
		int nMsgToProduce = new Random().nextInt(maxProd - minProd + 1) + minProd;
		while (nMsgToProduce > 0) {
			try {
				System.out.println("> (" + this.id + ") Producing ");
				Thread.sleep(Producer.productionT);
				buffer.put(new Message(this.id, "Produced by " + this.id));
				System.out.println("> (" + this.id + ") Produced a msg");
				nMsgToProduce--;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("> (" + this.id + ") Producer STOPPED ");
	}

	public static void setProperties(int prodT, int minP, int maxP) {
		Producer.productionT = prodT;
		Producer.minProd = minP;
		Producer.maxProd = maxP;
	}

}
