import edu.duke.*;
import java.util.*;

public class ImprovedGladLib {
	private HashMap<String, ArrayList<String>> labelsTagMap;
	private HashMap<String, ArrayList<String>> usedMap;

	private String[] labels = { "adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb",
			"fruit" };

	private Random myRandom;

	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "datalong";

	public ImprovedGladLib() {
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}

	public ImprovedGladLib(String source) {
		initializeFromSource(source);
		myRandom = new Random();
	}

	private void initializeFromSource(String source) {
		this.labelsTagMap = new HashMap<String, ArrayList<String>>();
		for (String label : labels) {
			this.labelsTagMap.put(label, readIt(source + "/" + label + ".txt"));
		}
		usedMap = new HashMap<String, ArrayList<String>>();
	}

	private String randomFrom(ArrayList<String> source) {
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}

	private String getSubstitute(String label) {
		if (label.equals("number")) {
			return "" + myRandom.nextInt(50) + 5;
		}
		if (this.labelsTagMap.containsKey(label)) {
			return randomFrom(this.labelsTagMap.get(label));
		}
		return "**UNKNOWN**";
	}

	/*
	 * replace one tag inside the template with random word
	 */
	private String processWord(String w) {
		int first = w.indexOf("<");
		int last = w.indexOf(">", first);
		if (first == -1 || last == -1) {
			return w;
		}
		String prefix = w.substring(0, first);
		String suffix = w.substring(last + 1);
		String sub = "";
		String tag = w.substring(first + 1, last);
		do {
			sub = getSubstitute(tag);
		} while (this.usedMap.containsKey(tag) && this.usedMap.get(tag).contains(sub));
		if(!this.usedMap.containsKey(tag)){
			ArrayList<String> ls = new ArrayList<String>();
			ls.add(sub);
			this.usedMap.put(tag, ls);
		}
		else{
			this.usedMap.get(tag).add(sub);
		}
		return prefix + sub + suffix;
	}

	private void printOut(String s, int lineWidth) {
		int charsWritten = 0;
		// splits into an array of substrings using one or more whitespace characters as
		// the delimiter.
		for (String w : s.split("\\s+")) {
			if (charsWritten + w.length() > lineWidth) {
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w + " ");
			charsWritten += w.length() + 1;
		}
		this.totalWordsUsed();
	}

	private String fromTemplate(String source) {
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for (String word : resource.words()) {
				story = story + processWord(word) + " ";
			}
		} else {
			FileResource resource = new FileResource(source);
			for (String word : resource.words()) {
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}

	private ArrayList<String> readIt(String source) {
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for (String line : resource.lines()) {
				list.add(line);
			}
		} else {
			FileResource resource = new FileResource(source);
			for (String line : resource.lines()) {
				list.add(line);
			}
		}
		return list;
	}

	public void makeStory() {
		System.out.println("\n");
		String story = fromTemplate("datalong/madtemplate2.txt");
		printOut(story, 60);
	}

	public void totalWordsInMap(){
		int total = 0;
		for(String key : this.labelsTagMap.keySet()){
			total += this.labelsTagMap.get(key).size();
		}
		System.out.println("Total number of words that were possible to pick from: " + total);
	}

	public void totalWordsConsidered(){
		int total = 0;
		for(String key : this.usedMap.keySet()){
			total += this.labelsTagMap.get(key).size();
		}
		System.out.println("Total number of words that were considered to pick from: " + total);
	}

	public void totalWordsUsed(){
		System.out.println();
		System.out.println();
		int total = 0;
		for(String key : this.labelsTagMap.keySet()){
			total += this.labelsTagMap.get(key).size();
		}
		System.out.println("Total numbers of words that were used: " + total);
	}

	public void test() {
		makeStory();
		totalWordsInMap();
	}

}
