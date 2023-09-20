
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {
	private int n;

	public MarkovModel() {
		myRandom = new Random();
		this.n = 0;
	}

	public MarkovModel(int num) {
		myRandom = new Random();
		this.n = num;
	}

	public void setTraining(String s) {
		myText = s.trim();
	}

	public String getRandomText(int numChars) {
		if (myText == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		String key = "";
		String next = "";
		int index = 0;

		if (0 != this.n) {
			index = myRandom.nextInt(myText.length() - this.n);
			key = myText.substring(index, index + this.n);
			sb.append(key);
		}

		for (int k = 0; k < numChars - this.n; k++) {
			ArrayList<Character> follows = getFollows(key);
			if (0 == follows.size()) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			next = Character.toString(follows.get(index));
			sb.append(next);
			if (0 != this.n) {
				key = key.substring(1, this.n) + next;
			}
		}

		return sb.toString();
	}
	
	public String toString(){
		return "MarkovModel of order " + this.n;
	}
}
