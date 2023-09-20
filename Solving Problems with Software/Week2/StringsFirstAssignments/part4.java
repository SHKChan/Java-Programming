import edu.duke.*;

public class part4 {
    public void findYTAddress() {
        URLResource url = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        int count = 0;
        for (String word : url.words()) {
            String tempWord = word.toLowerCase();
            int pos = tempWord.indexOf("youtube");
            if (-1 != pos) {
                count++;
                int start = word.lastIndexOf("\"", pos);
                int stop = word.indexOf("\"", pos + 1);
                System.out.println(count + ": " + word.substring(start + 1, stop));
            }
        }
    }

    public void testing() {
        findYTAddress();
    }
}
