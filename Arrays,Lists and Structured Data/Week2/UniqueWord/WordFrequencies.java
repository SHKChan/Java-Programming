import java.util.ArrayList;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        this.myWords = new ArrayList<String>();
        this.myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        this.myWords.clear();
        this.myFreqs.clear();
        FileResource fr = new FileResource();

        int index = -1;
        for (String word : fr.words()) {
            index = this.myWords.indexOf(word.toLowerCase());
            if (-1 == index) {
                this.myWords.add(word.toLowerCase());
                this.myFreqs.add(1);
            } else {
                this.myFreqs.set(index, this.myFreqs.get(index) + 1);
            }
        }
    }

    public void test() {
        this.findUnique();
        int mostFreqIdx = 0;
        int mostFreq = 0;
        for (int i = 0; i < this.myWords.size(); i++) {
            System.out.println(this.myFreqs.get(i) + " " + this.myWords.get(i));
            if (this.myFreqs.get(i) > mostFreq) {
                mostFreq = this.myFreqs.get(i);
                mostFreqIdx = i;
            }
        }
        
        System.out.println("The word that occurs most often and its count are: " + this.myWords.get(mostFreqIdx) + " " + mostFreq);
    }
}
