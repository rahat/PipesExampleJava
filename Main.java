import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {

	static private PipedInputStream pis1; // TA -> TC
	static private PipedOutputStream pos1;

	static private PipedInputStream pis2; // TB -> TA
	static private PipedOutputStream pos2;

	static private PipedInputStream pis3; // TB -> TC
	static private PipedOutputStream pos3;

	static private PipedInputStream pis4; // TC -> TB
	static private PipedOutputStream pos4;

	static private ObjectOutputStream oos;
	static private ObjectInputStream ois;

	public static void main(String[] args) {

		try {

			System.out.println("Initialize Pipes");
			pos1 = new PipedOutputStream();
			pis1 = new PipedInputStream(pos1);

			pos2 = new PipedOutputStream();
			pis2 = new PipedInputStream(pos2);

			pos3 = new PipedOutputStream();
			pis3 = new PipedInputStream(pos3);

			pos4 = new PipedOutputStream();
			pis4 = new PipedInputStream(pos4);

			System.out.println("Start Threads A, B, C");

			// Create the thread for TA
			Thread TA = new Thread(new A(pis2, pos1, ois, oos), "TA");
			TA.start();

			// Create the thread for TB
			Thread TB = new Thread(new B(pis4, pos2, pos3, ois, oos), "TB");
			TB.start();

			// Create the thread for TC
			Thread TC = new Thread(new C(pis1, pis3, pos4, ois, oos), "TC");
			TC.start();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
