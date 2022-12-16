package prodcons.v6;

import java.util.Random;

public class Producer extends Thread {

	private IProdConsBuffer buffer;
	private int id;

	public boolean isStopped;
	public static int productionT;
	public static int minProd, maxProd;
	public static int minCopies, maxCopies;

	public Producer(IProdConsBuffer buffer, int id) {
		this.buffer = buffer;
		this.id = id;
		this.isStopped = false;
		this.start();
	}

	public void run() {
		int nMsgToProduce = new Random().nextInt(maxProd - minProd + 1) + minProd;
		int objective = nMsgToProduce;
		while (nMsgToProduce > 0) {
			try {
				Thread.sleep(Producer.productionT);
				buffer.put(
						new Message(this.id,
								"(" + this.id + ") -> (" + Integer.toString(objective - nMsgToProduce) + ")"),
						new Random().nextInt(maxCopies - minCopies + 1) + minCopies);
				nMsgToProduce--;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void setProperties(int prodT, int minP, int maxP, int minC, int maxC) {
		Producer.productionT = prodT;
		Producer.minProd = minP;
		Producer.maxProd = maxP;
		Producer.minCopies = minC;
		Producer.maxCopies = maxC;
	}

}
