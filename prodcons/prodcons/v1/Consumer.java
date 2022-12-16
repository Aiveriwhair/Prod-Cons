package prodcons.v1;

public class Consumer extends Thread {

	private IProdConsBuffer buffer;
	private int id;

	public static int consumptionT = 200;

	public Consumer(IProdConsBuffer buffer, int id) {
		this.buffer = buffer;
		this.id = id;
		this.start();
	}

	public void run() {
		while (true) {
			try {
				System.out.println("> (" + this.id + ") Consuming ");
				Thread.sleep(Consumer.consumptionT);
				buffer.get();
				System.out.println("> (" + this.id + ") Consumed a msg ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void setProperties(int consT) {
		Consumer.consumptionT = consT;
	}
}
