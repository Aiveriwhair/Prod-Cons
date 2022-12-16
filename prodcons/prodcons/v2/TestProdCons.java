package prodcons.v2;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class TestProdCons {
	static Properties props;

	public static void getProperties(String f_path) throws InvalidPropertiesFormatException, IOException {
		Properties props = new Properties();
		props.loadFromXML(TestProdCons.class.getResourceAsStream(f_path));
		TestProdCons.props = props;
	}

	public static int propAsInt(String propName) {
		return Integer.parseInt(props.getProperty(propName));
	}

	public static void testV2() throws InterruptedException {

		IProdConsBuffer buffer = new ProdConsBuffer(propAsInt("bufSz"));

		// Création des producers ---------------------------------
		int nProd = propAsInt("nProd");
		int prodTime = propAsInt("prodTime");
		int minProd = propAsInt("minProd");
		int maxProd = propAsInt("maxProd");

		Producer.setProperties(prodTime, minProd, maxProd);
		Producer[] prods = new Producer[nProd];
		for (int i = 0; i < prods.length; i++) {
			prods[i] = new Producer(buffer, i);
		}

		// Création des consumers ---------------------------------
		int nCons = propAsInt("nCons");
		int consTime = propAsInt("consTime");
		Consumer.setProperties(consTime);
		Consumer[] cons = new Consumer[nCons];
		for (int i = 0; i < cons.length; i++) {
			cons[i] = new Consumer(buffer, 100 + i);
		}

		for (Producer prod : prods) {
			prod.join();
		}
		System.out.println("### All messages have been produced ###");

		while (buffer.nmsg() > 0)
			;

		System.out.println("### All messages have been consumed ###");

		if (buffer.totmsg() != buffer.totmsgout()) {
			System.out.println("RESULT ERROR");
		}

		System.exit(0);

	}

	public static void main(String[] args) throws InterruptedException, InvalidPropertiesFormatException, IOException {
		getProperties("options.xml");
		TestProdCons.testV2();
	}
}
