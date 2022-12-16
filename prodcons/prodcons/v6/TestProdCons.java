package prodcons.v6;

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

	public static void testV6() throws InterruptedException {

		/**
		 * 
		 * Attentions aux options, il doit y avoir au moins autant de consumers que le
		 * nombre max de copies créable par un producer sinon on reste bloqué. La
		 * bufferSize doit aussi etre suffisante pour accueillir les copies. On met un
		 * consTime élevé pour montrer que le producer est bien en attente et que les
		 * consumers aussi.
		 * 
		 */

		// La sortie est de la forme :
		// # PUT (n) avec n l'ID du thread qui a produit le message.
		// # GET (n) avec n l'ID du thread qui a consommé le message
		// On voit bien qu'un unique thread consomme 4 threads consécutivement

		IProdConsBuffer buffer = new ProdConsBuffer(propAsInt("bufSz"));

		int nProd = propAsInt("nProd");
		int prodTime = propAsInt("prodTime");
		int minProd = propAsInt("minProd");
		int maxProd = propAsInt("maxProd");
		int minCopies = propAsInt("minCopies");
		int maxCopies = propAsInt("maxCopies");

		Producer.setProperties(prodTime, minProd, maxProd, minCopies, maxCopies);
		Producer[] prods = new Producer[nProd];
		for (int i = 0; i < prods.length; i++) {
			prods[i] = new Producer(buffer, i);
		}

		int nCons = propAsInt("nCons");
		int consTime = propAsInt("consTime");
		Consumer.setProperties(consTime);
		Consumer[] cons = new Consumer[nCons];
		for (int i = 0; i < cons.length; i++) {
			cons[i] = new Consumer(buffer, 100 + i);
		}

	}

	public static void main(String[] args) throws InterruptedException, InvalidPropertiesFormatException, IOException {
		getProperties("options.xml");
		TestProdCons.testV6();
	}
}
