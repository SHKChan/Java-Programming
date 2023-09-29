import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordInFiles {
    private HashMap<String, ArrayList<String>> wordFileMap;

    public WordInFiles() {
        this.wordFileMap = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        String fName = f.getName();
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            if (!this.wordFileMap.containsKey(word)){
                ArrayList<String> ls = new ArrayList<String>();
                ls.add(fName);
                this.wordFileMap.put(word, ls);
            }
            if(!this.wordFileMap.get(word).contains(fName)){
                this.wordFileMap.get(word).add(fName);
            }
        }
    }

    public void buildWordFileMap() {
        this.wordFileMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            this.addWordsFromFile(f);
        }
    }

    public int maxNumber(){
        int max = 0;
        for(String key : this.wordFileMap.keySet()){
            if (this.wordFileMap.get(key).size() > max){
                max = this.wordFileMap.get(key).size();
            }
        }
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> ret = new ArrayList<String>();
        for(String key : this.wordFileMap.keySet()){
            if (this.wordFileMap.get(key).size() == number){
                ret.add(key);
            }
        }
        return ret;
    }

    public void printFilesIn(String word){
        for(String key : this.wordFileMap.keySet()){
            if (word.equals(key)){
                System.out.println(key + "\t" + this.wordFileMap.get(key));
            }
        }
    }

    public void printMap(){
        System.out.println("The completed map is: ");
        for(String key : this.wordFileMap.keySet()){
            System.out.println(key + "\t" + this.wordFileMap.get(key));
        }
    }

    public void test() {
        this.buildWordFileMap();
        // this.printMap();
        int max = this.maxNumber();
        System.out.println("The maximum number of files any word is in: " + max);
        ArrayList<String> words = this.wordsInNumFiles(7);
        for(String word : words){
            this.printFilesIn(word);
        }
        this.printFilesIn("laid");
        this.printFilesIn("tree");
    }
}
