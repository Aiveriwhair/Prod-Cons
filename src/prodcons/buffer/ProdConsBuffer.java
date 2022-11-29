package prodcons.buffer;

public class ProdConsBuffer implements IProdConsBuffer {
	int taille ;
	Message buffer[];

	public ProdConsBuffer(Message buff[], int taille){
		this.buffer = new Message[taille];
		this.taille = taille; 


	}



	@Override
	public void put(Message m) throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public Message get() throws InterruptedException  {


        
		return ;

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
