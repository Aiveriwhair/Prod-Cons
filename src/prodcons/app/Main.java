package prodcons.app;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import prodcons.buffer.IProdConsBuffer;
import prodcons.buffer.ProdConsBuffer;
import prodcons.threads.Consumer;
import prodcons.threads.Producer;

public class Main {

	static Properties props;

	public static void getProperties(String f_path) throws InvalidPropertiesFormatException, IOException {
		Properties props = new Properties();
		props.loadFromXML(Main.class.getResourceAsStream(f_path));
		Main.props = props;
	}

	public static void main(String[] args) throws InterruptedException {

		IProdConsBuffer buffer = new ProdConsBuffer(5);

		Producer[] prods = new Producer[2];
		for (int i = 0; i < prods.length; i++) {
			prods[i] = new Producer(buffer, i);
		}
		Consumer[] cons = new Consumer[2];
		for (int i = 0; i < cons.length; i++) {
			cons[i] = new Consumer(buffer, 10 + i);
		}

		for (int i = 0; i < prods.length; i++) {
			prods[i].join();
		}

		System.out.println("tot msg in :" + buffer.totmsg());
		System.out.println("tot msg out :" + buffer.totmsgout());
	}

}
