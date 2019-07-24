import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class B implements Runnable {

	private ObjectOutputStream oos; // TB -> TA
	private ObjectInputStream ois; // TC -> TB

	private InputStream is; // TC -> TB
	private OutputStream os; // TB -> TA
	private OutputStream os2; // TB -> TC

	B(InputStream is, OutputStream os, OutputStream os2, ObjectInputStream ois, ObjectOutputStream oos) {
		this.is = is;
		this.os = os;
		this.os2 = os2;
		this.ois = ois;
		this.oos = oos;
	}

	@Override
	public void run() {
		int num2 = 5;

		// TB to TA
		Message m = new Message(5, 6);
		System.out.println("Thread B writes to Thread A: " + m);

		try {
			// Send it to TA
			oos = new ObjectOutputStream(os);
			oos.writeObject(m);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// TB to TC
		try {
			// Send it to TC
			System.out.println("Thread B writes to Thread C: " + num2);
			os2.write(num2);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// TC to TB
		try { // Read object from TC
			ois = new ObjectInputStream(is);
			Message m2 = (Message) ois.readObject();
			System.out.println("Thread B received from Thread C: " + m2);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
