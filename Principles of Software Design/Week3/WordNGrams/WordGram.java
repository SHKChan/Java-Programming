
public class WordGram {
    private String[] myWords;
    // private int myHash;
    public WordGram(String[] source, int start, int size) {
        this.myWords = new String[size];
        System.arraycopy(source, start, this.myWords, 0, size);
    }

    public WordGram(String[] source) {
        int size = source.length;
        this.myWords = new String[size];
        System.arraycopy(source, 0, this.myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= this.myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return this.myWords[index];
    }

    public int length(){
        return this.myWords.length;
    }

    public String toString(){
        String ret = "";
        for (String word : this.myWords) {
            ret += word + " ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.myWords.length != other.myWords.length){
            return false;
        }

        for(int i = 0; i < this.myWords.length; i++){
            if(!this.myWords[i].equals(other.myWords[i])){
                return false;
            }
        }

        return true;

    }

    public WordGram shiftAdd(String word) {	
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        WordGram out = new WordGram(myWords, 0, myWords.length);
        for (int i = 0; i < myWords.length-1; i++) {
            out.myWords[i] = myWords[i+1];
        }
        out.myWords[myWords.length-1] = word;
        return out;
    }

    public int hashCode(){
        return this.toString().hashCode();
    }
}