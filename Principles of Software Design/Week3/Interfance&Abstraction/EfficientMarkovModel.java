
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
	private int n;
	private HashMap<String, ArrayList<Character>> wordsMap;

	public EfficientMarkovModel() {
		this.myRandom = new Random();
		this.n = 0;
		this.wordsMap = new HashMap<String, ArrayList<Character>>();
	}

	public EfficientMarkovModel(int num) {
		this.myRandom = new Random();
		this.n = num;
		this.wordsMap = new HashMap<String, ArrayList<Character>>();
	}

	public void setTraining(String s) {
		this.myText = s.trim();
		this.buildMap();
	}

	public String getRandomText(int numChars) {
		if (this.myText == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		String key = "";
		String next = "";
		int index = 0;

		if (0 != this.n) {
			index = this.myRandom.nextInt(this.myText.length() - this.n);
			key = this.myText.substring(index, index + this.n);
			sb.append(key);
		}

		for (int k = 0; k < numChars - this.n; k++) {
			ArrayList<Character> follows = getMapFollows(key);
			if (0 == follows.size()) {
				break;
			}
			index = this.myRandom.nextInt(follows.size());
			next = Character.toString(follows.get(index));
			sb.append(next);
			if (0 != this.n) {
				key = key.substring(1, this.n) + next;
			}
		}

		return sb.toString();
	}

	public String toString() {
		return "MarkovModel of order " + this.n;
	}

	private void buildMap() {
		for (int k = 0; k < this.myText.length() - this.n; k++) {
			String key = this.myText.substring(k, k + this.n);
			if (!this.wordsMap.containsKey(key)) {;
				ArrayList<Character> follows = getFollows(key);
				this.wordsMap.put(key, follows);
			}
		}
		this.wordsMap.put(this.myText.substring(this.myText.length() - this.n, this.myText.length()), new ArrayList<Character>());
		this.printHashMapInfo();
	}

	private ArrayList<Character> getMapFollows(String key) {
		return this.wordsMap.get(key);
	}

	public void printHashMapInfo(){
		System.out.println("----------HashMap Info----------");
		int maxLen = 0;
		ArrayList<Character> follows = null;
		for(String key : this.wordsMap.keySet()){
			follows = this.wordsMap.get(key);
			// System.out.println(key + " : " + this.wordsMap.get(key));
			if(maxLen < follows.size()){
				maxLen = follows.size();
			}
		}
		System.out.println("--------------------");
		System.out.println("Number of keys: " + this.wordsMap.size());
		System.out.println("Longest follows' size: " + maxLen);

		System.out.println("Longest follows' key: ");
		for(String key : this.wordsMap.keySet()){
			if(this.wordsMap.get(key).size() < maxLen) continue;
			System.out.println(key);
		}
	}
}
