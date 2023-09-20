
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
	private String[] myText;
	private Random myRandom;

	public MarkovWordTwo() {
		myRandom = new Random();
	}

	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}

	public void setTraining(String text) {
		myText = text.split("\\s+");
	}

	public String getRandomText(int numWords) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length - 2); // random word to start with
		String key1 = myText[index];
		String key2 = myText[index + 1];
		sb.append(key1 + " " + key2 + " ");
		for (int k = 0; k < numWords - 2; k++) {
			ArrayList<String> follows = getFollows(key1, key2);
			if (follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next + " ");
			key1 = key2;
			key2 = next;
		}

		return sb.toString().trim();
	}

	private ArrayList<String> getFollows(String key1, String key2) {
		ArrayList<String> follows = new ArrayList<String>();
		int index = indexOf(myText, key1 + " " + key2, 0);
		while (index < this.myText.length - 2 && -1 != index) {
			index += 2;
			follows.add(myText[index]);
			index = indexOf(myText, key1 + " " + key2, index);
		}
		return follows;
	}

	private int indexOf(String[] words, String target, int start) {
		int ret = -1;
		for (int k = start; k < words.length - 1; k++) {
			if (target.equals(words[k] + " " + words[k + 1])) {
				ret = k;
				break;
			}
		}
		return ret;
	}

	public void testGetFollows() {
		this.myText = "this is just a test yes this is a simple test".split("\\s+");
		System.out.println(getFollows("this", "is"));
		System.out.println(getFollows("yes", "this"));
		System.out.println(getFollows("frog", "go"));
		System.out.println(getFollows("go", "frog"));
		System.out.println(getFollows("a", "simple"));
		System.out.println(getFollows("simple", "test"));
	}

	public void testIndexOf() {
		// 0 1 2 3 4 5 6 7 8 9 0
		String[] words = "this is just a test yes this is a simple test".split("\\s+");
		System.out.println(indexOf(words, "this is", 0));
		System.out.println(indexOf(words, "yes this", 1));
		System.out.println(indexOf(words, "frog go", 0));
		System.out.println(indexOf(words, "go frog", 5));
		System.out.println(indexOf(words, "a simple", 5));
		System.out.println(indexOf(words, "simple test", 5));
	}

}
