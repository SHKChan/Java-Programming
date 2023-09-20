
import edu.duke.FileResource;

public class Tester {
    void testGetFollows() {
        FileResource fr = new FileResource("./data/confucius.txt");
        String st = fr.asString();
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        markov.setRandom(42);

        /*
         * String[] array = { "t", "e", "es", ".", "t." };
         * for (String key : array) {
         * System.out.println(key + ": " + markov.getFollows(key));
         * }
         */
        System.out.println("size: " + markov.getFollows("he").size());
    }
}
