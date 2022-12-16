package prodcons.v6;

public class Message {
	String msg;
	int id;
	public int nCopies;

	public Message() {
		this.msg = "undefined";
		this.id = -1;
	}

	public Message(int id, String msg) {
		this.id = id;
		this.msg = msg;
		this.nCopies = 1;
	}

	public String getMsg() {
		return this.msg;
	}

	public int getId() {
		return this.id;
	}
}
