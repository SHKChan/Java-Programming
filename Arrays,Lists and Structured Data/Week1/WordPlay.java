import java.util.ArrayList;
import java.util.List;

public class WordPlay {
    private ArrayList<Character> vowels = new ArrayList<>(List.of('a', 'e', 'i', 'o', 'u'));

    public void test(){
        System.out.println(this.replaceVowels("Hello World", '*'));
        System.out.println(this.emphasize("Mary Bella Abracadabra", 'a'));
    }

    private boolean isVowel(char c){
        c = Character.toLowerCase(c);
        if(this.vowels.contains(c)){
            return true;
        }
        return false;
    }

    private String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        phrase = phrase.toLowerCase();
        for(int i = 0; i < sb.length(); i++){
            if(this.isVowel(sb.charAt(i))){
                sb.setCharAt(i, ch);
            }

        }
        return sb.toString();
    }

    private String emphasize (String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        phrase = phrase.toLowerCase();
        ch = Character.toLowerCase(ch);
        for(int i = 0; i < phrase.length(); i++){
            if(phrase.charAt(i) != ch){
                continue;
            }
            if(i % 2 == 0){
                sb.setCharAt(i, '*');
            }
            else{
                sb.setCharAt(i, '+');
            }

        }
        return sb.toString();
    }
}