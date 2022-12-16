package prodcons.v5;

import java.util.concurrent.Semaphore;

public class ProdConsBuffer implements IProdConsBuffer {
	Message[] msgs;
	int in, out;
	Semaphore notFull, notEmpty;

	int bufferSize;
	int currentCount;
	int totmsg, totmsgout;

	public ProdConsBuffer() {
		this.bufferSize = 10;
		msgs = new Message[bufferSize];
		this.in = 0;
		this.out = 0;
		this.notFull = new Semaphore(bufferSize);
		this.notEmpty = new Semaphore(0);
		this.currentCount = 0;
		this.totmsg = 0;
		this.totmsgout = 0;
	}

	public ProdConsBuffer(int n) {
		this.bufferSize = n;
		msgs = new Message[bufferSize];
		this.in = 0;
		this.out = 0;
		this.notFull = new Semaphore(bufferSize);
		this.notEmpty = new Semaphore(0);
		this.currentCount = 0;
		this.totmsg = 0;
		this.totmsgout = 0;
	}

	@Override
	public void put(Message m) throws InterruptedException {
		notFull.acquire();
		synchronized (this) {
			msgs[in] = m;
			in = (in + 1) % bufferSize;
			System.out.println("### PUT " + Thread.currentThread().getId());
			currentCount++;
			totmsg++;
			notEmpty.release();
		}
	}

	@Override
	public Message get() throws InterruptedException {
		notEmpty.acquire();
		synchronized (this) {
			Message m = msgs[out];
			out = (out + 1) % bufferSize;
			System.out.println("# GET " + Thread.currentThread().getId());
			currentCount--;
			totmsgout++;
			notFull.release();
			notifyAll();
			return m;
		}
	}

	@Override
	public Message[] get(int k) throws InterruptedException {
		notEmpty.acquire(k);
		synchronized (this) {
			while (currentCount < k)
				wait();

			Message[] res = new Message[k];
			for (int i = 0; i < k; i++) {
				res[i] = msgs[out];
				System.out.println("# GET " + Thread.currentThread().getId());
				currentCount--;
				out = (out + 1) % bufferSize;
				totmsgout++;
			}
			notFull.release(k);
			notifyAll();
			return res;
		}
	}

	@Override
	public int nmsg() {
		return currentCount;
	}

	@Override
	public int totmsg() {
		return this.totmsg;
	}

	@Override
	public int totmsgout() {
		return this.totmsgout;
	}

}
