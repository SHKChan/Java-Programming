
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunnerWithInterface {
	public void runModel(AbstractMarkovModel markov, String text, int size, int seed) {
		markov.setTraining(text);
		markov.setRandom(seed);
		System.out.println("running with " + markov);
		for (int k = 0; k < 3; k++) {
			String st = markov.getRandomText(size);
			printOut(st);
		}
	}

	public void runMarkov() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 125;

		EfficientMarkovModel eMM = new EfficientMarkovModel(2);
		runModel(eMM, st, size, seed);

		// MarkovZero mz = new MarkovZero();
		// runModel(mz, st, size, seed);

		// MarkovOne mOne = new MarkovOne();
		// runModel(mOne, st, size, seed);

		// MarkovModel mThree = new MarkovModel(3);
		// runModel(mThree, st, size, seed);

		// MarkovFour mFour = new MarkovFour();
		// runModel(mFour, st, size, seed);
	}

	public void compareMethods() {
		FileResource fr = new FileResource(".//data/hawthorne.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		int seed = 42;

		long startTime = System.currentTimeMillis();

		EfficientMarkovModel eMM = new EfficientMarkovModel(2);
		runModel(eMM, st, size, seed);

		long endTime = System.currentTimeMillis();
		System.out.println("Efficient Markov Model Runtime: " + (endTime - startTime) + " milliseconds");

		startTime = System.currentTimeMillis();

		MarkovModel mThree = new MarkovModel(2);
		runModel(mThree, st, size, seed);

		endTime = System.currentTimeMillis();
		System.out.println("Markov Model Runtime: " + (endTime - startTime) + " milliseconds");
	}

	public void testHashMap() {
		FileResource fr = new FileResource(".//data/romeo.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 50;
		int seed = 615;

		EfficientMarkovModel eMM = new EfficientMarkovModel(5);
		runModel(eMM, st, size, seed);
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
