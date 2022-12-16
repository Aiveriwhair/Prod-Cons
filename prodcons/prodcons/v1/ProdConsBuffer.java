package prodcons.v1;

public class ProdConsBuffer implements IProdConsBuffer {

	private int in, out;
	private int bufferSize;
	private int totmsg;
	private int totmsgout;
	private int currentCount;
	private Message[] msgs;

	public ProdConsBuffer(int bufferSize) {
		this.totmsg = 0;
		this.in = 0;
		this.out = 0;
		this.currentCount = 0;
		this.bufferSize = bufferSize;
		this.msgs = new Message[bufferSize];
	}

	@Override
	public synchronized void put(Message m) throws InterruptedException {
		while (currentCount == bufferSize)
			wait();
		System.out.println("# PUT " + this.currentCount + " in buffer");
		this.msgs[in] = m;
		this.in = (this.in + 1) % this.bufferSize;
		this.totmsg++;
		this.currentCount++;
		notifyAll();
	}

	@Override
	public synchronized Message get() throws InterruptedException {
		while (currentCount == 0)
			wait();
		System.out.println("# GET " + this.currentCount + " in buffer");
		Message msg = new Message();
		msg = msgs[out];
		out = (out + 1) % bufferSize;
		totmsgout++;
		this.currentCount--;
		notifyAll();
		return msg;
	}

	@Override
	public int nmsg() {
		return this.currentCount;
	}

	@Override
	public int totmsg() {
		return this.totmsg;
	}

	public int totmsgout() {
		return this.totmsgout;
	}

}
