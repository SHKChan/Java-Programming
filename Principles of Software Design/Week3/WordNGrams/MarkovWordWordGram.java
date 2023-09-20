
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordWordGram implements IMarkovModel {
	private String[] myText;
	private Random myRandom;
	private int order;
	private HashMap<WordGram, ArrayList<String>> wordsMap;

	public MarkovWordWordGram(int n) {
		myRandom = new Random();
		this.order = n;
		this.wordsMap = new HashMap<WordGram, ArrayList<String>>();
	}

	public MarkovWordWordGram() {
		this.myRandom = new Random();
		this.order = 0;
		this.wordsMap = new HashMap<WordGram, ArrayList<String>>();
	}

	public void setRandom(int seed) {
		this.myRandom = new Random(seed);
	}

	public void setTraining(String text) {
		this.myText = text.split("\\s+");
		this.buildMap();
	}

	public String getRandomText(int numWords) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(this.myText.length - this.order); // random word to start with
		WordGram keys = null;
		if (this.order != 0) {
			String[] temp = Arrays.copyOfRange(this.myText, index, index + this.order);
			keys = new WordGram(temp, 0, this.order);
			sb.append(keys.toString() + " ");
		}
		String next = "";
		for (int k = 0; k < numWords - this.order; k++) {
			if (0 != this.order) {
				ArrayList<String> follows = this.getMatchFollows(keys);
				if (follows.size() == 0) {
					break;
				}
				index = myRandom.nextInt(follows.size());
				next = follows.get(index);
				keys = keys.shiftAdd(next);
			} else {
				index = myRandom.nextInt(this.myText.length);
				next = this.myText[index];
			}
			sb.append(next + " ");
		}

		return sb.toString().trim();
	}

	private ArrayList<String> getMatchFollows(WordGram keys) {
		return this.wordsMap.get(keys);
	}

	private ArrayList<String> getFollows(WordGram kGram) {
		ArrayList<String> follows = new ArrayList<String>();

		if (0 == this.order) {
			return follows;
		}

		int index = -1;
		int len = kGram.length();
		index = indexOf(myText, kGram, 0);
		while (index < this.myText.length - len && -1 != index) {
			index += len;
			follows.add(myText[index]);
			index = indexOf(myText, kGram, index);
		}
		return follows;
	}

	private int indexOf(String[] words, WordGram targets, int start) {
		int ret = -1;
		int len = targets.length();
		WordGram cur = null;
		for (int k = start; k < words.length - len + 1; k++) {
			cur = new WordGram(words, k, len);
			if (targets.equals(cur)) {
				ret = k;
				break;
			}
		}
		return ret;
	}

	private void buildMap() {
		if (this.myText.length == 0) {
			return;
		}

		for (int k = 0; k < this.myText.length - this.order + 1; k++) {
			String[] temp = Arrays.copyOfRange(this.myText, k, k + this.order);
			WordGram keys = new WordGram(temp, 0, this.order);
			if (!this.wordsMap.containsKey(keys)) {
				ArrayList<String> list = this.getFollows(keys);
				this.wordsMap.put(keys, list);
			}
		}
		// this.printHashMapInfo();
	}

	public void testGetFollows() {
		this.myText = "this is just a test yes this is a simple test".split("\\s+");
		System.out.println(getFollows(new WordGram(new String[] { "this", "is" })));
		System.out.println(getFollows(new WordGram(new String[] { "yes", "this" })));
		System.out.println(getFollows(new WordGram(new String[] { "frog", "go" })));
		System.out.println(getFollows(new WordGram(new String[] { "go", "frog" })));
		System.out.println(getFollows(new WordGram(new String[] { "a", "simple" })));
		System.out.println(getFollows(new WordGram(new String[] { "simple", "test" })));
	}

	public void testIndexOf() {
		// 0 1 2 3 4 5 6 7 8 9 0
		String[] words = "this is just a test yes this is a simple test".split("\\s+");
		System.out.println(indexOf(words, new WordGram(new String[] { "this", "is" }), 0));
		System.out.println(indexOf(words, new WordGram(new String[] { "yes", "this" }), 1));
		System.out.println(indexOf(words, new WordGram(new String[] { "frog", "go" }), 0));
		System.out.println(indexOf(words, new WordGram(new String[] { "go", "frog" }), 5));
		System.out.println(indexOf(words, new WordGram(new String[] { "a", "simple" }), 5));
		System.out.println(indexOf(words, new WordGram(new String[] { "simple", "test" }), 5));
	}

	public void printHashMapInfo() {
		System.out.println("----------HashMap Info----------");
		int maxLen = 0;
		ArrayList<String> follows = null;
		for (WordGram keys : this.wordsMap.keySet()) {
			follows = this.wordsMap.get(keys);
			// System.out.println(keys + " : " + this.wordsMap.get(keys));
			if (maxLen < follows.size()) {
				maxLen = follows.size();
			}
		}
		System.out.println("--------------------");
		System.out.println("Number of keys: " + this.wordsMap.size());
		System.out.println("Longest follows' size: " + maxLen);

		System.out.println("Longest follows' key: ");
		for (WordGram keys : this.wordsMap.keySet()) {
			if (this.wordsMap.get(keys).size() < maxLen)
				continue;
			System.out.println(keys);
		}
	}
}
