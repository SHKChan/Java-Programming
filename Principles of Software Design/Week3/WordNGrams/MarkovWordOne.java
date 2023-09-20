
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
	private String[] myText;
	private Random myRandom;

	public MarkovWordOne() {
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
		int index = myRandom.nextInt(myText.length - 1); // random word to start with
		String key = myText[index];
		sb.append(key + " ");
		for (int k = 0; k < numWords - 1; k++) {
			ArrayList<String> follows = getFollows(key);
			if (follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next + " ");
			key = next;
		}

		return sb.toString().trim();
	}

	private ArrayList<String> getFollows(String key) {
		ArrayList<String> follows = new ArrayList<String>();
		int index = indexOf(myText, key, 0);
		while (index < this.myText.length - 1 && -1 != index) {
			index++;
			follows.add(myText[index]);
			index = indexOf(myText, key, index);
		}
		return follows;
	}

	private int indexOf(String[] words, String target, int start) {
		int ret = -1;
		for (int k = start; k < words.length; k++) {
			if (target.equals(words[k])) {
				ret = k;
				break;
			}
		}
		return ret;
	}

	public void testGetFollows() {
		this.myText = "this is just a test yes this is a simple test".split("\\s+");
		System.out.println(getFollows("this"));
		System.out.println(getFollows("frog"));
		System.out.println(getFollows("simple"));
		System.out.println(getFollows("test"));
		System.out.println(getFollows("is"));
	}

	public void testIndexOf() {
		String[] words = "this is just a test yes this is a simple test".split("\\s+");
		System.out.println(indexOf(words, "this", 0));
		System.out.println(indexOf(words, "this", 1));
		System.out.println(indexOf(words, "frog", 0));
		System.out.println(indexOf(words, "frog", 5));
		System.out.println(indexOf(words, "simple", 2));
		System.out.println(indexOf(words, "test", 5));
	}

}
