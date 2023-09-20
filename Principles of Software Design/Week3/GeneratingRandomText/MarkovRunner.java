
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {
	public void runMarkov() {
		FileResource fr = new FileResource("./data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');

		// MarkovZero markov = new MarkovZero();
		// MarkovOne markov = new MarkovOne();
		MarkovModel markov = new MarkovModel(6);

		markov.setRandom(792);
		markov.setTraining(st);

		for (int k = 0; k < 1; k++) {
			String text = markov.getRandomText(100);
			printOut(text);
		}
	}

	private void printOut(String s) {
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for (int k = 0; k < words.length; k++) {
			System.out.print(words[k] + " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

}
