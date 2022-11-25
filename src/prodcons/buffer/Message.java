package prodcons.buffer;

public class Message {
	String msg;
	int id;

	public Message(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}

	public void printMsg(String msg) {
		System.out.println(msg + this.id);
	}
}
