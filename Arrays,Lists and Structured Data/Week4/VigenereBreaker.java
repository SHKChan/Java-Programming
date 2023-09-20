import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        // REPLACE WITH YOUR CODE
        StringBuilder slicedStr = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            slicedStr.append(message.charAt(i));
        }
        return slicedStr.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        // WRITE YOUR CODE HERE
        StringBuilder[] slicedStrs = new StringBuilder[klength];
        int i = 0;
        for (char c : encrypted.toCharArray()) {
            int idx = i % klength;
            if (null == slicedStrs[idx]) {
                slicedStrs[idx] = new StringBuilder();
            }
            slicedStrs[idx].append(c);
            i++;
        }
        for (int j = 0; j < klength; j++) {
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[j] = cc.getKey(slicedStrs[j].toString());
        }
        return key;
    }

    public void breakVigenere() {
        // WRITE YOUR CODE HERE
        /* String encrypted = new FileResource().asString();
        int[] key = this.tryKeyLength(encrypted, 4, 'e');
        System.out.println("Keys are: " + Arrays.toString(key));
        VigenereCipher cipher = new VigenereCipher(key);
        System.out.println("The decrypted message is: \n" +
        cipher.decrypt(encrypted)); */

        /* System.out.println("Please select a dictionary for language");
        HashSet<String> dict = this.readDictionary(new FileResource());

        System.out.println("Please select a file to break");
        this.breakForLanguage(new FileResource().asString(), dict); */

        HashMap<String, HashSet<String>> mapLanguageDict = new HashMap<String, HashSet<String>>();
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        String folder = "./dictionaries";
        for(String language: languages){
            mapLanguageDict.put(language, this.readDictionary(new FileResource(folder + "/" + language)));
        }

        System.out.println("Please select a file to break");
        this.breakForAllLangs(new FileResource().asString(), mapLanguageDict);
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dict = new HashSet<String>();
        for (String word : fr.words()) {
            dict.add(word.toLowerCase());
        }
        return dict;
    }

    public int countWords(String message, HashSet<String> dict) {
        int count = 0;
        String[] words = message.split("\\W+");
        for (String word : words) {
            if (dict.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Integer> breakForLanguage(String message, HashSet<String> dict) {
        return this.breakForLanguage(message, dict, 'e');
    }

    public ArrayList<Integer> breakForLanguage(String message, HashSet<String> dict, char mostCommonChar) {
        int maxWordsCount = 0;
        int maxIdx = 0;
        ArrayList<int[]> lsKey = new ArrayList<int[]>();
        int count = 0;
        for (int i = 1; i < 100; i++) {
            lsKey.add(this.tryKeyLength(message, i, mostCommonChar));
            VigenereCipher cipher = new VigenereCipher(lsKey.get(i - 1));
            count = this.countWords(cipher.decrypt(message), dict);
            if (count > maxWordsCount) {
                maxWordsCount = count;
                maxIdx = i - 1;
            }
        }

        /*
         * System.out.println("The key is: " + Arrays.toString(lsKey.get(maxIdx)));
         * // System.out.println("The key size is: " + lsKey.get(maxIdx).length);
         * String decrypted = new VigenereCipher(lsKey.get(maxIdx)).decrypt(message);
         * System.out.println("The decrypted message is: \n" + decrypted);
         */

        ArrayList<Integer> countKeys = new ArrayList<Integer>();
        countKeys.add(maxWordsCount);
        for (int key : lsKey.get(maxIdx)) {
            countKeys.add(key);
        }
        return countKeys;
    }

    public char mostCommonCharIn(HashSet<String> dict) {
        HashMap<Character, Integer> mapCharCount = new HashMap<Character, Integer>();

        for (String word : dict) {
            for (char c : word.toCharArray()) {
                if (mapCharCount.containsKey(c)) {
                    mapCharCount.put(c, mapCharCount.get(c) + 1);
                } else {
                    mapCharCount.put(c, 1);
                }
            }
        }

        // find the maximum value in the map
        int maxCount = Collections.max(mapCharCount.values());
        for (Character c : mapCharCount.keySet()) {
            if (mapCharCount.get(c) == maxCount) {
                return c;
            }
        }
        // if('\u0000') will return false
        return '\u0000';
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        char mostCommonChar = '\u0000';
        String maxLanguage = "";
        HashMap<String, ArrayList<Integer>> mapLanguageKeys = new HashMap<String, ArrayList<Integer>>();
        for (String language : languages.keySet()) {
            System.out.println("Breaking decrypted message in language: " + language);
            mostCommonChar = this.mostCommonCharIn(languages.get(language));
            if ('\u0000' != mostCommonChar) {
                mapLanguageKeys.put(language, this.breakForLanguage(encrypted, languages.get(language), mostCommonChar));
                if (maxLanguage.isEmpty() || mapLanguageKeys.get(language).get(0) > mapLanguageKeys.get(maxLanguage).get(0)) {
                    maxLanguage = language;
                }
            }
        }

        ArrayList<Integer> countKeys = mapLanguageKeys.get(maxLanguage); 
        int wordsCount = countKeys.get(0);
        int[] keys = new int[countKeys.size()-1];
        for(int i = 1; i < countKeys.size(); i++){
            keys[i-1] = countKeys.get(i);
        }
        System.out.println("---------------------------------------");
        System.out.println("The encrypted meassage is written in : " + maxLanguage);
        System.out.println("Coresponding key is: " + Arrays.toString(keys));
        System.out.println("Valid words count is: " + wordsCount);
        String decrypted = new VigenereCipher(keys).decrypt(encrypted);
        // System.out.println("The decrypted message is: \n" + decrypted);
        return decrypted;
    }
}
