
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour extends AbstractMarkovModel {public MarkovFour() {
		myRandom = new Random();
	}

	public void setTraining(String s) {
		myText = s.trim();
	}

	public String getRandomText(int numChars) {
		if (myText == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - 4);
		String key = myText.substring(index, index + 4);
		sb.append(key);
		String next = "";

		for (int k = 0; k < numChars - 4; k++) {
			ArrayList<Character> follows = getFollows(key);
			if (0 == follows.size()) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			next = Character.toString(follows.get(index));
			sb.append(next);
			key = key.substring(1, 4) + next;
		}

		return sb.toString();
	}
	
	public String toString(){
		return "MarkovModel of order " + 4;
	}
}
