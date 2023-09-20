
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}
 
    abstract public String getRandomText(int numChars);

    abstract public String toString();

	protected ArrayList<Character> getFollows(String key) {
		ArrayList<Character> follows = new ArrayList<Character>();
		int keyLen = key.length();
		for (int i = 0; i < this.myText.length() - keyLen; i++) {
			if (key.equals(this.myText.substring(i, i + keyLen)) || key.isEmpty()) {
				follows.add(this.myText.charAt(i + keyLen));
			}
		}
		return follows;
	}

}
