import edu.duke.FileResource;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;

    public CharactersInPlay() {
        this.names = new ArrayList<String>();
        this.counts = new ArrayList<Integer>();
    }

    public void update(String name) {
        int index = this.names.indexOf(name);
        if (-1 != index) {
            this.counts.set(index, this.counts.get(index) + 1);
        } else {
            this.names.add(name);
            this.counts.add(1);
        }
    }

    public void findAllCharacters() {
        this.names.clear();
        this.counts.clear();
        FileResource fr = new FileResource();

        int index = -1;
        for (String line : fr.lines()) {
            index = line.indexOf(".");
            if (-1 != index) {
                this.update(line.substring(0, index));
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2){
        int mostCountIdx = 0;
        int mostCount = 0;

        int curCount = 0;
        for (int i = 0; i < this.names.size(); i++) {
            curCount = this.counts.get(i);
            if(num1 <= curCount && num2 >= curCount){
                System.out.println(this.names.get(i) + " " + curCount);
            }
            if (this.counts.get(i) > mostCount) {
                mostCount = this.counts.get(i);
                mostCountIdx = i;
            }
        }

        System.out.println("The main character is: " + this.names.get(mostCountIdx) + " " + mostCount);
    }

    public void test() {
        this.findAllCharacters();
        this.charactersWithNumParts(50,10000);
    }
}
