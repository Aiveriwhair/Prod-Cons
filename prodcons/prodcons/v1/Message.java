package prodcons.v1;

public class Message {
	String msg;
	int id;

	public Message() {
		this.msg = "undefined";
		this.id = -1;
	}

	public Message(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	public int getId() {
		return this.id;
	}
}
