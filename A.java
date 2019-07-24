import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class A implements Runnable {

	private ObjectOutputStream oos;
	private ObjectInputStream ois; // TB -> TA

	private InputStream is; // TB -> TA
	private OutputStream os; // TA -> TC

	A(InputStream is, OutputStream os, ObjectInputStream ois, ObjectOutputStream oos) {
		this.is = is;
		this.os = os;
		this.ois = ois;
		this.oos = oos;
	}

	@Override
	public void run() {
		int num = 1;
		try {
			// Write to TC
			System.out.println("Thread A writes to Thread C: " + num);
			os.write(num);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// Read object from TB
			ois = new ObjectInputStream(is);
			Message m = (Message) ois.readObject();
			System.out.println("Thread A received from Thread B: " + m);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
