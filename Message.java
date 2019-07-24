import java.io.Serializable;

class Message implements Serializable {
	public int number, id;

	public Message(int number, int id) {
		this.number = number;
		this.id = id;
	}

	public String toString() {
		return "Message: " + "Number: " + number + " ID: " + id;

	}
}
