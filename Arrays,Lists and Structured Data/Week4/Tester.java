
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.File;
import java.util.*;

import edu.duke.FileResource;

public class Tester {
    public static void test() {
        VigenereBreaker breaker = new VigenereBreaker();
        /*
         * // test sliceString()
         * System.out.println("Sliced message is: " +
         * breaker.sliceString("abcdefghijklm", 4, 5));
         */

        /*
         * // test tryKeyLength()
         * FileResource fr = new FileResource();
         * int[] key = breaker.tryKeyLength(fr.asString(), 5, 'e');
         * System.out.println("Keys are: " + Arrays.toString(key));
         */

        /*
         * // test readDictionary()
         * FileResource fr = new FileResource();
         * breaker.readDictionary(fr);
         */

        /*
         * // test countWords()
         * System.out.println("Please select a dictionary for language");
         * HashSet<String> dict = breaker.readDictionary(new FileResource());
         * int wordCount = breaker.countWords(new FileResource().asString(), dict);
         * System.out.println("Counted words is: " + wordCount);
         */

        // // test breakForLanguage()
        // System.out.println("Please select a dictionary for language");
        // HashSet<String> dict = breaker.readDictionary(new FileResource());

        // System.out.println("Please select a file to break");
        // breaker.breakForLanguage(new FileResource().asString(), dict);


        // test breakVigenere()
        breaker.breakVigenere();

        /* // test mostCommonCharIn()
        char mostCommonChar = breaker.mostCommonCharIn(breaker.readDictionary(new FileResource()));
        System.out.println("Most common character is: " + mostCommonChar); */
    }
}
