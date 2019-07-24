import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class C implements Runnable {

	private ObjectOutputStream oos; // TC -> TB
	private ObjectInputStream ois;

	private InputStream is; // TA -> TC
	private InputStream is2; // TB -> TC
	private OutputStream os; // TC -> TB

	C(InputStream is, InputStream is2, OutputStream os, ObjectInputStream ois, ObjectOutputStream oos) {
		this.is = is;
		this.is2 = is2;
		this.os = os;
		this.ois = ois;
		this.oos = oos;
	}

	@Override
	public void run() {
		int c = 0;
		try {
			// Read from TA
			c = is.read();
			System.out.println("Thread C received from Thread A: " + c);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// Read from TB
			c = is2.read();
			System.out.println("Thread C received from Thread B: " + c);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// TC -> TB
		Message m = new Message(1, 2);

		try {
			// Send to TB

			System.out.println("Thread C writes to Thread B: " + m);

			oos = new ObjectOutputStream(os);
			oos.writeObject(m);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
