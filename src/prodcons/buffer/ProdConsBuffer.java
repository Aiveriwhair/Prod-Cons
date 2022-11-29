package prodcons.buffer;

public class ProdConsBuffer implements IProdConsBuffer {
	int BufferSize ;
	Message buffer[];
	int in; 
	int out;

	public ProdConsBuffer(Message buff[], int BufferSize){
		this.buffer = new Message[BufferSize];
		this.BufferSize = BufferSize; 
		this.in =0;
		this.out=0;
	}



	@Override
	public void put(Message m) throws InterruptedException {
		while(in == out && in >0 && out>0 ){
           //wait() 
		}
		buffer[in]=m;
		in = in + 1%BufferSize;

		//notify()

		

	}

	@Override
	public Message get() throws InterruptedException  {


	}

	@Override
	public int nmsg() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totmsg() {
		// TODO Auto-generated method stub
		return 0;
	}

}
