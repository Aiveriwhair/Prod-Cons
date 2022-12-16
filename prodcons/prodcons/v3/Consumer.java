package prodcons.v3;

public class Consumer extends Thread {

	private IProdConsBuffer buffer;
	private int id;
	private boolean running;

	public static int consumptionT = 200;

	public Consumer(IProdConsBuffer buffer, int id) {
		this.buffer = buffer;
		this.id = id;
		this.running = true;
		this.start();
	}

	public void run() {
		while (running) {
			try {
				System.out.println("> (" + this.id + ") Consuming ");
				Thread.sleep(Consumer.consumptionT);
				buffer.get();
				System.out.println("> (" + this.id + ") Consumed a msg ");
			} catch (InterruptedException e) {
				System.out.println("> (" + this.id + ") was interrupted");
			}
		}
		System.out.println("> (" + this.id + ") Consumer STOPPED ");
	}

	public static void setProperties(int consT) {
		Consumer.consumptionT = consT;
	}

	public void kill() {
		this.running = false;
	}
}
